package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.Saldo;
import br.com.p9k.p9k.domain.service.SaldoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/saldo")
public class SaldoController {

    private final SaldoService service;

    public SaldoController(SaldoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody Saldo objeto) {
        service.salvar(objeto);
        return new ResponseEntity<>("Saldo criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable int id, @Valid @RequestBody Saldo objeto) {
        Optional<Saldo> optional = service.findById(id);

        if (optional.isPresent()) {
            objeto.setId(id);
            service.salvar(objeto);
            return new ResponseEntity<>("Saldo atualizada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Saldo não encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Saldo>> listar() {
        List<Saldo> objeto = service.buscarTodos();
        return new ResponseEntity<>(objeto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable int id) {
        Optional<Saldo> objeto = service.findById(id);
        if (objeto.isPresent()) {
            return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Saldo não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id) {
        Optional<Saldo> objeto = service.findById(id);
        if (objeto.isPresent()) {
            service.remover(objeto.get());
            return new ResponseEntity<>("Saldo deletada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Saldo não encontrada", HttpStatus.NOT_FOUND);
        }
    }
}

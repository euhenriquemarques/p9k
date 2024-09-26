package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.service.BancoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/banco")
public class BancoController {

    private final BancoService service;

    public BancoController(BancoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody Banco objeto) {
        service.salvar(objeto);
        return new ResponseEntity<>("Banco criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable int id, @Valid @RequestBody Banco objeto) {
        Optional<Banco> optional = service.findById(id);

        if (optional.isPresent()) {
            objeto.setId(id);
            service.salvar(objeto);
            return new ResponseEntity<>("Banco atualizada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Banco não encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Banco>> listar() {
        List<Banco> objeto = service.buscarTodos();
        return new ResponseEntity<>(objeto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable int id) {
        Optional<Banco> objeto = service.findById(id);
        if (objeto.isPresent()) {
            return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Banco não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id) {
        Optional<Banco> objeto = service.findById(id);
        if (objeto.isPresent()) {
            service.remover(objeto.get());
            return new ResponseEntity<>("Banco deletada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Banco não encontrada", HttpStatus.NOT_FOUND);
        }
    }
}

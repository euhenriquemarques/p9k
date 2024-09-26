package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.ExtratoDespesa;
import br.com.p9k.p9k.domain.service.ExtratoDespesaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/extratoDespesa")
public class ExtratoDespesaController {

    private final ExtratoDespesaService service;

    public ExtratoDespesaController(ExtratoDespesaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody ExtratoDespesa objeto) {
        service.salvar(objeto);
        return new ResponseEntity<>("ExtratoDespesa criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable int id, @Valid @RequestBody ExtratoDespesa objeto) {
        Optional<ExtratoDespesa> optional = service.findById(id);

        if (optional.isPresent()) {
            objeto.setId(id);
            service.salvar(objeto);
            return new ResponseEntity<>("ExtratoDespesa atualizada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoDespesa não encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ExtratoDespesa>> listar() {
        List<ExtratoDespesa> objeto = service.buscarTodos();
        return new ResponseEntity<>(objeto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable int id) {
        Optional<ExtratoDespesa> objeto = service.findById(id);
        if (objeto.isPresent()) {
            return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoDespesa não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id) {
        Optional<ExtratoDespesa> objeto = service.findById(id);
        if (objeto.isPresent()) {
            service.remover(objeto.get());
            return new ResponseEntity<>("ExtratoDespesa deletada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoDespesa não encontrada", HttpStatus.NOT_FOUND);
        }
    }
}

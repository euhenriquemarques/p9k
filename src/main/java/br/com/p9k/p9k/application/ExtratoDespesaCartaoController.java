package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.ExtratoDespesaCartao;
import br.com.p9k.p9k.domain.service.ExtratoDespesaCartaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/extratoDespesaCartao")
public class ExtratoDespesaCartaoController {

    private final ExtratoDespesaCartaoService service;

    public ExtratoDespesaCartaoController(ExtratoDespesaCartaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody ExtratoDespesaCartao objeto) {
        service.salvar(objeto);
        return new ResponseEntity<>("ExtratoDespesaCartao criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable int id, @Valid @RequestBody ExtratoDespesaCartao objeto) {
        Optional<ExtratoDespesaCartao> optional = service.findById(id);

        if (optional.isPresent()) {
            objeto.setId(id);
            service.salvar(objeto);
            return new ResponseEntity<>("ExtratoDespesaCartao atualizada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoDespesaCartao não encontrada", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<List<ExtratoDespesaCartao>> listar() {
        List<ExtratoDespesaCartao> objeto = service.buscarTodos();
        return new ResponseEntity<>(objeto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable int id) {
        Optional<ExtratoDespesaCartao> objeto = service.findById(id);
        if (objeto.isPresent()) {
            return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoDespesaCartao não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id) {
        Optional<ExtratoDespesaCartao> objeto = service.findById(id);
        if (objeto.isPresent()) {
            service.remover(objeto.get());
            return new ResponseEntity<>("ExtratoDespesaCartao deletada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoDespesaCartao não encontrada", HttpStatus.NO_CONTENT);
        }
    }
}

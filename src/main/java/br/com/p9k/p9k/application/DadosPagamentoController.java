package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.DadosPagamento;
import br.com.p9k.p9k.domain.service.DadosPagamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dadosPagamento")
public class DadosPagamentoController {

    private final DadosPagamentoService service;

    public DadosPagamentoController(DadosPagamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody DadosPagamento objeto) {
        service.salvar(objeto);
        return new ResponseEntity<>("DadosPagamento criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable int id, @Valid @RequestBody DadosPagamento objeto) {
        Optional<DadosPagamento> optional = service.findById(id);

        if (optional.isPresent()) {
            objeto.setId(id);
            service.salvar(objeto);
            return new ResponseEntity<>("DadosPagamento atualizada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("DadosPagamento não encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<DadosPagamento>> listar() {
        List<DadosPagamento> objeto = service.buscarTodos();
        return new ResponseEntity<>(objeto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable int id) {
        Optional<DadosPagamento> objeto = service.findById(id);
        if (objeto.isPresent()) {
            return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("DadosPagamento não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id) {
        Optional<DadosPagamento> objeto = service.findById(id);
        if (objeto.isPresent()) {
            service.remover(objeto.get());
            return new ResponseEntity<>("DadosPagamento deletada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("DadosPagamento não encontrada", HttpStatus.NOT_FOUND);
        }
    }
}

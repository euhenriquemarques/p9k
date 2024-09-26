package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.ExtratoInvestimento;
import br.com.p9k.p9k.domain.service.ExtratoInvestimentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/extratoInvestimento")
public class ExtratoInvestimentoController {

    private final ExtratoInvestimentoService service;

    public ExtratoInvestimentoController(ExtratoInvestimentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody ExtratoInvestimento objeto) {
        service.salvar(objeto);
        return new ResponseEntity<>("ExtratoInvestimento criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable int id, @Valid @RequestBody ExtratoInvestimento objeto) {
        Optional<ExtratoInvestimento> optional = service.findById(id);

        if (optional.isPresent()) {
            objeto.setId(id);
            service.salvar(objeto);
            return new ResponseEntity<>("ExtratoInvestimento atualizada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoInvestimento não encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ExtratoInvestimento>> listar() {
        List<ExtratoInvestimento> objeto = service.buscarTodos();
        return new ResponseEntity<>(objeto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable int id) {
        Optional<ExtratoInvestimento> objeto = service.findById(id);
        if (objeto.isPresent()) {
            return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoInvestimento não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id) {
        Optional<ExtratoInvestimento> objeto = service.findById(id);
        if (objeto.isPresent()) {
            service.remover(objeto.get());
            return new ResponseEntity<>("ExtratoInvestimento deletada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoInvestimento não encontrada", HttpStatus.NOT_FOUND);
        }
    }
}

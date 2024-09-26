package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.ExtratoMeta;
import br.com.p9k.p9k.domain.service.ExtratoMetaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/extratoMeta")
public class ExtratoMetaController {

    private final ExtratoMetaService service;

    public ExtratoMetaController(ExtratoMetaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody ExtratoMeta objeto) {
        service.salvar(objeto);
        return new ResponseEntity<>("ExtratoMeta criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable int id, @Valid @RequestBody ExtratoMeta objeto) {
        Optional<ExtratoMeta> optional = service.findById(id);

        if (optional.isPresent()) {
            objeto.setId(id);
            service.salvar(objeto);
            return new ResponseEntity<>("ExtratoMeta atualizada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoMeta não encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ExtratoMeta>> listar() {
        List<ExtratoMeta> objeto = service.buscarTodos();
        return new ResponseEntity<>(objeto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable int id) {
        Optional<ExtratoMeta> objeto = service.findById(id);
        if (objeto.isPresent()) {
            return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoMeta não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id) {
        Optional<ExtratoMeta> objeto = service.findById(id);
        if (objeto.isPresent()) {
            service.remover(objeto.get());
            return new ResponseEntity<>("ExtratoMeta deletada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoMeta não encontrada", HttpStatus.NOT_FOUND);
        }
    }
}

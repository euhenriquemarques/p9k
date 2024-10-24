package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.ExtratoReceita;
import br.com.p9k.p9k.domain.service.ExtratoReceitaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/extratoReceita")
public class ExtratoReceitaController {

    private final ExtratoReceitaService service;

    public ExtratoReceitaController(ExtratoReceitaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody ExtratoReceita objeto) {
        service.salvar(objeto);
        return new ResponseEntity<>("ExtratoReceita criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable int id, @Valid @RequestBody ExtratoReceita objeto) {
        Optional<ExtratoReceita> optional = service.findById(id);

        if (optional.isPresent()) {
            objeto.setId(id);
            service.salvar(objeto);
            return new ResponseEntity<>("ExtratoReceita atualizada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoReceita não encontrada", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<List<ExtratoReceita>> listar() {
        List<ExtratoReceita> objeto = service.buscarTodos();
        return new ResponseEntity<>(objeto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable int id) {
        Optional<ExtratoReceita> objeto = service.findById(id);
        if (objeto.isPresent()) {
            return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoReceita não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id) {
        Optional<ExtratoReceita> objeto = service.findById(id);
        if (objeto.isPresent()) {
            service.remover(objeto.get());
            return new ResponseEntity<>("ExtratoReceita deletada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoReceita não encontrada", HttpStatus.NO_CONTENT);
        }
    }
}

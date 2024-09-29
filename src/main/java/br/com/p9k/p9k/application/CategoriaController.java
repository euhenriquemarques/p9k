package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.Categoria;
import br.com.p9k.p9k.domain.enums.TipoCategoria;
import br.com.p9k.p9k.domain.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody Categoria objeto) {
        service.salvar(objeto);
        return new ResponseEntity<>("Categoria criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable int id, @Valid @RequestBody Categoria objeto) {
        Optional<Categoria> optional = service.findById(id);

        if (optional.isPresent()) {
            objeto.setId(id);
            service.salvar(objeto);
            return new ResponseEntity<>("Categoria atualizada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Categoria não encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        List<Categoria> objeto = service.buscarTodos();
        return new ResponseEntity<>(objeto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable int id) {
        Optional<Categoria> objeto = service.findById(id);
        if (objeto.isPresent()) {
            return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Categoria não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tipoCategoria")
    public ResponseEntity<Object> buscarTipoCategoria() {

            return new ResponseEntity<>(TipoCategoria.values(), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id) {
        Optional<Categoria> objeto = service.findById(id);
        if (objeto.isPresent()) {
            service.remover(objeto.get());
            return new ResponseEntity<>("Categoria deletada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Categoria não encontrada", HttpStatus.NOT_FOUND);
        }
    }
}

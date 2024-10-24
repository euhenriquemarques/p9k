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

    @GetMapping("/todos")
    public ResponseEntity<Object> buscarCategoria(@RequestParam int idUsuario) {
        List<Categoria> objeto = service.buscarCategoriaPorUsuario(idUsuario);
        if (!objeto.isEmpty()) {
            return new ResponseEntity<>(objeto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Categoria não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/despesa")
    public ResponseEntity<Object> buscarPorId(@RequestParam int idUsuario) {
        List<Categoria> objeto = service.findById(idUsuario);
        if (!objeto.isEmpty()) {
            return new ResponseEntity<>(objeto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Categoria não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/receita")
    public ResponseEntity<Object> buscarReceitaPorIdUsuario(@RequestParam int idUsuario) {
        List<Categoria> objeto = service.findyInvestimentoEntrada(idUsuario);
        if (!objeto.isEmpty()) {
            return new ResponseEntity<>(objeto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Categoria não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/tipoCategoria")
    public ResponseEntity<Object> buscarTipoCategoria() {

            return new ResponseEntity<>(TipoCategoria.values(), HttpStatus.OK);

    }

}

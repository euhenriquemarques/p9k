package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.Receita;
import br.com.p9k.p9k.domain.service.ReceitaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/receita")
public class ReceitaController {

    private final ReceitaService service;

    public ReceitaController(ReceitaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody Receita objeto) {
        service.salvar(objeto);
        return new ResponseEntity<>("Receita criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable int id, @Valid @RequestBody Receita objeto) {
        Optional<Receita> optional = service.findById(id);

        if (optional.isPresent()) {
            objeto.setId(id);
            service.salvar(objeto);
            return new ResponseEntity<>("Receita atualizada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Receita não encontrada", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/vigente")
    public ResponseEntity<Object> buscarReceitasVigentes(@RequestParam int idUsuario) {
        List<Receita> listaReceitas = service.buscarReceitasVigentes(idUsuario);
        if (!listaReceitas.isEmpty()) {
            return new ResponseEntity<>(listaReceitas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Receita não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/geral")
    public ResponseEntity<Object> buscarReceitasGeralEFuturas(@RequestParam int idUsuario) {
        List<Receita> listaReceitas = service.buscarReceitasGeralEFuturas(idUsuario);
        if (!listaReceitas.isEmpty()) {
            return new ResponseEntity<>(listaReceitas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Receita não encontrado", HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/geralVigentes")
    public ResponseEntity<Object> buscarReceitasVigentesEFuturas(@RequestParam int idUsuario) {
        List<Receita> listaReceitas = service.buscarReceitasVigentesEFuturas(idUsuario);
        if (!listaReceitas.isEmpty()) {
            return new ResponseEntity<>(listaReceitas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Receita não encontrado", HttpStatus.NO_CONTENT);
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable int id) {
        Optional<Receita> objeto = service.findById(id);
        if (objeto.isPresent()) {
            return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Receita não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id) {
        Optional<Receita> objeto = service.findById(id);
        if (objeto.isPresent()) {
            service.remover(objeto.get());
            return new ResponseEntity<>("Receita deletada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Receita não encontrada", HttpStatus.NO_CONTENT);
        }
    }
}

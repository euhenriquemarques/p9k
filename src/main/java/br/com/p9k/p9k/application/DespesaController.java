package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.Despesa;
import br.com.p9k.p9k.domain.service.DespesaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/despesa")
public class DespesaController {

    private final DespesaService service;

    public DespesaController(DespesaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody Despesa objeto) {
        service.salvar(objeto);
        return new ResponseEntity<>("Despesa criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable int id, @Valid @RequestBody Despesa objeto) {
        Optional<Despesa> optional = service.findById(id);

        if (optional.isPresent()) {
            objeto.setId(id);
            service.salvar(objeto);
            return new ResponseEntity<>("Despesa atualizada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Despesa não encontrada", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/vigente")
    public ResponseEntity<Object> buscarDespesasVigentes(@RequestParam int idUsuario) {
        List<Despesa> listaDespesas = service.buscarDespesasVigentes(idUsuario);
        if (!listaDespesas.isEmpty()) {
            return new ResponseEntity<>(listaDespesas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Despesa não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/geral")
    public ResponseEntity<Object> buscarDespesasGeralEFuturas(@RequestParam int idUsuario) {
        List<Despesa> listaDespesas = service.buscarDespesasGeralEFuturas(idUsuario);
        if (!listaDespesas.isEmpty()) {
            return new ResponseEntity<>(listaDespesas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Despesa não encontrado", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/geralVigentes")
    public ResponseEntity<Object> buscarDespesasVigentesEFuturas(@RequestParam int idUsuario) {
        List<Despesa> listaDespesas = service.buscarDespesasVigentesEFuturas(idUsuario);
        if (!listaDespesas.isEmpty()) {
            return new ResponseEntity<>(listaDespesas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Despesa não encontrado", HttpStatus.NOT_FOUND);
        }
    }



    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable int id) {
        Optional<Despesa> objeto = service.findById(id);
        if (objeto.isPresent()) {
            return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Despesa não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id) {
        Optional<Despesa> objeto = service.findById(id);
        if (objeto.isPresent()) {
            service.remover(objeto.get());
            return new ResponseEntity<>("Despesa deletada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Despesa não encontrada", HttpStatus.NOT_FOUND);
        }
    }
}

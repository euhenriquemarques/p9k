package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.Despesa;
import br.com.p9k.p9k.domain.service.BancoService;
import br.com.p9k.p9k.infraestructure.config.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/banco")
@Validated
public class BancoController {

    private final BancoService service;
    private final JwtUtil jwtUtil;

    public BancoController(BancoService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody Banco objeto) {
        service.salvar(objeto);
        return new ResponseEntity<>("Banco criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@Valid @PathVariable int id, @Valid @RequestBody Banco objeto) {
        Optional<Banco> optional = service.findById(id);

        if (optional.isPresent()) {
            objeto.setId(id);
            service.salvar(objeto);
            return new ResponseEntity<>("Banco atualizada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Banco não encontrada", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<List<Banco>> listar() {
        List<Banco> objeto = service.buscarTodos();
        return new ResponseEntity<>(objeto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId( @Valid @RequestParam int id) {


        Optional<Banco> objeto = service.findById(id);
        if (objeto.isPresent()) {
            return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Banco não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar( @Valid @RequestParam int id) {

        Optional<Banco> objeto = service.findById(id);
        if (objeto.isPresent()) {
            service.remover(objeto.get());
            return new ResponseEntity<>("Banco deletada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Banco não encontrada", HttpStatus.NO_CONTENT);
        }
    }
}

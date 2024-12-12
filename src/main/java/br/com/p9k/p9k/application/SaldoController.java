package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.Saldo;
import br.com.p9k.p9k.domain.service.SaldoService;
import br.com.p9k.p9k.domain.service.UserService;
import br.com.p9k.p9k.infraestructure.config.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/saldo")
@Validated
public class SaldoController {

    private final SaldoService service;
    private final JwtUtil jwtUtil;
    private final UserService usuarioService;

    public SaldoController(SaldoService service, JwtUtil jwtUtil, UserService usuarioService) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> salvar( @Valid @RequestBody Saldo objeto) {
        service.salvar(objeto);
        return new ResponseEntity<>("Saldo criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@Valid @PathVariable int id, @Valid @RequestBody Saldo objeto) {
        Optional<Saldo> optional = service.findByContaId(id);

        if (optional.isPresent()) {
            objeto.setId(id);
            service.salvar(objeto);
            return new ResponseEntity<>("Saldo atualizada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Saldo não encontrada", HttpStatus.NO_CONTENT);
        }
    }


    @GetMapping()
    public ResponseEntity<Object> buscarPorId(@Valid @RequestParam int contaId) {
        Optional<Saldo> objeto = service.findByContaId(contaId);
        if (objeto.isPresent()) {
            return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Saldo não encontrado", HttpStatus.NO_CONTENT);
        }
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deletar(@PathVariable int id) {
//        Optional<Saldo> objeto = service.findById(id);
//        if (objeto.isPresent()) {
//            service.remover(objeto.get());
//            return new ResponseEntity<>("Saldo deletada com sucesso!", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Saldo não encontrada", HttpStatus.NO_CONTENT);
//        }
//    }
}

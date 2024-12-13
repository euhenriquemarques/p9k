package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.User;
import br.com.p9k.p9k.domain.service.UserService;
import br.com.p9k.p9k.domain.service.UsuarioService;
import br.com.p9k.p9k.infraestructure.config.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService service;
    private final JwtUtil jwtUtil;
    private final UserService usuarioService;

    public UsuarioController(UsuarioService service, JwtUtil jwtUtil, UserService usuarioService) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody User objeto) {
        service.salvar(objeto);
        return new ResponseEntity<>("Usuario criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@Valid @PathVariable int id, @Valid @RequestBody User objeto) {
        Optional<User> optional = service.findById(id);

        if (optional.isPresent()) {
            objeto.setId( id);
            service.salvar(objeto);
            return new ResponseEntity<>("Usuario atualizada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario não encontrada", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> listar() {
        List<User> objeto = service.buscarTodos();
        return new ResponseEntity<>(objeto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@Valid @PathVariable int id) {
        Optional<User> objeto = service.findById(id);
        if (objeto.isPresent()) {
            return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@Valid @PathVariable int id) {
        Optional<User> objeto = service.findById(id);
        if (objeto.isPresent()) {
            service.remover(objeto.get());
            return new ResponseEntity<>("Usuario deletada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuario não encontrada", HttpStatus.NO_CONTENT);
        }
    }
}

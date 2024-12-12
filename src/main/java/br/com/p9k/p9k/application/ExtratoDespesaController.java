package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.ExtratoDespesa;
import br.com.p9k.p9k.domain.service.ExtratoDespesaService;
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
@RequestMapping("/extratoDespesa")
@Validated
public class ExtratoDespesaController {

    private final ExtratoDespesaService service;
    private final JwtUtil jwtUtil;
    private final UserService usuarioService;

    public ExtratoDespesaController(ExtratoDespesaService service, JwtUtil jwtUtil, UserService usuarioService) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody ExtratoDespesa objeto, @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        objeto.setUsuario(usuarioService.getUserIdByUsername(username));
        try {
            service.salvar(objeto);
            return new ResponseEntity<>("ExtratoDespesa criado com sucesso!", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@Valid @PathVariable int id, @Valid @RequestBody ExtratoDespesa objeto) {
        Optional<ExtratoDespesa> optional = service.findById(id);

        if (optional.isPresent()) {
            objeto.setId(id);
            service.salvar(objeto);
            return new ResponseEntity<>("ExtratoDespesa atualizada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoDespesa não encontrada", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<List<ExtratoDespesa>> listar() {
        List<ExtratoDespesa> objeto = service.buscarTodos();
        return new ResponseEntity<>(objeto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@Valid @PathVariable int id) {
        Optional<ExtratoDespesa> objeto = service.findById(id);
        if (objeto.isPresent()) {
            return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoDespesa não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@Valid @PathVariable int id) {
        Optional<ExtratoDespesa> objeto = service.findById(id);
        if (objeto.isPresent()) {
            service.remover(objeto.get());
            return new ResponseEntity<>("ExtratoDespesa deletada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoDespesa não encontrada", HttpStatus.NO_CONTENT);
        }
    }
}

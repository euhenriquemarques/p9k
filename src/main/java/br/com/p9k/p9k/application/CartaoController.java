package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.Cartao;
import br.com.p9k.p9k.domain.service.CartaoService;
import br.com.p9k.p9k.domain.service.UserService;
import br.com.p9k.p9k.infraestructure.config.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartao")
@Validated
public class CartaoController {

    private final CartaoService service;
    private final JwtUtil jwtUtil;
    private final UserService usuarioService;

    public CartaoController(CartaoService service, JwtUtil jwtUtil, UserService usuarioService) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody Cartao objeto, @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        objeto.setUsuario(usuarioService.getUserIdByUsername(username));

        service.salvar(objeto);
        return new ResponseEntity<>("Cartao criado com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<Object> buscarCartao(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        int idUsuario = jwtUtil.extractUserId(token);

        List<Cartao> objeto = service.buscarCartaoPorUsuario(idUsuario);
        if (!objeto.isEmpty()) {
            return new ResponseEntity<>(objeto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cartao não encontrado", HttpStatus.NO_CONTENT);
        }
    }

}

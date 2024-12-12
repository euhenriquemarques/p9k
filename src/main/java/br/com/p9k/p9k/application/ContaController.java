package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.Conta;
import br.com.p9k.p9k.domain.service.ContaService;
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
@RequestMapping("/conta")
@Validated
public class ContaController {

    private final ContaService service;
    private final JwtUtil jwtUtil;
    private final UserService usuarioService;

    public ContaController(ContaService service, JwtUtil jwtUtil, UserService usuarioService) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody Conta objeto,  @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        objeto.setUsuario(usuarioService.getUserIdByUsername(username));

        service.salvar(objeto);
        return new ResponseEntity<>("Conta criado com sucesso!", HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<Object> buscarPorId(@RequestHeader("Authorization") String authorizationHeader) {
         if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        int idUsuario = jwtUtil.extractUserId(token);

        List<Conta> lista = service.findById(idUsuario);
        if (!lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Conta não encontrado", HttpStatus.NO_CONTENT);
        }
    }


}

package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.dto.HomeDTO;
import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.Conta;
import br.com.p9k.p9k.domain.service.BancoService;
import br.com.p9k.p9k.domain.service.HomeService;
import br.com.p9k.p9k.domain.service.UserService;
import br.com.p9k.p9k.infraestructure.config.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/homepage")
@Validated
public class HomeController {

    private final HomeService service;
    private final JwtUtil jwtUtil;
    private final UserService usuarioService;

    public HomeController(HomeService service, JwtUtil jwtUtil, UserService usuarioService) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    public ResponseEntity<Object> buscarPorId(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        int idUsuario = jwtUtil.extractUserId(token);

        HomeDTO lista = service.buscarHome(idUsuario);
        if (lista != null) {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Conta não encontrado", HttpStatus.NO_CONTENT);
        }
    }
}

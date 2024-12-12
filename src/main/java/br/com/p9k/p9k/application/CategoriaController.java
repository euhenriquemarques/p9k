package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.Categoria;
import br.com.p9k.p9k.domain.enums.TipoCategoria;
import br.com.p9k.p9k.domain.service.CategoriaService;
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
@RequestMapping("/categoria")
@Validated
public class CategoriaController {

    private final CategoriaService service;
    private final JwtUtil jwtUtil;
    private final UserService usuarioService;

    public CategoriaController(CategoriaService service, JwtUtil jwtUtil, UserService usuarioService) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody Categoria objeto, @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        objeto.setUsuario(usuarioService.getUserIdByUsername(username));

        service.salvar(objeto);
        return new ResponseEntity<>("Categoria criado com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<Object> buscarCategoria(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        int idUsuario = jwtUtil.extractUserId(token);

        List<Categoria> objeto = service.buscarCategoriaPorUsuario(idUsuario);
        if (!objeto.isEmpty()) {
            return new ResponseEntity<>(objeto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Categoria não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/despesa")
    public ResponseEntity<Object> buscarPorId(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        int idUsuario = jwtUtil.extractUserId(token);

        List<Categoria> objeto = service.findById(idUsuario);
        if (!objeto.isEmpty()) {
            return new ResponseEntity<>(objeto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Categoria não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/receita")
    public ResponseEntity<Object> buscarReceitaPorIdUsuario(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        int idUsuario = jwtUtil.extractUserId(token);

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

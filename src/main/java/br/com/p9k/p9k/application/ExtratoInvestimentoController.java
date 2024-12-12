package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.ExtratoInvestimento;
import br.com.p9k.p9k.domain.service.ExtratoInvestimentoService;
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
@RequestMapping("/extratoInvestimento")
@Validated
public class ExtratoInvestimentoController {

    private final ExtratoInvestimentoService service;
    private final JwtUtil jwtUtil;
    private final UserService usuarioService;

    public ExtratoInvestimentoController(ExtratoInvestimentoService service, JwtUtil jwtUtil, UserService usuarioService) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody ExtratoInvestimento objeto, @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        objeto.setUsuario(usuarioService.getUserIdByUsername(username));
        service.salvar(objeto);
        return new ResponseEntity<>("ExtratoInvestimento criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@Valid @PathVariable int id, @Valid @RequestBody ExtratoInvestimento objeto) {
        Optional<ExtratoInvestimento> optional = service.findById(id);

        if (optional.isPresent()) {
            objeto.setId(id);
            service.salvar(objeto);
            return new ResponseEntity<>("ExtratoInvestimento atualizada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoInvestimento não encontrada", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<List<ExtratoInvestimento>> listar() {
        List<ExtratoInvestimento> objeto = service.buscarTodos();
        return new ResponseEntity<>(objeto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@Valid @PathVariable int id) {
        Optional<ExtratoInvestimento> objeto = service.findById(id);
        if (objeto.isPresent()) {
            return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoInvestimento não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@Valid @PathVariable int id) {
        Optional<ExtratoInvestimento> objeto = service.findById(id);
        if (objeto.isPresent()) {
            service.remover(objeto.get());
            return new ResponseEntity<>("ExtratoInvestimento deletada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("ExtratoInvestimento não encontrada", HttpStatus.NO_CONTENT);
        }
    }
}

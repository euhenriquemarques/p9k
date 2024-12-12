package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.DadosPagamento;
import br.com.p9k.p9k.domain.entidade.Despesa;
import br.com.p9k.p9k.domain.service.DadosPagamentoService;
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
@RequestMapping("/dadosPagamento")
@Validated
public class DadosPagamentoController {

    private final DadosPagamentoService service;
    private final JwtUtil jwtUtil;
    private final UserService usuarioService;

    public DadosPagamentoController(DadosPagamentoService service, JwtUtil jwtUtil, UserService usuarioService) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody DadosPagamento objeto) {
        service.salvar(objeto);
        return new ResponseEntity<>("DadosPagamento criado com sucesso!", HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<Object> buscarPorId(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        int idUsuario = jwtUtil.extractUserId(token);

        List<DadosPagamento> lista = service.findByIdUsuario(idUsuario);
        if (!lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("DadosPagamento não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/vigente")
    public ResponseEntity<Object> buscarDespesasVigentes(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        int idUsuario = jwtUtil.extractUserId(token);

        List<DadosPagamento> lista = service.findByVigentes(idUsuario);
        if (!lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("DadosPagamento não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/cancelar")
    public ResponseEntity<Object> cancelarDadosPagamento(@Valid @RequestParam int id) {
        Optional<DadosPagamento> dadosPagamento = service.findById(id);
        if (dadosPagamento.isPresent()) {
           service.remover(dadosPagamento.get());
            return new ResponseEntity<>( HttpStatus.OK);
        } else {
            return new ResponseEntity<>("DadosPagamento não encontrado", HttpStatus.NO_CONTENT);
        }
    }


}

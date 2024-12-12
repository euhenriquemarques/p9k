package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.DespesaCartao;
import br.com.p9k.p9k.domain.service.DespesaCartaoService;
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
@RequestMapping("/despesaCartao")
@Validated
public class DespesaCartaoController {

    private final DespesaCartaoService service;
    private final JwtUtil jwtUtil;
    private final UserService usuarioService;

    public DespesaCartaoController(DespesaCartaoService service, JwtUtil jwtUtil, UserService usuarioService) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody DespesaCartao objeto, @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        objeto.setUsuario(usuarioService.getUserIdByUsername(username));
        service.salvar(objeto);
        return new ResponseEntity<>("Despesa criado com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping("/vigente")
    public ResponseEntity<Object> buscarDespesasVigentes(@RequestParam int idCartao,@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        int idUsuario = jwtUtil.extractUserId(token);

        List<DespesaCartao> listaDespesas = service.buscarDespesasCartaoAtivoMes(idUsuario, idCartao);
        if (!listaDespesas.isEmpty()) {
            return new ResponseEntity<>(listaDespesas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Despesa não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/todas")
    public ResponseEntity<Object> findDespesasByUsuario(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        int idUsuario = jwtUtil.extractUserId(token);

        List<DespesaCartao> listaDespesas = service.findDespesasByUsuario(idUsuario);
        if (!listaDespesas.isEmpty()) {
            return new ResponseEntity<>(listaDespesas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Despesa não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@Valid @PathVariable int id) {
        Optional<DespesaCartao> objeto = service.findById(id);
        if (objeto.isPresent()) {
            service.remover(objeto.get());
            return new ResponseEntity<>("Despesa deletada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Despesa não encontrada", HttpStatus.NO_CONTENT);
        }
    }
}

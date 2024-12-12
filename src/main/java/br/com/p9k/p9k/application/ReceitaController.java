package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.Receita;
import br.com.p9k.p9k.domain.service.ReceitaService;
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
@RequestMapping("/receita")
@Validated
public class ReceitaController {

    private final ReceitaService service;
    private final JwtUtil jwtUtil;
    private final UserService usuarioService;

    public ReceitaController(ReceitaService service, JwtUtil jwtUtil, UserService usuarioService) {
        this.service = service;
        this.jwtUtil = jwtUtil;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody Receita objeto, @RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        objeto.setUsuario(usuarioService.getUserIdByUsername(username));
        service.salvar(objeto);
        return new ResponseEntity<>("Receita criado com sucesso!", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@Valid @PathVariable int id, @Valid @RequestBody Receita objeto) {
        Optional<Receita> optional = service.findById(id);

        if (optional.isPresent()) {
            objeto.setId(id);
            service.salvar(objeto);
            return new ResponseEntity<>("Receita atualizada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Receita não encontrada", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/vigente")
    public ResponseEntity<Object> buscarReceitasVigentes(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        int idUsuario = jwtUtil.extractUserId(token);

        List<Receita> listaReceitas = service.buscarReceitasVigentes(idUsuario);
        if (!listaReceitas.isEmpty()) {
            return new ResponseEntity<>(listaReceitas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Receita não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/geral")
    public ResponseEntity<Object> buscarReceitasGeralEFuturas(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        int idUsuario = jwtUtil.extractUserId(token);

        List<Receita> listaReceitas = service.buscarReceitasGeralEFuturas(idUsuario);
        if (!listaReceitas.isEmpty()) {
            return new ResponseEntity<>(listaReceitas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Receita não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/geralVigentes")
    public ResponseEntity<Object> buscarReceitasVigentesEFuturas(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        int idUsuario = jwtUtil.extractUserId(token);

        List<Receita> listaReceitas = service.buscarReceitasVigentesEFuturas(idUsuario);
        if (!listaReceitas.isEmpty()) {
            return new ResponseEntity<>(listaReceitas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Receita não encontrado", HttpStatus.NO_CONTENT);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@Valid @PathVariable int id) {
        Optional<Receita> objeto = service.findById(id);
        if (objeto.isPresent()) {
            return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Receita não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@Valid @PathVariable int id) {
        Optional<Receita> objeto = service.findById(id);
        if (objeto.isPresent()) {
            service.remover(objeto.get());
            return new ResponseEntity<>("Receita deletada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Receita não encontrada", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/todas")
    public ResponseEntity<Object> findDespesasByUsuario(@RequestHeader("Authorization") String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>("Token não fornecido ou inválido", HttpStatus.UNAUTHORIZED);
        }

        String token = authorizationHeader.substring(7);
        int idUsuario = jwtUtil.extractUserId(token);

        List<Receita> listaDespesas = service.findDespesasByUsuario(idUsuario);
        if (!listaDespesas.isEmpty()) {
            return new ResponseEntity<>(listaDespesas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Receita não encontrado", HttpStatus.NO_CONTENT);
        }
    }
}

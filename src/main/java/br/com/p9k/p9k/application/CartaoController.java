package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.Cartao;
import br.com.p9k.p9k.domain.service.CartaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartao")
public class CartaoController {

    private final CartaoService service;

    public CartaoController(CartaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody Cartao objeto) {
        service.salvar(objeto);
        return new ResponseEntity<>("Cartao criado com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping("/todos")
    public ResponseEntity<Object> buscarCartao(@RequestParam int idUsuario) {
        List<Cartao> objeto = service.buscarCartaoPorUsuario(idUsuario);
        if (!objeto.isEmpty()) {
            return new ResponseEntity<>(objeto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cartao não encontrado", HttpStatus.NO_CONTENT);
        }
    }

}

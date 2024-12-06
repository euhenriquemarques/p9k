package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.DadosPagamento;
import br.com.p9k.p9k.domain.entidade.Despesa;
import br.com.p9k.p9k.domain.service.DadosPagamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dadosPagamento")
public class DadosPagamentoController {

    private final DadosPagamentoService service;

    public DadosPagamentoController(DadosPagamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody DadosPagamento objeto) {
        service.salvar(objeto);
        return new ResponseEntity<>("DadosPagamento criado com sucesso!", HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<Object> buscarPorId(@RequestParam int idUsuario) {
        List<DadosPagamento> lista = service.findByIdUsuario(idUsuario);
        if (!lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("DadosPagamento não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/vigente")
    public ResponseEntity<Object> buscarDespesasVigentes(@RequestParam int idUsuario) {
        List<DadosPagamento> lista = service.findByVigentes(idUsuario);
        if (!lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("DadosPagamento não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/cancelar")
    public ResponseEntity<Object> cancelarDadosPagamento(@RequestParam int id) {
        Optional<DadosPagamento> dadosPagamento = service.findById(id);
        if (dadosPagamento.isPresent()) {
           service.remover(dadosPagamento.get());
            return new ResponseEntity<>( HttpStatus.OK);
        } else {
            return new ResponseEntity<>("DadosPagamento não encontrado", HttpStatus.NO_CONTENT);
        }
    }


}

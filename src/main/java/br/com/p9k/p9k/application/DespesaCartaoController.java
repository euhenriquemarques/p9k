package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.DespesaCartao;
import br.com.p9k.p9k.domain.service.DespesaCartaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/despesaCartao")
public class DespesaCartaoController {

    private final DespesaCartaoService service;

    public DespesaCartaoController(DespesaCartaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody DespesaCartao objeto) {
        service.salvar(objeto);
        return new ResponseEntity<>("Despesa criado com sucesso!", HttpStatus.CREATED);
    }

    @GetMapping("/vigente")
    public ResponseEntity<Object> buscarDespesasVigentes(@RequestParam int idUsuario, @RequestParam int idCartao) {
        List<DespesaCartao> listaDespesas = service.buscarDespesasCartaoAtivoMes(idUsuario, idCartao);
        if (!listaDespesas.isEmpty()) {
            return new ResponseEntity<>(listaDespesas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Despesa não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/todas")
    public ResponseEntity<Object> findDespesasByUsuario(@RequestParam int idUsuario) {
        List<DespesaCartao> listaDespesas = service.findDespesasByUsuario(idUsuario);
        if (!listaDespesas.isEmpty()) {
            return new ResponseEntity<>(listaDespesas, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Despesa não encontrado", HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id) {
        Optional<DespesaCartao> objeto = service.findById(id);
        if (objeto.isPresent()) {
            service.remover(objeto.get());
            return new ResponseEntity<>("Despesa deletada com sucesso!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Despesa não encontrada", HttpStatus.NO_CONTENT);
        }
    }
}

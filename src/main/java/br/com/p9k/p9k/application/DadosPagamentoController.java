package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.DadosPagamento;
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
        List<DadosPagamento> lista = service.findById(idUsuario);
        if (!lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("DadosPagamento não encontrado", HttpStatus.NO_CONTENT);
        }
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deletar(@PathVariable int id) {
//        Optional<DadosPagamento> objeto = service.findById(id);
//        if (objeto.isPresent()) {
//            service.remover(objeto.get());
//            return new ResponseEntity<>("DadosPagamento deletada com sucesso!", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("DadosPagamento não encontrada", HttpStatus.NO_CONTENT);
//        }
//    }
}

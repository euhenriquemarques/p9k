package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.entidade.Conta;
import br.com.p9k.p9k.domain.service.ContaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private final ContaService service;

    public ContaController(ContaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> salvar(@Valid @RequestBody Conta objeto) {
        service.salvar(objeto);
        return new ResponseEntity<>("Conta criado com sucesso!", HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Object> atualizar(@PathVariable int id, @Valid @RequestBody Conta objeto) {
//        Optional<Conta> optional = service.findById(id);
//
//        if (optional.isPresent()) {
//            objeto.setId(id);
//            service.salvar(objeto);
//            return new ResponseEntity<>("Conta atualizada com sucesso!", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Conta não encontrada", HttpStatus.NOT_FOUND);
//        }
//    }



    @GetMapping()
    public ResponseEntity<Object> buscarPorId(@RequestParam int idUsuario) {
        List<Conta> lista = service.findById(idUsuario);
        if (!lista.isEmpty()) {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Conta não encontrado", HttpStatus.NOT_FOUND);
        }
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deletar(@PathVariable int id) {
//        Optional<Conta> objeto = service.findById(id);
//        if (objeto.isPresent()) {
//            service.remover(objeto.get());
//            return new ResponseEntity<>("Conta deletada com sucesso!", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Conta não encontrada", HttpStatus.NOT_FOUND);
//        }
//    }
}

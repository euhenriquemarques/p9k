//package br.com.p9k.p9k.application;
//
//import br.com.p9k.p9k.domain.entidade.Patrimonio;
//import br.com.p9k.p9k.domain.service.PatrimonioService;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/Patrimonio")
//public class PatrimonioController {
//
//    private final PatrimonioService service;
//
//    public PatrimonioController(PatrimonioService service) {
//        this.service = service;
//    }
//
//    @PostMapping
//    public ResponseEntity<String> salvar(@Valid @RequestBody Patrimonio objeto) {
//        service.salvar(objeto);
//        return new ResponseEntity<>("Patrimonio criado com sucesso!", HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Object> atualizar(@PathVariable int id, @Valid @RequestBody Patrimonio objeto) {
//        Optional<Patrimonio> optional = service.findById(id);
//
//        if (optional.isPresent()) {
//            objeto.setId(id);
//            service.salvar(objeto);
//            return new ResponseEntity<>("Patrimonio atualizada com sucesso!", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Patrimonio não encontrada", HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Patrimonio>> listar() {
//        List<Patrimonio> objeto = service.buscarTodos();
//        return new ResponseEntity<>(objeto, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Object> buscarPorId(@PathVariable int id) {
//        Optional<Patrimonio> objeto = service.findById(id);
//        if (objeto.isPresent()) {
//            return new ResponseEntity<>(objeto.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Patrimonio não encontrado", HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deletar(@PathVariable int id) {
//        Optional<Patrimonio> objeto = service.findById(id);
//        if (objeto.isPresent()) {
//            service.remover(objeto.get());
//            return new ResponseEntity<>("Patrimonio deletada com sucesso!", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Patrimonio não encontrada", HttpStatus.NOT_FOUND);
//        }
//    }
//}

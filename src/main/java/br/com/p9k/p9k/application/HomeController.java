package br.com.p9k.p9k.application;

import br.com.p9k.p9k.domain.dto.HomeDTO;
import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.Conta;
import br.com.p9k.p9k.domain.service.BancoService;
import br.com.p9k.p9k.domain.service.HomeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/homepage")
public class HomeController {

    private final HomeService service;

    public HomeController(HomeService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<Object> buscarPorId(@RequestParam int idUsuario) {
        HomeDTO lista = service.buscarHome(idUsuario);
        if (lista != null) {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Conta não encontrado", HttpStatus.NOT_FOUND);
        }
    }
}

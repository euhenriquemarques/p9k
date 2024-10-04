package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.dto.HomeDTO;
import br.com.p9k.p9k.domain.dto.SaldoDTO;
import br.com.p9k.p9k.domain.entidade.Conta;
import br.com.p9k.p9k.domain.entidade.Saldo;
import br.com.p9k.p9k.domain.repository.SaldoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeService {

    private final SaldoRepository saldoRepository;

    public HomeService(SaldoRepository saldoRepository) {
        this.saldoRepository = saldoRepository;
    }

    public HomeDTO buscarHome(int idUsuario) {
        List<Saldo> listaSaldo = saldoRepository.buscarPorIdUsuario(idUsuario);

        HomeDTO homeDTO= HomeDTO.builder().listaSaldo(listaSaldo.stream().map(item -> {
            return SaldoDTO.builder().saldo(item.getSaldo())
                    .banco(item.getConta().getBanco().getDescricao())
                    .numero(item.getConta().getNumero().replaceFirst("^.{2}", "XX"))
                    .build();
        }).collect(Collectors.toList())).build();

        return  homeDTO;
    }
}

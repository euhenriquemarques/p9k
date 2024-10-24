package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.Utils;
import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.DadosPagamento;
import br.com.p9k.p9k.domain.repository.DadosPagamentoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
public class DadosPagamentoService {

    private final DadosPagamentoRepository repository;

    public DadosPagamentoService(DadosPagamentoRepository repository) {
        this.repository = repository;
    }

    public void salvar(DadosPagamento DadosPagamento) {
        repository.salvar(DadosPagamento);
    }

    public void remover(DadosPagamento DadosPagamento) {
        repository.remover(DadosPagamento);
    }

    public void alterar(DadosPagamento DadosPagamento) {
        repository.alterar(DadosPagamento);
    }

    public List<DadosPagamento> buscarTodos() {
        return repository.buscarTodos();
    }

    public List<DadosPagamento> findById(int idUsuario) {
        return repository.findById(idUsuario, Utils.buscarDataInicioMes());
    }
}

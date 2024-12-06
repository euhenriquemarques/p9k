package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.Utils;
import br.com.p9k.p9k.domain.entidade.DadosPagamento;
import br.com.p9k.p9k.domain.entidade.Despesa;
import br.com.p9k.p9k.domain.repository.DadosPagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DadosPagamentoService {

    private final DadosPagamentoRepository repository;
    private final DespesaService despessaService;

    public DadosPagamentoService(DadosPagamentoRepository repository, DespesaService despessaService) {
        this.repository = repository;
        this.despessaService = despessaService;
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

    public Optional<DadosPagamento> findById(int id) {
        return repository.findById(id);
    }

    public List<DadosPagamento> findByIdUsuario(int idUsuario) {
        return repository.findByUsuarioId(idUsuario, Utils.buscarDataInicioMes());
    }

    public List<DadosPagamento> findByVigentes(int idUsuario) {
        List<Despesa> listadespesasVigentesEFuturas = despessaService.buscarDespesasVigentesEFuturas(idUsuario);
       return repository.findByDespesasVigentes(listadespesasVigentesEFuturas);
    }
}

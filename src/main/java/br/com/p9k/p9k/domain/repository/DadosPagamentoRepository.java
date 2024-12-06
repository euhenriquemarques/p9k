package br.com.p9k.p9k.domain.repository;


import br.com.p9k.p9k.domain.entidade.DadosPagamento;
import br.com.p9k.p9k.domain.entidade.Despesa;
import br.com.p9k.p9k.infraestructure.persisstence.DadosPagamentoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class DadosPagamentoRepository {

    @Autowired
    private DadosPagamentoRepositoryImpl repositoryImpl;

    public DadosPagamento salvar(DadosPagamento DadosPagamento) {
        return repositoryImpl.save(DadosPagamento);
    }

    public void remover(DadosPagamento DadosPagamento) {
        repositoryImpl.delete(DadosPagamento);
    }

    public DadosPagamento alterar(DadosPagamento DadosPagamento) {
        return repositoryImpl.save(DadosPagamento);
    }

    public List<DadosPagamento> buscarTodos() {
        return repositoryImpl.findAll();
    }

    public DadosPagamento buscarPorId(int idDadosPagamento) {
        Optional<DadosPagamento> optionalDadosPagamento = repositoryImpl.findById(idDadosPagamento);
        if (optionalDadosPagamento.isPresent()){
            return optionalDadosPagamento.get();
        }

        return null;
    }

    public List<DadosPagamento> findByUsuarioId(int idUsuario, LocalDateTime dataAtual) {
        return repositoryImpl.findByUsuarioId(idUsuario, dataAtual);
    }

    public List<DadosPagamento> findByDespesasVigentes(List<Despesa> listadespesasVigentesEFuturas) {
        return repositoryImpl.findByDespesasVigentes(listadespesasVigentesEFuturas);
    }

    public Optional<DadosPagamento> findById(int id) {
        return repositoryImpl.findById(id);
    }
}

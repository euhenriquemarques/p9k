package br.com.p9k.p9k.domain.repository;


import br.com.p9k.p9k.domain.dto.DespesaCartaoDTO;
import br.com.p9k.p9k.domain.entidade.DespesaCartao;
import br.com.p9k.p9k.domain.entidade.ExtratoDespesaCartao;
import br.com.p9k.p9k.infraestructure.persisstence.DespesaCartaoRepositoryImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class DespesaCartaoRepository {

    private final DespesaCartaoRepositoryImpl repositoryImpl;

    public DespesaCartaoRepository(DespesaCartaoRepositoryImpl repositoryImpl) {
        this.repositoryImpl = repositoryImpl;
    }

    public DespesaCartao salvar(DespesaCartao despesa) {
        return repositoryImpl.save(despesa);
    }

    public void remover(DespesaCartao despesa) {
        repositoryImpl.delete(despesa);
    }

    public DespesaCartao alterar(DespesaCartao despesa) {
        return repositoryImpl.save(despesa);
    }

    public List<DespesaCartao> buscarTodos() {
        return repositoryImpl.findAll();
    }

    public DespesaCartao buscarPorId(int idDespesa) {
        Optional<DespesaCartao> optionalDespesa = repositoryImpl.findById(idDespesa);
        if (optionalDespesa.isPresent()) {
            return optionalDespesa.get();
        }

        return null;
    }

    public Optional<DespesaCartao> findById(int idBanco) {
        return repositoryImpl.findById(idBanco);
    }


    public List<DespesaCartao> buscarDespesasCartaoAtivoMes(int idUsuario, int idCartao, LocalDateTime dataInicio, LocalDateTime dataFim) {
        return repositoryImpl.findByIdDespesasCartaoAtivoMes(idUsuario, idCartao, dataInicio, dataFim);
    }

    public Double findSomaPorCartaoDentroPeriodo(int idCartao, LocalDateTime dataInicio, LocalDateTime dataFim) {
        return repositoryImpl.findSomaPorCartaoDentroPeriodo(idCartao,  dataInicio, dataFim);
    }
}

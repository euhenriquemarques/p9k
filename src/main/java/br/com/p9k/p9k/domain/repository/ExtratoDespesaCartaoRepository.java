package br.com.p9k.p9k.domain.repository;


import br.com.p9k.p9k.domain.entidade.ExtratoDespesaCartao;
import br.com.p9k.p9k.infraestructure.persisstence.ExtratoDespesaCartaoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ExtratoDespesaCartaoRepository {

    @Autowired
    private ExtratoDespesaCartaoRepositoryImpl repositoryImpl;

    public ExtratoDespesaCartao salvar(ExtratoDespesaCartao extratoDespesaCartao) {
        return repositoryImpl.save(extratoDespesaCartao);
    }

    public void remover(ExtratoDespesaCartao extratoDespesaCartao) {
        repositoryImpl.delete(extratoDespesaCartao);
    }

    public ExtratoDespesaCartao alterar(ExtratoDespesaCartao extratoDespesaCartao) {
        return repositoryImpl.save(extratoDespesaCartao);
    }

    public List<ExtratoDespesaCartao> buscarTodos() {
        return repositoryImpl.findAll();
    }

    public ExtratoDespesaCartao buscarPorId(int idExtratoDespesaCartao) {
        Optional<ExtratoDespesaCartao> optionalExtratoDespesaCartao = repositoryImpl.findById(idExtratoDespesaCartao);
        if (optionalExtratoDespesaCartao.isPresent()){
            return optionalExtratoDespesaCartao.get();
        }

        return null;
    }

    public Optional<ExtratoDespesaCartao> findById(int idCartao) {
        return repositoryImpl.findByCartaoId(idCartao);
    }

    public List<ExtratoDespesaCartao> findDespesaCartaoPaga(int idUsuario, LocalDateTime dataInicio, LocalDateTime dataFimMes) {
        return repositoryImpl.findByCartaoId(idUsuario,dataInicio,  dataFimMes);
    }
    public List<ExtratoDespesaCartao> findExtratosByUsuarioAndPeriodo(int idUsuario, LocalDateTime dataInicio, LocalDateTime dataFimMes) {
        return repositoryImpl.findExtratosByUsuarioAndPeriodo(idUsuario,dataInicio,  dataFimMes);
    }
}

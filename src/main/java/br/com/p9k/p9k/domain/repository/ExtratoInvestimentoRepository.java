package br.com.p9k.p9k.domain.repository;


import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.ExtratoInvestimento;
import br.com.p9k.p9k.infraestructure.persisstence.ExtratoInvestimentoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ExtratoInvestimentoRepository {

    @Autowired
    private ExtratoInvestimentoRepositoryImpl repositoryImpl;

    public ExtratoInvestimento salvar(ExtratoInvestimento ExtratoInvestimento) {
        return repositoryImpl.save(ExtratoInvestimento);
    }

    public void remover(ExtratoInvestimento ExtratoInvestimento) {
        repositoryImpl.delete(ExtratoInvestimento);
    }

    public ExtratoInvestimento alterar(ExtratoInvestimento ExtratoInvestimento) {
        return repositoryImpl.save(ExtratoInvestimento);
    }

    public List<ExtratoInvestimento> buscarTodos() {
        return repositoryImpl.findAll();
    }

    public ExtratoInvestimento buscarPorId(int idExtratoInvestimento) {
        Optional<ExtratoInvestimento> optionalExtratoInvestimento = repositoryImpl.findById(idExtratoInvestimento);
        if (optionalExtratoInvestimento.isPresent()){
            return optionalExtratoInvestimento.get();
        }

        return null;
    }

    public Optional<ExtratoInvestimento> findById(int idBanco) {
        return repositoryImpl.findById(idBanco);
    }

}

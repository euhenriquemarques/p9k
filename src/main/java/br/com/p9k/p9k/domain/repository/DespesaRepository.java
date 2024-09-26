package br.com.p9k.p9k.domain.repository;


import br.com.p9k.p9k.domain.entidade.Despesa;
import br.com.p9k.p9k.infraestructure.persisstence.DespesaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class DespesaRepository {

    private final DespesaRepositoryImpl repositoryImpl;

    public DespesaRepository(DespesaRepositoryImpl repositoryImpl) {
        this.repositoryImpl = repositoryImpl;
    }

    public Despesa salvar(Despesa Despesa) {
        return repositoryImpl.save(Despesa);
    }

    public void remover(Despesa Despesa) {
        repositoryImpl.delete(Despesa);
    }

    public Despesa alterar(Despesa Despesa) {
        return repositoryImpl.save(Despesa);
    }

    public List<Despesa> buscarTodos() {
        return repositoryImpl.findAll();
    }

    public Despesa buscarPorId(int idDespesa) {
        Optional<Despesa> optionalDespesa = repositoryImpl.findById(idDespesa);
        if (optionalDespesa.isPresent()) {
            return optionalDespesa.get();
        }

        return null;
    }

    public Optional<Despesa> findById(int idBanco) {
        return repositoryImpl.findById(idBanco);
    }

    public List<Despesa> buscarDespesasVigentes(int idUsuario, LocalDateTime dataAtual) {
        return repositoryImpl.findDespesasAtivasByUsuarioAndDataVencimento(idUsuario, dataAtual);
    }

    public List<Despesa> buscarDespesasVigentesEFuturas(int idUsuario) {
        return repositoryImpl.findDespesasAtivasByUsuarioSemData(idUsuario);
    }
}

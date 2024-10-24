package br.com.p9k.p9k.domain.repository;


import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.Despesa;
import br.com.p9k.p9k.domain.entidade.ExtratoDespesa;
import br.com.p9k.p9k.infraestructure.persisstence.ExtratoDespesaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ExtratoDespesaRepository {

    @Autowired
    private ExtratoDespesaRepositoryImpl repositoryImpl;

    public ExtratoDespesa salvar(ExtratoDespesa ExtratoDespesa) {
        return repositoryImpl.save(ExtratoDespesa);
    }

    public void remover(ExtratoDespesa ExtratoDespesa) {
        repositoryImpl.delete(ExtratoDespesa);
    }

    public ExtratoDespesa alterar(ExtratoDespesa ExtratoDespesa) {
        return repositoryImpl.save(ExtratoDespesa);
    }

    public List<ExtratoDespesa> buscarTodos() {
        return repositoryImpl.findAll();
    }

    public ExtratoDespesa buscarPorId(int idExtratoDespesa) {
        Optional<ExtratoDespesa> optionalExtratoDespesa = repositoryImpl.findById(idExtratoDespesa);
        if (optionalExtratoDespesa.isPresent()){
            return optionalExtratoDespesa.get();
        }

        return null;
    }

    public Optional<ExtratoDespesa> findById(int idBanco) {
        return repositoryImpl.findById(idBanco);
    }

    public List<ExtratoDespesa> findDespesasAtivasByUsuarioAndPagas(int idUsuario, LocalDateTime inicioMes, LocalDateTime ultimoDiaMes) {
        return repositoryImpl.findDespesasAtivasByUsuarioAndPagas(idUsuario, inicioMes, ultimoDiaMes);
    }
}

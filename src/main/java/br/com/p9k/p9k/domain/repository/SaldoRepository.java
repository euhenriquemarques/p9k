package br.com.p9k.p9k.domain.repository;


import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.Saldo;
import br.com.p9k.p9k.infraestructure.persisstence.SaldoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SaldoRepository {

    @Autowired
    private SaldoRepositoryImpl repositoryImpl;

    public Saldo salvar(Saldo Saldo) {
        return repositoryImpl.save(Saldo);
    }

    public void remover(Saldo Saldo) {
        repositoryImpl.delete(Saldo);
    }

    public Saldo alterar(Saldo Saldo) {
        return repositoryImpl.save(Saldo);
    }

    public List<Saldo> buscarTodos() {
        return repositoryImpl.findAll();
    }

    public Saldo buscarPorId(int idSaldo) {
        Optional<Saldo> optionalSaldo = repositoryImpl.findById(idSaldo);
        if (optionalSaldo.isPresent()){
            return optionalSaldo.get();
        }

        return null;
    }

    public Optional<Saldo> findById(int idBanco) {
        return repositoryImpl.findById(idBanco);
    }

}

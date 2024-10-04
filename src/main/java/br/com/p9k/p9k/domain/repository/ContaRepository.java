package br.com.p9k.p9k.domain.repository;


import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.Conta;
import br.com.p9k.p9k.infraestructure.persisstence.ContaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ContaRepository {

    @Autowired
    private ContaRepositoryImpl repositoryImpl;

    public Conta salvar(Conta Conta) {
        return repositoryImpl.save(Conta);
    }

    public void remover(Conta Conta) {
        repositoryImpl.delete(Conta);
    }

    public Conta alterar(Conta Conta) {
        return repositoryImpl.save(Conta);
    }

    public List<Conta> buscarTodos() {
        return repositoryImpl.findAll();
    }

    public Conta buscarPorId(int idConta) {
        Optional<Conta> optionalConta = repositoryImpl.findById(idConta);
        if (optionalConta.isPresent()){
            return optionalConta.get();
        }

        return null;
    }

    public List<Conta> findById(int idUsuario) {
        return repositoryImpl.findByUsuarioId(idUsuario);
    }

}

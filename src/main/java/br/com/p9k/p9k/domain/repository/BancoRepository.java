package br.com.p9k.p9k.domain.repository;


import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.Usuario;
import br.com.p9k.p9k.infraestructure.persisstence.BancoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BancoRepository {

    @Autowired
    private BancoRepositoryImpl repositoryImpl;

    public Banco salvar(Banco banco) {
        return repositoryImpl.save(banco);
    }

    public void remover(Banco banco) {
        repositoryImpl.delete(banco);
    }

    public Banco alterar(Banco banco) {
        return repositoryImpl.save(banco);
    }

    public List<Banco> buscarTodos() {
        return repositoryImpl.findAll();
    }

    public Banco buscarPorId(int idBanco) {
        Optional<Banco> optionalBanco = repositoryImpl.findById(idBanco);
        if (optionalBanco.isPresent()){
            return optionalBanco.get();
        }

        return null;
    }
    public Banco buscarPorIdUsuario(Usuario usuario) {
        Optional<Banco> optionalBanco = repositoryImpl.findByIdUsuario(usuario);
        if (optionalBanco.isPresent()){
            return optionalBanco.get();
        }

        return null;
    }
}

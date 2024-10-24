package br.com.p9k.p9k.domain.repository;


import br.com.p9k.p9k.domain.entidade.Cartao;
import br.com.p9k.p9k.infraestructure.persisstence.CartaoRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CartaoRepository {

    @Autowired
    private CartaoRepositoryImpl repositoryImpl;

    public Cartao salvar(Cartao cartao) {
        return repositoryImpl.save(cartao);
    }

    public void remover(Cartao cartao) {
        repositoryImpl.delete(cartao);
    }

    public Cartao alterar(Cartao cartao) {
        return repositoryImpl.save(cartao);
    }

    public List<Cartao> buscarTodos() {
        return repositoryImpl.findAll();
    }

    public Cartao buscarPorId(int idCartao) {
        Optional<Cartao> optionalCartao = repositoryImpl.findById(idCartao);
        if (optionalCartao.isPresent()){
            return optionalCartao.get();
        }

        return null;
    }


    public List<Cartao> buscarCartaoPorUsuario(int idUsuario) {
        return repositoryImpl.buscarCartaoPorUsuario(idUsuario);
    }
}

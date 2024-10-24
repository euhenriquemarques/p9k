package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.Cartao;
import br.com.p9k.p9k.domain.repository.CartaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaoService {

    private final CartaoRepository repository;

    public CartaoService(CartaoRepository repository) {
        this.repository = repository;
    }

    public void salvar(Cartao cartao) {
        repository.salvar(cartao);
    }

    public void remover(Cartao cartao) {
        repository.remover(cartao);
    }

    public void alterar(Cartao cartao) {
        repository.alterar(cartao);
    }

    public List<Cartao> buscarTodos() {
        return repository.buscarTodos();
    }


    public List<Cartao> buscarCartaoPorUsuario(int idUsuario) {
        return repository.buscarCartaoPorUsuario(idUsuario);
    }
}

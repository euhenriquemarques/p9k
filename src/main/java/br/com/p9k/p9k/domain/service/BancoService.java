package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.Usuario;
import br.com.p9k.p9k.domain.repository.BancoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoService {

    private BancoRepository repository;

    public void salvar(Banco banco) {
        repository.salvar(banco);
    }

    public void remover(Banco banco) {
        repository.remover(banco);
    }

    public void alterar(Banco banco) {
        repository.alterar(banco);
    }

    public List<Banco> buscarTodos() {
        return repository.buscarTodos();
    }

    public Banco buscarPorId(int idBanco) {
        return repository.buscarPorId(idBanco);
    }

//    public Banco buscarPorIdUsuario(Usuario usuario) {
//        return repository.buscarPorIdUsuario(usuario);
//    }
}

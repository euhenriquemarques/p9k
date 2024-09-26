package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.repository.BancoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancoService {

    private final BancoRepository repository;

    public BancoService(BancoRepository repository) {
        this.repository = repository;
    }

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

    public Optional<Banco> findById(int id) {
        return repository.findById(id);
    }



//    public Banco buscarPorIdUsuario(Usuario usuario) {
//        return repository.buscarPorIdUsuario(usuario);
//    }
}

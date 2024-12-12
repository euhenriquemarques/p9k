package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.User;
import br.com.p9k.p9k.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvar(User Usuario) {
        repository.salvar(Usuario);
    }

    public void remover(User Usuario) {
        repository.remover(Usuario);
    }

    public void alterar(User Usuario) {
        repository.alterar(Usuario);
    }

    public List<User> buscarTodos() {
        return repository.buscarTodos();
    }

    public Optional<User> findById(int id) {
        return repository.findById(id);
    }
}

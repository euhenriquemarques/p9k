package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.Usuario;
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

    public void salvar(Usuario Usuario) {
        repository.salvar(Usuario);
    }

    public void remover(Usuario Usuario) {
        repository.remover(Usuario);
    }

    public void alterar(Usuario Usuario) {
        repository.alterar(Usuario);
    }

    public List<Usuario> buscarTodos() {
        return repository.buscarTodos();
    }

    public Optional<Usuario> findById(int id) {
        return repository.findById(id);
    }
}

package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.Usuario;
import br.com.p9k.p9k.domain.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository repository;

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

    public Usuario buscarPorId(int idUsuario) {
        return repository.buscarPorId(idUsuario);
    }

//    public Usuario buscarPorIdUsuario(Usuario usuario) {
//        return repository.buscarPorIdUsuario(usuario);
//    }
}

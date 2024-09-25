package br.com.p9k.p9k.domain.repository;


import br.com.p9k.p9k.domain.entidade.Usuario;
import br.com.p9k.p9k.infraestructure.persisstence.UsuarioRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsuarioRepository {

    @Autowired
    private UsuarioRepositoryImpl repositoryImpl;

    public Usuario salvar(Usuario Usuario) {
        return repositoryImpl.save(Usuario);
    }

    public void remover(Usuario Usuario) {
        repositoryImpl.delete(Usuario);
    }

    public Usuario alterar(Usuario Usuario) {
        return repositoryImpl.save(Usuario);
    }

    public List<Usuario> buscarTodos() {
        return repositoryImpl.findAll();
    }

    public Usuario buscarPorId(int idUsuario) {
        Optional<Usuario> optionalUsuario = repositoryImpl.findById(idUsuario);
        if (optionalUsuario.isPresent()){
            return optionalUsuario.get();
        }

        return null;
    }

}

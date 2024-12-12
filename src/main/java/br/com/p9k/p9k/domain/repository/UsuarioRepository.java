package br.com.p9k.p9k.domain.repository;


import br.com.p9k.p9k.domain.entidade.User;
import br.com.p9k.p9k.infraestructure.persisstence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UsuarioRepository {

    @Autowired
    private UserRepository repositoryImpl;

    public User salvar(User Usuario) {
        return repositoryImpl.save(Usuario);
    }

    public void remover(User Usuario) {
        repositoryImpl.delete(Usuario);
    }

    public User alterar(User Usuario) {
        return repositoryImpl.save(Usuario);
    }

    public List<User> buscarTodos() {
        return repositoryImpl.findAll();
    }

    public User buscarPorId(int idUsuario) {
        Optional<User> optionalUsuario = repositoryImpl.findById((long) idUsuario);
        if (optionalUsuario.isPresent()){
            return optionalUsuario.get();
        }

        return null;
    }

    public Optional<User> findById(int idBanco) {
        return repositoryImpl.findById((long) idBanco);
    }

}

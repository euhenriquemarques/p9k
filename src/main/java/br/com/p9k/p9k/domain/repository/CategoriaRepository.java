package br.com.p9k.p9k.domain.repository;


import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.Categoria;
import br.com.p9k.p9k.infraestructure.persisstence.CategoriaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoriaRepository {

    @Autowired
    private CategoriaRepositoryImpl repositoryImpl;

    public Categoria salvar(Categoria Categoria) {
        return repositoryImpl.save(Categoria);
    }

    public void remover(Categoria Categoria) {
        repositoryImpl.delete(Categoria);
    }

    public Categoria alterar(Categoria Categoria) {
        return repositoryImpl.save(Categoria);
    }

    public List<Categoria> buscarTodos() {
        return repositoryImpl.findAll();
    }

    public Categoria buscarPorId(int idCategoria) {
        Optional<Categoria> optionalCategoria = repositoryImpl.findById(idCategoria);
        if (optionalCategoria.isPresent()){
            return optionalCategoria.get();
        }

        return null;
    }

    public List<Categoria> findById(int idUsuario) {
        return repositoryImpl.findByIdusuario(idUsuario);
    }

    public List<Categoria> buscarCategoriaPorUsuario(int idUsuario) {
        return repositoryImpl.buscarCategoriaPorUsuario(idUsuario);
    }
}

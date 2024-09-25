package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.Categoria;
import br.com.p9k.p9k.domain.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private CategoriaRepository repository;

    public void salvar(Categoria Categoria) {
        repository.salvar(Categoria);
    }

    public void remover(Categoria Categoria) {
        repository.remover(Categoria);
    }

    public void alterar(Categoria Categoria) {
        repository.alterar(Categoria);
    }

    public List<Categoria> buscarTodos() {
        return repository.buscarTodos();
    }

    public Categoria buscarPorId(int idCategoria) {
        return repository.buscarPorId(idCategoria);
    }

//    public Categoria buscarPorIdUsuario(Usuario usuario) {
//        return repository.buscarPorIdUsuario(usuario);
//    }
}

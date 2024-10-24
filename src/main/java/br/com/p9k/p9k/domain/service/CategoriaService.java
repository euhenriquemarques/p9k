package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.Categoria;
import br.com.p9k.p9k.domain.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

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

    public List<Categoria> findById(int id) {
        return repository.findById(id);
    }
    public List<Categoria> findyInvestimentoEntrada(int id) {
        return repository.findyInvestimentoEntrada(id);
    }

    public List<Categoria> buscarCategoriaPorUsuario(int idUsuario) {
        return repository.buscarCategoriaPorUsuario(idUsuario);
    }
}

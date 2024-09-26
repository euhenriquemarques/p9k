package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.Meta;
import br.com.p9k.p9k.domain.repository.MetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetaService {

    private final MetaRepository repository;

    public MetaService(MetaRepository repository) {
        this.repository = repository;
    }

    public void salvar(Meta Meta) {
        repository.salvar(Meta);
    }

    public void remover(Meta Meta) {
        repository.remover(Meta);
    }

    public void alterar(Meta Meta) {
        repository.alterar(Meta);
    }

    public List<Meta> buscarTodos() {
        return repository.buscarTodos();
    }

    public Optional<Meta> findById(int id) {
        return repository.findById(id);
    }
}

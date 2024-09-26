package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.ExtratoMeta;
import br.com.p9k.p9k.domain.repository.ExtratoMetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExtratoMetaService {

    private final ExtratoMetaRepository repository;

    public ExtratoMetaService(ExtratoMetaRepository repository) {
        this.repository = repository;
    }

    public void salvar(ExtratoMeta ExtratoMeta) {
        repository.salvar(ExtratoMeta);
    }

    public void remover(ExtratoMeta ExtratoMeta) {
        repository.remover(ExtratoMeta);
    }

    public void alterar(ExtratoMeta ExtratoMeta) {
        repository.alterar(ExtratoMeta);
    }

    public List<ExtratoMeta> buscarTodos() {
        return repository.buscarTodos();
    }

    public Optional<ExtratoMeta> findById(int id) {
        return repository.findById(id);
    }
}

package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.Utils;
import br.com.p9k.p9k.domain.entidade.ExtratoReceita;
import br.com.p9k.p9k.domain.repository.ExtratoReceitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExtratoReceitaService {

    private final ExtratoReceitaRepository repository;

    public ExtratoReceitaService(ExtratoReceitaRepository repository) {
        this.repository = repository;
    }

    public void salvar(ExtratoReceita extratoReceita) {
        extratoReceita.setDataProcessamento(Utils.buscarDataAtual());
        repository.salvar(extratoReceita);
    }

    public void remover(ExtratoReceita ExtratoReceita) {
        repository.remover(ExtratoReceita);
    }

    public void alterar(ExtratoReceita ExtratoReceita) {
        repository.alterar(ExtratoReceita);
    }

    public List<ExtratoReceita> buscarTodos() {
        return repository.buscarTodos();
    }

    public Optional<ExtratoReceita> findById(int id) {
        return repository.findById(id);
    }
}

package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.Utils;
import br.com.p9k.p9k.domain.entidade.ExtratoDespesa;
import br.com.p9k.p9k.domain.repository.ExtratoDespesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExtratoDespesaService {

    private final ExtratoDespesaRepository repository;

    public ExtratoDespesaService(ExtratoDespesaRepository repository) {
        this.repository = repository;
    }

    public void salvar(ExtratoDespesa extratoDespesa) {
        extratoDespesa.setDataProcessamento(Utils.buscarDataAtual());
        repository.salvar(extratoDespesa);
    }

    public void remover(ExtratoDespesa ExtratoDespesa) {
        repository.remover(ExtratoDespesa);
    }

    public void alterar(ExtratoDespesa ExtratoDespesa) {
        repository.alterar(ExtratoDespesa);
    }

    public List<ExtratoDespesa> buscarTodos() {
        return repository.buscarTodos();
    }

    public Optional<ExtratoDespesa> findById(int id) {
        return repository.findById(id);
    }
}

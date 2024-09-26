package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.ExtratoInvestimento;
import br.com.p9k.p9k.domain.repository.ExtratoInvestimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExtratoInvestimentoService {

    private final ExtratoInvestimentoRepository repository;

    public ExtratoInvestimentoService(ExtratoInvestimentoRepository repository) {
        this.repository = repository;
    }

    public void salvar(ExtratoInvestimento ExtratoInvestimento) {
        repository.salvar(ExtratoInvestimento);
    }

    public void remover(ExtratoInvestimento ExtratoInvestimento) {
        repository.remover(ExtratoInvestimento);
    }

    public void alterar(ExtratoInvestimento ExtratoInvestimento) {
        repository.alterar(ExtratoInvestimento);
    }

    public List<ExtratoInvestimento> buscarTodos() {
        return repository.buscarTodos();
    }

    public Optional<ExtratoInvestimento> findById(int id) {
        return repository.findById(id);
    }
}

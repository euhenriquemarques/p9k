package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.Conta;
import br.com.p9k.p9k.domain.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    private final ContaRepository repository;

    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }

    public void salvar(Conta Conta) {
        repository.salvar(Conta);
    }

    public void remover(Conta Conta) {
        repository.remover(Conta);
    }

    public void alterar(Conta Conta) {
        repository.alterar(Conta);
    }

    public List<Conta> buscarTodos() {
        return repository.buscarTodos();
    }

    public Optional<Conta> findById(int id) {
        return repository.findById(id);
    }
}

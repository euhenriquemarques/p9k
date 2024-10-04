package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.Saldo;
import br.com.p9k.p9k.domain.repository.SaldoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaldoService {

    private final SaldoRepository repository;

    public SaldoService(SaldoRepository repository) {
        this.repository = repository;
    }

    public void salvar(Saldo Saldo) {
        repository.salvar(Saldo);
    }

    public void remover(Saldo Saldo) {
        repository.remover(Saldo);
    }

    public void alterar(Saldo Saldo) {
        repository.alterar(Saldo);
    }

    public List<Saldo> buscarTodos() {
        return repository.buscarTodos();
    }

    public Optional<Saldo> findByContaId(int id) {
        return repository.findByContaId(id);
    }
}

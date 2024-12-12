package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.Conta;
import br.com.p9k.p9k.domain.entidade.Saldo;
import br.com.p9k.p9k.domain.repository.ContaRepository;
import br.com.p9k.p9k.domain.repository.SaldoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaldoService {

    private final SaldoRepository repository;
    private final ContaRepository contaRepository;

    public SaldoService(SaldoRepository repository, ContaRepository contaRepository) {
        this.repository = repository;
        this.contaRepository = contaRepository;
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

    public void adicionarSaldoConta(int idConta, double valor) {
        Optional<Saldo> saldoOptional = repository.findByContaId(idConta);

        if (saldoOptional.isPresent()) {
            Saldo item = saldoOptional.get();
            item.setSaldo(item.getSaldo() + valor);
            salvar(item);
            return;
        }
        Conta conta = contaRepository.buscarPorId(idConta);
        Saldo item = new Saldo();
        item.setSaldo(valor);
        item.setConta(conta);
        salvar(item);

    }
    public void descontarSaldoConta(int idConta, double valor) {
        Optional<Saldo> saldoOptional = repository.findByContaId(idConta);

        if (saldoOptional.isPresent()) {
            Saldo item = saldoOptional.get();
            item.setSaldo(item.getSaldo() - valor);
            salvar(item);
            return;
        }
        throw new RuntimeException("Saldo Nao Encontrado");

    }
}

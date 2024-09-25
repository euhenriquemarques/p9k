package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.Saldo;
import br.com.p9k.p9k.domain.repository.SaldoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaldoService {

    private SaldoRepository repository;

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

    public Saldo buscarPorId(int idSaldo) {
        return repository.buscarPorId(idSaldo);
    }

//    public Saldo buscarPorIdUsuario(Usuario usuario) {
//        return repository.buscarPorIdUsuario(usuario);
//    }
}

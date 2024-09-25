package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.Conta;
import br.com.p9k.p9k.domain.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    private ContaRepository repository;

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

    public Conta buscarPorId(int idConta) {
        return repository.buscarPorId(idConta);
    }

//    public Conta buscarPorIdUsuario(Usuario usuario) {
//        return repository.buscarPorIdUsuario(usuario);
//    }
}

package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.ExtratoDespesa;
import br.com.p9k.p9k.domain.repository.ExtratoDespesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtratoDespesaService {

    private ExtratoDespesaRepository repository;

    public void salvar(ExtratoDespesa ExtratoDespesa) {
        repository.salvar(ExtratoDespesa);
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

    public ExtratoDespesa buscarPorId(int idExtratoDespesa) {
        return repository.buscarPorId(idExtratoDespesa);
    }

//    public ExtratoDespesa buscarPorIdUsuario(Usuario usuario) {
//        return repository.buscarPorIdUsuario(usuario);
//    }
}

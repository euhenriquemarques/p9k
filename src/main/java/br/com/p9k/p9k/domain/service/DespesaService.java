package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.Despesa;
import br.com.p9k.p9k.domain.repository.DespesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaService {

    private DespesaRepository repository;

    public void salvar(Despesa Despesa) {
        repository.salvar(Despesa);
    }

    public void remover(Despesa Despesa) {
        repository.remover(Despesa);
    }

    public void alterar(Despesa Despesa) {
        repository.alterar(Despesa);
    }

    public List<Despesa> buscarTodos() {
        return repository.buscarTodos();
    }

    public Despesa buscarPorId(int idDespesa) {
        return repository.buscarPorId(idDespesa);
    }

//    public Despesa buscarPorIdUsuario(Usuario usuario) {
//        return repository.buscarPorIdUsuario(usuario);
//    }
}

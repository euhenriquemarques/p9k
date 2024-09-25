package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.DadosPagamento;
import br.com.p9k.p9k.domain.repository.DadosPagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DadosPagamentoService {

    private DadosPagamentoRepository repository;

    public void salvar(DadosPagamento DadosPagamento) {
        repository.salvar(DadosPagamento);
    }

    public void remover(DadosPagamento DadosPagamento) {
        repository.remover(DadosPagamento);
    }

    public void alterar(DadosPagamento DadosPagamento) {
        repository.alterar(DadosPagamento);
    }

    public List<DadosPagamento> buscarTodos() {
        return repository.buscarTodos();
    }

    public DadosPagamento buscarPorId(int idDadosPagamento) {
        return repository.buscarPorId(idDadosPagamento);
    }

//    public DadosPagamento buscarPorIdUsuario(Usuario usuario) {
//        return repository.buscarPorIdUsuario(usuario);
//    }
}

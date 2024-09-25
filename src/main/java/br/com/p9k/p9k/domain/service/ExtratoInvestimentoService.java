package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.ExtratoInvestimento;
import br.com.p9k.p9k.domain.repository.ExtratoInvestimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtratoInvestimentoService {

    private ExtratoInvestimentoRepository repository;

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

    public ExtratoInvestimento buscarPorId(int idExtratoInvestimento) {
        return repository.buscarPorId(idExtratoInvestimento);
    }

//    public ExtratoInvestimento buscarPorIdUsuario(Usuario usuario) {
//        return repository.buscarPorIdUsuario(usuario);
//    }
}

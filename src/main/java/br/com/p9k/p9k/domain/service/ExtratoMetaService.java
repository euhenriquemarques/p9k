package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.ExtratoMeta;
import br.com.p9k.p9k.domain.repository.ExtratoMetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExtratoMetaService {

    private ExtratoMetaRepository repository;

    public void salvar(ExtratoMeta ExtratoMeta) {
        repository.salvar(ExtratoMeta);
    }

    public void remover(ExtratoMeta ExtratoMeta) {
        repository.remover(ExtratoMeta);
    }

    public void alterar(ExtratoMeta ExtratoMeta) {
        repository.alterar(ExtratoMeta);
    }

    public List<ExtratoMeta> buscarTodos() {
        return repository.buscarTodos();
    }

    public ExtratoMeta buscarPorId(int idExtratoMeta) {
        return repository.buscarPorId(idExtratoMeta);
    }

//    public ExtratoMeta buscarPorIdUsuario(Usuario usuario) {
//        return repository.buscarPorIdUsuario(usuario);
//    }
}

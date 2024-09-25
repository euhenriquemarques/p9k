package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.Meta;
import br.com.p9k.p9k.domain.repository.MetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaService {

    private MetaRepository repository;

    public void salvar(Meta Meta) {
        repository.salvar(Meta);
    }

    public void remover(Meta Meta) {
        repository.remover(Meta);
    }

    public void alterar(Meta Meta) {
        repository.alterar(Meta);
    }

    public List<Meta> buscarTodos() {
        return repository.buscarTodos();
    }

    public Meta buscarPorId(int idMeta) {
        return repository.buscarPorId(idMeta);
    }

//    public Meta buscarPorIdUsuario(Usuario usuario) {
//        return repository.buscarPorIdUsuario(usuario);
//    }
}

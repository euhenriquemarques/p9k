package br.com.p9k.p9k.domain.repository;


import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.Meta;
import br.com.p9k.p9k.infraestructure.persisstence.MetaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MetaRepository {

    @Autowired
    private MetaRepositoryImpl repositoryImpl;

    public Meta salvar(Meta Meta) {
        return repositoryImpl.save(Meta);
    }

    public void remover(Meta Meta) {
        repositoryImpl.delete(Meta);
    }

    public Meta alterar(Meta Meta) {
        return repositoryImpl.save(Meta);
    }

    public List<Meta> buscarTodos() {
        return repositoryImpl.findAll();
    }

    public Meta buscarPorId(int idMeta) {
        Optional<Meta> optionalMeta = repositoryImpl.findById(idMeta);
        if (optionalMeta.isPresent()){
            return optionalMeta.get();
        }

        return null;
    }

    public Optional<Meta> findById(int idBanco) {
        return repositoryImpl.findById(idBanco);
    }

}

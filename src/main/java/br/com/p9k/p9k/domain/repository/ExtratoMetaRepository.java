package br.com.p9k.p9k.domain.repository;


import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.ExtratoMeta;
import br.com.p9k.p9k.infraestructure.persisstence.ExtratoMetaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ExtratoMetaRepository {

    @Autowired
    private ExtratoMetaRepositoryImpl repositoryImpl;

    public ExtratoMeta salvar(ExtratoMeta ExtratoMeta) {
        return repositoryImpl.save(ExtratoMeta);
    }

    public void remover(ExtratoMeta ExtratoMeta) {
        repositoryImpl.delete(ExtratoMeta);
    }

    public ExtratoMeta alterar(ExtratoMeta ExtratoMeta) {
        return repositoryImpl.save(ExtratoMeta);
    }

    public List<ExtratoMeta> buscarTodos() {
        return repositoryImpl.findAll();
    }

    public ExtratoMeta buscarPorId(int idExtratoMeta) {
        Optional<ExtratoMeta> optionalExtratoMeta = repositoryImpl.findById(idExtratoMeta);
        if (optionalExtratoMeta.isPresent()){
            return optionalExtratoMeta.get();
        }

        return null;
    }

    public Optional<ExtratoMeta> findById(int idBanco) {
        return repositoryImpl.findById(idBanco);
    }

}

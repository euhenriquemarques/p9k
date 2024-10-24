package br.com.p9k.p9k.domain.repository;


import br.com.p9k.p9k.domain.entidade.ExtratoReceita;
import br.com.p9k.p9k.infraestructure.persisstence.ExtratoReceitaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ExtratoReceitaRepository {

    @Autowired
    private ExtratoReceitaRepositoryImpl repositoryImpl;

    public ExtratoReceita salvar(ExtratoReceita ExtratoReceita) {
        return repositoryImpl.save(ExtratoReceita);
    }

    public void remover(ExtratoReceita ExtratoReceita) {
        repositoryImpl.delete(ExtratoReceita);
    }

    public ExtratoReceita alterar(ExtratoReceita ExtratoReceita) {
        return repositoryImpl.save(ExtratoReceita);
    }

    public List<ExtratoReceita> buscarTodos() {
        return repositoryImpl.findAll();
    }

    public ExtratoReceita buscarPorId(int idExtratoReceita) {
        Optional<ExtratoReceita> optionalExtratoReceita = repositoryImpl.findById(idExtratoReceita);
        if (optionalExtratoReceita.isPresent()){
            return optionalExtratoReceita.get();
        }

        return null;
    }

    public Optional<ExtratoReceita> findById(int idBanco) {
        return repositoryImpl.findById(idBanco);
    }

    public List<ExtratoReceita> findReceitasAtivasByUsuarioAndPagas(int idUsuario, LocalDateTime inicioMes, LocalDateTime ultimoDiaMes) {
        return repositoryImpl.findReceitasAtivasByUsuarioAndPagas(idUsuario, inicioMes, ultimoDiaMes);
    }
}

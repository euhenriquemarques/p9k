package br.com.p9k.p9k.domain.repository;


import br.com.p9k.p9k.domain.entidade.Receita;
import br.com.p9k.p9k.infraestructure.persisstence.ReceitaRepositoryImpl;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ReceitaRepository {

    private final ReceitaRepositoryImpl repositoryImpl;

    public ReceitaRepository(ReceitaRepositoryImpl repositoryImpl) {
        this.repositoryImpl = repositoryImpl;
    }

    public Receita salvar(Receita Receita) {
        return repositoryImpl.save(Receita);
    }

    public void remover(Receita Receita) {
        repositoryImpl.delete(Receita);
    }

    public Receita alterar(Receita Receita) {
        return repositoryImpl.save(Receita);
    }

    public List<Receita> buscarTodos() {
        return repositoryImpl.findAll();
    }

    public Receita buscarPorId(int idReceita) {
        Optional<Receita> optionalReceita = repositoryImpl.findById(idReceita);
        if (optionalReceita.isPresent()) {
            return optionalReceita.get();
        }

        return null;
    }

    public Optional<Receita> findById(int idBanco) {
        return repositoryImpl.findById(idBanco);
    }

    public List<Receita> buscarReceitasVigentes(int idUsuario, LocalDateTime dataInicioMes, LocalDateTime dataFimMes) {
        return repositoryImpl.findReceitasAtivasByUsuarioAndDataVencimento(idUsuario, dataInicioMes, dataFimMes);
    }


    public Double buscarValorTotalParcelado(int idUsuario, LocalDateTime dataInicioMes) {
        return repositoryImpl.findValorTotalParcelado(idUsuario, dataInicioMes);
    }

    public Double buscarValorTotalRecorrente(int idUsuario) {
        return repositoryImpl.findValorTotalRecorrente(idUsuario);
    }



    public List<Receita> buscarReceitasGeralEFuturas(int idUsuario) {
        return repositoryImpl.findReceitasAtivasByUsuarioSemData(idUsuario);
    }

    public List<Receita> buscarReceitasVigentesEFuturas(int idUsuario) {
        return repositoryImpl.findReceitasAtivasByUsuarioSemDataVigentes(idUsuario);
    }

    public List<Receita> findDespesasByUsuario(int idUsuario, LocalDateTime dataInicioMes) {
        return repositoryImpl.findDespesasByUsuario(idUsuario, dataInicioMes);
    }
}

package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.Utils;
import br.com.p9k.p9k.domain.entidade.Receita;
import br.com.p9k.p9k.domain.repository.ReceitaRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService {

    private final ReceitaRepository repository;

    public ReceitaService(ReceitaRepository repository) {
        this.repository = repository;
    }

    public void salvar(Receita receita) {
        int contadorVencimentoMes = 0;

        for (int parcelaCurrent = receita.getParcela(); parcelaCurrent <= receita.getParcelaTotais(); parcelaCurrent++) {
            repository.salvar(
                    Receita
                            .builder()
                            .categoria(receita.getCategoria())
                            .dataProcessamento(Utils.buscarDataAtual())
                            .parcela(receita.isRecorrente() ? 0 : parcelaCurrent)
                            .recorrente(receita.isRecorrente())
                            .usuario(receita.getUsuario())
                            .dataVencimentoParcela(receita.isRecorrente() ? Utils.buscarDataAtual() : receita.getDataVencimentoParcela().plus(contadorVencimentoMes, ChronoUnit.MONTHS))
                            .parcelaTotais(receita.isRecorrente() ? 0 : receita.getParcelaTotais())
                            .valorParcela(receita.getValorParcela())
                            .valorTotal(receita.isRecorrente() ? receita.getValorParcela()  : receita.getValorTotal())
                            .ativo(true)
                            .descricao(receita.getDescricao())
                            .build());
            contadorVencimentoMes++;
        }


    }

    public void remover(Receita Receita) {
        repository.remover(Receita);
    }

    public void alterar(Receita Receita) {
        repository.alterar(Receita);
    }

    public List<Receita> buscarTodos() {
        return repository.buscarTodos();
    }

    public Optional<Receita> findById(int id) {
        return repository.findById(id);
    }


    public List<Receita> buscarReceitasVigentes(int idUsuario) {
       return repository.buscarReceitasVigentes(idUsuario, Utils.buscarDataInicioMes(), Utils.buscarDataFimMes());

    }

    public List<Receita> buscarReceitasGeralEFuturas(int idUsuario) {
       return repository.buscarReceitasGeralEFuturas(idUsuario);

    }
    public List<Receita> buscarReceitasVigentesEFuturas(int idUsuario) {
       return repository.buscarReceitasVigentesEFuturas(idUsuario);

    }
}

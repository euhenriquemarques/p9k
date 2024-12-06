package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.Utils;
import br.com.p9k.p9k.domain.entidade.Despesa;
import br.com.p9k.p9k.domain.repository.DespesaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
public class DespesaService {

    private final DespesaRepository repository;

    public DespesaService(DespesaRepository repository) {
        this.repository = repository;
    }

    public void salvar(Despesa despesa) {

        if(despesa.getId()!= 0){
            repository.salvar(despesa);
            return;
        }
        int contadorVencimentoMes = 0;

        for (int parcelaCurrent = despesa.getParcela(); parcelaCurrent <= despesa.getParcelaTotais(); parcelaCurrent++) {
            repository.salvar(
                    Despesa
                            .builder()
                            .categoria(despesa.getCategoria())
                            .dataProcessamento(Utils.buscarDataAtual())
                            .juros(despesa.isJuros())
                            .parcela(despesa.isRecorrente() ? 0 : parcelaCurrent)
                            .recorrente(despesa.isRecorrente())
                            .usuario(despesa.getUsuario())
                            .dataVencimentoParcela(despesa.isRecorrente() ? Utils.buscarDataAtual() : despesa.getDataVencimentoParcela().plus(contadorVencimentoMes, ChronoUnit.MONTHS))
                            .parcelaTotais(despesa.isRecorrente() ? 0 : despesa.getParcelaTotais())
                            .valorParcela(despesa.getValorParcela())
                            .valorTotal(despesa.isRecorrente() ? despesa.getValorParcela()  : despesa.getValorTotal())
                            .ativo(true)
                            .descricao(despesa.getDescricao())
                            .build());
            contadorVencimentoMes++;
        }


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

    public Optional<Despesa> findById(int id) {
        return repository.findById(id);
    }


    public List<Despesa> buscarDespesasVigentes(int idUsuario) {
       return repository.buscarDespesasVigentes(idUsuario, Utils.buscarDataInicioMes(), Utils.buscarDataFimMes());

    }

    public List<Despesa> findDespesasByUsuario(int idUsuario) {
       return repository.findDespesasByUsuario(idUsuario, Utils.buscarDataInicioMes());

    }

    public List<Despesa> buscarDespesasGeralEFuturas(int idUsuario) {
       return repository.buscarDespesasGeralEFuturas(idUsuario);

    }
    public List<Despesa> buscarDespesasVigentesEFuturas(int idUsuario) {
       return repository.buscarDespesasVigentesEFuturas(idUsuario);

    }
}

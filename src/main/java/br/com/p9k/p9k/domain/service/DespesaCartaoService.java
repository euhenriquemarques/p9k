package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.Utils;
import br.com.p9k.p9k.domain.entidade.Despesa;
import br.com.p9k.p9k.domain.entidade.DespesaCartao;
import br.com.p9k.p9k.domain.repository.DespesaCartaoRepository;
import br.com.p9k.p9k.domain.repository.DespesaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
public class DespesaCartaoService {

    private final DespesaCartaoRepository repository;

    public DespesaCartaoService(DespesaCartaoRepository repository) {
        this.repository = repository;
    }

    public void salvar(DespesaCartao despesa) {

        if(despesa.getId()!= 0){
            repository.salvar(despesa);
            return;
        }

        int contadorVencimentoMes = 0;

        for (int parcelaCurrent = despesa.getParcela(); parcelaCurrent <= despesa.getParcelaTotais(); parcelaCurrent++) {
            repository.salvar(
                    DespesaCartao
                            .builder()
                            .cartao(despesa.getCartao())
                            .dataProcessamento(Utils.buscarDataAtual())
                            .parcela(despesa.isRecorrente() ? 0 : parcelaCurrent)
                            .recorrente(despesa.isRecorrente())
                            .usuario(despesa.getUsuario())
                            .dataCompra(despesa.isRecorrente() ? Utils.buscarDataAtual() : despesa.getDataCompra().plus(contadorVencimentoMes, ChronoUnit.MONTHS))
                            .parcelaTotais(despesa.isRecorrente() ? 0 : despesa.getParcelaTotais())
                            .valorParcela(despesa.getValorParcela())
                            .valorTotal(despesa.isRecorrente() ? despesa.getValorParcela()  : despesa.getValorTotal())
                            .ativo(true)
                            .descricao(despesa.getDescricao())
                            .build());
            contadorVencimentoMes++;
        }
    }

    public void remover(DespesaCartao Despesa) {
        repository.remover(Despesa);
    }

    public void alterar(DespesaCartao Despesa) {
        repository.alterar(Despesa);
    }

    public List<DespesaCartao> buscarTodos() {
        return repository.buscarTodos();
    }

    public Optional<DespesaCartao> findById(int id) {
        return repository.findById(id);
    }


    public List<DespesaCartao> buscarDespesasCartaoAtivoMes(int idUsuario,  int idCartao) {
       return repository.buscarDespesasCartaoAtivoMes(idUsuario, idCartao, Utils.buscarDataInicioMes(), Utils.buscarDataFimMes());

    }

    public List<DespesaCartao> findDespesasByUsuario(int idUsuario) {
        return repository.findDespesasByUsuario(idUsuario, Utils.buscarDataInicioMes());

    }
}

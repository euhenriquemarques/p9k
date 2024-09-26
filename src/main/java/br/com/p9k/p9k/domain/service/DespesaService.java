package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.entidade.Despesa;
import br.com.p9k.p9k.domain.repository.DespesaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class DespesaService {

    public static final LocalDateTime DATA_ATUAL = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    private final DespesaRepository repository;

    public DespesaService(DespesaRepository repository) {
        this.repository = repository;
    }

    public void salvar(Despesa despesa) {

        for (int parcelaCurrent = 1; parcelaCurrent <= despesa.getParcelaTotais(); parcelaCurrent++) {
            repository.salvar(
                    Despesa
                            .builder()
                            .categoria(despesa.getCategoria())
                            .dataProcessamento(DATA_ATUAL)
                            .juros(despesa.isJuros())
                            .parcela(despesa.isRecorrente() ? 0 : parcelaCurrent)
                            .recorrente(despesa.isRecorrente())
                            .usuario(despesa.getUsuario())
                            .dataVencimentoParcela(despesa.isRecorrente() ? DATA_ATUAL : despesa.getDataVencimentoParcela().plus(parcelaCurrent, ChronoUnit.MONTHS))
                            .parcelaTotais(despesa.isRecorrente() ? 0 : despesa.getParcelaTotais())
                            .valorParcela(despesa.getValorParcela())
                            .valorTotal(despesa.isRecorrente() ? despesa.getValorParcela()  : despesa.getValorTotal())
                            .ativo(true)
                            .build());
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
       return repository.buscarDespesasVigentes(idUsuario,DATA_ATUAL);

    }

    public List<Despesa> buscarDespesasVigentesEFuturas(int idUsuario) {
       return repository.buscarDespesasVigentesEFuturas(idUsuario);

    }
}

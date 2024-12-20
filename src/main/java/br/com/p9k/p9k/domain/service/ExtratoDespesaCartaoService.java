package br.com.p9k.p9k.domain.service;

import br.com.p9k.p9k.domain.Utils;
import br.com.p9k.p9k.domain.entidade.ExtratoDespesaCartao;
import br.com.p9k.p9k.domain.repository.ExtratoDespesaCartaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class ExtratoDespesaCartaoService {

    private final ExtratoDespesaCartaoRepository repository;
    private final SaldoService saldoService;

    public ExtratoDespesaCartaoService(ExtratoDespesaCartaoRepository repository, SaldoService saldoService) {
        this.repository = repository;
        this.saldoService = saldoService;
    }

    public void salvar(ExtratoDespesaCartao extratoDespesaCartao) {
        try {
            saldoService.descontarSaldoConta(extratoDespesaCartao.getIdConta(), (extratoDespesaCartao.getValor() + extratoDespesaCartao.getValorJuros()) - extratoDespesaCartao.getValorDesconto());
            extratoDespesaCartao.setDataProcessamento(Utils.buscarDataAtual());
            repository.salvar(extratoDespesaCartao);
        }catch (Exception e){
            throw e;
        }
    }

    public void remover(ExtratoDespesaCartao ExtratoDespesaCartao) {
        repository.remover(ExtratoDespesaCartao);
    }

    public void alterar(ExtratoDespesaCartao ExtratoDespesaCartao) {
        repository.alterar(ExtratoDespesaCartao);
    }

    public List<ExtratoDespesaCartao> buscarTodos() {
        return repository.buscarTodos();
    }

    public Optional<ExtratoDespesaCartao> findById(int id) {
        return repository.findById(id);
    }
}

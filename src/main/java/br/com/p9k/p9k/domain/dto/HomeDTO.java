package br.com.p9k.p9k.domain.dto;

import br.com.p9k.p9k.domain.entidade.Despesa;
import br.com.p9k.p9k.domain.entidade.DespesaCartao;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class HomeDTO {

    private List<SaldoDTO> listaSaldo;
    private List<DespesaDTO> listaDespesa;
    private List<DespesaCartaoDTO> listaDespesaCartao;
    private List<DespesaCartaoDTO> listaDespesaCartao12Meses;
    private List<ResumoMensalDTO> listaResumoMensal;
    private List<ExtratoDespesaDTO> listaExtratoDespesa;
    private List<ExtratoDespesaCartaoDTO> listaExtratoDespesaCartao;
    private Double valortotalParcelado;
    private Double valortotalRecorrente;
}

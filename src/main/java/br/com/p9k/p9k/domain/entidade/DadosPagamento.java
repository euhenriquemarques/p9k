package br.com.p9k.p9k.domain.entidade;

import lombok.Data;

@Data
public class DadosPagamento {
    private int id;
    private Despesa idDespesa;
    private String descricao;
    private String dadosPagamento;

}

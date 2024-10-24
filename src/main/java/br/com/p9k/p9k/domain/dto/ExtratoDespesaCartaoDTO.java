package br.com.p9k.p9k.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ExtratoDespesaCartaoDTO {

    private String cartao;
    private LocalDateTime dataPagamento;
    private Double valor;
    private Double valorJuros;
    private Double valorDesconto;
}

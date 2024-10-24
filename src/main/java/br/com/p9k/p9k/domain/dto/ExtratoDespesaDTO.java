package br.com.p9k.p9k.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ExtratoDespesaDTO {

    private String despesa;
    private LocalDateTime dataPagamento;
    private LocalDateTime dataVencimento;
    private Double valor;
    private Double valorJuros;
    private Double valorDesconto;
    private int parcela;
}

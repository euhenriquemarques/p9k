package br.com.p9k.p9k.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class DespesaDTO {

    private String categoria;
    private int parcela;
    private int parcelaTotais;
    private LocalDateTime dataVencimentoParcela;
    private double valorParcela;
    private String descricao;
}

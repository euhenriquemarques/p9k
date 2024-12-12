package br.com.p9k.p9k.domain.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SaldoDTO {

    private double saldo;
    private String banco;
    private String descricao;
    private String numero;
}

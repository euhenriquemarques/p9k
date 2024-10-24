package br.com.p9k.p9k.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Data
public class DespesaCartaoDTO {
    private int idCartao;
    private String cartao;
    private Map<String, Double> meses;
    private Double valor;
}
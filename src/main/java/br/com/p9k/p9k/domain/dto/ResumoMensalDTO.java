package br.com.p9k.p9k.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class ResumoMensalDTO {

    private String categoria;
    private Map<String, Double> meses;
    private Double valor;
}
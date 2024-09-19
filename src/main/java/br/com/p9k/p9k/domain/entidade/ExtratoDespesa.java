package br.com.p9k.p9k.domain.entidade;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExtratoDespesa {

    private int id;
    private Despesa idDepessa;
    private LocalDateTime dataProcessamento;
    private Double valor;
    private Double valorJuros;
    private Double valorDesconto;
    private Usuario idUsuario;

}

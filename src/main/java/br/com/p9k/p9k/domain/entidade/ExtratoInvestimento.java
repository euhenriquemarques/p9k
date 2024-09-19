package br.com.p9k.p9k.domain.entidade;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExtratoInvestimento {

    private int id;
    private Categoria idCategoria;
    private Conta idConta;
    private LocalDateTime dataProcessamento;
    private Double valor;
    private Usuario idUsuario;
}

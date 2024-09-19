package br.com.p9k.p9k.domain.entidade;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Meta {

    private int id;
    private Usuario idUsuario;
    private Double valor;
    private String descricao;
    private LocalDateTime dataProcessamento;
    private LocalDateTime dataRealizacao;

}

package br.com.p9k.p9k.domain.entidade;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExtratoMeta {

    private int id;
    private Meta idMeta;
    private LocalDateTime dataProcessamento;
    private Double valor;
    private Usuario idUsuario;
}

package br.com.p9k.p9k.domain.entidade;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Despesa {

    private int id;
    private Categoria idCategoria;
    private Usuario idUsuario;
    private LocalDateTime dataProcessamento;
    private boolean recorrente;
    private int parcela;
    private int parcelaTotais;
    private LocalDateTime dataVencimentoParcela;
    private boolean juros;
    private Double valorPacela;
    private Double valorTotal;
}

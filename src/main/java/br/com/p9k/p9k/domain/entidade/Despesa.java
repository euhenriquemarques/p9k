package br.com.p9k.p9k.domain.entidade;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class Despesa  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Usuario usuario;
    private LocalDateTime dataProcessamento;
    private boolean recorrente;
    private int parcela;
    private int parcelaTotais;
    private LocalDateTime dataVencimentoParcela;
    private boolean juros;
    private double valorParcela;
    private double valorTotal;
}

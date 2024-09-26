package br.com.p9k.p9k.domain.entidade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Despesa  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Categoria categoria;
    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;
    private LocalDateTime dataProcessamento;
    private boolean recorrente;
    private int parcela;
    private int parcelaTotais;
    private LocalDateTime dataVencimentoParcela;
    private boolean juros;
    private boolean ativo;
    private double valorParcela;
    private double valorTotal;
}

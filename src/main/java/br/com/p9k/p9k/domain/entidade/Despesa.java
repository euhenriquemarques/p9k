package br.com.p9k.p9k.domain.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private Categoria categoria;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Usuario usuario;
    private LocalDateTime dataProcessamento;
    @NotNull
    private boolean recorrente;
    @NotNull
    private int parcela;
    @NotNull
    private int parcelaTotais;
    @NotNull
    private LocalDateTime dataVencimentoParcela;
    @NotNull
    private boolean juros;
    @NotNull
    private boolean ativo;
    @NotNull
    private double valorParcela;
    @NotNull
    private double valorTotal;
    @NotNull
    private String descricao;
}

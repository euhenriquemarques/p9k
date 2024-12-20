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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExtratoDespesa  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @NotNull
    private Despesa despesa;
    private LocalDateTime dataProcessamento;
    @NotNull
    private LocalDateTime dataPagamento;
    private Double valor;
    @NotNull
    private Double valorJuros;
    @NotNull
    private Double valorDesconto;
    @ManyToOne
    @NotNull
    private User usuario;
    @Transient
    @NotNull
    private int idConta;


}

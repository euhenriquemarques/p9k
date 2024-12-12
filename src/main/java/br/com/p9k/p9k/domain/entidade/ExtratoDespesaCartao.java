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
public class ExtratoDespesaCartao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @NotNull
    private Cartao cartao;
    private LocalDateTime dataProcessamento;
    @NotNull
    private LocalDateTime dataPagamento;
    @NotNull
    private Double valor;
    @NotNull
    private Double valorJuros;
    @NotNull
    private Double valorDesconto;
    @NotNull
    @ManyToOne
    private User usuario;
    @NotNull
    @Transient
    private int idConta;

}

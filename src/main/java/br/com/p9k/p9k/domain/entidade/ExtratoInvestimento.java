package br.com.p9k.p9k.domain.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ExtratoInvestimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @NotNull
    private Categoria categoria;
    @NotNull
    @ManyToOne
    private Conta conta;
    private LocalDateTime dataProcessamento;
    @NotNull
    private Double valor;
    @ManyToOne
    @NotNull
    private User usuario;
}

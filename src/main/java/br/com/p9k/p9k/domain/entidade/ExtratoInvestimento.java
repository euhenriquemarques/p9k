package br.com.p9k.p9k.domain.entidade;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class ExtratoInvestimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Conta conta;
    private LocalDateTime dataProcessamento;
    private Double valor;
    @ManyToOne
    private Usuario usuario;
}

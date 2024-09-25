package br.com.p9k.p9k.domain.entidade;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Saldo  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Double saldo;
    @ManyToOne
    private Conta conta;
}

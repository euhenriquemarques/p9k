package br.com.p9k.p9k.domain.entidade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Conta  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String numero;
    private String agencia;
    @ManyToOne
    private Banco banco;
    private boolean compartilhado;
    private boolean status;
    private boolean nacional;
    @ManyToOne
    private Usuario usuario;

}

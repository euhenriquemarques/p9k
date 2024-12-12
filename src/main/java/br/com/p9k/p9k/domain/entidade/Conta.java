package br.com.p9k.p9k.domain.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String numero;
    @NotNull
    private String agencia;
    @NotNull
    private String descricao;
    @ManyToOne
    @NotNull
    private Banco banco;
    private boolean compartilhado;
    private boolean status;
    private boolean nacional;
    @ManyToOne
    @NotNull
    private User usuario;

}

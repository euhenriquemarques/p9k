package br.com.p9k.p9k.domain.entidade;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cartao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private String descricao;
    @ManyToOne
    @NotNull
    private User usuario;
    @NotNull
    private String dataVencimentoCartao;
    @NotNull
    private String dataFechamento;

}

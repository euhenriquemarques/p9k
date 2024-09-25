package br.com.p9k.p9k.domain.entidade;

import br.com.p9k.p9k.domain.enums.TipoCategoria;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private TipoCategoria movimentacao;

}

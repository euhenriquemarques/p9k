package br.com.p9k.p9k.domain.entidade;

import br.com.p9k.p9k.domain.enums.TipoCategoria;
import jakarta.persistence.*;
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
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private TipoCategoria movimentacao;
    @ManyToOne
    private Usuario usuario;

}

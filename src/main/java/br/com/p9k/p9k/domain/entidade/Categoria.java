package br.com.p9k.p9k.domain.entidade;

import br.com.p9k.p9k.domain.enums.TipoCategoria;
import lombok.Data;

@Data
public class Categoria {

    private int id;
    private String descricao;
    private TipoCategoria movimentacao;

}

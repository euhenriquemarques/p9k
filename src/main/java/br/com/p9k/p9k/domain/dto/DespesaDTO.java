package br.com.p9k.p9k.domain.dto;

import br.com.p9k.p9k.domain.entidade.Categoria;
import br.com.p9k.p9k.domain.entidade.Usuario;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class DespesaDTO {

    private String categoria;
    private int parcela;
    private int parcelaTotais;
    private LocalDateTime dataVencimentoParcela;
    private double valorParcela;
    private String descricao;
}

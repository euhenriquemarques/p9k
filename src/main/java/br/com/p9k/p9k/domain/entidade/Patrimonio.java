package br.com.p9k.p9k.domain.entidade;

import br.com.p9k.p9k.domain.enums.CategoriaPatrimonio;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Patrimonio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descricao;
    private CategoriaPatrimonio categoriaPatrimonio;
    private Double valorCompra;
    private Double valorAtual;
    private Double valorVendaRapida;
    private LocalDateTime dataAquisicao;

}

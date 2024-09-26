package br.com.p9k.p9k.domain.entidade;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExtratoDespesa  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Despesa despesa;
    private LocalDateTime dataProcessamento;
    private Double valor;
    private Double valorJuros;
    private Double valorDesconto;
    @ManyToOne
    private Usuario usuario;

}

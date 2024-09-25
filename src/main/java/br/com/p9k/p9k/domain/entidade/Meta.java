package br.com.p9k.p9k.domain.entidade;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class Meta  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Usuario usuario;
    private Double valor;
    private String descricao;
    private LocalDateTime dataProcessamento;
    private LocalDateTime dataRealizacao;

}

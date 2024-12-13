package br.com.p9k.p9k.domain.entidade;


import br.com.p9k.p9k.domain.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nome;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate nascimento;

    private LocalDateTime criacao;

    private LocalDateTime data_bloqueio;

    private String status;

    private boolean ativo;

    @NotNull
    @Transient
    private int plano;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // Hash BCrypt

    @Column(nullable = false)
    private String role ;


}

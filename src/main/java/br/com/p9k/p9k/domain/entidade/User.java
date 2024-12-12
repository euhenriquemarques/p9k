package br.com.p9k.p9k.domain.entidade;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password; // Hash BCrypt

    @Column(nullable = false)
    private String role = "ROLE_USER"; // Pode ser ROLE_USER ou ROLE_ADMIN


}

// UserRepository.java
package br.com.p9k.p9k.infraestructure.persisstence;

import br.com.p9k.p9k.domain.entidade.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

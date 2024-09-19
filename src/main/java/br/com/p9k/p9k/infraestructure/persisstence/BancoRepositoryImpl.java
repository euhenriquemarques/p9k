package br.com.p9k.p9k.infraestructure.persisstence;


import br.com.p9k.p9k.domain.entidade.Banco;
import br.com.p9k.p9k.domain.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BancoRepositoryImpl  extends JpaRepository<Banco, Integer> {

    Optional<Banco> findByIdUsuario(Usuario usuario);
}

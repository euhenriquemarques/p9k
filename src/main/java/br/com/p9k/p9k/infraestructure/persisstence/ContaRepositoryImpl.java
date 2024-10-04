package br.com.p9k.p9k.infraestructure.persisstence;


import br.com.p9k.p9k.domain.entidade.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepositoryImpl extends JpaRepository<Conta, Integer> {

    List<Conta> findByUsuarioId(int idUsuario);
}

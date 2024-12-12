package br.com.p9k.p9k.infraestructure.persisstence;


import br.com.p9k.p9k.domain.entidade.Conta;
import br.com.p9k.p9k.domain.entidade.Saldo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaldoRepositoryImpl extends JpaRepository<Saldo, Integer> {

    Optional<Saldo> findByContaId(int contaId);

    @Query("SELECT s FROM Saldo s LEFT JOIN Conta c ON c.id = s.conta.id " +
            "LEFT JOIN Banco b ON c.banco.id = b.id" +
            " WHERE c.usuario.id = :usuarioId AND c.status = true")
    List<Saldo> buscarPorIdUsuario(@Param("usuarioId") int idUsuario);

    Optional<Saldo> findByConta(Conta contaId);
}

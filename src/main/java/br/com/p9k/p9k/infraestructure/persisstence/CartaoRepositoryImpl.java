package br.com.p9k.p9k.infraestructure.persisstence;


import br.com.p9k.p9k.domain.entidade.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaoRepositoryImpl extends JpaRepository<Cartao, Integer> {



    @Query("SELECT t FROM Cartao t " +
            " WHERE t.usuario.id = :usuarioId ORDER BY t.descricao")
    List<Cartao> buscarCartaoPorUsuario(@Param("usuarioId") int usuarioId);

}

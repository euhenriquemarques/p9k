package br.com.p9k.p9k.infraestructure.persisstence;


import br.com.p9k.p9k.domain.entidade.ExtratoReceita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExtratoReceitaRepositoryImpl extends JpaRepository<ExtratoReceita, Integer> {


    @Query("SELECT ed FROM ExtratoReceita ed " +
            "INNER JOIN Receita d ON d.id = ed.receita.id " +
            "WHERE d.usuario.id = :usuarioId " +
            "AND d.ativo = true " +
            "AND d.dataVencimentoParcela BETWEEN :dataInicioMes AND :dataFimMes " +
            " OR d.recorrente = true " +
            "AND ed.dataPagamento BETWEEN :dataInicioMes AND :dataFimMes" +
            " ORDER BY d. dataVencimentoParcela, d.descricao ")
    List<ExtratoReceita> findReceitasAtivasByUsuarioAndPagas(
            @Param("usuarioId") int usuarioId,
            @Param("dataInicioMes") LocalDateTime dataInicioMes,
            @Param("dataFimMes") LocalDateTime dataFimMes);
}

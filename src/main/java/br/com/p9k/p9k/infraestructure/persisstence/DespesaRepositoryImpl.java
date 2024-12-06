package br.com.p9k.p9k.infraestructure.persisstence;


import br.com.p9k.p9k.domain.entidade.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DespesaRepositoryImpl extends JpaRepository<Despesa, Integer> {

    @Query("SELECT d FROM Despesa d " +
            "LEFT JOIN ExtratoDespesa ed ON d.id = ed.despesa.id " +
            "WHERE ed.id IS NULL " +
            "AND d.usuario.id = :usuarioId " +
            "AND d.ativo = true " +
            "AND (" +
            "(d.recorrente = false AND d.dataVencimentoParcela BETWEEN :dataInicioMes AND :dataFimMes) " +
            "OR d.recorrente = true" +
            ") " +
            "ORDER BY d.dataVencimentoParcela, d.descricao")
    List<Despesa> findDespesasAtivasByUsuarioAndDataVencimento(  @Param("usuarioId") int usuarioId,
                                                                   @Param("dataInicioMes") LocalDateTime dataInicioMes,
                                                                   @Param("dataFimMes") LocalDateTime dataFimMes);

    @Query("SELECT d FROM Despesa d " +
            "LEFT JOIN ExtratoDespesa ed ON d.id = ed.despesa.id " +
            "WHERE ed.id IS NULL " +
            "AND d.usuario.id = :usuarioId " +
            "AND (" +
            "(d.recorrente = false AND d.dataVencimentoParcela > :dataInicioMes ) " +
            "OR d.recorrente = true" +
            ") " +
            "ORDER BY d.dataVencimentoParcela, d.descricao")
    List<Despesa> findDespesasByUsuario(  @Param("usuarioId") int usuarioId,
                                                                 @Param("dataInicioMes") LocalDateTime dataInicioMes);

    @Query("SELECT t FROM Despesa t LEFT JOIN ExtratoDespesa e ON t.id = e.despesa.id" +
            " WHERE t.usuario.id = :usuarioId AND t.ativo = true ORDER BY t.dataProcessamento ")
    List<Despesa> findDespesasAtivasByUsuarioSemData(@Param("usuarioId") int usuarioId);

    @Query("SELECT t FROM Despesa t LEFT JOIN ExtratoDespesa e ON t.id = e.despesa.id" +
            " WHERE t.usuario.id = :usuarioId AND t.ativo  = true AND e.id IS NULL ORDER BY t.dataProcessamento ")
    List<Despesa> findDespesasAtivasByUsuarioSemDataVigentes(@Param("usuarioId") int usuarioId);

    @Query("SELECT SUM(d.valorParcela) FROM Despesa d " +
            "WHERE d.recorrente = false " +
            "AND d.dataVencimentoParcela >= :dataInicioMes " +
            "AND d.ativo = true " +
            "AND d.usuario.id = :usuarioId"+
            " ORDER BY d. dataVencimentoParcela, d.descricao ")
    Double findValorTotalParcelado(@Param("usuarioId") int usuarioId,  @Param("dataInicioMes") LocalDateTime dataInicioMes);

    @Query("SELECT SUM(d.valorParcela) FROM Despesa d " +
            "WHERE d.recorrente = true " +
            "AND d.ativo = true " +
            "AND d.usuario.id = :usuarioId"+
            " ORDER BY d. dataVencimentoParcela, d.descricao ")
    Double findValorTotalRecorrente(@Param("usuarioId") int usuarioId);


    @Query("SELECT SUM(d.valorParcela) FROM Despesa d " +
            "WHERE d.recorrente = true " +
            "AND d.ativo = true " +
            "AND d.usuario.id = :usuarioId"+
           " AND d.dataVencimentoParcela BETWEEN :dataInicioMes AND :dataFimMes" +
            " ORDER BY d. dataVencimentoParcela, d.descricao ")
    Double findSomaMensal(@Param("usuarioId") int idUsuario, @Param("dataInicioMes") LocalDateTime dataInicioMes,
                          @Param("dataFimMes") LocalDateTime dataFimMes);
}

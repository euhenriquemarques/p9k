package br.com.p9k.p9k.infraestructure.persisstence;


import br.com.p9k.p9k.domain.entidade.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReceitaRepositoryImpl extends JpaRepository<Receita, Integer> {

    @Query("SELECT d FROM Receita d " +
            "LEFT JOIN ExtratoReceita ed ON d.id = ed.receita.id " +
            "WHERE ed.id IS NULL " +
            "AND d.usuario.id = :usuarioId " +
            "AND d.ativo = true " +
            "AND (" +
            "(d.recorrente = false AND d.dataVencimentoParcela BETWEEN :dataInicioMes AND :dataFimMes) " +
            "OR d.recorrente = true" +
            ") " +
            "ORDER BY d.dataVencimentoParcela, d.descricao")
    List<Receita> findReceitasAtivasByUsuarioAndDataVencimento(  @Param("usuarioId") int usuarioId,
                                                                   @Param("dataInicioMes") LocalDateTime dataInicioMes,
                                                                   @Param("dataFimMes") LocalDateTime dataFimMes);

    @Query("SELECT t FROM Receita t LEFT JOIN ExtratoReceita e ON t.id = e.receita.id" +
            " WHERE t.usuario.id = :usuarioId AND t.ativo = true ORDER BY t.dataProcessamento ")
    List<Receita> findReceitasAtivasByUsuarioSemData(@Param("usuarioId") int usuarioId);

    @Query("SELECT t FROM Receita t LEFT JOIN ExtratoReceita e ON t.id = e.receita.id" +
            " WHERE t.usuario.id = :usuarioId AND t.ativo  = true AND e.id IS NULL ORDER BY t.dataProcessamento ")
    List<Receita> findReceitasAtivasByUsuarioSemDataVigentes(@Param("usuarioId") int usuarioId);

    @Query("SELECT SUM(d.valorParcela) FROM Receita d " +
            "WHERE d.recorrente = false " +
            "AND d.dataVencimentoParcela >= :dataInicioMes " +
            "AND d.ativo = true " +
            "AND d.usuario.id = :usuarioId"+
            " ORDER BY d. dataVencimentoParcela, d.descricao ")
    Double findValorTotalParcelado(@Param("usuarioId") int usuarioId,  @Param("dataInicioMes") LocalDateTime dataInicioMes);

    @Query("SELECT SUM(d.valorParcela) FROM Receita d " +
            "WHERE d.recorrente = true " +
            "AND d.ativo = true " +
            "AND d.usuario.id = :usuarioId"+
            " ORDER BY d. dataVencimentoParcela, d.descricao ")
    Double findValorTotalRecorrente(@Param("usuarioId") int usuarioId);

}

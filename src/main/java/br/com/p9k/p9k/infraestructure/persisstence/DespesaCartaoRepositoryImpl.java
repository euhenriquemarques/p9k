package br.com.p9k.p9k.infraestructure.persisstence;


import br.com.p9k.p9k.domain.dto.DespesaCartaoDTO;
import br.com.p9k.p9k.domain.entidade.DespesaCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DespesaCartaoRepositoryImpl extends JpaRepository<DespesaCartao, Integer> {


    @Query("SELECT d FROM DespesaCartao d " +
            "LEFT JOIN Cartao c ON c.id = d.cartao.id " +
            "LEFT JOIN ExtratoDespesaCartao ed ON c.id = ed.cartao.id " +
            "WHERE d.usuario.id = :usuarioId " +
            "AND  c.id = :idCartao " +
            "AND d.ativo = true " +
            "AND d.dataCompra BETWEEN :dataInicioMes AND :dataFimMes " +
            "AND (ed.id IS NULL " +
            "OR (d.recorrente = true AND (ed.dataPagamento < :dataInicioMes OR ed.dataPagamento > :dataFimMes)))" +
            " ORDER BY d. dataCompra, d.descricao ")
    List<DespesaCartao> findByIdDespesasCartaoAtivoMes(@Param("usuarioId") int usuarioId,@Param("idCartao") int idCartao,
                                                               @Param("dataInicioMes") LocalDateTime dataInicioMes,
                                                               @Param("dataFimMes") LocalDateTime dataFimMes);


    @Query("SELECT c.descricao, SUM(d.valorParcela) FROM DespesaCartao d " +
            "LEFT JOIN Cartao c ON c.id = d.cartao.id " +
            "WHERE d.recorrente = false " +
            "AND d.dataCompra BETWEEN :dataInicioMes AND :dataFimMes " +
            "AND d.ativo = true " +
            "AND d.usuario.id = :usuarioId"+
            " GROUP BY c.id"+
            " ORDER BY c.descricao ")
    Double findValorTotalDentroMes(@Param("usuarioId") int usuarioId, @Param("dataInicioMes") LocalDateTime dataInicioMes, @Param("dataFimMes") LocalDateTime dataFimMes);

    @Query("SELECT SUM(d.valorParcela) " +
            " FROM DespesaCartao d " +
            " JOIN d.cartao c " +
            " WHERE d.dataCompra BETWEEN :dataInicioMes AND :dataFimMes " +
            " AND c.id = :idCartao "+
            " GROUP BY c.descricao")
    Double findSomaPorCartaoDentroPeriodo(@Param("idCartao") int usuarioId, @Param("dataInicioMes") LocalDateTime dataInicioMes, @Param("dataFimMes") LocalDateTime dataFimMes);
}

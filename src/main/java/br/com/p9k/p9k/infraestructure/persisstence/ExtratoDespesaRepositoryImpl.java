package br.com.p9k.p9k.infraestructure.persisstence;


import br.com.p9k.p9k.domain.entidade.Despesa;
import br.com.p9k.p9k.domain.entidade.ExtratoDespesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExtratoDespesaRepositoryImpl extends JpaRepository<ExtratoDespesa, Integer> {


    @Query("SELECT ed FROM ExtratoDespesa ed " +
            "INNER JOIN Despesa d ON d.id = ed.despesa.id " +
            "WHERE d.usuario.id = :usuarioId " +
            "AND d.ativo = true " +
            "AND d.dataVencimentoParcela BETWEEN :dataInicioMes AND :dataFimMes " +
            " OR d.recorrente = true " +
            "AND ed.dataPagamento BETWEEN :dataInicioMes AND :dataFimMes" +
            " ORDER BY d. dataVencimentoParcela, d.descricao ")
    List<ExtratoDespesa> findDespesasAtivasByUsuarioAndPagas(
            @Param("usuarioId") int usuarioId,
            @Param("dataInicioMes") LocalDateTime dataInicioMes,
            @Param("dataFimMes") LocalDateTime dataFimMes);
}

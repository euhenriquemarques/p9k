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

    @Query("SELECT t FROM Despesa t LEFT JOIN ExtratoDespesa e ON t.id = e.despesa.id" +
            " WHERE t.usuario.id = :usuarioId AND t.ativo = true AND t.dataVencimentoParcela < :dataFimMes AND e.id IS NULL")
    List<Despesa> findDespesasAtivasByUsuarioAndDataVencimento(@Param("usuarioId") int usuarioId, @Param("dataFimMes") LocalDateTime dataFimMes);

    @Query("SELECT t FROM Despesa t LEFT JOIN ExtratoDespesa e ON t.id = e.despesa.id" +
            " WHERE t.usuario.id = :usuarioId AND t.ativo = true ORDER BY t.dataProcessamento")
    List<Despesa> findDespesasAtivasByUsuarioSemData(@Param("usuarioId") int usuarioId);

    @Query("SELECT t FROM Despesa t LEFT JOIN ExtratoDespesa e ON t.id = e.despesa.id" +
            " WHERE t.usuario.id = :usuarioId AND t.ativo  = true AND e.id IS NULL ORDER BY t.dataProcessamento ")
    List<Despesa> findDespesasAtivasByUsuarioSemDataVigentes(@Param("usuarioId") int usuarioId);

}

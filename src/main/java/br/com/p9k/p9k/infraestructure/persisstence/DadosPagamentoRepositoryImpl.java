package br.com.p9k.p9k.infraestructure.persisstence;


import br.com.p9k.p9k.domain.entidade.Conta;
import br.com.p9k.p9k.domain.entidade.DadosPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DadosPagamentoRepositoryImpl extends JpaRepository<DadosPagamento, Integer> {

    @Query("SELECT t FROM DadosPagamento t LEFT JOIN Despesa e ON t.despesa.id = e.id" +
            " WHERE e.usuario.id = :usuarioId AND e.ativo = true AND e.dataVencimentoParcela >= :dataAtual  " +
            " ORDER BY t.descricao ")
    List<DadosPagamento> findByUsuarioId(@Param("usuarioId") int usuarioId, @Param("dataAtual") LocalDateTime dataAtual);
}

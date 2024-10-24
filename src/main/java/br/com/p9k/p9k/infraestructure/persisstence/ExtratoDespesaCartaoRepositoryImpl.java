package br.com.p9k.p9k.infraestructure.persisstence;


import br.com.p9k.p9k.domain.entidade.ExtratoDespesaCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExtratoDespesaCartaoRepositoryImpl extends JpaRepository<ExtratoDespesaCartao, Integer> {


    Optional<ExtratoDespesaCartao> findByCartaoId(int idCartao);


    @Query("SELECT ed FROM ExtratoDespesaCartao ed " +
            "LEFT JOIN Cartao c ON c.id = ed.cartao.id " +
            "WHERE c.usuario.id = :idUsuario " +
            "AND ed.dataPagamento BETWEEN :dataInicio AND :dataFimMes " +
            " ORDER BY c.descricao ")
    List<ExtratoDespesaCartao> findByCartaoId(int idUsuario, LocalDateTime dataInicio, LocalDateTime dataFimMes);

    @Query("SELECT ed FROM ExtratoDespesaCartao ed " +
            "LEFT JOIN ed.cartao c " +
            "WHERE c.usuario.id = :idUsuario " +
            "AND ed.dataPagamento BETWEEN :dataInicio AND :dataFimMes " +
            "ORDER BY c.descricao")
    List<ExtratoDespesaCartao> findExtratosByUsuarioAndPeriodo(
            @Param("idUsuario") int idUsuario,
            @Param("dataInicio") LocalDateTime dataInicio,
            @Param("dataFimMes") LocalDateTime dataFimMes);


}

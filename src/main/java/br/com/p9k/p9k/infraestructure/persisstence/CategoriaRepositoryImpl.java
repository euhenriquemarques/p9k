package br.com.p9k.p9k.infraestructure.persisstence;


import br.com.p9k.p9k.domain.entidade.Categoria;
import br.com.p9k.p9k.domain.entidade.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepositoryImpl extends JpaRepository<Categoria, Integer> {


    @Query("SELECT t FROM Categoria t " +
            " WHERE t.usuario.id = :usuarioId AND t.movimentacao != 'INVESTIMENTO' AND t.movimentacao != 'ENTRADA' ORDER BY t.descricao")
    List<Categoria> findByIdusuario(@Param("usuarioId") int usuarioId);

    @Query("SELECT t FROM Categoria t " +
            " WHERE t.usuario.id = :usuarioId ORDER BY t.descricao")
    List<Categoria> buscarCategoriaPorUsuario(@Param("usuarioId") int usuarioId);

}

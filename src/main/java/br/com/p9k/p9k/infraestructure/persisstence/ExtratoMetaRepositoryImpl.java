package br.com.p9k.p9k.infraestructure.persisstence;


import br.com.p9k.p9k.domain.entidade.ExtratoMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtratoMetaRepositoryImpl extends JpaRepository<ExtratoMeta, Integer> {

}

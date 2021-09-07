package co.edu.usbbog.sgpi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.Semillero;

public interface ISemilleroRepository extends JpaRepository<Semillero, Integer>{

	//solo para consultar por grupo de investigacion
	@Transactional(readOnly = true)
	Optional<Semillero> findByGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion);
}

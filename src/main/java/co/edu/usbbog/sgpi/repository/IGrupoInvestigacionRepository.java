package co.edu.usbbog.sgpi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.Proyecto;

public interface IGrupoInvestigacionRepository extends JpaRepository<GrupoInvestigacion, Integer>{

	@Query(value = "DELETE FROM  grupo_investigacion WHERE id = ?1 ", nativeQuery = true)
	List<GrupoInvestigacion> findByGrupo(int id);
}

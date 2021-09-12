package co.edu.usbbog.sgpi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.Semillero;
import net.minidev.json.JSONObject;

public interface ISemilleroRepository extends JpaRepository<Semillero, Integer>{

	//solo para consultar por grupo de investigacion
	@Query(value = "select * from semillero where grupo_investigacion= ?1", nativeQuery = true)
	List<Semillero> findByGrupoInvestigacion(int grupoInvestigacion);
	
	//solo para consultar por lider
	@Query(value = "select * from semillero where lider_semillero= ?1", nativeQuery = true)
	List<Semillero> findByLiderSemillero(String lider);
	
	//solo para consultar por linea
	@Query(value = "select * from semillero where linea_investigacion= ?1", nativeQuery = true)
	List<Semillero> findByLineaInvestigacion(String linea);
	
	//solo para consultar el programa
		@Query(value = "SELECT * FROM programas_semilleros where semillero = ?1", nativeQuery = true)
		List<JSONObject> findByPrograma(int semillero);
}

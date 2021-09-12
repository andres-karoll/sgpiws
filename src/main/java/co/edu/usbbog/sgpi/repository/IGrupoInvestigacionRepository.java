package co.edu.usbbog.sgpi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.Usuario;
import net.minidev.json.JSONObject;

public interface IGrupoInvestigacionRepository extends JpaRepository<GrupoInvestigacion, Integer>{
	//para programas
	@Query(value = "SELECT * FROM programas_grupos_inv where grupo_investigacion = ?1", nativeQuery = true)
	List<JSONObject> findByPrograma(int grupo_investigacion);

	
	//para lineas
	@Query(value = "SELECT * FROM sgpi_db.grupo_inv_lineas_inv where grupo_investigacion = ?1", nativeQuery = true)
	List<JSONObject> findByLinea(int grupo_investigacion);
}

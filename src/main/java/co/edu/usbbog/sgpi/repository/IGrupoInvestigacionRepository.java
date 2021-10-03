package co.edu.usbbog.sgpi.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	
	//des asignar linea
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM `sgpi_db`.`grupo_inv_lineas_inv` WHERE (`grupo_investigacion` = ?2) and (`linea_investigacion` = ?1)", nativeQuery = true)
	void desAsignarLinea( String linea_investigacion, String grupo_investigacion);
	
	//des asignar programa
			@Modifying
			@Transactional
			@Query(value = "DELETE FROM `sgpi_db`.`programas_grupos_inv` WHERE (`programa` = ?1) and (`grupo_investigacion` = ?2)", nativeQuery = true)
			void desAsignarPrograma( String programa, String grupo_investigacion);
}

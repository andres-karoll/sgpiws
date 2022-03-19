package co.edu.usbbog.sgpi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbbog.sgpi.model.Clase;
import net.minidev.json.JSONObject;

public interface IClaseRepository extends JpaRepository<Clase, Integer>{
	/**
	 * lista de clases por profesor
	 * @param profesor
	 * @return
	 */
	@Query(value = "SELECT * FROM clase WHERE profesor = ?1", nativeQuery = true)
	List<Clase> findByProfesor(String profesor);
	/**
	 * lista de clases por materias 
	 * @param materia
	 * @return
	 */
	@Query(value = "SELECT * FROM clase WHERE materia = ?1", nativeQuery = true)
	List<Clase> findByMateria(String materia);
	/**
	 * lista de proyectos de clase por clase 
	 * @param clase
	 * @return
	 */
	
	@Query(value = "SELECT * FROM proyectos_clase where clase = ?1", nativeQuery = true)
	List<JSONObject> findByProyecto(int clase);
	
	//des asignar proyecto a clase
			@Modifying
			@Transactional
			@Query(value = "DELETE FROM `sgpi_db`.`proyectos_clase` WHERE (`clase` = ?1) and (`proyecto` = ?2)", nativeQuery = true)
			void desAsignarProyecto( String clase, String proyecto);
}

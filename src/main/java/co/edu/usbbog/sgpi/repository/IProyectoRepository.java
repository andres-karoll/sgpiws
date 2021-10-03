package co.edu.usbbog.sgpi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.TipoProyecto;

public interface IProyectoRepository extends JpaRepository<Proyecto, Integer> {

	// solo para consultar por tipo y estado
	
	@Query(value = "SELECT * FROM proyecto WHERE tipo_proyecto = ?1 and estado = ?2", nativeQuery = true)
	List<Proyecto> findByTipoProyectoAndEstado(String tipoProyecto, String estado);

	// solo para consultar por estado
	@Transactional(readOnly = true)
	List<Proyecto> findByEstado(String estado);

	@Query(value = "SELECT * FROM proyecto WHERE tipo_proyecto = 'grado'", nativeQuery = true)
	List<Proyecto> findByTipoProyectoGrado();
	
	@Query(value = "SELECT * FROM proyecto WHERE tipo_proyecto = ?1", nativeQuery = true)
	List<Proyecto> findByTipoProyecto(String tipo_proyecto);
	
	
	@Query(value = "SELECT * FROM sgpi_db.proyecto JOIN areas_conocimiento ON sgpi_db.proyecto.id=areas_conocimiento.proyecto WHERE areas_conocimiento.area_conocimiento= ?1", nativeQuery = true)
	List<Proyecto> findByAreaConocimiento(int area_conocimiento);
	
	@Query(value = "SELECT * FROM sgpi_db.proyecto where titulo = ?1", nativeQuery = true)
	List<Proyecto> findByTitulo(String titulo);
}

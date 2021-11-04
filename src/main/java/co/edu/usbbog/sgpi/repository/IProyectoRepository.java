package co.edu.usbbog.sgpi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.TipoProyecto;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

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
	@Query(value="SELECT usuario.cedula as \"cedula\", proyecto.id, proyecto.titulo, proyecto.estado, proyecto.descripcion, proyecto.fecha_inicio, proyecto.fecha_fin, proyecto.metodologia, proyecto.visibilidad from participantes inner join usuario on participantes.usuario =  usuario.cedula inner join proyecto on proyecto.id = participantes.proyecto where participantes.usuario=?1 and proyecto.tipo_proyecto=\"Aula\" ",nativeQuery = true)
	List<JSONObject> proyectosParticipaClase(String cedula);
	@Query(value="SELECT distinct id, titulo,descripcion,estado FROM sgpi_db.participantes,proyecto where proyecto.tipo_proyecto='Semillero' and sgpi_db.participantes.usuario=?1",nativeQuery = true)
	List<JSONObject> proyectosParticipaSemillero(String cedula);
	@Query(value="SELECT distinct id, titulo,descripcion,estado FROM sgpi_db.participantes,proyecto where proyecto.tipo_proyecto='Grado' and sgpi_db.participantes.usuario=?1",nativeQuery = true)
	List<JSONObject> proyectosParticipaGrado(String cedula);
}

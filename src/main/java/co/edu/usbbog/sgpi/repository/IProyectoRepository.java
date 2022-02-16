package co.edu.usbbog.sgpi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	@Query(value="select * from proyecto where proyecto.semillero=?1",nativeQuery = true)
	List<JSONObject> proyectosParticipaSemillero(int semillero);
	@Query(value="select id, titulo,descripcion,estado  from proyecto, participantes where proyecto.tipo_proyecto=\"Grado\" and proyecto.id=participantes.proyecto and participantes.usuario=?1",nativeQuery = true)
	List<JSONObject> proyectosParticipaGrado(String cedula);

	
	
	
	@Query(value="select area_conocimiento.nombre, proyecto.id, proyecto.titulo, proyecto.estado, proyecto.descripcion, proyecto.fecha_inicio, proyecto.fecha_fin, proyecto.metodologia, proyecto.visibilidad from areas_conocimiento inner join area_conocimiento on areas_conocimiento.area_conocimiento = area_conocimiento.id inner join proyecto on areas_conocimiento.proyecto = proyecto.id where proyecto.visibilidad = 1",nativeQuery = true)
	List<JSONObject> proyectosVisibles();
	@Modifying
	@Transactional
	@Query(value="DELETE FROM sgpi_db.areas_conocimiento WHERE (proyecto = ?2) and (area_conocimiento = ?1)",nativeQuery = true)
	void eliminarArea(int area,int pro);
	@Query(value="select proyecto.id,proyecto.descripcion,proyecto.titulo,proyecto.estado, proyectos_convocatoria.convocatoria from proyecto,proyectos_convocatoria,participantes where proyecto.id=proyectos_convocatoria.proyectos and  participantes.proyecto=proyecto.id and participantes.usuario=?1",nativeQuery = true)
	List<JSONObject> tusProyectoConvocatoria(String cedula);
	@Query(value="select proyecto.id,proyecto.descripcion,proyecto.titulo,proyecto.estado, proyecto.semillero from proyecto,participantes where proyecto.tipo_proyecto=\"Semillero\" and  participantes.proyecto=proyecto.id and participantes.usuario=?1 and proyecto.id=participantes.proyecto",nativeQuery = true)
	List<JSONObject> tusProyectoSemillero(String cedula);
	@Query(value="Select convocatoria.nombre_convocatoria,convocatoria.id from convocatoria, proyectos_convocatoria where proyectos_convocatoria.proyectos=?1 and proyectos_convocatoria.convocatoria=convocatoria.id",nativeQuery = true)
	List<JSONObject> paticipacionesConvocatoria(int proyecto);
	@Query(value="select * from proyecto where tipo_proyecto=\"Grado\"",nativeQuery = true)
	List<JSONObject> proyectosGrado();
	@Query(value="select * from proyecto, proyectos_clase where proyecto.tipo_proyecto=\"Aula\" and proyecto.id=proyectos_clase.proyecto and proyecto.estado=\"Propuesta\" and proyectos_clase.clase=?1",nativeQuery = true)
	List<JSONObject> proyectosPropuestaClase(int clase);
	@Query(value="select * from proyecto, proyectos_clase where proyecto.tipo_proyecto=\"Aula\" and proyecto.id=proyectos_clase.proyecto and proyecto.estado=\"Desarrollo\" and proyectos_clase.clase=?1",nativeQuery = true)
	List<JSONObject> proyectosDesarrolloClase(int curso);
	@Query(value="select * from proyecto, proyectos_clase where proyecto.tipo_proyecto=\"Aula\" and proyecto.id=proyectos_clase.proyecto and proyecto.estado=\"Finalizado\" and proyectos_clase.clase=?1",nativeQuery = true)
	List<JSONObject> proyectosFinalizadosClase(int curso);
	@Query(value="select distinct proyecto.id, proyecto.titulo, proyecto.descripcion,proyectos_convocatoria.id_proyecto , convocatoria.nombre_convocatoria from proyectos_convocatoria,proyecto, participantes,convocatoria where proyectos_convocatoria.proyectos=proyecto.id \r\n"
			+ "and proyectos_convocatoria.convocatoria=?1 and participantes.proyecto=proyecto.id and participantes.usuario=?2 and convocatoria.id=proyectos_convocatoria.convocatoria",nativeQuery = true)
	List<JSONObject> tusProyectosConvocatoria(int convocatoria,int id);
	
}

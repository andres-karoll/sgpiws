package co.edu.usbbog.sgpi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbbog.sgpi.model.Comentario;


public interface IComentarioRepository extends JpaRepository<Comentario, Integer>{

	@Query(value = "SELECT * FROM sgpi_db.comentario where producto_id = ?1", nativeQuery = true)
	List<Comentario> findByProducto(int producto_id);
	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE `sgpi_db`.`comentario` SET `calificacion` = ?1 WHERE (`id` = ?2 )", nativeQuery = true)
	void asignarCalificacion( double calificacion, int id);
}

package co.edu.usbbog.sgpi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbbog.sgpi.model.Convocatoria;

public interface IConvocatoriaRepository extends JpaRepository<Convocatoria, Integer>{
	@Query(value = "SELECT * FROM sgpi_db.convocatoria where estado = ?1", nativeQuery = true)
	List<Convocatoria> findByEstadoAbierto(String estado);
}

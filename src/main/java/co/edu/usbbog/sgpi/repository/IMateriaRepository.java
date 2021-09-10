package co.edu.usbbog.sgpi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbbog.sgpi.model.Materia;
import co.edu.usbbog.sgpi.model.Programa;

public interface IMateriaRepository extends JpaRepository<Materia, String>{

	//solo para consultar por Facultad
			@Query(value = "SELECT * FROM materia where programa = ?1", nativeQuery = true)
			List<Materia> findByPrograma(int programa);
}

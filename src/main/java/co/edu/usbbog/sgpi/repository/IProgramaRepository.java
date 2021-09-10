package co.edu.usbbog.sgpi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbbog.sgpi.model.Programa;
import co.edu.usbbog.sgpi.model.Semillero;

public interface IProgramaRepository extends JpaRepository<Programa, Integer>{

	//solo para consultar por Facultad
		@Query(value = "select * from programa where facultad_id= ?1", nativeQuery = true)
		List<Programa> findByFacultad(int facultad_id);
		
		//solo para consultar por Director
		@Query(value = "select * from programa where director= ?1", nativeQuery = true)
		List<Programa> findByDirector(String director);
		//solo para consultar por Semillero
}

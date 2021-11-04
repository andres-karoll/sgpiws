package co.edu.usbbog.sgpi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbbog.sgpi.model.Evento;
import net.minidev.json.JSONObject;

public interface IEventoRepository extends JpaRepository<Evento, Integer>{
	@Query(value = "SELECT * FROM sgpi_db.participaciones where evento_id = ?1", nativeQuery = true)
	List<JSONObject> findByParticipaciones(int id);
	
	@Query(value = "select count(*) as \"eventos_contados\" from sgpi_db.evento", nativeQuery = true)
	List<JSONObject> contarEventos();
}

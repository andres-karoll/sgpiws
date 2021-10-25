package co.edu.usbbog.sgpi.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbbog.sgpi.model.Participantes;
import co.edu.usbbog.sgpi.model.ParticipantesPK;

public interface IParticipantesRepository extends JpaRepository<Participantes, ParticipantesPK>{
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE `sgpi_db`.`participantes` SET `fecha_fin` = ?3 WHERE (`usuario` = ?2) and (`proyecto` = ?1)", nativeQuery = true)
	void actualizarParticipante(int id, String cedula, LocalDate fechafin);

}

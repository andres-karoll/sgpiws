package co.edu.usbbog.sgpi.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import co.edu.usbbog.sgpi.model.Participantes;
import co.edu.usbbog.sgpi.model.ParticipantesPK;

public interface IPartisipacionesRepository extends JpaRepository<Participantes, ParticipantesPK>{

}

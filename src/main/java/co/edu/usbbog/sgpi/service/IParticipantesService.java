package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.Participantes;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.Usuario;

public interface IParticipantesService {
	public List<Participantes> todosLosParticipantes();
	public List<Participantes> todosLosParticipantesPorUsuario(Usuario usuario);
	public List<Participantes> todosLosParticipantesPorProyecto(Proyecto proyecto);
	public boolean eliminaParticipante(String id);
	public boolean crearParticipante(Participantes participante);
}

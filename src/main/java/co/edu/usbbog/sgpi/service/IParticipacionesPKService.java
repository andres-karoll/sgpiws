package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.Evento;
import co.edu.usbbog.sgpi.model.Participaciones;
import co.edu.usbbog.sgpi.model.Participantes;
import co.edu.usbbog.sgpi.model.Proyecto;

public interface IParticipacionesPKService {
	public List<Participaciones> todasLasParticipaciones();
	public List<Participaciones> todasLasParticipaciones(Evento evento);
	public List<Participaciones> todasLasParticipacionesPorProyecto(Proyecto proyecto);
	public boolean eliminaParticipacion();//que llave se usa para eliminar?
	public boolean crearParticion();//no se
}

package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.Evento;

public interface IEventoService {
	public List<Evento> todosLosEventos();
	public boolean eliminarEvento(int id);
	public boolean crearEvento(Evento evento);
}

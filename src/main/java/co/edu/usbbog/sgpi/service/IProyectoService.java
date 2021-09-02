package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.Proyecto;



public interface IProyectoService {
	public List<Proyecto> todosLosProyectos();
	
	public boolean eliminarProyecto(int id);
	public boolean crearProyecto(Proyecto proyecto);
}

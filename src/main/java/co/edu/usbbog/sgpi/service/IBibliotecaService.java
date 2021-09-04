package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.TipoProyecto;

public interface IBibliotecaService {
	public List<Proyecto> todosLosProyectos(TipoProyecto grado);
	public List<Proyecto> todosLosProyectosTerminados(TipoProyecto grado, String estado);
	
}

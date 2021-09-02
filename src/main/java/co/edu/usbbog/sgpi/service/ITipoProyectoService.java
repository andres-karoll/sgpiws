package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.TipoProyecto;



public interface ITipoProyectoService {
	public List<TipoProyecto> todosLosTipoProyecto();
	public boolean eliminarTipoProyecto(String nombre);
	public boolean crearTipoProyecto(TipoProyecto tipoProyecto);
}

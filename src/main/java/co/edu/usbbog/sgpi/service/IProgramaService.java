package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.Facultad;
import co.edu.usbbog.sgpi.model.Producto;
import co.edu.usbbog.sgpi.model.Programa;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.Usuario;

public interface IProgramaService {
	public List<Programa> todosLosProgramas();
	public List<Producto> todosLosProgramasPorFacultad(Facultad facultad);
	public List<Producto> todosLosProgramasPorDirector(Usuario usuario);
	public boolean crearPrograma(Programa programa);
}

package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.Clase;
import co.edu.usbbog.sgpi.model.Materia;
import co.edu.usbbog.sgpi.model.Usuario;

public interface IClaseService {
	public List<Clase> todasLasClases();
	public List<Clase> clasesPorProfesor(Usuario profesor);
	public List<Clase> clasesPorMateria(Materia materia);
	
	/*public boolean asignarProfesor(Usuario profesor);
	public boolean asignarMateria(Materia materia);*/
}

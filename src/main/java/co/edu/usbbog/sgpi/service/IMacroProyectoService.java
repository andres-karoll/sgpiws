package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.MacroProyecto;
import co.edu.usbbog.sgpi.model.Proyecto;

public interface IMacroProyectoService {
	public List<MacroProyecto> todosLosMacroproyectos();
	public List<MacroProyecto> todosLosMacroproyectosPorProyecto(Proyecto proyecto);
	public boolean eliminaMarcoProyecto(int id);
	public boolean crearMarcoProyecto(MacroProyecto macroProyecto);
}


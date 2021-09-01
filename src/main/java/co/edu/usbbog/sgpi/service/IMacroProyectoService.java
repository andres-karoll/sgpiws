package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.MacroProyecto;

public interface IMacroProyectoService {
	public List<MacroProyecto> todosLosMacroproyectos();
	public boolean eliminaMarcoProyecto(int id);
	public boolean crearMarcoProyecto(MacroProyecto macroProyecto);
}


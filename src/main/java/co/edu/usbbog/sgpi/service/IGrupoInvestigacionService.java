package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.Usuario;


public interface IGrupoInvestigacionService {
	public List<GrupoInvestigacion> todosLosGruposInvestigacion();
	public List<GrupoInvestigacion> todosLosGruposInvestigacionPorDirector(Usuario director);
	public boolean eliminarGrupoInvestigacion(int id);
	public boolean crearGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion);
}

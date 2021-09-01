package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.GrupoInvestigacion;


public interface IGrupoInvestigacionService {

	public List<GrupoInvestigacion> todosLosGruposInvestigacion();
	public boolean eliminarGrupoInvestigacion(int id);
	public boolean crearGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion);
}

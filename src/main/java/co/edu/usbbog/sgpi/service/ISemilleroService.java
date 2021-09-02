package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.LineaInvestigacion;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.Usuario;


public interface ISemilleroService {
	public List<Semillero> todosLosSemilleros();
	public List<Semillero> todosLosSemillerosPorGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion);
	public List<Semillero> todosLosSemillerosPorLiderSemillero(Usuario lider);
	public List<Semillero> todosLosSemillerosPorLineaInvestigacion(LineaInvestigacion lineaInvestigacion);
	public boolean eliminarSemillero(int id);
	public boolean crearSemillero(Semillero semillero);
}

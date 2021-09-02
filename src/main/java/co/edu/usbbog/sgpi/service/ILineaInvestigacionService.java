package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.LineaInvestigacion;
import co.edu.usbbog.sgpi.model.Semillero;



public interface ILineaInvestigacionService {

	public List<LineaInvestigacion> todasLasLineasInvestigacion();
	public List<LineaInvestigacion> todasLasLineasInvestigacionPorGrupo(GrupoInvestigacion grupoInvestigacion);
	public List<LineaInvestigacion> todasLasLineasInvestigacionPorSemillero(Semillero semillero);
	public boolean eliminarLineainvestigacion(String id);
	public boolean crearLineainvestigacion(LineaInvestigacion lineaInvestigacion);
}

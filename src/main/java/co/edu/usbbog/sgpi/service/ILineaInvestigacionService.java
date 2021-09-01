package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.LineaInvestigacion;



public interface ILineaInvestigacionService {

	public List<LineaInvestigacion> todasLasLineasInvestigacion();
	public boolean eliminarLineainvestigacion(String id);
	public boolean crearLineainvestigacion(LineaInvestigacion lineaInvestigacion);
}

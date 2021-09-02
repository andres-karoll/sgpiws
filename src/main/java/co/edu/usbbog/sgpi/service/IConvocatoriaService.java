package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.Convocatoria;
import co.edu.usbbog.sgpi.model.Proyecto;



public interface IConvocatoriaService {
	public List<Convocatoria> todasLasConvocatorias();
	public List<Convocatoria> todasLasConvocatoriasPorProyecto(Proyecto proyecto);
	public boolean eliminarConvocatoria(int id);
	public boolean crearConvocatoria(Convocatoria convocatoria);
}

package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.Convocatoria;



public interface IConvocatoriaService {
	public List<Convocatoria> todasLasConvocatorias();
	public boolean eliminarConvocatoria(int id);
	public boolean crearConvocatoria(Convocatoria convocatoria);
}

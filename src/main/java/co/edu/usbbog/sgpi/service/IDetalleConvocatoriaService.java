package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.Convocatoria;
import co.edu.usbbog.sgpi.model.DetalleConvocatoria;

public interface IDetalleConvocatoriaService {
	public List<DetalleConvocatoria> detalleConvocatoriaPorConvocatoria(Convocatoria convocatoria);
	public boolean eliminarDetalleConvocatoria(int id);
	public boolean crearDetalleConvocatoria(DetalleConvocatoria detalleConvocatoria);
}

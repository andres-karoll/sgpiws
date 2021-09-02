package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.AreaConocimiento;
import co.edu.usbbog.sgpi.model.Proyecto;

public interface IAreaConocimientoService {
	public List<AreaConocimiento> todasLasAreas();
		public List<AreaConocimiento> todasLasAreasPorProyecto(Proyecto proyecto);
}

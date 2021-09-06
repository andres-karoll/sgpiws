package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.AreaConocimiento;
import co.edu.usbbog.sgpi.model.LineaInvestigacion;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.TipoProyecto;

public interface IGestionFiltroProyectosService {
	
	public List<Proyecto> todosLosProyectos();
	//listar por lugar?????????
	public List<Proyecto> todosLosProyectosPorLugar();
	public List<Proyecto> todosLosProyectosPorTipoProyecto(TipoProyecto tipoProyecto);
	public List<Proyecto> todosLosProyectosPorAreaConocimiento(AreaConocimiento areaConocimiento);
	public List<Proyecto> todosLosProyectosPorNombre(String titulo);
	//El sistema deberá contar con un filtro que evidencie la línea de investigación a la cual se está aplicando el proyecto.
	public List<Proyecto> todosLosProyectosPorNombre(Semillero semillero, LineaInvestigacion lineainvestigacion);
}

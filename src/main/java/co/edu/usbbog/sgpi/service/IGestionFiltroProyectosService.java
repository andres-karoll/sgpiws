package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.AreaConocimiento;
import co.edu.usbbog.sgpi.model.LineaInvestigacion;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.TipoProyecto;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public interface IGestionFiltroProyectosService {
	
	public List<Proyecto> todosLosProyectos();
	public List<JSONObject> todosLosProyectosVisibles();
	//listar por lugar?????????
	public List<Proyecto> todosLosProyectosPorLugar();
	public List<Proyecto> todosLosProyectosPorTipoProyecto(String tipo_proyecto);
	public List<Proyecto> todosLosProyectosPorAreaConocimiento(int areaConocimiento);
	public List<Proyecto> todosLosProyectosPorNombre(String titulo);
	//El sistema deberá contar con un filtro que evidencie la línea de investigación a la cual se está aplicando el proyecto.
	public List<Proyecto> todosLosProyectosPorLinea(String lineainvestigacion);
	public List<TipoProyecto> todosLosTiposProyecto();
}

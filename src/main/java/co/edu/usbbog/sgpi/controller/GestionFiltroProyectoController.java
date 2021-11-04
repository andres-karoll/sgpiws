package co.edu.usbbog.sgpi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.TipoProyecto;
import co.edu.usbbog.sgpi.service.IGestionFiltroProyectosService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@RestController
@CrossOrigin
@RequestMapping("/gestionfiltroproyecto")
public class GestionFiltroProyectoController {

	@Autowired
	private IGestionFiltroProyectosService gestionFiltroProyectosService;
	
	@GetMapping(value = "/listarproyectos")
	public JSONArray listarProyectos() {
		
		JSONArray salida = new JSONArray(); 
		List<Proyecto> pro = gestionFiltroProyectosService.todosLosProyectos();
		for (Proyecto proyecto : pro) {
			salida.add(proyecto.toJson()) ;
		}
		return salida;	
	}
	
	@GetMapping(value = "/listarproyectosvisibles")
	public List<JSONObject> listarProyectosVisibles() {
		
		List<JSONObject> salida = gestionFiltroProyectosService.todosLosProyectosVisibles();

		return salida;
	}
	
	@GetMapping(value = "/listarproyectosportipo/{tipo_proyecto}")
	public JSONArray listarProyectosPorTipo(@PathVariable String tipo_proyecto) {		
		JSONArray salida = new JSONArray(); 
		List<Proyecto> proyectos = gestionFiltroProyectosService.todosLosProyectosPorTipoProyecto(tipo_proyecto);
		for (Proyecto proyecto : proyectos) {
			salida.add(proyecto.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/listarproyectosporarea/{area_conocimiento}")
	public JSONArray listarProyectosPorArea(@PathVariable int area_conocimiento) {		
		JSONArray salida = new JSONArray(); 
		List<Proyecto> proyectos = gestionFiltroProyectosService.todosLosProyectosPorAreaConocimiento(area_conocimiento);
		for (Proyecto proyecto : proyectos) {
			salida.add(proyecto.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/buscarportitulo/{titulo}")
	public JSONArray listarProyectosPorArea(@PathVariable String titulo) {		
		JSONArray salida = new JSONArray(); 
		List<Proyecto> proyectos = gestionFiltroProyectosService.todosLosProyectosPorNombre(titulo);
		for (Proyecto proyecto : proyectos) {
			salida.add(proyecto.toJson()) ;
		}
		return salida;		
	}
	@GetMapping(value = "/todoslostiposproyecto")
	public JSONArray todosLosTiposProyecto() {		
		JSONArray salida = new JSONArray(); 
		List<TipoProyecto> tipos = gestionFiltroProyectosService.todosLosTiposProyecto();
		for (TipoProyecto tipo : tipos) {
			salida.add(tipo.toJson()) ;
		}
		return salida;		
	}
}

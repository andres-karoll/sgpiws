package co.edu.usbbog.sgpi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbbog.sgpi.model.Facultad;
import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.Usuario;
import co.edu.usbbog.sgpi.service.IGestionInstitucionalService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import netscape.javascript.JSObject;


@RestController
@CrossOrigin
@RequestMapping("/gestionInstitucional")
public class GestionInstitucionalController {
	@Autowired
	private IGestionInstitucionalService gestionInstitucionalService;
	
	
	
	@GetMapping(value = "/listarGruposI")
	public JSONArray listarGruposI() {
		
		JSONArray salida = new JSONArray(); 
		List<GrupoInvestigacion> gru = gestionInstitucionalService.todosLosGruposInvestigacion();
		for (GrupoInvestigacion grupoInvestigacion : gru) {
			salida.add(grupoInvestigacion.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/eliminarGruposI/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarGruposI(@PathVariable int id) {		
		gestionInstitucionalService.eliminarGrupoInvestigacion(id);
		return "Eliminado";	
	}
	
	@PostMapping(value = "/crearGruposI")
	public JSONObject crearGruposI(@RequestBody GrupoInvestigacion grupoInvestigacion) {		
		gestionInstitucionalService.crearGrupoInvestigacion(grupoInvestigacion);	
		return new JSONObject();
	}
	
	/*@GetMapping(value = "/asignarDirectorGruposI/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String asignarDirector(@PathVariable int id) {
		gestionInstitucionalService.asignarDirector(null, id);
		return "director asignado";
	}*/
	/*
	@PostMapping(value = "/asignarDirectorGruposI")
	public JSONObject asignarDirector(@RequestBody JSONObject director, int id) {
		
		gestionInstitucionalService.asignarDirector(director, id);
		return new JSONObject();
	}*/
	
	
	@GetMapping(value = "/listarSemilleros")
	public JSONArray listarSemilleros() {
		
		JSONArray salida = new JSONArray(); 
		List<Semillero> semi = gestionInstitucionalService.todosLosSemilleros();
		for (Semillero semillero : semi) {
			salida.add(semillero.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/listarSemillerosPorGrupo/{grupo_investigacion}")
	public JSONArray listarSemillerosPorGrupo(@PathVariable int grupo_investigacion) {
		
		JSONArray salida = new JSONArray(); 
		List<Semillero> semi = gestionInstitucionalService.todosLosSemillerosPorGrupoInvestigacion(grupo_investigacion);
		for (Semillero semillero : semi) {
			salida.add(semillero.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/listarSemillerosPorLider/{lider_semillero}")
	public JSONArray listarSemillerosPorLider(@PathVariable String lider_semillero) {
		
		JSONArray salida = new JSONArray(); 
		List<Semillero> semi = gestionInstitucionalService.todosLosSemillerosPorLiderSemillero(lider_semillero);
		for (Semillero semillero : semi) {
			salida.add(semillero.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/listarSemillerosPorLinea/{linea_investigacion}")
	public JSONArray listarSemillerosPorLinea(@PathVariable String linea_investigacion) {
		
		JSONArray salida = new JSONArray(); 
		List<Semillero> semi = gestionInstitucionalService.todosLosSemillerosPorLineaInvestigacion(linea_investigacion);
		for (Semillero semillero : semi) {
			salida.add(semillero.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/eliminarSemi/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarSemillero(@PathVariable int id) {		
		gestionInstitucionalService.eliminarSemillero(id);
		return "Eliminado";
	}
	
	@PostMapping(value = "/crearSemilleros")
	public JSONObject crearSemilleros(@RequestBody Semillero semillero) {		
		gestionInstitucionalService.crearSemillero(semillero);	
		return new JSONObject();
	}
	/*
	@PostMapping(value = "/asignarSemillero")
	public JSONObject asignarLider(@RequestBody JSONObject lider, int id) {
		
		gestionInstitucionalService.asignarDirector(lider, id);
		return new JSONObject();
	}*/
	
	@GetMapping(value = "/listarFacultades")
	public JSONArray listarFacultades() {
		
		JSONArray salida = new JSONArray(); 
		List<Facultad> facul = gestionInstitucionalService.todasLasFacultades();
		for (Facultad facultad : facul) {
			salida.add(facultad.toJson()) ;
		}
		return salida;		
	}
	@GetMapping(value = "/eliminarFacultad/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarFacultad(@PathVariable int id) {		
		gestionInstitucionalService.eliminarFacultad(id);
		return "Eliminado";
	}
}

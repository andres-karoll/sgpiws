package co.edu.usbbog.sgpi.controller;

import java.time.LocalDate;
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
import co.edu.usbbog.sgpi.model.Programa;
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
	
	
	//GRUPOS DE INVESTIGACION
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
	public JSONObject crearGruposI(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		GrupoInvestigacion grupoInvestigacion = new GrupoInvestigacion(
				Integer.parseInt(entrada.getAsString("id")),
				entrada.getAsString("nombre"),
				LocalDate.parse(entrada.getAsString("fechaFun")) ,
				entrada.getAsString("categoria"),
				LocalDate.parse(entrada.getAsString("fechaCat")));
		//grupoInvestigacion.setDirectorGrupo(entrada.getAsString("director_grupo"));
		if(gestionInstitucionalService.crearGrupoInvestigacion(grupoInvestigacion, Integer.parseInt(entrada.getAsString("grupos_investigacion")))) {
			salida.put("respuesta", "el grupo se creo");
		}
		else {
			salida.put("respuesta", "no se pudo");
		}
		
			
		
		
		return salida;
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
	
	//SEMILLEROS
	
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
	
	
	//FACULTADES
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
	public boolean eliminarFacultad(@PathVariable int id) {		
		boolean salida = gestionInstitucionalService.eliminarFacultad(id);
		return salida;
	}
	@PostMapping(value = "/crearFacultad")
	public boolean crearFacultad(@RequestBody Facultad facultad) {		
		boolean salida = gestionInstitucionalService.crearFacultad(facultad);	
		return salida;
	}
	
	//ASIGNAR DECANO Y COORDINADOR INVITADO
	
	//PROGRAMAS
	@GetMapping(value = "/listarProgramas")
	public JSONArray listarProgramas() {
		
		JSONArray salida = new JSONArray(); 
		List<Programa> progra = gestionInstitucionalService.todosLosProgramas();
		for (Programa programa : progra) {
			salida.add(programa.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/listarProgramasPorFacultad/{facultad_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public JSONArray listarProgramasPorFacultad(@PathVariable int facultad_id) {
		
		JSONArray salida = new JSONArray(); 
		List<Programa> progra = gestionInstitucionalService.todosLosProgramasPorFacultad(facultad_id);
		for (Programa programa : progra) {
			salida.add(programa.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/listarProgramasPorDirector/{director}", produces = MediaType.APPLICATION_JSON_VALUE)
	public JSONArray listarProgramasPorDirector(@PathVariable int director) {
		JSONArray salida = new JSONArray(); 
		List<Programa> progra = gestionInstitucionalService.todosLosProgramasPorFacultad(director);
		for (Programa programa : progra) {
			salida.add(programa.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/eliminarPrograma/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean eliminarPrograma(@PathVariable int id) {		
		boolean salida = gestionInstitucionalService.eliminarPrograma(id);
		return salida;
	}
	
	@PostMapping(value = "/crearPrograma")
	public boolean crearPrograma(@RequestBody Programa programa) {		
		boolean salida = gestionInstitucionalService.crearPrograma(programa);	
		return salida;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Asignar cositas
	
	
	
	
	
	
}

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

import co.edu.usbbog.sgpi.model.Clase;
import co.edu.usbbog.sgpi.model.Convocatoria;
import co.edu.usbbog.sgpi.model.Facultad;
import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.Materia;
import co.edu.usbbog.sgpi.model.Programa;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.Usuario;
import co.edu.usbbog.sgpi.service.IGestionInstitucionalService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;


@RestController
@CrossOrigin
@RequestMapping("/gestioninstitucional")
public class GestionInstitucionalController {
	@Autowired
	private IGestionInstitucionalService gestionInstitucionalService;
	
	
	//GRUPOS DE INVESTIGACION
	@GetMapping(value = "/listargruposi")
	public JSONArray listarGruposI() {
		
		JSONArray salida = new JSONArray(); 
		List<GrupoInvestigacion> gru = gestionInstitucionalService.todosLosGruposInvestigacion();
		for (GrupoInvestigacion grupoInvestigacion : gru) {
			salida.add(grupoInvestigacion.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/eliminargruposi/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarGruposI(@PathVariable int id) {
		if(gestionInstitucionalService.eliminarGrupoInvestigacion(id)) {
			return "Se elimino con exito";
		}
		//gestionInstitucionalService.eliminarGrupoInvestigacion(id);
		return "Fallo la eliminacion";	
	}
	
	@PostMapping(value = "/creargruposi")
	public JSONObject crearGruposI(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		GrupoInvestigacion grupoInvestigacion = new GrupoInvestigacion(
				Integer.parseInt(entrada.getAsString("id")),
				entrada.getAsString("nombre"),
				LocalDate.parse(entrada.getAsString("fechaFun")) ,
				entrada.getAsString("categoria"),
				LocalDate.parse(entrada.getAsString("fechaCat")));
		if(gestionInstitucionalService.crearGrupoInvestigacion(grupoInvestigacion)) {
			salida.put("respuesta", "el grupo se creo");
		}
		else {
			salida.put("respuesta", "el grupo ya existe");
		}
		return salida;
	}
	
	@PostMapping(value = "/asignarprogramaagrupo")
	public JSONObject asignarFacultadAGrupo(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if(gestionInstitucionalService.asignarProgramaAGrupoInvestigacion(Integer.parseInt(entrada.getAsString("programa")), Integer.parseInt(entrada.getAsString("grupo_investigacion")))) {
			salida.put("respuesta", "se asigno la facultad correctamente");
		}
		else {
			salida.put("respuesta", "no se pudo asignar la facultad");
		}
		return salida;
	}
	
	@PostMapping(value = "/desasignarprogramaagrupo")
	public JSONObject desasignarFacultadAGrupo(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if(gestionInstitucionalService.desasignarProgramaAGrupoInvestigacion(Integer.parseInt(entrada.getAsString("programa")), Integer.parseInt(entrada.getAsString("grupo_investigacion")))) {
			salida.put("respuesta", "se desasigno el grupo correctamente");
		}
		else {
			salida.put("respuesta", "no se pudo desasignar grupo");
		}
		return salida;
	}
	
	@GetMapping(value = "/listarprogramadelgrupo/{grupo_investigacion}")
	public JSONArray listarProgramaDelGrupo(@PathVariable int grupo_investigacion) {		
		JSONArray salida = new JSONArray(); 
		List<JSONObject> grupo = gestionInstitucionalService.programaDelGrupo(grupo_investigacion);
		salida.add(grupo);
		return salida;		
	}
	
	@PostMapping(value = "/asignarlineaagrupo")
	public JSONObject asignarLineaAGrupo(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if(gestionInstitucionalService.asignarLineaAGrupoInvestigacion(entrada.getAsString("linea_investigacion"), Integer.parseInt(entrada.getAsString("grupo_investigacion")))) {
			salida.put("respuesta", "se asigno la facultad correctamente");
		}
		else {
			salida.put("respuesta", "no se pudo asignar la facultad");
		}
		return salida;
	}
	
	@PostMapping(value = "/desasignarlineaagrupo")
	public JSONObject desasignarLineaAGrupo(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if(gestionInstitucionalService.desasignarLineaAGrupoInvestigacion(entrada.getAsString("linea_investigacion"), Integer.parseInt(entrada.getAsString("grupo_investigacion")))) {
			salida.put("respuesta", "se desasigno la linea correctamente");
		}
		else {
			salida.put("respuesta", "no se pudo desasignar la linea");
		}
		return salida;
	}
	
	@GetMapping(value = "/listarlineasdelgrupo/{grupo_investigacion}")
	public JSONArray listarLineasDelGrupo(@PathVariable int grupo_investigacion) {		
		JSONArray salida = new JSONArray(); 
		List<JSONObject> grupo = gestionInstitucionalService.lineaDelGrupo(grupo_investigacion);
		salida.add(grupo);
		return salida;		
	}
	
	//SEMILLEROS
	
	@GetMapping(value = "/listarsemilleros")
	public JSONArray listarSemilleros() {
		
		JSONArray salida = new JSONArray(); 
		List<Semillero> semi = gestionInstitucionalService.todosLosSemilleros();
		for (Semillero semillero : semi) {
			salida.add(semillero.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/listarusuariosdelsemillero/{semillero_id}")
	public JSONArray listarUsuariosDelSemillero(@PathVariable int semillero_id) {
		
		JSONArray salida = new JSONArray(); 
		List<Usuario> usu = gestionInstitucionalService.usuariosPorSemillero(semillero_id);
		for (Usuario usuario : usu) {
			salida.add(usuario.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/listarproyectosdelsemillero/{semillero}")
	public JSONArray listarproyectosDelSemillero(@PathVariable int semillero) {
		
		JSONArray salida = new JSONArray(); 
		List<Proyecto> pro = gestionInstitucionalService.proyectosPorSemillero(semillero);
		for (Proyecto proyecto : pro) {
			salida.add(proyecto.toJson()) ;
		}
		System.out.println();
		System.out.println(salida);
		return salida;		
	}
	@GetMapping(value = "/listarsemillerosporgrupo/{grupo_investigacion}")
	public JSONArray listarSemillerosPorGrupo(@PathVariable int grupo_investigacion) {
		
		JSONArray salida = new JSONArray(); 
		List<Semillero> semi = gestionInstitucionalService.todosLosSemillerosPorGrupoInvestigacion(grupo_investigacion);
		for (Semillero semillero : semi) {
			salida.add(semillero.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/listarsemillerosporlider/{lider_semillero}")
	public JSONArray listarSemillerosPorLider(@PathVariable String lider_semillero) {
		
		JSONArray salida = new JSONArray(); 
		List<Semillero> semi = gestionInstitucionalService.todosLosSemillerosPorLiderSemillero(lider_semillero);
		for (Semillero semillero : semi) {
			salida.add(semillero.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/listarsemillerosporlinea/{linea_investigacion}")
	public JSONArray listarSemillerosPorLinea(@PathVariable String linea_investigacion) {
		
		JSONArray salida = new JSONArray(); 
		List<Semillero> semi = gestionInstitucionalService.todosLosSemillerosPorLineaInvestigacion(linea_investigacion);
		for (Semillero semillero : semi) {
			salida.add(semillero.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/eliminarsemillero/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarSemillero(@PathVariable int id) {	
		if(gestionInstitucionalService.eliminarSemillero(id)) {
			return "Eliminado";
		}
		
		return "No se puede eliminar";
	}
	
	@PostMapping(value = "/crearsemilleros")
	public JSONObject crearSemilleros(@RequestBody Semillero semillero) {
		JSONObject salida = new JSONObject();
		if(gestionInstitucionalService.crearSemillero(semillero)) {
			salida.put("respuesta", "se creo el semillero correctamente");
			
		}
		salida.put("respuesta", "NO se creo el semillero correctamente");
		return salida;
	}
	
	@PostMapping(value = "/asignarsemilleroaprograma")
	public JSONObject asignarSemilleroAPrograma(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if(gestionInstitucionalService.asignarSemilleroAPrograma(Integer.parseInt(entrada.getAsString("programa")), Integer.parseInt(entrada.getAsString("semillero")))) {
			salida.put("respuesta", "se asigno la facultad correctamente");
		}
		else {
			salida.put("respuesta", "no se pudo asignar la facultad");
		}
		return salida;
	}
	
	@GetMapping(value = "/listarelprogramadelsemillero/{semillero}")
	public JSONArray listarElProgramaDelSemillero(@PathVariable int semillero) {
		
		JSONArray salida = new JSONArray(); 
		List<JSONObject> programa = gestionInstitucionalService.programaDelSemillero(semillero);
		salida.add(programa);
		return salida;	
	}
	
	@PostMapping(value = "/desasignarsemilleroaprograma")
	public JSONObject desasignarSemilleroAPrograma(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if(gestionInstitucionalService.desasignarSemilleroAPrograma(Integer.parseInt(entrada.getAsString("programa")), Integer.parseInt(entrada.getAsString("semillero")))) {
			salida.put("respuesta", "se desasigno correctamente");
		}
		else {
			salida.put("respuesta", "no se pudo desasignar");
		}
		return salida;
	}
	//FACULTADES
	@GetMapping(value = "/listarfacultades")
	public JSONArray listarFacultades() {
		
		JSONArray salida = new JSONArray(); 
		List<Facultad> facul = gestionInstitucionalService.todasLasFacultades();
		for (Facultad facultad : facul) {
			salida.add(facultad.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/eliminarfacultad/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarFacultad(@PathVariable int id) {		
		
		if(gestionInstitucionalService.eliminarFacultad(id)) {
			return "Eliminado";
		}	
		return "No se puede eliminar";
	}
	
	@PostMapping(value = "/crearfacultad")
	public boolean crearFacultad(@RequestBody Facultad facultad) {		
		boolean salida = gestionInstitucionalService.crearFacultad(facultad);	
		return salida;
	}
	
	//PROGRAMAS
	@GetMapping(value = "/listarprogramas")
	public JSONArray listarProgramas() {
		
		JSONArray salida = new JSONArray(); 
		List<Programa> progra = gestionInstitucionalService.todosLosProgramas();
		for (Programa programa : progra) {
			salida.add(programa.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/listarprogramasporfacultad/{facultad_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public JSONArray listarProgramasPorFacultad(@PathVariable int facultad_id) {
		
		JSONArray salida = new JSONArray(); 
		List<Programa> progra = gestionInstitucionalService.todosLosProgramasPorFacultad(facultad_id);
		for (Programa programa : progra) {
			salida.add(programa.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/listarprogramaspordirector/{director}", produces = MediaType.APPLICATION_JSON_VALUE)
	public JSONArray listarProgramasPorDirector(@PathVariable int director) {
		JSONArray salida = new JSONArray(); 
		List<Programa> progra = gestionInstitucionalService.todosLosProgramasPorFacultad(director);
		for (Programa programa : progra) {
			salida.add(programa.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/eliminarprograma/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarPrograma(@PathVariable int id) {		
	
		if(gestionInstitucionalService.eliminarPrograma(id)) {
			return"eliminado con Exito";			
		}
		return "no se pudo eliminar";
	}
	
	@PostMapping(value = "/crearprograma")
	public JSONObject crearPrograma(@RequestBody JSONObject entrada) {		

		JSONObject salida = new JSONObject();
		Programa programa =  new Programa(
				
				Integer.parseInt(entrada.getAsString("id")), 
				entrada.getAsString("nombre"));
		System.out.println(programa);
		System.out.println(entrada);
		if (gestionInstitucionalService.crearPrograma(programa, Integer.parseInt( entrada.getAsString("facultad_id")))) {

			salida.put("respuesta", "se creo el programa");

		} else {
			salida.put("respuesta", "no se pudo el programa");
		}

		return salida;
	}
	
	@GetMapping(value = "/listargruposdelprograma/{programa}")
	public JSONArray listargruposdelprograma(@PathVariable int programa) {	
		JSONArray salida = new JSONArray(); 
		List<JSONObject> grupos = gestionInstitucionalService.gruposDelPrograma(programa);
		salida.add(grupos);
		return salida;	
	}
	
	@GetMapping(value = "/listarsemillerosdelprograma/{programa}")
	public JSONArray listarsemillerosdelprograma(@PathVariable int programa) {	
		JSONArray salida = new JSONArray(); 
		List<JSONObject> grupos = gestionInstitucionalService.semillerosDelPrograma(programa);
		salida.add(grupos);
		return salida;	
	}
	
	
	//MATERIAS
	
	
	@GetMapping(value = "/listarmaterias")
	public JSONArray listarMaterias() {
		JSONArray salida = new JSONArray(); 
		List<Materia> mate = gestionInstitucionalService.todasLasMaterias();
		for (Materia materia : mate) {
			salida.add(materia.toJson());
		}
		return salida;		
	}
	
	@GetMapping(value = "/listarprogramadelamateria/{programa}")
	public JSONArray listarProgramaDeLaMateria(@PathVariable int programa) {	
		JSONArray salida = new JSONArray(); 
		List<Materia> mate = gestionInstitucionalService.todasLasMateriasPorPrograma(programa);
		for (Materia materia : mate) {
			salida.add(materia.toJson()) ;
		}
		return salida;	
	}
	
	@GetMapping(value = "/eliminarmateria/{materia}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarMateria(@PathVariable int materia) {		
		if(gestionInstitucionalService.eliminarMateria(String.valueOf(materia))) {
			return"eliminado con Exito";			
		}
		return "no se pudo eliminar";
	}
	
	@PostMapping(value = "/crearmateria")
	public boolean crearMateria(@RequestBody Materia materia) {		
		boolean salida = gestionInstitucionalService.crearMateria(materia);	
		return salida;
	}
	
	//CLASES
	
	@GetMapping(value = "/listarclases")
	public JSONArray listarClases() {

		JSONArray salida = new JSONArray(); 
		List<Clase> cla = gestionInstitucionalService.todasLasClases();
		System.out.println(gestionInstitucionalService.todasLasClases());
		
		for(Clase clase : cla) {
			salida.add(clase.toJson());
		}	
		return salida;		
	}
	
	@GetMapping(value = "/listarclasesporprofesor/{profesor}", produces = MediaType.APPLICATION_JSON_VALUE)
	public JSONArray listarProgramasPorDirector(@PathVariable String profesor) {
		JSONArray salida = new JSONArray(); 
		List<Clase> cla = gestionInstitucionalService.clasesPorProfesor(profesor);
		for (Clase clase : cla) {
			salida.add(clase.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/listarclasespormateria/{materia}", produces = MediaType.APPLICATION_JSON_VALUE)
	public JSONArray listarProgramasPorMateria(@PathVariable String materia) {
		JSONArray salida = new JSONArray(); 
		List<Clase> cla = gestionInstitucionalService.clasesPorMateria(materia);
		for (Clase clase : cla) {
			salida.add(clase.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/eliminarclase/{clase}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarClase(@PathVariable int clase) {		
		if(gestionInstitucionalService.eliminarClase(clase)) {
			return"eliminado con Exito";			
		}
		return "no se pudo eliminar";
	}
	
	@PostMapping(value = "/crearclase")
	public JSONObject crearClase(@RequestBody JSONObject entrada) {		
		
		//boolean salida = gestionInstitucionalService.crearPrograma(programa);	
		//return salida;
		JSONObject salida = new JSONObject();
		Clase clase =  new Clase(				
				Integer.parseInt(entrada.getAsString("numero")), 
				entrada.getAsString("nombre"),
				entrada.getAsString("semestre"));

		if (gestionInstitucionalService.crearClase(clase,entrada.getAsString("materia"))) {

			salida.put("respuesta", "se creo la clase");

		} else {
			salida.put("respuesta", "no se pudo crear");
		}

		return salida;
	}
	
	@PostMapping(value = "/asignarproyectosaclase")
	public JSONObject asignarProyectosAClase(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if(gestionInstitucionalService.asignarProyectosAClase(Integer.parseInt(entrada.getAsString("proyecto")), Integer.parseInt(entrada.getAsString("clase")))) {
			salida.put("respuesta", "se asigno el proyecto correctamente");
		}
		else {
			salida.put("respuesta", "no se pudo asignar el proyecto");
		}
		return salida;
	}
	
	@GetMapping(value = "/listarlosproyectosdeclase/{clase}")
	public JSONArray listarLosProyectosDeClase(@PathVariable int clase) {
		
		JSONArray salida = new JSONArray(); 
		List<JSONObject> clas = gestionInstitucionalService.proyectosPorClase(clase);
		salida.add(clas);
		return salida;	
	}
	
	@PostMapping(value = "/desasignarproyectodeclase")
	public JSONObject desasignarProyectoDeClase(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if(gestionInstitucionalService.desasignarProyectosAClase(Integer.parseInt(entrada.getAsString("proyecto")), Integer.parseInt(entrada.getAsString("clase")))) {
			salida.put("respuesta", "se desasigno el proyecto");
		}
		else {
			salida.put("respuesta", "no se pudo desasignar el proyecto");
		}
		return salida;
	}
	
	@GetMapping(value = "/convocatoriasestado/{estado}")
	public JSONArray listarLosProyectosDeClase(@PathVariable String estado) {
		
		JSONArray salida = new JSONArray(); 
		List<Convocatoria> con = gestionInstitucionalService.todasLasConvocatoriasAbiertas(estado);
		for (Convocatoria convocatoria : con) {
			salida.add(convocatoria.toJson());
		}
		return salida;
	}


}
	


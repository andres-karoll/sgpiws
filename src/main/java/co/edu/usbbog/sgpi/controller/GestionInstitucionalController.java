package co.edu.usbbog.sgpi.controller;

import java.time.LocalDate;

import java.util.Date;

import java.util.Iterator;
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

import co.edu.usbbog.sgpi.model.AreaConocimiento;
import co.edu.usbbog.sgpi.model.Clase;
import co.edu.usbbog.sgpi.model.Convocatoria;
import co.edu.usbbog.sgpi.model.Evento;
import co.edu.usbbog.sgpi.model.Facultad;
import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.LineaInvestigacion;
import co.edu.usbbog.sgpi.model.Materia;
import co.edu.usbbog.sgpi.model.Programa;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.ProyectosConvocatoria;
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
				
				entrada.getAsString("nombre"),
				LocalDate.parse(entrada.getAsString("fechaFun")) ,
				entrada.getAsString("categoria"),
				LocalDate.parse(entrada.getAsString("fechaCat")));

		System.out.println(entrada);
		if(gestionInstitucionalService.crearGrupoInvestigacion(grupoInvestigacion, entrada.getAsString("director")) == "se creo el grupo") {

			salida.put("respuesta", "el grupo se creo");
		}
		else if (gestionInstitucionalService.crearGrupoInvestigacion(grupoInvestigacion, entrada.getAsString("director")) == "el usuario no existe"){
			salida.put("respuesta", "el grupo no se creo porque el usuario no existe");
		}
		
		else if (gestionInstitucionalService.crearGrupoInvestigacion(grupoInvestigacion, entrada.getAsString("director")) == "usuario invalido 1"){
			salida.put("respuesta", "el grupo no se creo porque el usuario que escogio es un estudiante inactivo");
		}
		else if (gestionInstitucionalService.crearGrupoInvestigacion(grupoInvestigacion, entrada.getAsString("director")) == "usuario invalido 2"){
			salida.put("respuesta", "el grupo no se creo porque el usuario que escogio es un estudiante activo");
		}
		else if (gestionInstitucionalService.crearGrupoInvestigacion(grupoInvestigacion, entrada.getAsString("director")) == "usuario invalido 7"){
			salida.put("respuesta", "el grupo no se creo porque el usuario que escogio es un Semillerista");
		}
		else if (gestionInstitucionalService.crearGrupoInvestigacion(grupoInvestigacion, entrada.getAsString("director")) == "este usuario ya es lider de grupo"){
			salida.put("respuesta", "este usuario ya es lider de grupo");
		}
		
		
		return salida;
	}
	
	@GetMapping(value = "/grupolistarporid/{id}")
	public JSONObject grupolistarPorId(@PathVariable int id) {
		JSONObject x= new JSONObject();
		if(gestionInstitucionalService.grupoiporid(id) !=null) {
			GrupoInvestigacion gru = gestionInstitucionalService.grupoiporid(id);
			return gru.toJson();
		}
		else {

			return x;
		}	
	}
	
	@PostMapping("/modificargrupoi")
	public JSONObject modificarGrupo(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if(gestionInstitucionalService.modificarGrupoI(Integer.parseInt(entrada.getAsString("id"))
				,entrada.getAsString("nombre"),entrada.getAsString("fechaFun")
				, entrada.getAsString("categoria")
				, entrada.getAsString("fechaCat")
				, entrada.getAsString("director"))=="grupo actualizado") {
			salida.put("respuesta", "el grupo fue actualizado");
		}else if(gestionInstitucionalService.modificarGrupoI(Integer.parseInt(entrada.getAsString("id"))
				,entrada.getAsString("nombre"),entrada.getAsString("fechaFun")
				, entrada.getAsString("categoria")
				, entrada.getAsString("fechaCat")
				, entrada.getAsString("director"))=="usuario invalido 1") {
			salida.put("respuesta", "el grupo no se creo porque el usuario que escogio es un estudiante inactivo");
		}else if(gestionInstitucionalService.modificarGrupoI(Integer.parseInt(entrada.getAsString("id"))
				,entrada.getAsString("nombre"),entrada.getAsString("fechaFun")
				, entrada.getAsString("categoria")
				, entrada.getAsString("fechaCat")
				, entrada.getAsString("director"))=="usuario invalido 2") {
			salida.put("respuesta", "el grupo no se creo porque el usuario que escogio es un estudiante activo");
		}else if(gestionInstitucionalService.modificarGrupoI(Integer.parseInt(entrada.getAsString("id"))
				,entrada.getAsString("nombre"),entrada.getAsString("fechaFun")
				, entrada.getAsString("categoria")
				, entrada.getAsString("fechaCat")
				, entrada.getAsString("director"))=="usuario invalido 3") {
			salida.put("respuesta", "el grupo no se creo porque el usuario que escogio es un Semillerista");
		}else if(gestionInstitucionalService.modificarGrupoI(Integer.parseInt(entrada.getAsString("id"))
				,entrada.getAsString("nombre"),entrada.getAsString("fechaFun")
				, entrada.getAsString("categoria")
				, entrada.getAsString("fechaCat")
				, entrada.getAsString("director"))=="este usuario ya es lider de grupo") {
			salida.put("respuesta", "este usuario ya es lider de grupo");
		}else if(gestionInstitucionalService.modificarGrupoI(Integer.parseInt(entrada.getAsString("id"))
				,entrada.getAsString("nombre"),entrada.getAsString("fechaFun")
				, entrada.getAsString("categoria")
				, entrada.getAsString("fechaCat")
				, entrada.getAsString("director"))=="el usuario ingresado no existe") {
			salida.put("respuesta", "el usuario ingresado no existe");
		}
		else {
			salida.put("respuesta", "el grupo no fue actualizado");
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
		List<Programa> grupo = gestionInstitucionalService.programaDelGrupo(grupo_investigacion);
		for (Iterator iterator = grupo.iterator(); iterator.hasNext();) {
			Programa programa = (Programa) iterator.next();
			salida.add(programa.toJson());
		}
		//salida.add(grupo);
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
		List<LineaInvestigacion> linea = gestionInstitucionalService.lineaDelGrupo(grupo_investigacion);
		for (Iterator iterator = linea.iterator(); iterator.hasNext();) {
			
			LineaInvestigacion lineaInvestigacion = (LineaInvestigacion) iterator.next();
			salida.add(lineaInvestigacion.toJson());
		}
		//salida.add(grupo);
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
		for (Iterator iterator = usu.iterator(); iterator.hasNext();) {
			Usuario usuario = (Usuario) iterator.next();
			salida.add(usuario.toJson());
		}
		return salida;		
	}
	
	@GetMapping(value = "/listarproyectosdelsemillero/{semillero}")
	public JSONArray listarproyectosDelSemillero(@PathVariable int semillero) {
		
		JSONArray salida = new JSONArray(); 
		List<Proyecto> pro = gestionInstitucionalService.proyectosPorSemillero(semillero);
		for (Iterator iterator = pro.iterator(); iterator.hasNext();) {
			Proyecto proyecto = (Proyecto) iterator.next();
			salida.add(proyecto.toJson());
		}
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
	public JSONObject crearSemilleros(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		Semillero semillero = new Semillero(
				entrada.getAsString("nombre"),
				entrada.getAsString("descripcion"), LocalDate.parse( entrada.getAsString("fechaFun")));
		
		if(gestionInstitucionalService.crearSemillero(semillero, Integer.parseInt(entrada.getAsString("grupoInvestigacion")),entrada.getAsString("liderSemillero"), entrada.getAsString("lineaInvestigacion"))== "se creo el semillero") {
			salida.put("respuesta", "se creo el semillero correctamente");
			
		}
		else if (gestionInstitucionalService.crearSemillero(semillero, Integer.parseInt(entrada.getAsString("grupoInvestigacion")),entrada.getAsString("liderSemillero"), entrada.getAsString("lineaInvestigacion")) == "el usuario no existe"){
			salida.put("respuesta", "el semillero no se creo porque el usuario no existe");
		}
		else if (gestionInstitucionalService.crearSemillero(semillero, Integer.parseInt(entrada.getAsString("grupoInvestigacion")),entrada.getAsString("liderSemillero"), entrada.getAsString("lineaInvestigacion")) == "el grupo no existe"){
			salida.put("respuesta", "el semillero no se creo porque el grupo no existe");
		}
		else if (gestionInstitucionalService.crearSemillero(semillero, Integer.parseInt(entrada.getAsString("grupoInvestigacion")),entrada.getAsString("liderSemillero"), entrada.getAsString("lineaInvestigacion")) == "la linea no existe"){
			salida.put("respuesta", "el semillero no se creo porque la linea no existe");
		}
		else if (gestionInstitucionalService.crearSemillero(semillero, Integer.parseInt(entrada.getAsString("grupoInvestigacion")),entrada.getAsString("liderSemillero"), entrada.getAsString("lineaInvestigacion")) == "usuario invalido 1"){
			salida.put("respuesta", "el semillero no se creo porque el usuario es un estudiante inactivo");
		}
		else if (gestionInstitucionalService.crearSemillero(semillero, Integer.parseInt(entrada.getAsString("grupoInvestigacion")),entrada.getAsString("liderSemillero"), entrada.getAsString("lineaInvestigacion")) == "usuario invalido 2"){
			salida.put("respuesta", "el semillero no se creo porque el usuario es un estudiante activo");
		}
		else if (gestionInstitucionalService.crearSemillero(semillero, Integer.parseInt(entrada.getAsString("grupoInvestigacion")),entrada.getAsString("liderSemillero"), entrada.getAsString("lineaInvestigacion")) == "usuario invalido 3"){
			salida.put("respuesta", "el semillero no se creo porque el usuario es un semillerista");
		}
		else if (gestionInstitucionalService.crearSemillero(semillero, Integer.parseInt(entrada.getAsString("grupoInvestigacion")),entrada.getAsString("liderSemillero"), entrada.getAsString("lineaInvestigacion")) == "este usuario ya es lider de semillero"){
			salida.put("respuesta", "el semillero no se creo porque el usuario escogido ya es lider de semillero");
		}
		return salida;
	}
	
	@PostMapping("/modificarsemillero")
	public JSONObject modificarSemillero(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if(gestionInstitucionalService.modificarSemillero(Integer.parseInt(entrada.getAsString("id"))
				,entrada.getAsString("nombre")
				,entrada.getAsString("descripcion")
				,entrada.getAsString("fechaFun")
				,entrada.getAsString("grupoInvestigacion")
				,entrada.getAsString("lineaInvestigacion")
				,entrada.getAsString("liderSemillero"))=="semillero actualizado") {
			salida.put("respuesta", "el semillero fue actualizado");
		}else if(gestionInstitucionalService.modificarSemillero(Integer.parseInt(entrada.getAsString("id"))
				,entrada.getAsString("nombre")
				,entrada.getAsString("descripcion")
				,entrada.getAsString("fechaFun")
				,entrada.getAsString("grupoInvestigacion")
				,entrada.getAsString("lineaInvestigacion")
				,entrada.getAsString("liderSemillero"))=="usuario invalido 1") {
			salida.put("respuesta", "el semillero no se creo porque el usuario es un estudiante inactivo");
		}else if(gestionInstitucionalService.modificarSemillero(Integer.parseInt(entrada.getAsString("id"))
				,entrada.getAsString("nombre")
				,entrada.getAsString("descripcion")
				,entrada.getAsString("fechaFun")
				,entrada.getAsString("grupoInvestigacion")
				,entrada.getAsString("lineaInvestigacion")
				,entrada.getAsString("liderSemillero"))=="usuario invalido 2") {
			salida.put("respuesta", "el semillero no se creo porque el usuario es un estudiante activo");
		}else if(gestionInstitucionalService.modificarSemillero(Integer.parseInt(entrada.getAsString("id"))
				,entrada.getAsString("nombre")
				,entrada.getAsString("descripcion")
				,entrada.getAsString("fechaFun")
				,entrada.getAsString("grupoInvestigacion")
				,entrada.getAsString("lineaInvestigacion")
				,entrada.getAsString("liderSemillero"))=="usuario invalido 3") {
			salida.put("respuesta", "el semillero no se creo porque el usuario es un semillerista");
		}else if(gestionInstitucionalService.modificarSemillero(Integer.parseInt(entrada.getAsString("id"))
				,entrada.getAsString("nombre")
				,entrada.getAsString("descripcion")
				,entrada.getAsString("fechaFun")
				,entrada.getAsString("grupoInvestigacion")
				,entrada.getAsString("lineaInvestigacion")
				,entrada.getAsString("liderSemillero"))=="este usuario ya es lider de semillero") {
			salida.put("respuesta", "el semillero no se creo porque el usuario ya es lider de semillero");
		}else {
			salida.put("respuesta", "el semillero no fue actualizado");
		}
		return salida;
	}
	
	@GetMapping(value = "/semilleroid/{id}")
	public JSONObject semillerolistarPorId(@PathVariable int id) {
		JSONObject x= new JSONObject();
		if(gestionInstitucionalService.semilleroporid(id) !=null) {
			Semillero semillero = gestionInstitucionalService.semilleroporid(id);
			return semillero.toJson();
		}
		else {
			return x;
		}	
	}
	
	@PostMapping(value = "/asignarsemilleroaprograma")
	public JSONObject asignarSemilleroAPrograma(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if(gestionInstitucionalService.asignarSemilleroAPrograma(Integer.parseInt(entrada.getAsString("programa")), Integer.parseInt(entrada.getAsString("semillero")))==true) {
			salida.put("respuesta", "se asigno la programa correctamente");
		}
		else {
			salida.put("respuesta", "no se pudo asignar la facultad");
		}
		return salida;
	}
	
	@GetMapping(value = "/listarprogramadelsemillero/{semillero}")
	public JSONArray listarElProgramaDelSemillero(@PathVariable int semillero) {
		
		JSONArray salida = new JSONArray();
		List<Programa> pro = gestionInstitucionalService.programaDelSemillero(semillero);
		for (Iterator iterator = pro.iterator(); iterator.hasNext();) {
			Programa programa = (Programa) iterator.next();
			salida.add(programa.toJson());
		}
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
	
	
	@PostMapping("/asignarsemillero")
	public JSONObject asignarsemillero(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if (gestionInstitucionalService.asignarSemilleroAUsuario(entrada.getAsString("cedula"), Integer.parseInt(entrada.getAsString("semillero")))) {
			salida.put("respuesta", "el usuario fue asignado exitosamente");
		}else {
			salida.put("respuesta", "el usuario fue no asignado ");
		}
		return salida;
	}
	
	@PostMapping("/desasignarsemillero")
	public JSONObject desasignarsemillero(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if (gestionInstitucionalService.desasignarSemilleroAUsuario(entrada.getAsString("cedula"))) {
			salida.put("respuesta", "el usuario fue asignado exitosamente");
		}else {
			salida.put("respuesta", "el usuario fue no asignado ");
		}
		return salida;
	}
	
	@GetMapping(value = "/contarsemilleros")
	public List<JSONObject> contarSemilleros() {

		List<JSONObject> salida = gestionInstitucionalService.contarSemilleros(); 
		return salida;		
	}
	//FACULTADES
	@GetMapping(value = "/listarfacultades")
	public JSONArray listarFacultades() {
		JSONArray salida = new JSONArray(); 
		List<Facultad> facul = gestionInstitucionalService.todasLasFacultades();
		for (Iterator<Facultad> iterator = facul.iterator(); iterator.hasNext();) {
			Facultad facultad = (Facultad) iterator.next();
			salida.add(facultad.toJson());
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
	

	
	@PostMapping( "/crearfacultad")
	public JSONObject crearFacultad(@RequestBody JSONObject entrada) {		
		JSONObject salida = new JSONObject();
		Facultad facultad =  new Facultad(

				entrada.getAsString("nombre")
				);

		if (gestionInstitucionalService.crearFacultad(facultad,entrada.getAsString("coordinador"),entrada.getAsString("decano"))=="se creo la facultad") {
			salida.put("respuesta", "se creo la facultad de manera exitosa");
		} 
		
		else if (gestionInstitucionalService.crearFacultad(facultad,entrada.getAsString("coordinador"),entrada.getAsString("decano"))=="usuario invalido 1"){
			salida.put("respuesta", "la falcultad no fue creada el primer usuario es un estudiante inactivo");
		} else if (gestionInstitucionalService.crearFacultad(facultad,entrada.getAsString("coordinador"),entrada.getAsString("decano"))=="usuario invalido 2"){
			salida.put("respuesta", "la falcultad no fue creada el primer usuario es un estudiante activo");
		} else if (gestionInstitucionalService.crearFacultad(facultad,entrada.getAsString("coordinador"),entrada.getAsString("decano"))=="usuario invalido 3"){
			salida.put("respuesta", "la falcultad no fue creada el primer usuario es un semillerista");
		} else if (gestionInstitucionalService.crearFacultad(facultad,entrada.getAsString("coordinador"),entrada.getAsString("decano"))=="este usuario ya es Lider investigacion facultad"){
			salida.put("respuesta", "la falcultad no fue creada el primer usuario ya es Lider investigacion facultad");
		}
		
		else if (gestionInstitucionalService.crearFacultad(facultad,entrada.getAsString("coordinador"),entrada.getAsString("decano"))=="usuario invalido 4"){
			salida.put("respuesta", "la falcultad no fue creada el segundo usuario es un estudiante inactivo");
		} else if (gestionInstitucionalService.crearFacultad(facultad,entrada.getAsString("coordinador"),entrada.getAsString("decano"))=="usuario invalido 5"){
			salida.put("respuesta", "la falcultad no fue creada el segundo usuario es un estudiante activo");
		} else if (gestionInstitucionalService.crearFacultad(facultad,entrada.getAsString("coordinador"),entrada.getAsString("decano"))=="usuario invalido 6"){
			salida.put("respuesta", "la falcultad no fue creada el segundo usuario es un semillerista");
		} else if (gestionInstitucionalService.crearFacultad(facultad,entrada.getAsString("coordinador"),entrada.getAsString("decano"))=="este usuario ya es Coordinador investigacion facultad"){
			salida.put("respuesta", "la falcultad no fue creada el segundo ya es Coordinador investigacion facultad");
		}	
		
		return salida;
	}
	
	
	@PostMapping("/modificarfacultad")
	public JSONObject modificarFacultad(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if(gestionInstitucionalService.modificarFacultad(Integer.parseInt(entrada.getAsString("id"))
				, entrada.getAsString("nombre")
				, entrada.getAsString("coordinador")
				, entrada.getAsString("decano"))=="facultad actualizada"){
			salida.put("respuesta", "la facultad fue actualizada");
		}else if(gestionInstitucionalService.modificarFacultad(Integer.parseInt(entrada.getAsString("id"))
				, entrada.getAsString("nombre")
				, entrada.getAsString("coordinador")
				, entrada.getAsString("decano"))=="usuario invalido 1"){
			salida.put("respuesta", "la falcultad no fue creada el primer usuario es un estudiante inactivo");
		}else if(gestionInstitucionalService.modificarFacultad(Integer.parseInt(entrada.getAsString("id"))
				, entrada.getAsString("nombre")
				, entrada.getAsString("coordinador")
				, entrada.getAsString("decano"))=="usuario invalido 2"){
			salida.put("respuesta", "la falcultad no fue creada el primer usuario es un estudiante activo");
		}else if(gestionInstitucionalService.modificarFacultad(Integer.parseInt(entrada.getAsString("id"))
				, entrada.getAsString("nombre")
				, entrada.getAsString("coordinador")
				, entrada.getAsString("decano"))=="usuario invalido 3"){
			salida.put("respuesta", "la falcultad no fue creada el primer usuario es un semillerista");
		}else if(gestionInstitucionalService.modificarFacultad(Integer.parseInt(entrada.getAsString("id"))
				, entrada.getAsString("nombre")
				, entrada.getAsString("coordinador")
				, entrada.getAsString("decano"))=="la falcultad no fue creada el primer usuario ya es Lider investigacion facultad"){
			salida.put("respuesta", "la falcultad no fue creada el primer usuario ya es Lider investigacion facultad");
		}else if(gestionInstitucionalService.modificarFacultad(Integer.parseInt(entrada.getAsString("id"))
				, entrada.getAsString("nombre")
				, entrada.getAsString("coordinador")
				, entrada.getAsString("decano"))=="usuario invalido 4"){
			salida.put("respuesta", "la falcultad no fue creada el segundo usuario es un estudiante inactivo");
		}else if(gestionInstitucionalService.modificarFacultad(Integer.parseInt(entrada.getAsString("id"))
				, entrada.getAsString("nombre")
				, entrada.getAsString("coordinador")
				, entrada.getAsString("decano"))=="usuario invalido 5"){
			salida.put("respuesta", "la falcultad no fue creada el segundo usuario es un estudiante activo");
		}else if(gestionInstitucionalService.modificarFacultad(Integer.parseInt(entrada.getAsString("id"))
				, entrada.getAsString("nombre")
				, entrada.getAsString("coordinador")
				, entrada.getAsString("decano"))=="usuario invalido 6"){
			salida.put("respuesta", "la falcultad no fue creada el segundo usuario es un semillerista");
		}else if(gestionInstitucionalService.modificarFacultad(Integer.parseInt(entrada.getAsString("id"))
				, entrada.getAsString("nombre")
				, entrada.getAsString("coordinador")
				, entrada.getAsString("decano"))=="la falcultad no fue creada el segundo ya es Coordinador investigacion facultad"){
			salida.put("respuesta", "la falcultad no fue creada el segundo ya es Coordinador investigacion facultad");
		}
				else {
			salida.put("respuesta", "la facultad no fue actualizada");
		}

		return salida;
	}
	
	@GetMapping(value = "/facultadid/{id}")
	public JSONObject facultadlistarPorId(@PathVariable int id) {
		JSONObject x= new JSONObject();
		if(gestionInstitucionalService.facultadporid(id) !=null) {
			Facultad facultad = gestionInstitucionalService.facultadporid(id);
			return facultad.toJson();
		}
		else {
			return x;
		}	
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
	
	@GetMapping(value = "/listarusuariosporprograma/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public JSONArray listarUsuariosPorPrograma(@PathVariable int id) {
		JSONArray salida = new JSONArray(); 
		List<Usuario> usua = gestionInstitucionalService.UsuariosPrograma(id);
		for (Iterator iterator = usua.iterator(); iterator.hasNext();) {
			Usuario usuario = (Usuario) iterator.next();
			salida.add(usuario.toJson()) ;
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
	
	@PostMapping( "/crearprograma")
	public JSONObject crearPrograma(@RequestBody JSONObject entrada) {		
		JSONObject salida = new JSONObject();
		Programa programa =  new Programa(	

				entrada.getAsString("nombre"));
		if (gestionInstitucionalService.crearPrograma(programa
				, Integer.parseInt( entrada.getAsString("facultad_id"))
				,entrada.getAsString("director"))=="se creo el programa") {
			salida.put("respuesta", "se creo el programa");
		}else if (gestionInstitucionalService.crearPrograma(programa
				, Integer.parseInt( entrada.getAsString("facultad_id"))
				,entrada.getAsString("director"))=="la facultad no existe") {
			salida.put("respuesta", "la facultad no existe");
		}else if (gestionInstitucionalService.crearPrograma(programa
				, Integer.parseInt( entrada.getAsString("facultad_id"))
				,entrada.getAsString("director"))=="el usuario no existe") {
			salida.put("respuesta", "el usuario no existe");
		}else if (gestionInstitucionalService.crearPrograma(programa
				, Integer.parseInt( entrada.getAsString("facultad_id"))
				,entrada.getAsString("director"))=="usuario invalido 1") {
			salida.put("respuesta", "el programa no se creo porque el usuario es un estudiante inactivo");
		}else if (gestionInstitucionalService.crearPrograma(programa
				, Integer.parseInt( entrada.getAsString("facultad_id"))
				,entrada.getAsString("director"))=="usuario invalido 2") {
			salida.put("respuesta", "el programa no se creo porque el usuario es un estudiante activo");
		}else if (gestionInstitucionalService.crearPrograma(programa
				, Integer.parseInt( entrada.getAsString("facultad_id"))
				,entrada.getAsString("director"))=="usuario invalido 3") {
			salida.put("respuesta", "el programa no se creo porque el usuario es un semillerista");
		}else if (gestionInstitucionalService.crearPrograma(programa
				, Integer.parseInt( entrada.getAsString("facultad_id"))
				,entrada.getAsString("director"))=="este usuario ya es Director de programa") {
			salida.put("respuesta", "el programa no se creo porque el usuario ya es Director de programa");
		}
		
		else {
			salida.put("respuesta", "no se pudo el programa");
		}
		
		return salida;
	}
	
	@PostMapping( "/modificarprograma")
	public JSONObject modificarPrograma(@RequestBody JSONObject entrada) {		
		JSONObject salida = new JSONObject();
		if (gestionInstitucionalService.modificarPrograma(Integer.parseInt(entrada.getAsString("id"))
				,entrada.getAsString("nombre")
				,entrada.getAsString("facultad_id")
				,entrada.getAsString("director"))=="se actualizo el programa") {
			salida.put("respuesta", "se actualizo el programa");
		}else if (gestionInstitucionalService.modificarPrograma(Integer.parseInt(entrada.getAsString("id"))
				,entrada.getAsString("nombre")
				,entrada.getAsString("facultad_id")
				,entrada.getAsString("director"))=="usuario invalido 1") {
			salida.put("respuesta", "el semillero no se creo porque el usuario es un estudiante inactivo");
		}else if (gestionInstitucionalService.modificarPrograma(Integer.parseInt(entrada.getAsString("id"))
				,entrada.getAsString("nombre")
				,entrada.getAsString("facultad_id")
				,entrada.getAsString("director"))=="usuario invalido 2") {
			salida.put("respuesta", "el semillero no se creo porque el usuario es un estudiante activo");
		}else if (gestionInstitucionalService.modificarPrograma(Integer.parseInt(entrada.getAsString("id"))
				,entrada.getAsString("nombre")
				,entrada.getAsString("facultad_id")
				,entrada.getAsString("director"))=="usuario invalido 3") {
			salida.put("respuesta", "el semillero no se creo porque el usuario es un semillerista");
		}
		 else {
			salida.put("respuesta", "no se pudo el programa");
		}
		return salida;
	}
	
	@GetMapping(value = "/programaid/{id}")
	public JSONObject programalistarPorId(@PathVariable int id) {
		JSONObject x= new JSONObject();
		
		if(gestionInstitucionalService.programaporid(id) !=null) {
			Programa programa = gestionInstitucionalService.programaporid(id);
			return programa.toJson();
		}
		else {
			return x;
		}	
	}
	
	@GetMapping(value = "/listargruposdelprograma/{programa}")
	public JSONArray listargruposdelprograma(@PathVariable int programa) {	
		JSONArray salida = new JSONArray(); 
		List<GrupoInvestigacion> gru = gestionInstitucionalService.gruposDelPrograma(programa);
		for (Iterator iterator = gru.iterator(); iterator.hasNext();) {
			GrupoInvestigacion grupoInvestigacion = (GrupoInvestigacion) iterator.next();
			salida.add(grupoInvestigacion.toJson());
		}
		return salida;	
	}
	
	@GetMapping(value = "/listarsemillerosdelprograma/{programa}")
	public JSONArray listarsemillerosdelprograma(@PathVariable int programa) {	
		JSONArray salida = new JSONArray(); 
		List<Semillero> semi = gestionInstitucionalService.semillerosDelPrograma(programa);
		for (Iterator iterator = semi.iterator(); iterator.hasNext();) {
			Semillero semillero = (Semillero) iterator.next();
			salida.add(semillero.toJson());
		}
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
	
	@GetMapping(value = "/listarmateriasdeprograma/{programa}")
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
	public JSONObject crearMateria(@RequestBody JSONObject entrada) {		
		JSONObject salida = new JSONObject();
		Materia materia = new Materia(
				entrada.getAsString("catalogo"),
				entrada.getAsString("nombre"));
		if(gestionInstitucionalService.crearMateria(materia, Integer.parseInt(entrada.getAsString("programa")))) {
			salida.put("respuesta", "se creo la materia");
		}else {
			salida.put("respuesta", "no se creo");
		}
		return salida;
	}
	
	@PostMapping("/modificarmateria")
	public JSONObject modificarMateria(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if(!gestionInstitucionalService.modificarMateria(entrada.getAsString("catalogo"), entrada.getAsString("nombre"), entrada.getAsString("programa"))) {
			salida.put("respuesta", "la materia fue actualizada");
		}else {
			salida.put("respuesta", "la materia no fue actualizada");
		}
		return salida;
	}
	
	
	@GetMapping(value = "/materiaid/{id}")
	public JSONObject materialistarPorId(@PathVariable String id) {
		JSONObject x= new JSONObject();
		
		if(gestionInstitucionalService.materiaporid(id) !=null) {
			
			Materia materia = gestionInstitucionalService.materiaporid(id);
			return materia.toJson();
		}
		else {
			return x;
		}	
	

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
		JSONObject salida = new JSONObject();
		Clase clase =  new Clase(				
				Integer.parseInt(entrada.getAsString("numero")), 
				entrada.getAsString("nombre"),
				entrada.getAsString("semestre"));

		
		salida.put("respuesta", gestionInstitucionalService.crearClase(clase,entrada.getAsString("materia"),entrada.getAsString("profesor")));
		
		/*
		if (gestionInstitucionalService.crearClase(clase,entrada.getAsString("materia"),entrada.getAsString("profesor"))=="la clase ya existe") {

			salida.put("respuesta", "la clase ya existe");

		}
		/*
		else if (gestionInstitucionalService.crearClase(clase,entrada.getAsString("materia"),entrada.getAsString("profesor"))=="se creo la clase") {

			salida.put("respuesta", "se creo la clase");

		}else if (gestionInstitucionalService.crearClase(clase,entrada.getAsString("materia"),entrada.getAsString("profesor"))=="el usuario no existe") {

			salida.put("respuesta", "el usuario no existe");

		}else if (gestionInstitucionalService.crearClase(clase,entrada.getAsString("materia"),entrada.getAsString("profesor"))=="la materia no existe") {

			salida.put("respuesta", "la materia no existe");

		}else if (gestionInstitucionalService.crearClase(clase,entrada.getAsString("materia"),entrada.getAsString("profesor"))=="usuario invalido 1") {

			salida.put("respuesta", "el usuario es un estudiante inactivo");

		}else if (gestionInstitucionalService.crearClase(clase,entrada.getAsString("materia"),entrada.getAsString("profesor"))=="usuario invalido 2") {

			salida.put("respuesta", "el usuario es un estudiante activo");

		}else if (gestionInstitucionalService.crearClase(clase,entrada.getAsString("materia"),entrada.getAsString("profesor"))=="usuario invalido 3") {

			salida.put("respuesta", "el usuario es un semillerista");

		}else if (gestionInstitucionalService.crearClase(clase,entrada.getAsString("materia"),entrada.getAsString("profesor"))=="este usuario ya es Docente") {

			salida.put("respuesta", "tenga en cuenta que este usuario ya era Docente de otra clase");

		} else {
			salida.put("respuesta", "no se pudo crear");
		}*/

		return salida;
	}
	
	@PostMapping("/modificarclase")
	public JSONObject modificarClase(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if((gestionInstitucionalService.modificarClase(Integer.parseInt(entrada.getAsString("numero"))
				,entrada.getAsString("nombre")
				,entrada.getAsString("semestre")
				,entrada.getAsString("materia")
				,entrada.getAsString("profesor"))=="clase actualizada")) {
			salida.put("respuesta", "la clase fue actualizada");
		}else if((gestionInstitucionalService.modificarClase(Integer.parseInt(entrada.getAsString("numero"))
				,entrada.getAsString("nombre")
				,entrada.getAsString("semestre")
				,entrada.getAsString("materia")
				,entrada.getAsString("profesor"))=="usuario invalido 1")) {
			salida.put("respuesta", "el usuario que escogio es un Estudiante inactivo");
		}else if((gestionInstitucionalService.modificarClase(Integer.parseInt(entrada.getAsString("numero"))
				,entrada.getAsString("nombre")
				,entrada.getAsString("semestre")
				,entrada.getAsString("materia")
				,entrada.getAsString("profesor"))=="usuario invalido 2")) {
			salida.put("respuesta", "el usuario que escogio es un Estudiante activo");
		}else if((gestionInstitucionalService.modificarClase(Integer.parseInt(entrada.getAsString("numero"))
				,entrada.getAsString("nombre")
				,entrada.getAsString("semestre")
				,entrada.getAsString("materia")
				,entrada.getAsString("profesor"))=="usuario invalido 3")) {
			salida.put("respuesta", "el usuario que escogio es un Semillerista");
		}
else {
			salida.put("respuesta", "la clase no fue actualizada");
		}
		return salida;
	}
	
	@GetMapping(value = "/claseid/{id}")
	public JSONObject claselistarPorId(@PathVariable int id) {
		JSONObject x= new JSONObject();
		if(gestionInstitucionalService.claseporid(id) !=null) {
			Clase clase = gestionInstitucionalService.claseporid(id);
			return clase.toJson();
		}
		else {
			return x;
		}	
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
		List<Proyecto> pro = gestionInstitucionalService.proyectosPorClase(clase);
		for (Iterator iterator = pro.iterator(); iterator.hasNext();) {
			Proyecto proyecto = (Proyecto) iterator.next();
			salida.add(proyecto.toJson());
		}
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
	
	//CONVOCATORIAS////////////////////////////
	@GetMapping(value = "/convocatoriasestado/{estado}")
	public JSONArray listarLosProyectosDeClase(@PathVariable String estado) {
		
		JSONArray salida = new JSONArray(); 
		List<Convocatoria> con = gestionInstitucionalService.todasLasConvocatoriasAbiertas(estado);
		for (Convocatoria convocatoria : con) {
			salida.add(convocatoria.toJson());
		}
		return salida;
	}
	
	@GetMapping(value = "/convocatoriaporid/{id}")
	public JSONObject listarPorId(@PathVariable int id) {
		JSONObject x= new JSONObject();	
		if(gestionInstitucionalService.convocatoriaPorID(id) !=null) {
			Convocatoria con = gestionInstitucionalService.convocatoriaPorID(id);
			return con.toJson();
		}
		else {
			return x;
		}	
	}
	
	
	@GetMapping(value = "/listarlosproyectosdeconvocatoria/{convocatoria}")
	public JSONArray listarLosProyectosDeConvocatoria(@PathVariable int convocatoria) {
		
		JSONArray salida = new JSONArray(); 
		List<ProyectosConvocatoria> pro = gestionInstitucionalService.proyectosPorConvocatoria(convocatoria);
		for (Iterator iterator = pro.iterator(); iterator.hasNext();) {
			ProyectosConvocatoria proyectosConvocatoria = (ProyectosConvocatoria) iterator.next();
			salida.add(proyectosConvocatoria.toJson());
		}
		return salida;
	}
	
	
	@GetMapping(value = "/listarconvocatorias")
	public JSONArray listarConvocatorias() {

		JSONArray salida = new JSONArray(); 
		List<Convocatoria> li = gestionInstitucionalService.todasLasConvocatorias();
		for (Iterator iterator = li.iterator(); iterator.hasNext();) {
			Convocatoria convocatoria = (Convocatoria) iterator.next();
			salida.add(convocatoria.toJson());
		}
		
		return salida;		
	}
	
	@PostMapping(value = "/crearconvocatoria")
	public JSONObject crearConvocatoria(@RequestBody JSONObject entrada) {		
		JSONObject salida = new JSONObject();

		Convocatoria convocatoria = new Convocatoria(entrada.getAsString("nombre_convocatoria"),LocalDate.parse( entrada.getAsString("fecha_inicio")), LocalDate.parse( entrada.getAsString("fecha_final")), entrada.getAsString("contexto"), entrada.getAsString("estado"), entrada.getAsString("tipo"));


		if (gestionInstitucionalService.crearConvocatoria(convocatoria, entrada.getAsString("numero_productos"), entrada.getAsString("entidad"))) {

			salida.put("respuesta", "se creo la convocatoria");

		} else {
			salida.put("respuesta", "no se pudo crear");
		}

		return salida;
	}
	
	@PostMapping("/modificarconvocatoria")
	public JSONObject modificarConvocatoria(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if(gestionInstitucionalService.modificarConvocatoria(Integer.parseInt(entrada.getAsString("id"))
				, entrada.getAsString("nombre_convocatoria")
				, entrada.getAsString("fecha_inicio")
				, entrada.getAsString("fecha_final")
				, entrada.getAsString("contexto")
				, entrada.getAsString("numero_productos")
				, entrada.getAsString("estado")
				, entrada.getAsString("tipo")
				, entrada.getAsString("entidad"))) {
			salida.put("respuesta", "se actualizo la convocatoria");
		}else {
			salida.put("respuesta", "no se pudo actualizar el evento");
		}
		return salida;
	}
	
	@GetMapping(value = "/convocatoriaid/{id}")
	public JSONObject convocatorialistarPorId(@PathVariable int id) {

		JSONObject x= new JSONObject();	
		if(gestionInstitucionalService.convocatoriaporid(id)!=null) {
			Convocatoria convocatoria = gestionInstitucionalService.convocatoriaporid(id);
			return convocatoria.toJson();
		}
		else {
			return x;
		}	

	}
	
	@GetMapping(value = "/eliminarconvocatoria/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarConvocatoria(@PathVariable int id) {		
		if(gestionInstitucionalService.eliminarConvocatoria(id)) {
			return"eliminado con Exito";			
		}
		return "no se pudo eliminar";
	}
	
	
	
	
	
	
	
	
	//LINEAS/////////////////////////////////////////////
	
	
	@GetMapping(value = "/listarlineas")
	public JSONArray listarLineas() {

		JSONArray salida = new JSONArray(); 
		List<LineaInvestigacion> li = gestionInstitucionalService.todasLasLineas();
		for(LineaInvestigacion linea : li) {
			salida.add(linea.toJson());
		}	
		return salida;		
	}
	@PostMapping(value = "/crearlinea")
	public JSONObject crearLinea(@RequestBody JSONObject entrada) {		
		JSONObject salida = new JSONObject();	
		LineaInvestigacion linea = new LineaInvestigacion(
				entrada.getAsString("nombre"),
				entrada.getAsString("descripcion"));
		System.out.println(linea.getDescripcion());
		if(gestionInstitucionalService.crearLinea(linea,
				LocalDate.parse( entrada.getAsString("fecha")))) {
			salida.put("respuesta", "se creo la linea");
		}else {
			salida.put("respuesta", "no se creo");
		}
		return salida;
	}
	@PostMapping(value = "/crearlinea2")
	public JSONObject crearLinea2(@RequestBody JSONObject entrada) {		
		JSONObject salida = new JSONObject();	
		
		
		if(gestionInstitucionalService.crearLinea2(entrada.getAsString("nombre"),
				entrada.getAsString("descripcion"),
				LocalDate.parse( entrada.getAsString("fecha")))) {
			salida.put("respuesta", "se creo la linea");
		}else {
			salida.put("respuesta", "no se creo");
		}
		return salida;
	}
	
	@PostMapping("/modificarlinea")
	public JSONObject modificarLinea(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if(!gestionInstitucionalService.modificarLinea(entrada.getAsString("nombre"), entrada.getAsString("descripcion"), entrada.getAsString("fecha"))) {
			salida.put("respuesta", "se actualizo la linea");
		}else {
			salida.put("respuesta", "no se pudo actualizar la linea");
		}
		return salida;
	}
	
	@GetMapping(value = "/lineaid/{id}")
	public JSONObject linealistarPorId(@PathVariable String id) {

		JSONObject x= new JSONObject();	
		if(gestionInstitucionalService.lineaporid(id+"") !=null) {
			LineaInvestigacion linea = gestionInstitucionalService.lineaporid(id+"");
			return linea.toJson();
		}
		else {
			return x;
		}	

	}
	
	@GetMapping(value = "/eliminarlinea/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarMateria(@PathVariable String nombre) {		
		if(gestionInstitucionalService.eliminarLinea(nombre)) {
			return"eliminado con Exito";			
		}
		return "no se pudo eliminar";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//AREAS//////////////////////////////////////////////////
	@GetMapping(value = "/listarareas")
	public JSONArray listarAreas() {

		JSONArray salida = new JSONArray(); 
		List<AreaConocimiento> are = gestionInstitucionalService.todasLasAreasConocimiento();
		for(AreaConocimiento area : are) {
			salida.add(area.toJson());
		}	
		return salida;		
	}
	
	@PostMapping(value = "/creararea")
	public JSONObject crearArea(@RequestBody JSONObject entrada) {		
		JSONObject salida = new JSONObject();
		AreaConocimiento area =  new AreaConocimiento(
				entrada.getAsString("nombre"),
				entrada.getAsString("descripcion"));

		if (gestionInstitucionalService.crearArea(area,entrada.getAsString("gran_area"))) {

			salida.put("respuesta", "se creo la area");

		} else {
			salida.put("respuesta", "no se pudo crear");
		}

		return salida;
	}
	
	@PostMapping("/modificararea")
	public JSONObject modificarArea(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if(!gestionInstitucionalService.modificarArea(Integer.parseInt(entrada.getAsString("id"))
				, entrada.getAsString("nombre")
				, entrada.getAsString("gran_area")
				, entrada.getAsString("descripcion"))) {
			salida.put("respuesta", "se actualizo la area");
		}else {
			salida.put("respuesta", "no se pudo actualizar la area");
		}
		return salida;
	}
	
	@GetMapping(value = "/areaid/{id}")
	public JSONObject arealistarPorId(@PathVariable int id) {

		JSONObject x= new JSONObject();	
		if(gestionInstitucionalService.areaporid(id)!=null) {
			AreaConocimiento area = gestionInstitucionalService.areaporid(id);
			return area.toJson();
		}
		else {
			return x;
		}	

	}
	
	
	@GetMapping(value = "/eliminararea/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarArea(@PathVariable int id) {		
		if(gestionInstitucionalService.eliminarArea(id)) {
			return"eliminado con Exito";			
		}
		return "no se pudo eliminar";
	}
	
	
	
	
	
	
	//EVENTOS////////////////////////////////////////////////
	@GetMapping(value = "/listareventos")
	public JSONArray listarEventos() {

		JSONArray salida = new JSONArray(); 
		List<Evento> eve = gestionInstitucionalService.todosLosEventos();
		for(Evento evento : eve) {
			salida.add(evento.toJson());
		}	
		return salida;		
	}
	
	@GetMapping(value = "/contareventos")
	public List<JSONObject> contarEventos() {

		List<JSONObject> salida = gestionInstitucionalService.contarEventos(); 
		return salida;		
	}
	
	
	
	@PostMapping(value = "/crearevento")
	public JSONObject crearEvento(@RequestBody JSONObject entrada) {		
		JSONObject salida = new JSONObject();
		Evento evento =  new Evento( entrada.getAsString("nombre"), LocalDate.parse( entrada.getAsString("fecha")), entrada.getAsString("estado"));

		if (gestionInstitucionalService.crearEvento(evento,entrada.getAsString("entidad"),entrada.getAsString("sitio_web"),entrada.getAsString("url_memoria"))) {

			salida.put("respuesta", "se creo el evento");

		} else {
			salida.put("respuesta", "no se pudo crear");
		}

		return salida;
	}
	
	@PostMapping("/modificarevento")
	public JSONObject modificarEvento(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if(!gestionInstitucionalService.modificarEvento(Integer.parseInt(entrada.getAsString("id"))
				, entrada.getAsString("nombre")
				, entrada.getAsString("fecha")
				, entrada.getAsString("entidad")
				, entrada.getAsString("estado")
				, entrada.getAsString("sitio_web")
				, entrada.getAsString("url_memoria"))) {
			salida.put("respuesta", "se actualizo el evento");
		}else {
			salida.put("respuesta", "no se pudo actualizar el evento");
		}
		return salida;
	}
	
	@GetMapping(value = "/eventoid/{id}")
	public JSONObject eventolistarPorId(@PathVariable int id) {

		JSONObject x= new JSONObject();	
		if(gestionInstitucionalService.eventoporid(id)!=null) {
			Evento evento = gestionInstitucionalService.eventoporid(id);
			return evento.toJson();
		}
		else {
			return x;
		}	

	}
	
	
	@GetMapping(value = "/eliminarevento/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarEvento(@PathVariable int id) {		
		if(gestionInstitucionalService.eliminarEvento(id)) {
			return"eliminado con Exito";			
		}
		return "no se pudo eliminar";
	}
	@GetMapping(value="/ProyectosPostuladosConvocatorias/{estado}")
	public List<JSONObject> ProyectosPostuladosConvocatorias(@PathVariable String estado) {
		List<JSONObject> salida = gestionInstitucionalService.ProyectosPostuladosConvocatorias(estado);
		return salida;		
	}
	@GetMapping(value="/datosProyectoConvocatoria/{id}")
	public List<JSONObject> datosProyectoConvocatoria(@PathVariable int id) {
		List<JSONObject> salida = gestionInstitucionalService.datosProyectoConvocatoria(id);
		return salida;		
	}
	
}
	


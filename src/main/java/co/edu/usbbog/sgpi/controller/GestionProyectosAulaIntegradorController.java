package co.edu.usbbog.sgpi.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import co.edu.usbbog.sgpi.model.Clase;
import co.edu.usbbog.sgpi.model.Comentario;
import co.edu.usbbog.sgpi.model.Participaciones;
import co.edu.usbbog.sgpi.model.Participantes;
import co.edu.usbbog.sgpi.model.Producto;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.service.IGestionProyectosAulaIntegradorService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@RestController
@CrossOrigin
@RequestMapping("/gestionproyectosaulaintegrador")
public class GestionProyectosAulaIntegradorController {
	@Autowired
	private IGestionProyectosAulaIntegradorService iGestionProyectosAulaIntegradorService;

	// duda por la facultad
	@PostMapping("/crearproyecto")
	public JSONObject crearProyectoAulaIntegrador(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		Proyecto proyecto = new Proyecto(Integer.parseInt(entrada.getAsString("id")), entrada.getAsString("titulo"),
				entrada.getAsString("estado"), entrada.getAsString("descripcion"),
				LocalDate.parse(entrada.getAsString("fechainicio")),
				Short.parseShort(entrada.getAsString("visibilidad")), entrada.getAsString("ciudad"),
				entrada.getAsString("metodologia"), entrada.getAsString("justificacion"));
		Participantes participante = new Participantes(entrada.getAsString("usuario"),
				Integer.parseInt(entrada.getAsString("id")), LocalDate.parse(entrada.getAsString("fechainicio")));
		if (iGestionProyectosAulaIntegradorService.crearProyecto(proyecto, entrada.getAsString("tipo"), participante,
				entrada.getAsString("rol"), entrada.getAsString("clase"))) {
			salida.put("respuesta", "el proyecto fue creado");
		} else {
			salida.put("respuesta", "el proyecto no fue creado");
		}
		return salida;
	}

	@PostMapping("/agregarParticipante")
	public JSONObject agregarParticipante(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		Participantes participante = new Participantes(entrada.getAsString("usuario"),
				Integer.parseInt(entrada.getAsString("id")), LocalDate.parse(entrada.getAsString("fechainicio")));
		if (iGestionProyectosAulaIntegradorService.crearParticipante(participante, entrada.getAsString("rol"))) {
			salida.put("respuesta", "el participante fue agregado");
		} else {
			salida.put("respuesta", "el participante no pudo ser agregado");
		}
		return salida;
	}

	@GetMapping("/todosLosproyectos")
	public JSONArray todosLosProyectos() {
		JSONArray salida = new JSONArray();
		List<Proyecto> proyectos = iGestionProyectosAulaIntegradorService.todosLosProyectos();
		for (Iterator iterator = proyectos.iterator(); iterator.hasNext();) {
			Proyecto proyecto = (Proyecto) iterator.next();
			salida.add(proyecto.toJson());
		}
		return salida;
	}

	// duda por la facultad
	@GetMapping("/todoslosproyectosporclase/{clase}")
	public JSONArray todosLosProyectosPorClase(@PathVariable String clase) {
		JSONArray salida = new JSONArray();
		List<Proyecto> proyectos = iGestionProyectosAulaIntegradorService.todosLosProyectosPorClase(clase);
		for (Iterator<Proyecto> iterator = proyectos.iterator(); iterator.hasNext();) {
			Proyecto proyecto = (Proyecto) iterator.next();
			salida.add(proyecto.toJson());
		}
		return salida;
	}

	@GetMapping("/todoslosproyectosportipoproyecto/{tipo}")
	public JSONArray todosLosProyectosPorTipoProyecto(@PathVariable String tipo) {
		JSONArray salida = new JSONArray();
		List<Proyecto> proyectos = iGestionProyectosAulaIntegradorService.todosLosProyectosPorTipoProyecto(tipo);
		for (Iterator<Proyecto> iterator = proyectos.iterator(); iterator.hasNext();) {
			Proyecto proyecto = (Proyecto) iterator.next();
			salida.add(proyecto.toJson());
		}
		return salida;
	}

	@GetMapping("/eliminarproyecto/{id}")
	public JSONObject elinimarProyecto(@PathVariable String id) {
		JSONObject salida = new JSONObject();
		if (iGestionProyectosAulaIntegradorService.eliminarProyecto(Integer.parseInt(id))) {
			salida.put("respuesta", "proyeto fue eliminado correctamente");
		} else {
			salida.put("respuesta", "el proyecto no fue eliminado");
		}
		return salida;
	}
	@PostMapping("/crearproducto")
	public JSONObject crearProducto(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		Proyecto proyecto=iGestionProyectosAulaIntegradorService.buscarProyecto(Integer.parseInt(entrada.getAsString("proyectoid")));
		Producto producto=new Producto(
				Integer.parseInt(entrada.getAsString("id")),
				entrada.getAsString("titulo") , 
				entrada.getAsString("tipo"),
				entrada.getAsString("url"),
				LocalDate.parse(entrada.getAsString("fecha")),
				proyecto);
		if(iGestionProyectosAulaIntegradorService.crearProducto(producto)) {
			salida.put("respuesta", "el producto fue guardado");
		}else {
			salida.put("respuesta", "el producto no fue guardado");
		}
		return salida;
	}
	@GetMapping("/eliminarproducto/{id}")
	public JSONObject elinimarProducto(@PathVariable String id) {
		JSONObject salida = new JSONObject();
		if (iGestionProyectosAulaIntegradorService.eliminarProducto(Integer.parseInt(id))) {
			salida.put("respuesta", "proyeto fue eliminado correctamente");
		} else {
			salida.put("respuesta", "el proyecto no fue eliminado");
		}
		return salida;
	}
	@PostMapping("/crearcomentario")
	public JSONObject crearComentario(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		Producto producto=iGestionProyectosAulaIntegradorService.buscarProducto(Integer.parseInt(entrada.getAsString("productoid")));
		Comentario comentario=new Comentario(
				Integer.parseInt(entrada.getAsString("id")),
				entrada.getAsString("comentario") , 
				entrada.getAsString("fase"),
				entrada.getAsString("nivel"),
				LocalDate.parse(entrada.getAsString("fecha")));
		if(iGestionProyectosAulaIntegradorService.crearComentario(comentario,entrada.getAsString("cedula"))) {
			salida.put("respuesta", "el comentario fue guardado");
		}else {
			salida.put("respuesta", "el comentario no fue guardado");
		}
		return salida;
	}
	@GetMapping("/eliminarcomentario/{id}")
	public JSONObject elinimarComentario(@PathVariable String id) {
		JSONObject salida = new JSONObject();
		if (iGestionProyectosAulaIntegradorService.eliminarComentario(Integer.parseInt(id))) {
			salida.put("respuesta", "el comentario fue eliminado correctamente");
		} else {
			salida.put("respuesta", "el comentario no fue eliminado");
		}
		return salida;
	}	
	@GetMapping("/comentariosproducto/{id}")
	public JSONArray ComentariosPorProducto(@PathVariable String id) {
		JSONArray salida = new JSONArray();
		
		List<Comentario> comentarios = iGestionProyectosAulaIntegradorService.ComentariosPorProducto(Integer.parseInt(id));
		for (Iterator<Comentario> iterator = comentarios.iterator(); iterator.hasNext();) {
			Comentario comentario = (Comentario) iterator.next();
			salida.add(comentario.toJson());
		}
		return salida;
	}
	@PostMapping("/participarevento")
	public JSONObject participarEvento(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		Participaciones participaciones=new Participaciones(
				Integer.parseInt(entrada.getAsString("evento")),
				Integer.parseInt(entrada.getAsString("proyecto")));
		System.out.println("holass");
		if(iGestionProyectosAulaIntegradorService.participarEvento(
				participaciones,
				LocalDate.parse(entrada.getAsString("fecha")),
				entrada.getAsString("reconocimiento"))) {
			salida.put("respuesta", "se unio al evento exitosamente");
		}else {
			salida.put("respuesta", "no se unio al evento ");
		}
	
		return salida;
	}
	@GetMapping("/participacionesproyecto/{proyecto}")
	public JSONArray participacionesProyecto(@PathVariable String proyecto) {
		JSONArray salida = new JSONArray();
		List<Participaciones> participaciones=iGestionProyectosAulaIntegradorService.buscarParticipaciones(Integer.parseInt(proyecto));
		for (Iterator<Participaciones> iterator = participaciones.iterator(); iterator.hasNext();) {
			Participaciones participacion = (Participaciones) iterator.next();
			salida.add(participacion.toJson());
		}
		return salida;
	}
	@PostMapping("/agregarantecedente")
	public JSONObject agregarAntecedente(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		Proyecto proyeto=iGestionProyectosAulaIntegradorService.buscarProyecto(Integer.parseInt(entrada.getAsString("proyecto")));
		Proyecto antecedente=iGestionProyectosAulaIntegradorService.buscarProyecto(Integer.parseInt(entrada.getAsString("antecedente")));
		System.out.println("holass");
		if(iGestionProyectosAulaIntegradorService.agregarAntecedente(proyeto,antecedente) {
			salida.put("respuesta", "se agrego exitosamente el antecedente");
		}else {
			salida.put("respuesta", "no se agrego el antecedente ");
		}
	
		return salida;
	}

}

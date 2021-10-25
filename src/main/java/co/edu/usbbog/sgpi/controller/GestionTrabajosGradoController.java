package co.edu.usbbog.sgpi.controller;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbbog.sgpi.model.Comentario;
import co.edu.usbbog.sgpi.model.Participantes;
import co.edu.usbbog.sgpi.model.Producto;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.service.IGestionProyectosAulaIntegradorService;
import co.edu.usbbog.sgpi.service.IGestionTrabajosGradoService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
@RestController
@CrossOrigin
@RequestMapping("/gestiontrabajogrado")
public class GestionTrabajosGradoController {
	@Autowired
	private IGestionTrabajosGradoService iGestionTrabajosGradoService;
	@GetMapping("/todosLosproyectos")
	public JSONArray todosLosProyectos() {
		JSONArray salida = new JSONArray();
		List<Proyecto> proyectos = iGestionTrabajosGradoService.todosLosProyectos("Grado");
		for (Iterator iterator = proyectos.iterator(); iterator.hasNext();) {
			Proyecto proyecto = (Proyecto) iterator.next();
			salida.add(proyecto.toJson());
		}
		return salida;
	}
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
		if (iGestionTrabajosGradoService.crearProyecto(proyecto, entrada.getAsString("tipo"), participante,
				entrada.getAsString("rol"))) {
			salida.put("respuesta", "el proyecto fue creado");
		} else {
			salida.put("respuesta", "el proyecto no fue creado");
		}
		return salida;
	}
	@GetMapping("/eliminarproyecto/{id}")
	public JSONObject elinimarProyecto(@PathVariable String id) {
		JSONObject salida = new JSONObject();
		if (iGestionTrabajosGradoService.eliminarProyecto(Integer.parseInt(id))) {
			salida.put("respuesta", "proyeto fue eliminado correctamente");
		} else {
			salida.put("respuesta", "el proyecto no fue eliminado");
		}
		return salida;
	}
	@PostMapping("/crearproducto")
	public JSONObject crearProducto(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		Proyecto proyecto = iGestionTrabajosGradoService
				.buscarProyecto(Integer.parseInt(entrada.getAsString("proyectoid")));
		
		if(proyecto.getTipoProyecto().getNombre().equals("Grado")) {
			Producto producto = new Producto(Integer.parseInt(entrada.getAsString("id")), entrada.getAsString("titulo"),
					entrada.getAsString("tipo"), entrada.getAsString("url"), LocalDate.parse(entrada.getAsString("fecha")));
		
		if (iGestionTrabajosGradoService.crearProducto(producto)) {
			salida.put("respuesta", "el producto fue guardado");
		} else {
			salida.put("respuesta", "el producto no fue guardado");
		}
		}else {
			salida.put("respuesta","el tipo del proyecto no es valido");
		}
			
		return salida;
	}

	@GetMapping("/eliminarproducto/{id}")
	public JSONObject elinimarProducto(@PathVariable String id) {
		JSONObject salida = new JSONObject();
		if (iGestionTrabajosGradoService.eliminarProductos(Integer.parseInt(id))) {
			salida.put("respuesta", "proyeto fue eliminado correctamente");
		} else {
			salida.put("respuesta", "el proyecto no fue eliminado");
		}
		return salida;
	}
	@PostMapping("/crearcomentario")
	public JSONObject crearComentario(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		Producto producto = iGestionTrabajosGradoService
				.buscarProducto(Integer.parseInt(entrada.getAsString("productoid")));
		Comentario comentario = new Comentario(Integer.parseInt(entrada.getAsString("id")),
				entrada.getAsString("comentario"), entrada.getAsString("fase"), entrada.getAsString("nivel"),
				LocalDate.parse(entrada.getAsString("fecha")));
		if (iGestionTrabajosGradoService.crearComentario(comentario, entrada.getAsString("cedula"))) {
			salida.put("respuesta", "el comentario fue guardado");
		} else {
			salida.put("respuesta", "el comentario no fue guardado");
		}
		return salida;
	}
	@GetMapping("/eliminarcomentario/{id}")
	public JSONObject elinimarComentario(@PathVariable String id) {
		JSONObject salida = new JSONObject();
		if (iGestionTrabajosGradoService.eliminarComentario(Integer.parseInt(id))) {
			salida.put("respuesta", "el comentario fue eliminado correctamente");
		} else {
			salida.put("respuesta", "el comentario no fue eliminado");
		}
		return salida;
	}
	@GetMapping(value = "/listarGradoTerminado")
	public JSONArray listarGradoTerminado() {
		JSONArray salida = new JSONArray();
		List<Proyecto> pro = iGestionTrabajosGradoService.todosLosProyectosFinalizados("grado", "finalizado");
		for (Proyecto proyecto : pro) {
			salida.add(proyecto.toJson());
		}
		return salida;
	}
}

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

import co.edu.usbbog.sgpi.model.AreaConocimiento;
import co.edu.usbbog.sgpi.model.Comentario;
import co.edu.usbbog.sgpi.model.Participaciones;
import co.edu.usbbog.sgpi.model.Participantes;
import co.edu.usbbog.sgpi.model.Producto;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.ProyectosConvocatoria;
import co.edu.usbbog.sgpi.service.IGestionProyectosInvestigacionService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;



@RestController
@CrossOrigin
@RequestMapping("/gestionproyectosinvestigacion")
public class GestionProyectosInvestigacionController {
	@Autowired
	private IGestionProyectosInvestigacionService iGestionProyectosInvestigacionService ;
	@PostMapping("/crearproyecto")
	public JSONObject crearProyectoAulaIntegrador(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		Proyecto proyecto = new Proyecto(entrada.getAsString("titulo"),
				entrada.getAsString("estado"), entrada.getAsString("descripcion"),
				LocalDate.parse(entrada.getAsString("fechainicio")),
				Short.parseShort(entrada.getAsString("visibilidad")), entrada.getAsString("ciudad"),
				entrada.getAsString("metodologia"), entrada.getAsString("justificacion"));
		Participantes participante = new Participantes(entrada.getAsString("usuario"),
				Integer.parseInt(entrada.getAsString("id")), LocalDate.parse(entrada.getAsString("fechainicio")));
		if (iGestionProyectosInvestigacionService.crearProyecto(proyecto, entrada.getAsString("tipo"), participante,
				entrada.getAsString("rol"),entrada.getAsString("semillero"))) {
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
		if (iGestionProyectosInvestigacionService.crearParticipante(participante, entrada.getAsString("rol"))) {
			salida.put("respuesta", "el participante fue agregado");
		} else {
			salida.put("respuesta", "el participante no pudo ser agregado");
		}
		return salida;
	}
	@GetMapping(value = "/proyectossemillero/{cedula}")
	public  List<JSONObject>  poryectosDeSemilleroParparticipante(@PathVariable String cedula	) {
		 List<JSONObject> x = iGestionProyectosInvestigacionService.proyectosParticipanteSemillero(cedula);
		 return x;
		}
	@GetMapping("/todosLosproyectosusuariosemillero/{cedula}")
	public  List<JSONObject> todosLosProyectosUsuarioSemillero(@PathVariable String cedula) {
		List<JSONObject> proyectos = iGestionProyectosInvestigacionService.todosLosProyectosUsuarioSemillero(cedula);
		
		return proyectos;
	}
	@GetMapping("/eliminarproyecto/{id}")
	public JSONObject elinimarProyecto(@PathVariable String id) {
		JSONObject salida = new JSONObject();
		if (iGestionProyectosInvestigacionService.eliminarProyecto(Integer.parseInt(id))) {
			salida.put("respuesta", "proyeto fue eliminado correctamente");
		} else {
			salida.put("respuesta", "el proyecto no fue eliminado");
		}
		return salida;
	}

	@PostMapping("/crearproducto")
	public JSONObject crearProducto(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		Proyecto proyecto = iGestionProyectosInvestigacionService
				.buscarProyecto(Integer.parseInt(entrada.getAsString("proyectoid")));
		Producto producto = new Producto(Integer.parseInt(entrada.getAsString("id")), entrada.getAsString("titulo"),
				entrada.getAsString("tipo"), entrada.getAsString("url"), LocalDate.parse(entrada.getAsString("fecha")));
		if (iGestionProyectosInvestigacionService.crearProducto(producto)) {
			salida.put("respuesta", "el producto fue guardado");
		} else {
			salida.put("respuesta", "el producto no fue guardado");
		}
		return salida;
	}

	@GetMapping("/eliminarproducto/{id}")
	public JSONObject elinimarProducto(@PathVariable String id) {
		JSONObject salida = new JSONObject();
		if (iGestionProyectosInvestigacionService.eliminarProducto(Integer.parseInt(id))) {
			salida.put("respuesta", "proyeto fue eliminado correctamente");
		} else {
			salida.put("respuesta", "el proyecto no fue eliminado");
		}
		return salida;
	}

	@PostMapping("/crearcomentario")
	public JSONObject crearComentario(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		Producto producto = iGestionProyectosInvestigacionService
				.buscarProducto(Integer.parseInt(entrada.getAsString("productoid")));
		Comentario comentario = new Comentario(
				entrada.getAsString("comentario"), entrada.getAsString("fase"), entrada.getAsString("nivel"),
				LocalDate.parse(entrada.getAsString("fecha")));
		if (iGestionProyectosInvestigacionService.crearComentario(comentario, entrada.getAsString("cedula"))) {
			salida.put("respuesta", "el comentario fue guardado");
		} else {
			salida.put("respuesta", "el comentario no fue guardado");
		}
		return salida;
	}
	@GetMapping("/eliminarcomentario/{id}")
	public JSONObject elinimarComentario(@PathVariable String id) {
		JSONObject salida = new JSONObject();
		if (iGestionProyectosInvestigacionService.eliminarComentario(Integer.parseInt(id))) {
			salida.put("respuesta", "el comentario fue eliminado correctamente");
		} else {
			salida.put("respuesta", "el comentario no fue eliminado");
		}
		return salida;
	}

	@GetMapping("/comentariosproducto/{id}")
	public JSONArray ComentariosPorProducto(@PathVariable String id) {
		JSONArray salida = new JSONArray();

		List<Comentario> comentarios = iGestionProyectosInvestigacionService
				.ComentariosPorProducto(Integer.parseInt(id));
		for (Iterator<Comentario> iterator = comentarios.iterator(); iterator.hasNext();) {
			Comentario comentario = (Comentario) iterator.next();
			salida.add(comentario.toJson());
		}
		return salida;
	}

	@PostMapping("/participarevento")
	public JSONObject participarEvento(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		Participaciones participaciones = new Participaciones(Integer.parseInt(entrada.getAsString("evento")),
				Integer.parseInt(entrada.getAsString("proyecto")));
		System.out.println("holass");
		if (iGestionProyectosInvestigacionService.participarEvento(participaciones,
				LocalDate.parse(entrada.getAsString("fecha")), entrada.getAsString("reconocimiento"))) {
			salida.put("respuesta", "se unio al evento exitosamente");
		} else {
			salida.put("respuesta", "no se unio al evento ");
		}

		return salida;
	}

	@GetMapping("/participacionesproyecto/{proyecto}")
	public JSONArray participacionesProyecto(@PathVariable String proyecto) {
		JSONArray salida = new JSONArray();
		List<Participaciones> participaciones = iGestionProyectosInvestigacionService
				.buscarParticipaciones(Integer.parseInt(proyecto));
		for (Iterator<Participaciones> iterator = participaciones.iterator(); iterator.hasNext();) {
			Participaciones participacion = (Participaciones) iterator.next();
			salida.add(participacion.toJson());
		}
		return salida;
	}

	@PostMapping("/agregarantecedente")
	public JSONObject agregarAntecedente(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		Proyecto proyecto = iGestionProyectosInvestigacionService
				.buscarProyecto(Integer.parseInt(entrada.getAsString("proyecto")));
		Proyecto antecedente = iGestionProyectosInvestigacionService
				.buscarProyecto(Integer.parseInt(entrada.getAsString("antecedente")));
		if (antecedente.getFechaFin() == null) {
			salida.put("respuesta ", "el antecedente aun no a terminado");
		} else {
			if (proyecto == antecedente) {
				salida.put("respuesta", "el proyecto no puede ser antedente de si mismo");
			} else {

				if (iGestionProyectosInvestigacionService.agregarAntecedente(proyecto, antecedente)) {
					salida.put("respuesta", "se agrego exitosamente el antecedente");
				} else {
					salida.put("respuesta", "no se agrego el antecedente ");
				}
			}
		}

		return salida;
	}

	@PostMapping("/agregarareaconocimiento")
	public JSONObject agregarAreaConocimiento(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if (iGestionProyectosInvestigacionService.agregarAreaConocimiento(
				Integer.parseInt(entrada.getAsString("proyecto")), Integer.parseInt(entrada.getAsString("area")))) {
			salida.put("respuesta", "se agrego exitosamente las areas");
		} else {
			salida.put("respuesta", "No se agrego las areas");
		}
		return salida;
	}

	@GetMapping("/listarareasproyecto/{proyecto}")
	public JSONArray listarAreasProyecto(@PathVariable String proyecto) {
		JSONArray salida = new JSONArray();
		List<AreaConocimiento> areaConocimientos = iGestionProyectosInvestigacionService
				.buscarAreasProyecto(Integer.parseInt(proyecto));
		for (Iterator<AreaConocimiento> iterator = areaConocimientos.iterator(); iterator.hasNext();) {
			AreaConocimiento areas = (AreaConocimiento) iterator.next();
			salida.add(areas.toJson());
		}
		return salida;
	}
	@PostMapping("/participarconvocatoria/")
	public JSONObject participarconvocatoria(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		ProyectosConvocatoria proyectoConvocatoria= new ProyectosConvocatoria(Integer.parseInt(entrada.getAsString("proyecto")),Integer.parseInt(entrada.getAsString("convocatoria")) );
		if (iGestionProyectosInvestigacionService.participarConvocatoria(proyectoConvocatoria,entrada.getAsString("proyecto_id"))) {
			salida.put("respuesta", "se agrego exitosamente a la convocatoria");
		} else {
			salida.put("respuesta", "No se agrego a la convocatoria");
		}
		return salida;
	}

}


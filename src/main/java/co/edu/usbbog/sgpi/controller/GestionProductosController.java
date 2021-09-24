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

import co.edu.usbbog.sgpi.model.Comentario;
import co.edu.usbbog.sgpi.model.Facultad;
import co.edu.usbbog.sgpi.model.Producto;
import co.edu.usbbog.sgpi.service.IGestionProductosService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@RestController
@CrossOrigin
@RequestMapping("/productos")
public class GestionProductosController {
	@Autowired
	private IGestionProductosService gestionProductos;
	
	
	@GetMapping(value = "/listarproductos")
	public JSONArray listarProductos() {
		
		JSONArray salida = new JSONArray(); 
		List<Producto> pro = gestionProductos.todosLosProductos();
		for (Producto producto : pro) {
			salida.add(producto.toJson()) ;
		}
		System.out.println(salida);
		return salida;		
	}
	
	@GetMapping(value = "/listarproductosporproyecto/{proyecto}")
	public JSONArray listaProductosPorProyecto(@PathVariable int proyecto) {
		
		JSONArray salida = new JSONArray(); 
		List<Producto> pro = gestionProductos.todosLosProductosPorProyecto(proyecto);
		for (Producto producto : pro) {
			salida.add(producto.toJson()) ;
		}
		System.out.println(salida);
		return salida;		
	}
	
	@GetMapping(value = "/eliminarproducto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarProducto(@PathVariable int id) {
		if(gestionProductos.eliminarProductos(id)) {
			return "Se elimino con exito";
		}
		//gestionInstitucionalService.eliminarGrupoInvestigacion(id);
		return "Fallo la eliminacion";	
	}
	
	@PostMapping(value = "/crearproducto")
	public JSONObject crearProducto(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		Producto producto = new Producto(
				Integer.parseInt(entrada.getAsString("id")),
				entrada.getAsString("titulo_producto"),
				entrada.getAsString("tipo_producto"),
				entrada.getAsString("url_repo"));

		if(gestionProductos.crearProducto(producto, Integer.parseInt( entrada.getAsString("proyecto")))) {
			salida.put("respuesta", "el producto se creo");
		}
		else {
			salida.put("respuesta", "el producto no se creo");
		}
		return salida;
	}
	
	
	@GetMapping(value = "/listarcomentariosporproducto/{producto_id}")
	public JSONArray listarComentariosPorProducto(@PathVariable int producto_id) {
		
		JSONArray salida = new JSONArray(); 
		List<Comentario> comen = gestionProductos.ComentariosPorProducto(producto_id);
		for (Comentario comentario : comen) {
			salida.add(comentario.toJson()) ;
		}
		System.out.println(comen);
		return salida;		
	}
	
	@GetMapping(value = "/eliminarcomentario/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarComentario(@PathVariable int id) {
		if(gestionProductos.eliminarComentario(id)) {
			return "Se elimino con exito";
		}
		//gestionInstitucionalService.eliminarGrupoInvestigacion(id);
		return "Fallo la eliminacion";	
	}
	
	@PostMapping(value = "/crearcomentario")
	public JSONObject crearComentario(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		Comentario comentario = new Comentario(
				Integer.parseInt(entrada.getAsString("id")),
				entrada.getAsString("comentario"),
				entrada.getAsString("fase"),
				entrada.getAsString("nivel"), 
				LocalDate.parse(entrada.getAsString("fecha")));

		if(gestionProductos.crearComentario(comentario, Integer.parseInt( entrada.getAsString("producto_id")))) {
			salida.put("respuesta", "el comentario se creo");
		}
		else {
			salida.put("respuesta", "el comentario no se creo");
		}
		return salida;
	}
	
	@PostMapping(value = "/asignarnota")
	public JSONObject asignarNota(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();

		if(gestionProductos.asignarCalificacion(Double.parseDouble(entrada.getAsString("calificacion")), Integer.parseInt( entrada.getAsString("id")))) {
			salida.put("respuesta", "se asigno la nota");
		}
		else {
			salida.put("respuesta", "NO se puedo");
		}
		return salida;
	}
	
}

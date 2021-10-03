package co.edu.usbbog.sgpi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.TipoProyecto;
import co.edu.usbbog.sgpi.service.IBibliotecaService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@RestController
@CrossOrigin
@RequestMapping("/biblioteca")
public class BibliotecaController {

	@Autowired
	private IBibliotecaService bibliotecaService;

	@GetMapping(value = "/listarGrado")
	public JSONArray listarGrado() {
		JSONArray salida = new JSONArray();
		List<Proyecto> pro = bibliotecaService.todosLosProyectosDeGrado();
		for (Proyecto proyecto : pro) {
			salida.add(proyecto.toJson());
		}
		return salida;
	}

	@GetMapping(value = "/listarGradoTerminado")
	public JSONArray listarGradoTerminado() {
		JSONArray salida = new JSONArray();
		List<Proyecto> pro = bibliotecaService.todosLosProyectosTerminados("grado", "finalizado");
		for (Proyecto proyecto : pro) {
			salida.add(proyecto.toJson());
		}
		return salida;
	}
	
	@GetMapping(value = "/listarporid/{id}")
	public JSONObject listarPorId(@PathVariable int id) {
		JSONObject x= new JSONObject();
		if(bibliotecaService.proyectoporid(id) !=null) {
			Proyecto pro = bibliotecaService.proyectoporid(id);
			return pro.toJson();
		}
		else {
			return x;
		}	
	}
}
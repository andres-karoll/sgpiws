package co.edu.usbbog.sgpi.controller;

import java.util.List;

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

@RestController
@CrossOrigin
@RequestMapping("/biblioteca")
public class BibliotecaController {

	@Autowired
	private IBibliotecaService bibliotecaService;

	@GetMapping(value = "/listarGrado")
	public String listarGrado() {
		String salida = "";
		List<Proyecto> pro = bibliotecaService.todosLosProyectosDeGrado();
		for (Proyecto proyecto : pro) {
			salida += proyecto.getTitulo() + ", ";
		}
		return salida;
	}

	@GetMapping(value = "/listarGradoTerminado")
	public String listarGradoTerminado() {
		String salida = "";
		List<Proyecto> pro = bibliotecaService.todosLosProyectosTerminados("grado", "fin");
		for (Proyecto proyecto : pro) {
			salida += proyecto.getTitulo() + ", ";
		}
		return salida;
	}
}
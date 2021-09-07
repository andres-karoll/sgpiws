package co.edu.usbbog.sgpi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.service.IGestionInstitucionalService;


@RestController
@CrossOrigin
@RequestMapping("/gestionInstitucional")
public class GestionInstitucionalController {

	@Autowired
	private IGestionInstitucionalService gestionInstitucionalService;
	
	@GetMapping(value = "/listar")
	public String listarGruposI() {
		
		String salida = ""; 
		List<GrupoInvestigacion> gru = gestionInstitucionalService.todosLosGruposInvestigacion();
		for (GrupoInvestigacion grupoInvestigacion : gru) {
			salida += grupoInvestigacion.getNombre() + ", ";
		}
		return salida;		
	}
	
	@GetMapping(value = "/eliminar")
	public int eliminarGruposI() {
		
		gestionInstitucionalService.eliminarGrupoInvestigacion(3);
		return 0;
	}
	
	
	
	
	
}

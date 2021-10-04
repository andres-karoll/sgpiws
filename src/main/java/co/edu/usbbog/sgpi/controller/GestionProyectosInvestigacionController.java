package co.edu.usbbog.sgpi.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbbog.sgpi.model.Participantes;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.repository.ISemilleroRepository;
import co.edu.usbbog.sgpi.service.IGestionProyectosInvestigacionService;
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
		Proyecto proyecto = new Proyecto(Integer.parseInt(entrada.getAsString("id")), entrada.getAsString("titulo"),
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


}

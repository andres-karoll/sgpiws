package co.edu.usbbog.sgpi.controller;

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

import co.edu.usbbog.sgpi.model.Compra;
import co.edu.usbbog.sgpi.model.Presupuesto;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.Usuario;
import co.edu.usbbog.sgpi.service.IGestionFinancieraService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@RestController
@CrossOrigin
@RequestMapping("/gestionfinanciera")
public class GestionFinanciera {
	@Autowired
	private IGestionFinancieraService gestionFinancieraService;

	
	@GetMapping(value = "/listarpresupuestoporproyecto/{id}")
	public JSONArray listarPresupuestoPorProyecto(@PathVariable int id) {
		
		JSONArray salida = new JSONArray(); 
		List<Presupuesto> pre = gestionFinancieraService.PresupuestoPorProyecto(id);
		for (Presupuesto presupuesto : pre) {
			salida.add(presupuesto.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/eliminarpresupuesto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarSemillero(@PathVariable int id) {	
		if(gestionFinancieraService.eliminarPresupuesto(id)) {
			return "Eliminado";
		}
		
		return "No se puede eliminar";
	}
	
	@PostMapping(value = "/crearpresupuesto")
	public JSONObject crearSemilleros(@RequestBody Presupuesto presupuesto) {
		JSONObject salida = new JSONObject();
		if(gestionFinancieraService.crearPresupuesto(presupuesto)) {
			salida.put("respuesta", "se creo");			
		}else {
			salida.put("respuesta", "NO se creo");
		}
		
		return salida;
	}
	
	@GetMapping(value = "/listarcomprasdelpresupuesto/{id}")
	public JSONArray listarComprasDelPresupuesto(@PathVariable int id) {
		
		JSONArray salida = new JSONArray(); 
		List<Compra> com = gestionFinancieraService.CompraPorPresupuesto(id);
		for (Compra compra : com) {
			salida.add(compra.toJson()) ;
		}
		return salida;		
	}
	
	@GetMapping(value = "/eliminarcompra/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String eliminarCompra(@PathVariable int id) {	
		boolean x = gestionFinancieraService.eliminarCompra(id);
		System.out.println(x);
		if(x) {
			return "Eliminado";
		}
		else {
			return "No se puede eliminar";
			
		}
		
	}
	
	@PostMapping(value = "/crearcompra")
	public JSONObject crearCompra(@RequestBody Compra compra) {
		JSONObject salida = new JSONObject();
		if(gestionFinancieraService.crearCompra(compra)) {
			salida.put("respuesta", "se creo");			
		}else {
			salida.put("respuesta", "NO se creo");
		}
		
		return salida;
	}
}

package co.edu.usbbog.sgpi.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.io.JsonEOFException;

import co.edu.usbbog.sgpi.model.Facultad;
import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.Programa;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.TipoUsuario;
import co.edu.usbbog.sgpi.model.Usuario;
import co.edu.usbbog.sgpi.repository.IUsuarioRepository;
import co.edu.usbbog.sgpi.service.IGestionInstitucionalService;
import co.edu.usbbog.sgpi.service.IGestionUsuariosService;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@RestController
@CrossOrigin
@RequestMapping("/gestionusuario")
public class GestionUsuarioController {
	@Autowired
	private IGestionUsuariosService iGestionUsuariosService;
	// usuario
	@GetMapping("/listarusuarios")
	public JSONArray getAllUsuarios() {
		JSONArray salida = new JSONArray();
		
		List<Usuario> usuarios = iGestionUsuariosService.todosLosUsuarios();
		for (Iterator<Usuario> iterator = usuarios.iterator(); iterator.hasNext();) {
			Usuario usuario = (Usuario) iterator.next();
			salida.add(usuario.toJson());
		}
		return salida;
	}

	@GetMapping("/existeusuario/{cedula}")
	public boolean isExistUsuario(@PathVariable String cedula) {
		if (iGestionUsuariosService.existeUsuario(cedula)) {
			return true;
		} else {
			return false;
		}
	}

	@GetMapping("/buscarusuario/{cedula}")
	public JSONObject buscarUsuario(@PathVariable String cedula) {
		Usuario usuario = iGestionUsuariosService.buscarUsuario(cedula);
		JSONObject salida = usuario.toJson();
		return salida;
	}

	@GetMapping("/eliminarusuario/{cedula}")
	public String eliminarUsuario(@PathVariable String cedula) {
		iGestionUsuariosService.eliminarUsuario(cedula);
		return "elimino";
	}

	// dudas
	@PostMapping("/guardarusuario")
	public JSONObject guardarUsuario(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		 String tipoUsuario="";
		Usuario usuario = new Usuario(entrada.getAsString("cedula"),
				Integer.parseInt(entrada.getAsString("codUniversitario")), entrada.getAsString("correoEst"),
				entrada.getAsString("contrasena"), entrada.getAsString("nombres"), entrada.getAsString("apellidos"),
				entrada.getAsString("visibilidad"));
		usuario.setTelefono(entrada.getAsString("telefono"));
		
		if ( entrada.getAsString("correoEst").contains("@academia.usbbog.edu.co")){
		    if(entrada.getAsString("tipo").equals("Estudiante")) {
		    	tipoUsuario=entrada.getAsString("tipo");
		    }else {
		        salida.put("respuesta", "el tipo de usuario es incorrecto ");
		        return salida;
		    }
		}else if(entrada.getAsString("correoEst").contains("@usbbog.edu.co")){	
			tipoUsuario=entrada.getAsString("tipo");
			}
		
		if (iGestionUsuariosService.crearUsuario(usuario,
				entrada.getAsString("programa"),tipoUsuario)) {		
				salida.put("respuesta", "usuario creado");

		} else {
			salida.put("respuesta", "el usuario ya existe");
		}
		return salida;
	}
	@PostMapping("/modificarusuario")
	public JSONObject modificarUsuario(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if(iGestionUsuariosService.modificarUsuario(entrada.getAsString("cedula"),entrada.getAsString("telefono"),entrada.getAsString("clave"),entrada.getAsString("correoP"))) {
			salida.put("respuesta", "el usuario fue actualizado");
		}else {
			salida.put("respuesta", "el usuario no fue actualizado");
		}
		return salida;
	}


	@GetMapping("/existesemillero/{id}")
	public boolean isExistSemillero(@PathVariable Integer id) {
		JSONObject salida = new JSONObject();
		if (iGestionUsuariosService.existeSemillero(id)) {
			return true;
		} else {
			return false;
		}

	}

	@PostMapping("/asignarsemillero")
	public JSONObject asignarsemillero(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		if (iGestionUsuariosService.asignarSemillero(entrada.getAsString("cedula"), Integer.parseInt(entrada.getAsString("semillero")))) {
			salida.put("respuesta", "el usuario fue asignado exitosamente");
		}else {
			salida.put("respuesta", "el usuario fue no asignado ");
		}
		return salida;
	}
	@PostMapping("/eliminarusuariosemillero/{cedula}")
	public boolean eliminarUsuarioSemillero(@PathVariable String cedula) {
		return iGestionUsuariosService.eliminarUsuarioSemillero(cedula);
		
	}
	@GetMapping("/existetipousuario/{nombre}")
	public boolean isExistTipoUsuario(@PathVariable String nombre) {
		return iGestionUsuariosService.existeTipoUsuario(nombre);
	}

	@PostMapping("/creartipousuario")
	public JSONObject guardarTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
		final JSONObject salida = new JSONObject();
		if (isExistTipoUsuario(tipoUsuario.getNombre())) {
			salida.put("respuesta", "el tipo de usuario ya existe");
			return salida;
		} else {
			iGestionUsuariosService.crearTipoUsuario(tipoUsuario);
			salida.put("respuesta", "tipo de usuario creado");
			return salida;
		}
	}
	@GetMapping("/eliminartipousuario/{nombre}")
	public String eliminarTipoUsuario(@PathVariable String nombre) {
		if(iGestionUsuariosService.eliminarTipoUsuario(nombre)) {
		return "elimino";
		}else {
			return "no se pudo eliminar";
		}
	}
	@GetMapping("/eliminartipousuarioausuario")
	public String quitarTipoUsuarioAUsuario(@RequestBody JSONObject entrada) {
		System.out.println(entrada);
		if(iGestionUsuariosService.eliminarTipoUsuarioAUsuario(entrada.getAsString("cedula"),entrada.getAsString("nombre"))) {
		return "elimino";
		}else {
			return "no se pudo eliminar";
		}
	}
	@GetMapping("/listartiposusuario")
	public JSONArray listarTiposUsuario() {
		JSONArray salida = new JSONArray();
		List<TipoUsuario> tiposUsuario = iGestionUsuariosService.todosLosTipoUsuario();
		for (Iterator iterator = tiposUsuario.iterator(); iterator.hasNext();) {
			TipoUsuario tipoUsuario = (TipoUsuario) iterator.next();
			salida.add(tipoUsuario.toJson());
		}
		return salida;
	}

	@PostMapping("/asignartipousuario")
	public JSONObject asignarTipoUsuario(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		Usuario usuario = iGestionUsuariosService.buscarUsuario(entrada.getAsString("idUsuario"));
		TipoUsuario tipoUsuario = iGestionUsuariosService.buscarTipoUsuario(entrada.getAsString("idTipoUsuario"));
		if (usuario != null && tipoUsuario != null) {
			iGestionUsuariosService.asignarTipoUsuario(usuario, tipoUsuario);
			salida.put("respuesta", "ingresado correctamente");
		} else {
			salida.put("respuesta", "el usuario o el tipo de usuario no existe");
		}
		return salida;
	}

	@PostMapping("/asignardecano")
	public JSONObject asignarDecano(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		try {
		Facultad facultad = iGestionUsuariosService.buscarFacultad(Integer.parseInt(entrada.getAsString("facultad")));
		if (iGestionUsuariosService.asignarDecano(facultad, entrada.getAsString("decano"))) {
			salida.put("respuesta", "el decano fue ingresado correctamente");
		} else {
			
			salida.put("respuesta", "el usuario no tiene el rol necesario para ser decano ");
		}} catch (Exception e) {
			System.out.println(e.getMessage()+" v "+e.getStackTrace());
			salida.put("respuesta", "el id del decano tiene un error");
		}
		return salida;
	}
	@PostMapping("/eliminardecanofacultad")
	public boolean eliminarDecanoFacultad(@RequestBody JSONObject entrada) {
		return iGestionUsuariosService.eliminarDecanoFacultad(entrada.getAsString("cedula"),entrada.getAsString("facultad"));
		
	}
	@PostMapping("/asignarcoorinv")
	public JSONObject asignarCoorInv(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		try {
		Facultad facultad = iGestionUsuariosService.buscarFacultad(Integer.parseInt(entrada.getAsString("facultad")));
		if (iGestionUsuariosService.asignarCoorInv(facultad, entrada.getAsString("coorinv"))) {
			salida.put("respuesta", "el coordinador de investigaciones fue ingresado correctamente");
		} else {
			
			salida.put("respuesta", "el usuario no fue asignado existosamente");
		}} catch (Exception e) {
			System.out.println(e.getMessage()+" v "+e.getStackTrace());
			salida.put("respuesta", "el id del coorInv tiene un error");
		}
		return salida;
	}
	@PostMapping("/eliminarcoorinvfacultad")
	public boolean eliminarCoorInvFacultad(@RequestBody JSONObject entrada) {
		return iGestionUsuariosService.eliminarCoorInvFacultad(entrada.getAsString("cedula"),entrada.getAsString("facultad"));
		
	}
	@GetMapping("/existefacultad1/{id}")
	private boolean isExistFacultad(int id) {
		if (iGestionUsuariosService.existeFacultad(id)) {
			return true;
		} else {
			return false;
		}
	}
	@PostMapping("/asignardirectorprograma")
	public JSONObject asignarDirectorPrograma(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		try {
		Programa programa = iGestionUsuariosService.buscarPrograma(Integer.parseInt(entrada.getAsString("id")));
		if (iGestionUsuariosService.asignarDirectorPrograma(programa, entrada.getAsString("director"))) {
			salida.put("respuesta", "el director de progrma fue ingresado correctamente");
		} else {
			
			salida.put("respuesta", "el director de progrma no fue asignado existosamente");
		}} catch (Exception e) {
			System.out.println(e.getMessage()+" v "+e.getStackTrace());
			salida.put("respuesta", "el id del director de progrma tiene un error");
		}
		return salida;
	}
	@PostMapping("/asignardirectorgrupo")
	public JSONObject asignarDirectorGrupo(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		try {
		GrupoInvestigacion grupo = iGestionUsuariosService.buscarGrpo(Integer.parseInt(entrada.getAsString("id")));
		if (iGestionUsuariosService.asignarDirectorGrupo(grupo, entrada.getAsString("director"))) {
			salida.put("respuesta", "el director de grupo de investigaciones fue ingresado correctamente");
		} else {
			
			salida.put("respuesta", "el director de grupo no fue asignado existosamente");
		}} catch (Exception e) {
			System.out.println(e.getMessage()+" v "+e.getStackTrace());
			salida.put("respuesta", "el id del director de grupo tiene un error");
		}
		return salida;
	}
	@PostMapping("/removerdirectorgrupo")
	public boolean eliminarDirectorGrupo(@RequestBody JSONObject entrada) {
		return iGestionUsuariosService.eliminarDirectorGrupo(entrada.getAsString("cedula"),entrada.getAsString("grupo"));
	}
	@PostMapping("/asignarlidersemillero")
	public JSONObject asignarLiderSemillero(@RequestBody JSONObject entrada) {
		JSONObject salida = new JSONObject();
		try {
		Semillero semi = iGestionUsuariosService.buscarSemillero(Integer.parseInt(entrada.getAsString("id")));
		if (iGestionUsuariosService.asignarLiderSemillero(semi, entrada.getAsString("lider"))) {
			salida.put("respuesta", "el lider de semillero fue ingresado correctamente");
		} else {
			
			salida.put("respuesta", "el lider de semillero no fue asignado existosamente");
		}} catch (Exception e) {
			System.out.println(e.getMessage()+" v "+e.getStackTrace());
			salida.put("respuesta", "el id del lider de semillero tiene un error");
		}
		return salida;
	}
	@PostMapping("/removerlidersemillero")
	public boolean eliminarLiderSemillero(@RequestBody JSONObject entrada) {
		return iGestionUsuariosService.eliminarLiderSemillero(entrada.getAsString("cedula"),entrada.getAsString("semillero"));
	}
	@GetMapping("/existefacultad/{id}")
	private boolean isExistFacultad(Integer id) {
		if (iGestionUsuariosService.existeFacultad(id)) {
			return true;
		} else {
			return false;
		}
	}
	@PostMapping("/login")
	private JSONObject Login(@RequestBody JSONObject entrada) {
		JSONObject salida=new JSONObject();
		salida= iGestionUsuariosService.login(entrada.getAsString("correoEstudiantil"),entrada.getAsString("contrasena"),entrada.getAsString("tipoUsuario")); 
		if(salida.equals(null)) {
			salida.put("respuesta", "el usuario o contraseña son incorrectos ");		
		}
		return salida;
	
	}
	
	@GetMapping("/roles/{cedula}")
	private JSONArray roles(@PathVariable String cedula) {
		JSONArray salida = new JSONArray();
		List<TipoUsuario> tiposUsuario = iGestionUsuariosService.roles(cedula);
		for (Iterator iterator = tiposUsuario.iterator(); iterator.hasNext();) {
			TipoUsuario tipoUsuario = (TipoUsuario) iterator.next();
			salida.add(tipoUsuario.toJson());
		}
		return salida;
	}
	@GetMapping("/todosroles")
	private JSONArray todosRoles() {
		JSONArray salida = new JSONArray();
		List<TipoUsuario> tiposUsuario = iGestionUsuariosService.todosLosRoles();
		for (Iterator iterator = tiposUsuario.iterator(); iterator.hasNext();) {
			TipoUsuario tipoUsuario = (TipoUsuario) iterator.next();
			salida.add(tipoUsuario.toJson());
		}
		return salida;
	}
	@GetMapping("/litarusuariosportipo/{tipo}")
	private JSONArray litarUsuariosPorTipo(@PathVariable String tipo) {
		JSONArray salida = new JSONArray();
		List<Usuario> tiposUsuario = iGestionUsuariosService.todosPorRol(tipo);
		for (Iterator iterator = tiposUsuario.iterator(); iterator.hasNext();) {
			Usuario usuario = (Usuario) iterator.next();
			salida.add(usuario.toJson());
		}
		
		return salida;
	}
	}


package co.edu.usbbog.sgpi.service;

import java.awt.geom.Area;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import co.edu.usbbog.sgpi.model.AreaConocimiento;
import co.edu.usbbog.sgpi.model.Clase;
import co.edu.usbbog.sgpi.model.Convocatoria;
import co.edu.usbbog.sgpi.model.Evento;
import co.edu.usbbog.sgpi.model.Facultad;
import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.LineaInvestigacion;
import co.edu.usbbog.sgpi.model.Materia;
import co.edu.usbbog.sgpi.model.Programa;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.ProyectosConvocatoria;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.TipoProyecto;
import co.edu.usbbog.sgpi.model.TipoUsuario;
import co.edu.usbbog.sgpi.model.Usuario;
import co.edu.usbbog.sgpi.repository.IAreaConocimientoRepository;
import co.edu.usbbog.sgpi.repository.IClaseRepository;
import co.edu.usbbog.sgpi.repository.IConvocatoriaRepository;
import co.edu.usbbog.sgpi.repository.IEventoRepository;
import co.edu.usbbog.sgpi.repository.IFacultadRepository;
import co.edu.usbbog.sgpi.repository.IGrupoInvestigacionRepository;
import co.edu.usbbog.sgpi.repository.ILineaInvestigacionRepository;
import co.edu.usbbog.sgpi.repository.IMateriaRepository;
import co.edu.usbbog.sgpi.repository.IProgramaRepository;
import co.edu.usbbog.sgpi.repository.IProyectoRepository;
import co.edu.usbbog.sgpi.repository.ISemilleroRepository;
import co.edu.usbbog.sgpi.repository.ITipoUsuarioRepository;
import co.edu.usbbog.sgpi.repository.IUsuarioRepository;
import javassist.expr.NewArray;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Service
public class GestionInstitucionalService implements IGestionInstitucionalService {

	@Autowired
	private IGrupoInvestigacionRepository grupoIRepo;

	@Autowired
	private ISemilleroRepository semilleroRepo;

	@Autowired
	private IFacultadRepository facultadRepo;

	@Autowired
	private IProgramaRepository programaRepo;

	@Autowired
	private IMateriaRepository materiaRepo;

	@Autowired
	private IClaseRepository claseRepo;

	@Autowired
	private IUsuarioRepository usuarioRepo;
	
	@Autowired
	private IProyectoRepository proyectoRepo;
	
	@Autowired
	private ILineaInvestigacionRepository lineaRepo;
	
	@Autowired
	private IConvocatoriaRepository convocatoriaRepo;
	
	@Autowired
	private IAreaConocimientoRepository areaRepo;
	
	@Autowired
	private IEventoRepository eventoRepo;
	@Autowired
	private ITipoUsuarioRepository iTipoUsuarioRepository;
	


	private static Logger logger = LoggerFactory.getLogger(BibliotecaService.class);

	@Override
	public List<GrupoInvestigacion> todosLosGruposInvestigacion() {
		List<GrupoInvestigacion> grupoInvestigacion = grupoIRepo.findAll();
		return grupoInvestigacion;
	}

	@Override
	public boolean eliminarGrupoInvestigacion(int id) {
		JSONObject programa = grupoIRepo.findByPrograma(id);
		JSONObject linea = grupoIRepo.findByLinea(id);
		boolean grupo = grupoIRepo.existsById(id);		
		if(programa ==null && linea ==null && grupo ==true) {			
			grupoIRepo.deleteById(id);
			return true;
		}

		return false;
	}

	@Override
	public String crearGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion,String director) {
	
			Usuario dir = usuarioRepo.getById(director);
			
			TipoUsuario tipousuario = iTipoUsuarioRepository.getById("Lider grupo investigacion");

			if(!usuarioRepo.existsById(dir.getCedula())) {
				return "el usuario no existe";
			}
			List<TipoUsuario> tipo = dir.getTiposUsuario();
			for (Iterator iterator = tipo.iterator(); iterator.hasNext();) {
				TipoUsuario tipoUsuario = (TipoUsuario) iterator.next();
				if(tipoUsuario.getNombre().equals("Estudiante inactivo")) {
					return "usuario invalido 1";
				}
				if(tipoUsuario.getNombre().equals("Estudiante activo")) {
					return "usuario invalido 2";
				}
				if(tipoUsuario.getNombre().equals("Semillerista")) {
					return "usuario invalido 7";
				}
				if(tipoUsuario.getNombre().equals("Lider grupo investigacion")) {
					return "este usuario ya es lider de grupo";
				}
			}		
			grupoInvestigacion.setDirectorGrupo(dir);
			grupoIRepo.save(grupoInvestigacion);
			
			tipousuario.getUsuarios().add(dir);
			iTipoUsuarioRepository.save(tipousuario);
			return "se creo el grupo";	
	}
	
	@Override
	public String modificarGrupoI(int id, String nombre, String fecha_fun, String categoria, String fecha_cat, String director) {

		
		Usuario direc = usuarioRepo.getById(director);
		
		
		TipoUsuario tipousuariouno = iTipoUsuarioRepository.getById("Lider grupo investigacion");
		
		GrupoInvestigacion grupo = grupoIRepo.getById(id);
		
		
		
		
		if(nombre!="") {
			grupo.setNombre(nombre);
		}
		if(fecha_fun!="") {
			grupo.setFechaFun(LocalDate.parse(fecha_fun));
		}
		if(categoria!="") {
			grupo.setCategoria(categoria);
		}
		if(fecha_cat!="") {
			grupo.setFechaCat(LocalDate.parse(fecha_cat));
		}
		if(director!="") {
			usuarioRepo.deleteTipo(grupo.getDirectorGrupo().getCedula(), "Lider grupo investigacion");
			
			List<TipoUsuario> tipo = direc.getTiposUsuario();
			for (Iterator iterator = tipo.iterator(); iterator.hasNext();) {
				TipoUsuario tipoUsuario = (TipoUsuario) iterator.next();
				if(tipoUsuario.getNombre().equals("Estudiante inactivo")) {
					return "usuario invalido 1";
				}
				if(tipoUsuario.getNombre().equals("Estudiante activo")) {
					return "usuario invalido 2";
				}
				if(tipoUsuario.getNombre().equals("Semillerista")) {
					return "usuario invalido 3";
				}
				if(tipoUsuario.getNombre().equals("Lider grupo investigacion")) {
					return "este usuario ya es lider de grupo";
				}
			}
			grupo.setDirectorGrupo(direc);
			tipousuariouno.getUsuarios().add(direc);
			iTipoUsuarioRepository.save(tipousuariouno);
		}
		
		grupoIRepo.save(grupo);
		return "grupo actualizado";
		
	}
	
	
	@Override
	public GrupoInvestigacion grupoiporid(int id) {
		if(grupoIRepo.existsById(id)) {
			GrupoInvestigacion grupo = grupoIRepo.getById(id);
			return grupo;
		}
		return null;
		
		
	}

	@Override
	public boolean asignarProgramaAGrupoInvestigacion(int programa, int grupo_investigacion) {
		if(!grupoIRepo.existsById(grupo_investigacion)) {
			System.out.println("no existe el grupo");
			return false;
		}	
		if(!programaRepo.existsById(programa)) {
			System.out.println("no existe el programa con id: "+ programa);
			return false;
		}
		Programa pro = programaRepo.getById(programa);
		GrupoInvestigacion grupoInvestigacion = grupoIRepo.getById(grupo_investigacion);
		System.out.println(grupoInvestigacion);
		//grupoInvestigacion.setProgramas(new ArrayList<>());		
		grupoInvestigacion.getProgramas().add(pro);
		grupoIRepo.save(grupoInvestigacion);
		return true;
	}
	
	@Override
	public boolean desasignarProgramaAGrupoInvestigacion(int programa, int grupo_investigacion) {
		if(!grupoIRepo.existsById(grupo_investigacion)) {
			System.out.println("no existe el grupo");
			return false;
		}
		if(!programaRepo.existsById(programa)) {
			System.out.println("no existe el programa con id: "+ programa);
			return false;
		}	
		grupoIRepo.desAsignarPrograma(programa+"", grupo_investigacion+"");
		return true;
	}
	
	@Override
	public List<Programa> programaDelGrupo(int grupo_investigacion) {
		GrupoInvestigacion grupo = grupoIRepo.getById(grupo_investigacion);
		List<Programa> programas = grupo.getProgramas();
		if(programas == null) {
			programas = new ArrayList<Programa>();
		}
		return programas;
	}

	@Override
	public boolean asignarLineaAGrupoInvestigacion(String linea_investigacion, int grupo_investigacion) {
		if(!grupoIRepo.existsById(grupo_investigacion)) {
			System.out.println("no existe el grupo");
			return false;
		}	
		if(!lineaRepo.existsById(linea_investigacion)) {
			System.out.println("no existe la linea");
			return false;			
		}
		LineaInvestigacion linea = lineaRepo.getById(linea_investigacion);
		GrupoInvestigacion grupoInvestigacion = grupoIRepo.getById(grupo_investigacion);
		//grupoInvestigacion.setLineasInvestigacion(new ArrayList<>());	
		grupoInvestigacion.getLineasInvestigacion().add(linea);
		grupoIRepo.save(grupoInvestigacion);
		return true;
	}
	
	
	@Override
	public List<LineaInvestigacion> lineaDelGrupo(int grupo_investigacion) {
		GrupoInvestigacion grupo = grupoIRepo.getById(grupo_investigacion); 
		List<LineaInvestigacion> lineas = grupo.getLineasInvestigacion();
		
		if(lineas == null) {
			lineas = new ArrayList<LineaInvestigacion>();	
		}
		return lineas;
		
	}
	
	@Override
	public boolean desasignarLineaAGrupoInvestigacion(String linea_investigacion, int grupo_investigacion) {
		if(!grupoIRepo.existsById(grupo_investigacion)) {
			System.out.println("no existe el grupo");
			return false;
		}	
		if(!lineaRepo.existsById(linea_investigacion)) {
			System.out.println("no existe la linea");
			return false;			
		}
		System.out.println(linea_investigacion);
		System.out.println(grupo_investigacion);
		grupoIRepo.desAsignarLinea(linea_investigacion, grupo_investigacion+"");
		
		return true;
	}

	
	//semilleros---------------------------------------------------------------------------------------------
	
	
	@Override
	public List<Semillero> todosLosSemilleros() {
		List<Semillero> semilleros = semilleroRepo.findAll();
		return semilleros;
	}

	@Override
	public List<Semillero> todosLosSemillerosPorGrupoInvestigacion(int grupoInvestigacion) {
		GrupoInvestigacion sem = grupoIRepo.getById(grupoInvestigacion);
		List<Semillero> x = new ArrayList<>();
		if (sem != null) {
			System.out.println("if");
			List<Semillero> semillero = semilleroRepo.findByGrupoInvestigacion(grupoInvestigacion);
			return semillero;
		} else {
			System.out.println("else");
			return x;
		}
	}

	@Override
	public List<Semillero> todosLosSemillerosPorLiderSemillero(String lider) {
		Usuario usu = usuarioRepo.getById(lider);
		List<Semillero> x = new ArrayList<>();
		if (usu != null) {
			List<Semillero> semillero = semilleroRepo.findByLiderSemillero(lider);
			return semillero;
		} else {
			logger.info("El tipo no existe");
			return x;
		}
	}

	@Override
	public List<Semillero> todosLosSemillerosPorLineaInvestigacion(String lineaInvestigacion) {
		LineaInvestigacion linea = lineaRepo.getById(lineaInvestigacion);
		List<Semillero> x = new ArrayList<>();
		if (linea != null) {
			List<Semillero> semillero = semilleroRepo.findByLineaInvestigacion(lineaInvestigacion);
			return semillero;
		} else {
			logger.info("El tipo no existe");
			return x;
		}
	}

	@Override
	public boolean eliminarSemillero(int id) {
		List<JSONObject> programa = semilleroRepo.findByPrograma(id);	
		boolean semillero = semilleroRepo.existsById(id);
		if(programa.isEmpty() && semillero ==true) {
			semilleroRepo.deleteById(id);
			return !semilleroRepo.existsById(id);
		}
		return false;
		
	}

	@Override
	public String crearSemillero(Semillero semillero, int grupo, String lider, String linea) {
		Usuario lid = usuarioRepo.getById(lider);
		
		TipoUsuario tipousuario = iTipoUsuarioRepository.getById("Docente lider semillero");
		
		GrupoInvestigacion gru = grupoIRepo.getById(grupo);
		LineaInvestigacion lin = lineaRepo.getById(linea);
		
		if(!usuarioRepo.existsById(lid.getCedula())) {
			return "el usuario no existe";
		}
		if(!grupoIRepo.existsById(gru.getId())) {
			return "el grupo no existe";
		}
		if(!lineaRepo.existsById(lin.getNombre())) {
			return "la linea no existe";
		}
		
		List<TipoUsuario> tipo = lid.getTiposUsuario();
		for (Iterator iterator = tipo.iterator(); iterator.hasNext();) {
			TipoUsuario tipoUsuario = (TipoUsuario) iterator.next();
			if(tipoUsuario.getNombre().equals("Estudiante inactivo")) {
				return "usuario invalido 1";
			}
			if(tipoUsuario.getNombre().equals("Estudiante activo")) {
				return "usuario invalido 2";
			}
			if(tipoUsuario.getNombre().equals("Semillerista")) {
				return "usuario invalido 3";
			}
			if(tipoUsuario.getNombre().equals("Docente lider semillero")) {
				return "este usuario ya es lider de semillero";
			}
		}
		
		semillero.setLiderSemillero(lid);
		semillero.setGrupoInvestigacion(gru);
		semillero.setLineaInvestigacion(lin);
		semilleroRepo.save(semillero);
		
		tipousuario.getUsuarios().add(lid);
		iTipoUsuarioRepository.save(tipousuario);
		return "se creo el semillero";
	}
	@Override
	public String modificarSemillero(int id, String nombre, String descripcion, String fechaFun, String grupoi, String lineai, String liderSemillero) {

		Usuario lider = usuarioRepo.getById(liderSemillero);
		
		TipoUsuario tipousuariouno = iTipoUsuarioRepository.getById("Docente lider semillero");
		
		Semillero semillero = semilleroRepo.getById(id);
		GrupoInvestigacion gru = grupoIRepo.getById(Integer.parseInt(grupoi));
		LineaInvestigacion lin = lineaRepo.getById(lineai);
		
		if(nombre!="") {
			semillero.setNombre(nombre);
		}
		if(descripcion!="") {
			semillero.setDescripcion(descripcion);
		}
		if(fechaFun!="") {
			semillero.setFechaFun(LocalDate.parse(fechaFun));;
		}
		if(grupoi!="") {
			semillero.setGrupoInvestigacion(gru);
		}
		if(lineai!="") {
			semillero.setLineaInvestigacion(lin);
		}
		if(liderSemillero!="") {
			usuarioRepo.deleteTipo(semillero.getLiderSemillero().getCedula(), "Docente lider semillero");
			
			List<TipoUsuario> tipo = lider.getTiposUsuario();
			for (Iterator iterator = tipo.iterator(); iterator.hasNext();) {
				TipoUsuario tipoUsuario = (TipoUsuario) iterator.next();
				if(tipoUsuario.getNombre().equals("Estudiante inactivo")) {
					return "usuario invalido 1";
				}
				if(tipoUsuario.getNombre().equals("Estudiante activo")) {
					return "usuario invalido 2";
				}
				if(tipoUsuario.getNombre().equals("Semillerista")) {
					return "usuario invalido 3";
				}
				if(tipoUsuario.getNombre().equals("Docente lider semillero")) {
					return "este usuario ya es lider de semillero";
				}
			}
			semillero.setLiderSemillero(lider);
			tipousuariouno.getUsuarios().add(lider);
			iTipoUsuarioRepository.save(tipousuariouno);
		}
		
		semilleroRepo.save(semillero);
		return "semillero actualizado";
	}
	
	@Override
	public Semillero semilleroporid(int id) {
		if(semilleroRepo.existsById(id)) {
			JSONObject salida = new JSONObject();
			Semillero semillero = semilleroRepo.getById(id);
			LineaInvestigacion li = lineaRepo.getById(semillero.getLineaInvestigacion().getNombre());
			
			salida=li.toJson();

			return semillero;
		}
		return null;
		
		
	}

	@Override
	public boolean asignarSemilleroAPrograma(int programa, int semillero) {
		if(!semilleroRepo.existsById(semillero)) {
			System.out.println("no existe el semillero");
			return false;
		}
		if(!programaRepo.existsById(programa)) {
			System.out.println("no existe el programa");
			return false;
		}
		Programa pro = programaRepo.getById(programa);
		Semillero semi = semilleroRepo.getById(semillero);
		pro.setSemilleros(new ArrayList<>());
		pro.getSemilleros().add(semi);
		programaRepo.save(pro);
		return true;
	}

	@Override
	public boolean desasignarSemilleroAPrograma(int programa, int semillero) {
		if(!semilleroRepo.existsById(semillero)) {
			System.out.println("no existe el semillero" + semillero);
			return false;
		}
		if(!programaRepo.existsById(programa)) {
			System.out.println("no existe el programa" + programa);
			return false;
		}
		semilleroRepo.desAsignarPrograma(programa+"", semillero+"");
		
		return true;
	}
	
	@Override
	public List<Programa> programaDelSemillero(int semillero) {
		Semillero semi = semilleroRepo.getById(semillero);
		List<Programa> programas = semi.getProgramas();
		if(programas.equals(null)) {
			programas = new ArrayList<Programa>();
		}		
		return programas;
	}
	
	@Override
	public boolean asignarSemilleroAUsuario(String cedula,int semillero) {
		Usuario usu=usuarioRepo.getById(cedula);
		Semillero semi=semilleroRepo.getById(semillero);
		usu.setSemilleroId(semi);
		usuarioRepo.save(usu);
		return usuarioRepo.existsById(usu.getCedula());
	}
	
	@Override
	public boolean desasignarSemilleroAUsuario(String cedula) {
		Usuario usu=usuarioRepo.getById(cedula);
		semilleroRepo.setSemilleroNullById(cedula);
		return usuarioRepo.existsById(usu.getCedula());
	}
	
	@Override
	public List<JSONObject> contarSemilleros() {
		List<JSONObject> semilleros = semilleroRepo.contarSemilleros();	
		return semilleros;
	}
	
	/*
	@Override
	public List<Clase> usuariosPorSemillero(int semillero) {
		List<Clase> x = new ArrayList<>();
		if(!semilleroRepo.existsById(semillero)){

		List<>
		return true;
	}
	

	@Override
	public List<Clase> proyectosPorSemillero(int semillero) {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	//facultades--------------------------------------------------------------------------------
	
	
	@Override
	public List<Facultad> todasLasFacultades() {
		List<Facultad> facultades = facultadRepo.findAll();
		if (facultades.equals(null)) {
			facultades = new ArrayList<Facultad>();
		}
		
		return facultades;
	}

	@Override
	public boolean eliminarFacultad(int id) {
		List<Programa> programas = programaRepo.findByFacultad(id);
		boolean facultad = facultadRepo.existsById(id);	
		if (facultad==true && programas.isEmpty()) {
			facultadRepo.deleteById(id);
			return !facultadRepo.existsById(id);
			
		}
		return false;
	}

	@Override
	public String crearFacultad(Facultad facultad, String coordinador, String decano) {
		Usuario deca = usuarioRepo.getById(decano);
		Usuario coor = usuarioRepo.getById(coordinador);
		
		
		TipoUsuario tipousuariouno = iTipoUsuarioRepository.getById("Lider investigacion facultad");
		TipoUsuario tipousuariodos = iTipoUsuarioRepository.getById("Coordinador investigacion facultad");
		
		if(!usuarioRepo.existsById(deca.getCedula())) {
			return "el decano no existe";
		}
		if(!usuarioRepo.existsById(coor.getCedula())) {
			return "el coordinador no existe";
		}
		
		List<TipoUsuario> tipouno = deca.getTiposUsuario();
		for (Iterator iterator = tipouno.iterator(); iterator.hasNext();) {
			TipoUsuario tipoUsuario = (TipoUsuario) iterator.next();
			if(tipoUsuario.getNombre().equals("Estudiante inactivo")) {
				return "usuario invalido 1";
			}
			if(tipoUsuario.getNombre().equals("Estudiante activo")) {
				return "usuario invalido 2";
			}
			if(tipoUsuario.getNombre().equals("Semillerista")) {
				return "usuario invalido 3";
			}
			if(tipoUsuario.getNombre().equals("Lider investigacion facultad")) {
				return "este usuario ya es Lider investigacion facultad";
			}
		}
		
		List<TipoUsuario> tipodos = coor.getTiposUsuario();
		for (Iterator iterator = tipodos.iterator(); iterator.hasNext();) {
			TipoUsuario tipoUsuario = (TipoUsuario) iterator.next();
			if(tipoUsuario.getNombre().equals("Estudiante inactivo")) {
				return "usuario invalido 4";
			}
			if(tipoUsuario.getNombre().equals("Estudiante activo")) {
				return "usuario invalido 5";
			}
			if(tipoUsuario.getNombre().equals("Semillerista")) {
				return "usuario invalido 6";
			}
			if(tipoUsuario.getNombre().equals("Coordinador investigacion facultad")) {
				return "este usuario ya es Coordinador investigacion facultad";
			}
		}
		
		
		facultad.setCoorInv(coor);
		facultad.setDecano(deca);
		facultadRepo.save(facultad);
		
		
		tipousuariouno.getUsuarios().add(deca);
		iTipoUsuarioRepository.save(tipousuariouno);
		
		tipousuariodos.getUsuarios().add(coor);
		iTipoUsuarioRepository.save(tipousuariodos);
		
		return "se creo la facultad";
	}

	@Override
	public String modificarFacultad(int id, String nombre, String coordinador, String decano) {

		Usuario deca = usuarioRepo.getById(decano);
		Usuario coor = usuarioRepo.getById(coordinador);

		TipoUsuario tipousuariouno = iTipoUsuarioRepository.getById("Lider investigacion facultad");
		TipoUsuario tipousuariodos = iTipoUsuarioRepository.getById("Coordinador investigacion facultad");
		
		Facultad facultad = facultadRepo.getById(id);
		System.out.println("decano" + facultad.getDecano().getCedula());
		System.out.println("coordinador" + facultad.getCoorInv().getCedula());
		
		
		
		if(nombre!="") {
			facultad.setNombre(nombre);
		}
		
		if(decano!="") {
			usuarioRepo.deleteTipo(facultad.getDecano().getCedula(), "Lider investigacion facultad");
			
			List<TipoUsuario> tipouno = deca.getTiposUsuario();
			for (Iterator iterator = tipouno.iterator(); iterator.hasNext();) {
				TipoUsuario tipoUsuario = (TipoUsuario) iterator.next();
				if(tipoUsuario.getNombre().equals("Estudiante inactivo")) {
					return "usuario invalido 1";
				}
				if(tipoUsuario.getNombre().equals("Estudiante activo")) {
					return "usuario invalido 2";
				}
				if(tipoUsuario.getNombre().equals("Semillerista")) {
					return "usuario invalido 3";
				}
				if(tipoUsuario.getNombre().equals("Lider investigacion facultad")) {
					return "la falcultad no fue creada el primer usuario ya es Lider investigacion facultad";
				}
			}
			
			facultad.setDecano(deca);
			tipousuariouno.getUsuarios().add(deca);
			iTipoUsuarioRepository.save(tipousuariouno);
			
		}
		if(coordinador!="") {
			usuarioRepo.deleteTipo(facultad.getCoorInv().getCedula(), "Coordinador investigacion facultad");
			
			List<TipoUsuario> tipodos = coor.getTiposUsuario();
			for (Iterator iterator = tipodos.iterator(); iterator.hasNext();) {
				TipoUsuario tipoUsuario = (TipoUsuario) iterator.next();
				if(tipoUsuario.getNombre().equals("Estudiante inactivo")) {
					return "usuario invalido 4";
				}
				if(tipoUsuario.getNombre().equals("Estudiante activo")) {
					return "usuario invalido 5";
				}
				if(tipoUsuario.getNombre().equals("Semillerista")) {
					return "usuario invalido 6";
				}
				if(tipoUsuario.getNombre().equals("Coordinador investigacion facultad")) {
					return "la falcultad no fue creada el segundo ya es Coordinador investigacion facultad";
				}
			}
			facultad.setCoorInv(coor);
			tipousuariodos.getUsuarios().add(coor);
			iTipoUsuarioRepository.save(tipousuariodos);
		}
		
		facultadRepo.save(facultad);
		return "facultad actualizada";
	}
	
	@Override
	public Facultad facultadporid(int id) {
		if(facultadRepo.existsById(id)) {
			Facultad facultad = facultadRepo.getById(id);

			return facultad;
		}
		return null;
	}
	
	@Override
	public List<Programa> todosLosProgramas() {
		List<Programa> programas = programaRepo.findAll();
		return programas;
	}

	@Override
	public List<Programa> todosLosProgramasPorFacultad(int facultad) {
		List<Programa> programas = programaRepo.findByFacultad(facultad);
		return programas;
	}

	@Override
	public List<Programa> todosLosProgramasPorDirector(String usuario) {

		if (existeUsuario(usuario)) {
			List<Programa> programas = programaRepo.findByDirector(usuario);
			return programas;
		} else {
			return null;
		}

	}

	@Override
	public boolean eliminarPrograma(int id) {
		
		List<JSONObject> grupo = programaRepo.findByGrupo(id);
		List<JSONObject> semillero = programaRepo.findBySemillero(id);
		List<JSONObject> usuario = programaRepo.findByUsuario(id);		
		boolean programa = programaRepo.existsById(id);
		System.out.println(grupo.isEmpty() + " " + semillero.isEmpty() + " " +  usuario.isEmpty() + " " + programa);
		System.out.println(usuario);
		if(grupo.isEmpty() && semillero.isEmpty() && usuario.isEmpty() && programa == true) {
			programaRepo.deleteById(id);
			return true;
		}
		
		return false;

	}

	
	@Override
	public String crearPrograma(Programa programa, int facultad, String director) {
		
		Usuario direc = usuarioRepo.getById(director);
		
		TipoUsuario tipousuario = iTipoUsuarioRepository.getById("Director programa");
		
		Facultad facul = facultadRepo.getById(facultad);
		
		
		
		if(!facultadRepo.existsById(facul.getId())) {
			return "la facultad no existe";
		}
		if(!usuarioRepo.existsById(direc.getCedula())) {
			return "el usuario no existe";
		}
		
		List<TipoUsuario> tipo = direc.getTiposUsuario();
		for (Iterator iterator = tipo.iterator(); iterator.hasNext();) {
			TipoUsuario tipoUsuario = (TipoUsuario) iterator.next();
			if(tipoUsuario.getNombre().equals("Estudiante inactivo")) {
				return "usuario invalido 1";
			}
			if(tipoUsuario.getNombre().equals("Estudiante activo")) {
				return "usuario invalido 2";
			}
			if(tipoUsuario.getNombre().equals("Semillerista")) {
				return "usuario invalido 3";
			}
			if(tipoUsuario.getNombre().equals("Docente lider semillero")) {
				return "este usuario ya es lider de semillero";
			}
		}
		
		
		programa.setFacultadId(facul);
		programa.setDirector(direc);
		programaRepo.save(programa);
		
		tipousuario.getUsuarios().add(direc);
		iTipoUsuarioRepository.save(tipousuario);
		return "se creo el programa";
	}
	
	
	@Override
	public String modificarPrograma(int id, String nombre, String facultad, String director) {
		
		Usuario direc = usuarioRepo.getById(director);
		
		TipoUsuario tipousuariouno = iTipoUsuarioRepository.getById("Director programa");
		
		Programa programa = programaRepo.getById(id);
		Facultad facul = facultadRepo.getById(Integer.parseInt(facultad));
		
		if(nombre!="") {
			programa.setNombre(nombre);
		}
		if(facultad!="") {
			programa.setFacultadId(facul);
		}
		if(director!="") {
			usuarioRepo.deleteTipo(programa.getDirector().getCedula(), "Director programa");
			
			List<TipoUsuario> tipo = direc.getTiposUsuario();
			for (Iterator iterator = tipo.iterator(); iterator.hasNext();) {
				TipoUsuario tipoUsuario = (TipoUsuario) iterator.next();
				if(tipoUsuario.getNombre().equals("Estudiante inactivo")) {
					return "usuario invalido 1";
				}
				if(tipoUsuario.getNombre().equals("Estudiante activo")) {
					return "usuario invalido 2";
				}
				if(tipoUsuario.getNombre().equals("Semillerista")) {
					return "usuario invalido 3";
				}
				if(tipoUsuario.getNombre().equals("Docente lider semillero")) {
					return "este usuario ya es lider de semillero";
				}
			}
			
			programa.setDirector(direc);
			tipousuariouno.getUsuarios().add(direc);
			iTipoUsuarioRepository.save(tipousuariouno);
		}
		
		programaRepo.save(programa);
		return "se actualizo el programa";
	}
	
	@Override
	public Programa programaporid(int id) {


			Programa programa = programaRepo.getById(id);

return programa;
		
		
	}
	@Override
	public List<GrupoInvestigacion> gruposDelPrograma(int programa) {
		Programa pro = programaRepo.getById(programa);
		List<GrupoInvestigacion> grupos = pro.getGruposInvestigacion();
		if(grupos.equals(null)) {
			grupos = new ArrayList<GrupoInvestigacion>();
		}
		return grupos;
	}

	@Override
	public List<Semillero> semillerosDelPrograma(int programa) {
		Programa pro = programaRepo.getById(programa);
		List<Semillero> semilleros = pro.getSemilleros();
		if(semilleros.equals(null)) {
			semilleros = new ArrayList<Semillero>();			
		}
		return semilleros;
	}
	
	
	//MATERIAS------------------------------------------------------------------------------------------
	
	@Override
	public List<Materia> todasLasMaterias() {
		List<Materia> materias = materiaRepo.findAll();
		
		return materias;
	}

	@Override
	public List<Materia> todasLasMateriasPorPrograma(int programa) {
		List<Materia> materia = materiaRepo.findByPrograma(programa);
		return materia;
	}
	

	@Override
	public boolean eliminarMateria(String catalogo) {
		List<JSONObject> clase = materiaRepo.findByClase(catalogo);
		
		boolean materia = materiaRepo.existsById(catalogo);
		if(clase.isEmpty() && materia == true) {
			materiaRepo.deleteById(catalogo);
			return true;
			}
		return false;
	}

	@Override
	public boolean crearMateria(Materia materia, int programa) {
		Programa pro = programaRepo.getById(programa);
		if(!programaRepo.existsById(pro.getId())) {
			return false;
		}
		materia.setPrograma(pro);
		materiaRepo.save(materia);
		return materiaRepo.existsById(materia.getCatalogo());
	}
	
	@Override
	public boolean modificarMateria(String catalogo, String nombre, String programa) {
		Programa pro = programaRepo.getById(Integer.parseInt(programa));
		
		Materia materia = materiaRepo.getById(catalogo);
		
		
		if(catalogo!="") {
			materia.setCatalogo(catalogo);
		}
		
		if(nombre!="") {
			materia.setNombre(nombre);
		}
		
		if(programa!="") {
			materia.setPrograma(pro);
		}
		materiaRepo.save(materia);
		return !materiaRepo.existsById(catalogo);
	}

	@Override
	public Materia materiaporid(String catalogo) {
	
			Materia materia = materiaRepo.getById(catalogo);
			System.out.println(catalogo);
			return materia;

		
		
	}
	
	
	
	//CLASES-------------------------------------------------------------------------------------------------
	
	@Override
	public List<Clase> todasLasClases() {

		List<Clase> clases = claseRepo.findAll();
		return clases;
	}

	@Override
	public List<Clase> clasesPorProfesor(String profesor) {
		if (existeUsuario(profesor)) {
			List<Clase> clases = claseRepo.findByProfesor(profesor);
			return clases;
		} else {
			return null;
		}

	}

	@Override
	public List<Clase> clasesPorMateria(String materia) {

		if (materiaRepo.findById(materia)!=null) {
			List<Clase> clases = claseRepo.findByMateria(materia);
			return clases;
		} else {
			return null;
		}
	}

	@Override
	public boolean eliminarClase(int numero) {
		List<JSONObject> profesor = claseRepo.findByProyecto(numero);
		boolean clase = claseRepo.existsById(numero);
		if(profesor.isEmpty() && clase == true) {
			claseRepo.deleteById(numero);
			return true;
			}
		return false;
	}
	
	@Override
	public String crearClase(Clase clase, String materia, String profesor) {
		Usuario profe = usuarioRepo.getById(profesor);
		
		TipoUsuario tipousuario = iTipoUsuarioRepository.getById("Docente");
		
		Materia mate = materiaRepo.getById(materia);
		if(!usuarioRepo.existsById(profe.getCedula())) {
			return "el usuario no existe"; 	
		}
		if(!materiaRepo.existsById(mate.getCatalogo())) {
			return "la materia no existe";
		}
		
		List<TipoUsuario> tipo = profe.getTiposUsuario();
		for (Iterator iterator = tipo.iterator(); iterator.hasNext();) {
			TipoUsuario tipoUsuario = (TipoUsuario) iterator.next();
			if(tipoUsuario.getNombre().equals("Estudiante inactivo")) {
				return "usuario invalido 1";
			}
			if(tipoUsuario.getNombre().equals("Estudiante activo")) {
				return "usuario invalido 2";
			}
			if(tipoUsuario.getNombre().equals("Semillerista")) {
				return "usuario invalido 3";
			}
			if(tipoUsuario.getNombre().equals("Docente lider semillero")) {
				return "este usuario ya es lider de semillero";
			}
		}
		
		clase.setMateria(mate);
		clase.setProfesor(profe);
		claseRepo.save(clase);
		
		tipousuario.getUsuarios().add(profe);
		iTipoUsuarioRepository.save(tipousuario);
		return "se creo la clase";
	}
	
	public String modificarClase(int numero, String nombre, String semestre, String materia, String profesor) {
		

			Usuario profe = usuarioRepo.getById(profesor);
			
			TipoUsuario tipousuariouno = iTipoUsuarioRepository.getById("Docente");
			
			Clase clase = claseRepo.getById(numero);
			Materia mate = materiaRepo.getById(materia);
			
			if(nombre!="") {
				clase.setNombre(nombre);
			}
			
			if(semestre!="") {
				clase.setSemestre(semestre);
			}
			
			if(materia!="") {
				clase.setMateria(mate);
			}
			
			if(profesor!="") {
				
				List<TipoUsuario> tipo = profe.getTiposUsuario();
				for (Iterator iterator = tipo.iterator(); iterator.hasNext();) {
					TipoUsuario tipoUsuario = (TipoUsuario) iterator.next();
					if(tipoUsuario.getNombre().equals("Estudiante inactivo")) {
						return "usuario invalido 1";
					}
					if(tipoUsuario.getNombre().equals("Estudiante activo")) {
						return "usuario invalido 2";
					}
					if(tipoUsuario.getNombre().equals("Semillerista")) {
						return "usuario invalido 3";
					}
					if(tipoUsuario.getNombre().equals("Docente lider semillero")) {
						return "este usuario ya es lider de semillero";
					}
				}
				clase.setProfesor(profe);
				tipousuariouno.getUsuarios().add(profe);
				iTipoUsuarioRepository.save(tipousuariouno);
			}
			claseRepo.save(clase);
			return "clase actualizada";
		
	}
	
	@Override
	public Clase claseporid(int id) {	
		Clase clase = claseRepo.getById(id);

		return clase;
		
		
	}
	
	@Override
	public boolean asignarProyectosAClase(int proyecto, int clase) {
		if(!proyectoRepo.existsById(proyecto)) {
			return false;
		}
		if(!claseRepo.existsById(clase)) {
			return false;			
		}
		Proyecto pro = proyectoRepo.getById(proyecto);
		Clase cla = claseRepo.getById(clase);
		cla.setProyectos(new ArrayList<>());
		cla.getProyectos().add(pro);
		claseRepo.save(cla);
		pro.setClases(new ArrayList<>());
		pro.getClases().add(cla);
		proyectoRepo.save(pro);
		return true;
	}


	@Override
	public List<Proyecto> proyectosPorClase(int clase) {
		Clase cla = claseRepo.getById(clase);
		List<Proyecto> proyectos = cla.getProyectos();
		if(proyectos.equals(null)) {
			proyectos = new ArrayList<Proyecto>();
		}
		return proyectos;
	}

	
	@Override
	public boolean desasignarProyectosAClase(int proyecto, int clase) {

		if(!claseRepo.existsById(clase)) {
			return false;
		}
		if(!proyectoRepo.existsById(proyecto)) {
			return false;
		}
		claseRepo.desAsignarProyecto(clase+"", proyecto + "");
		return true;
	}
	
	
	
	@Override
	public List<Convocatoria> todasLasConvocatoriasAbiertas(String estado) {
		List<Convocatoria> con= convocatoriaRepo.findByEstadoAbierto(estado);
		return con;
	}
	
	@Override
	public Convocatoria convocatoriaPorID(int id) {
		if(convocatoriaRepo.existsById(id)) {
			Convocatoria convocatoria = convocatoriaRepo.getById(id);
			return convocatoria;
		}
		return null;
	}
	
	//lineas////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<LineaInvestigacion> todasLasLineas() {
		List<LineaInvestigacion> lineas = lineaRepo.findAll();
		
		return lineas;
	} 
	@Override
	public boolean crearLinea(LineaInvestigacion linea, LocalDate fecha) {
		linea.setFecha(fecha);
		lineaRepo.save(linea);
		return lineaRepo.existsById(linea.getNombre());
	}
	public boolean modificarLinea(String nombre, String descripcion, String fecha) {
			LineaInvestigacion linea = lineaRepo.getById(nombre);
			
			if(descripcion!=""){
				linea.setDescripcion(descripcion);
			}
			
			if(fecha!=""){
				linea.setFecha(LocalDate.parse(fecha));
			}
		lineaRepo.save(linea);
		return !lineaRepo.existsById(linea.getNombre());
	}
	@Override
	public LineaInvestigacion lineaporid(String id) {
		LineaInvestigacion linea = lineaRepo.getById(id);
		return linea;		
	}

	@Override
	public boolean eliminarLinea(String nombre) {
		List<Semillero> semilleros= semilleroRepo.findByLineaInvestigacion(nombre);
		List<JSONObject> grupos = lineaRepo.findByGrupoInvestigacion(nombre);
		boolean linea = lineaRepo.existsById(nombre);
		if(semilleros.isEmpty() && linea == true && grupos.isEmpty()) {
			lineaRepo.deleteById(nombre);
			return true;
			}
		return false;
	}
	
	
	
	
	
	//Areas de conocimiento////////////////////////////////////////////////////////////////////////////
	@Override
	public List<AreaConocimiento> todasLasAreasConocimiento() {
		List<AreaConocimiento> areas = areaRepo.findAll();	
		return areas;
	}
	@Override
	public boolean crearArea(AreaConocimiento area, String gran_area) {
		
		area.setGranArea(gran_area);
		areaRepo.save(area);
		return true;
	}
	@Override
	public boolean modificarArea(int id, String nombre, String gran_area, String descripcion) {
		AreaConocimiento area = areaRepo.getById(id);
		if(nombre!=""){
			area.setNombre(nombre);
		}
		
		if(gran_area!=""){
			area.setGranArea(gran_area);
		}
		
		if(descripcion!=""){
			area.setDescripcion(descripcion);
		}
	areaRepo.save(area);
	return !areaRepo.existsById(area.getId());
}
	
	@Override
	public AreaConocimiento areaporid(int id) {
		AreaConocimiento area = areaRepo.getById(id);
		return area;
	}
	
	@Override
	public boolean eliminarArea(int id) {
		List<JSONObject> proyectos = areaRepo.findByProyecto(id);
		boolean area = areaRepo.existsById(id);
		if(proyectos.isEmpty() && area == true ) {
			areaRepo.deleteById(id);
			return true;
			}
		return false;
	}
	
	
	
	
	
	//EVENTOS ////////////////////////////////////////////////////////////////////////////////////////////
	@Override
	public List<Evento> todosLosEventos() {
		List<Evento> eventos = eventoRepo.findAll();	
		return eventos;
	}
	@Override
	public List<JSONObject> contarEventos() {
		List<JSONObject> eventos = eventoRepo.contarEventos();	
		return eventos;
	}
	@Override
	public boolean crearEvento(Evento evento, String entidad, String sitio_web, String url_memoria) {
		evento.setEntidad(entidad);
		evento.setSitioWeb(sitio_web);
		evento.setUrlMemoria(url_memoria);
		eventoRepo.save(evento);
		return eventoRepo.existsById(evento.getId());
	}
	
	

	@Override
	public boolean modificarEvento(int id, String nombre, String fecha, String entidad, String estado, String sitio_web, String url_memoria) {
		Evento evento = eventoRepo.getById(id);
		if(nombre!=""){
			evento.setNombre(nombre);
		}
		if(fecha!=""){
			evento.setFecha(LocalDate.parse(fecha));;
		}
		if(entidad!=""){
			evento.setEntidad(entidad);
		}
		if(estado!=""){
			evento.setEstado(estado);
		}
		if(sitio_web!=""){
			evento.setSitioWeb(sitio_web);
		}
		if(url_memoria!=""){
			evento.setUrlMemoria(url_memoria);
		}
		eventoRepo.save(evento);
		return !eventoRepo.existsById(evento.getId());
}
	
	@Override
	public Evento eventoporid(int id) {
		Evento evento = eventoRepo.getById(id);
		return evento;
	}
	
	@Override
	public boolean eliminarEvento(int id) {
		List<JSONObject> participaciones = eventoRepo.findByParticipaciones(id);
		boolean evento = eventoRepo.existsById(id);
		if(participaciones.isEmpty() && evento == true ) {
			eventoRepo.deleteById(id);
			return true;
			}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// METODOS DE SI EXISTE
	@Override
	public boolean existeUsuario(String cedula) {
		if (usuarioRepo.findById(cedula).isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Usuario> usuariosPorSemillero(int semillero) {
		Semillero semi = semilleroRepo.getById(semillero);
		List<Usuario> usuarios = semi.getUsuarios();
		if(usuarios.equals(null)) {
			usuarios = new ArrayList<Usuario>();
		}
		return usuarios;	
	}

	@Override
	public List<Proyecto> proyectosPorSemillero(int semillero) {
		Semillero semi = semilleroRepo.getById(semillero);
		List<Proyecto> proyectos = semi.getProyectos();	
		if(proyectos.equals(null)) {
			proyectos = new ArrayList<Proyecto>();
		}

		return proyectos;	
	}

	@Override
	public List<ProyectosConvocatoria> proyectosPorConvocatoria(int convocatoria) {
		Convocatoria con = convocatoriaRepo.getById(convocatoria);
		List<ProyectosConvocatoria> proyectos = con.getProyectosConvocatorias();
	
		if(proyectos.equals(null)) {
			proyectos = new ArrayList<ProyectosConvocatoria>();		
		}
		return proyectos;
	}
	@Override
	public List<Convocatoria> todasLasConvocatorias() {
		List<Convocatoria> convocatorias = convocatoriaRepo.findAll();	
		return convocatorias;
	}
	
	@Override
	public boolean crearConvocatoria(Convocatoria convocatoria, String numero_productos, String entidad) {
		convocatoria.setNumeroProductos(numero_productos);
		convocatoria.setEntidad(entidad);
		convocatoriaRepo.save(convocatoria);
		if(convocatoria.getFechaFinal().isBefore(convocatoria.getFechaInicio())) {
			return false;
		}
		return true;
	}

	@Override
	public boolean modificarConvocatoria(int id, String nombre_convocatoria, String fecha_inicio, String fecha_final, String contexto, String numero_productos, String estado, String tipo, String entidad) {
		
		Convocatoria convocatoria = convocatoriaRepo.getById(id);
		if(nombre_convocatoria!=""){
			convocatoria.setNombreConvocatoria(nombre_convocatoria);
		}
		if(fecha_inicio!=""){
			convocatoria.setFechaInicio(LocalDate.parse(fecha_inicio));
		}
		if(fecha_final!=""){
			convocatoria.setFechaFinal(LocalDate.parse(fecha_final));
		}
		if(contexto!=""){
			convocatoria.setContexto(contexto);
		}
		if(numero_productos!=""){
			convocatoria.setNumeroProductos(numero_productos);
		}
		if(estado!=""){
			convocatoria.setEstado(estado);
		}
		if(tipo!=""){
			convocatoria.setTipo(tipo);
		}
		if(entidad!=""){
			convocatoria.setEntidad(entidad);
		}
		convocatoriaRepo.save(convocatoria);
		return convocatoriaRepo.existsById(convocatoria.getId());
}
	
	@Override
	public Convocatoria convocatoriaporid(int id) {
		Convocatoria convocatoria = convocatoriaRepo.getById(id);
		Evento evento = eventoRepo.getById(id);
		return convocatoria;
	}
	
	@Override
	public boolean eliminarConvocatoria(int id) {
		boolean convo = convocatoriaRepo.existsById(id);		
		if(convo ==true) {			
			convocatoriaRepo.deleteById(id);;
			return true;
		}
		return false;
	}

	@Override
	public List<TipoUsuario> listarRoles() {
		List<TipoUsuario> tipo= iTipoUsuarioRepository.listarRoles();
		return tipo;
	}

	
	
	

	

}

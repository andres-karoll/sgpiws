package co.edu.usbbog.sgpi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usbbog.sgpi.model.Clase;
import co.edu.usbbog.sgpi.model.Convocatoria;
import co.edu.usbbog.sgpi.model.Facultad;
import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.LineaInvestigacion;
import co.edu.usbbog.sgpi.model.Materia;
import co.edu.usbbog.sgpi.model.Programa;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.Usuario;
import co.edu.usbbog.sgpi.repository.IClaseRepository;
import co.edu.usbbog.sgpi.repository.IConvocatoriaRepository;
import co.edu.usbbog.sgpi.repository.IFacultadRepository;
import co.edu.usbbog.sgpi.repository.IGrupoInvestigacionRepository;
import co.edu.usbbog.sgpi.repository.ILineaInvestigacionRepository;
import co.edu.usbbog.sgpi.repository.IMateriaRepository;
import co.edu.usbbog.sgpi.repository.IProgramaRepository;
import co.edu.usbbog.sgpi.repository.IProyectoRepository;
import co.edu.usbbog.sgpi.repository.ISemilleroRepository;
import co.edu.usbbog.sgpi.repository.IUsuarioRepository;
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

	private static Logger logger = LoggerFactory.getLogger(BibliotecaService.class);

	@Override
	public List<GrupoInvestigacion> todosLosGruposInvestigacion() {
		List<GrupoInvestigacion> grupoInvestigacion = grupoIRepo.findAll();
		return grupoInvestigacion;
	}

	@Override
	public boolean eliminarGrupoInvestigacion(int id) {
		List<JSONObject> programa = grupoIRepo.findByPrograma(id);
		List<JSONObject> linea = grupoIRepo.findByLinea(id);
		boolean grupo = grupoIRepo.existsById(id);		
		List<JSONObject> x = new ArrayList<JSONObject>();
		if(programa.isEmpty() && linea.isEmpty() && grupo ==true) {
			
			grupoIRepo.deleteById(id);
			return !grupoIRepo.existsById(id);
		}

		return false;
	}

	@Override
	public boolean crearGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion) {
		if (grupoIRepo.existsById(grupoInvestigacion.getId())) {
			return false;
		}
		grupoIRepo.save(grupoInvestigacion);
		return true;
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
		grupoInvestigacion.setProgramas(new ArrayList<>());		
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
	public List<JSONObject> programaDelGrupo(int grupo_investigacion) {
		List<JSONObject> x= new ArrayList<>();
		if(!grupoIRepo.existsById(grupo_investigacion)) {
			System.out.println("no existe el grupo");
			return x;
		}		
		List<JSONObject> grupo = grupoIRepo.findByPrograma(grupo_investigacion);
		return grupo;
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
		grupoInvestigacion.setLineasInvestigacion(new ArrayList<>());	
		grupoInvestigacion.getLineasInvestigacion().add(linea);
		grupoIRepo.save(grupoInvestigacion);
		return true;
	}
	
	
	@Override
	public List<JSONObject> lineaDelGrupo(int grupo_investigacion) {
		List<JSONObject> x= new ArrayList<>();
		if(!grupoIRepo.existsById(grupo_investigacion)) {
			System.out.println("no existe el grupo");
			return x;
		}		
		List<JSONObject> grupo = grupoIRepo.findByLinea(grupo_investigacion);
		return grupo;
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
		Semillero sem = semilleroRepo.getById(grupoInvestigacion);
		List<Semillero> x = new ArrayList<>();
		if (sem != null) {
			List<Semillero> semillero = semilleroRepo.findByGrupoInvestigacion(grupoInvestigacion);
			return semillero;
		} else {
			logger.info("El tipo no existe");
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
	public boolean crearSemillero(Semillero semillero) {
		semilleroRepo.save(semillero);
		return semilleroRepo.existsById(semillero.getId());
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
	public List<JSONObject> programaDelSemillero(int semillero) {
		List<JSONObject> x= new ArrayList<>();
		if(!semilleroRepo.existsById(semillero)) {
			System.out.println("no existe el semillero");
			return x;
		}		
		List<JSONObject> semi = semilleroRepo.findByPrograma(semillero);
		return semi;
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
	public boolean crearFacultad(Facultad facultad) {
		facultadRepo.save(facultad);
		return facultadRepo.existsById(facultad.getId());
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
	public boolean crearPrograma(Programa programa, int facultad) {
		Facultad facul = facultadRepo.getById(facultad);
		System.out.println("facultad    "+facul);
		if(facul == null) {
			return false;
		}
		programa.setFacultadId(facul);
		programaRepo.save(programa);
		return true;
	}
	
	
	@Override
	public List<JSONObject> gruposDelPrograma(int grupo_investigacion) {
		List<JSONObject> x= new ArrayList<>();
		if(!programaRepo.existsById(grupo_investigacion)){
			System.out.println("no existe el grupo");
			return x;
		}
		List<JSONObject> grupos = programaRepo.findByGrupo(grupo_investigacion);
		return grupos;
	}

	@Override
	public List<JSONObject> semillerosDelPrograma(int grupo_investigacion) {
		List<JSONObject> x= new ArrayList<>();
		if(!programaRepo.existsById(grupo_investigacion)){
			System.out.println("no existe el grupo");
			return x;
		}
		List<JSONObject> semilleros = programaRepo.findBySemillero(grupo_investigacion);
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
	public boolean crearMateria(Materia materia) {
		materiaRepo.save(materia);
		return materiaRepo.existsById(materia.getCatalogo());
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
	public boolean crearClase(Clase clase, String materia) {
		Materia mate = materiaRepo.getById(materia);
		if(mate == null) {
			return false; 	
		}
		clase.setMateria(mate);
		claseRepo.save(clase);
		return true;
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
	public List<JSONObject> proyectosPorClase(int clase) {
		List<JSONObject> x= new ArrayList<>();
		if(!claseRepo.existsById(clase)){
			System.out.println("no la clase");
			return x;
		}
		List<JSONObject> proyectos = claseRepo.findByProyecto(clase);
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
		List<Usuario> x = new ArrayList<>();
		if(!semilleroRepo.existsById(semillero)){
			return x;
		}
		List<Usuario> usuarios = semilleroRepo.findByUsuarios(semillero);
		return usuarios;	
	}

	@Override
	public List<Proyecto> proyectosPorSemillero(int semillero) {
		List<Proyecto> x = new ArrayList<>();
		if(!semilleroRepo.existsById(semillero)){
			return x;
		}
		List<Proyecto> proyectos = semilleroRepo.findByProyectos(semillero);
		return proyectos;	
	}

	

}

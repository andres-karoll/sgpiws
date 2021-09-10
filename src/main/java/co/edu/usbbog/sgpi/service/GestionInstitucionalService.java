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
import co.edu.usbbog.sgpi.repository.IFacultadRepository;
import co.edu.usbbog.sgpi.repository.IGrupoInvestigacionRepository;
import co.edu.usbbog.sgpi.repository.ILineaInvestigacionRepository;
import co.edu.usbbog.sgpi.repository.IMateriaRepository;
import co.edu.usbbog.sgpi.repository.IProgramaRepository;
import co.edu.usbbog.sgpi.repository.ISemilleroRepository;
import co.edu.usbbog.sgpi.repository.IUsuarioRepository;
import net.minidev.json.JSONObject;

@Service
public class GestionInstitucionalService implements IGestionInstitucionalService{

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
	private ILineaInvestigacionRepository lineaRepo;
	

	
	private static Logger logger = LoggerFactory.getLogger(BibliotecaService.class);
	@Override
	public List<GrupoInvestigacion> todosLosGruposInvestigacion() {
		List<GrupoInvestigacion> grupoInvestigacion = grupoIRepo.findAll();
		return grupoInvestigacion;
	}

	@Override
	public boolean eliminarGrupoInvestigacion(int id) {
		//
		//
		//
		//
		//
		//
		//
		//NO SEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
		//validar que no esten usando el grupoInvestigacion
		grupoIRepo.deleteById(id);
		return !grupoIRepo.existsById(id);
	}

	@Override
	public boolean crearGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion, int id) {
		
			if(grupoIRepo.existsById(grupoInvestigacion.getId())) {
				return false;
			}
			Programa programa = programaRepo.getById(id);
			programa.getId();
			programa.getGruposInvestigacion().add(grupoInvestigacion);			
			grupoIRepo.save(grupoInvestigacion);
			programaRepo.save(programa);
			System.out.println(programa);
			return grupoIRepo.existsById(grupoInvestigacion.getId());		
	}
	@Override
	public boolean asignarDirector(Usuario director, int id) {
			Usuario dir = usuarioRepo.getById(director.getCedula());
			if(dir!=null) {
				GrupoInvestigacion gru = grupoIRepo.getById(id);
				if(gru!=null) {
					//grupoIRepo.findByGrupoAndDirector(director_grupo, id);
					gru.setDirectorGrupo(dir);
					grupoIRepo.save(gru);
					return true;
				}
				else {
					logger.info("el usuario no existe");
					return false;
				}
			}else {
				logger.info("el usuario no existe");
				return false;
			}
			
	}
	@Override
	public List<Semillero> todosLosSemilleros() {
		List<Semillero> semilleros = semilleroRepo.findAll();	
		return semilleros;
	}
	
	@Override
	public List<Semillero> todosLosSemillerosPorGrupoInvestigacion(int grupoInvestigacion) {
		Semillero sem = semilleroRepo.getById(grupoInvestigacion);
		List<Semillero> x = new ArrayList<>();
		if(sem !=null) {
			List<Semillero> semillero = semilleroRepo.findByGrupoInvestigacion(grupoInvestigacion);
			return semillero;
		}
		else {		
			logger.info("El tipo no existe");
			return x;
		}
	}
	@Override
	public List<Semillero> todosLosSemillerosPorLiderSemillero(String lider) {
		Usuario usu = usuarioRepo.getById(lider);
		List<Semillero> x = new ArrayList<>();
		if(usu !=null) {
			List<Semillero> semillero = semilleroRepo.findByLiderSemillero(lider);
			return semillero;
		}
		else {		
			logger.info("El tipo no existe");
			return x;
		}
	}

	@Override
	public List<Semillero> todosLosSemillerosPorLineaInvestigacion(String lineaInvestigacion) {
		LineaInvestigacion linea = lineaRepo.getById(lineaInvestigacion);
		List<Semillero> x = new ArrayList<>();
		if(linea !=null) {
			List<Semillero> semillero = semilleroRepo.findByLineaInvestigacion(lineaInvestigacion);
			return semillero;
		}
		else {		
			logger.info("El tipo no existe");
			return x;
		}
	}

	@Override
	public boolean eliminarSemillero(int id) {
		semilleroRepo.deleteById(id);
		return !semilleroRepo.existsById(id);
	}

	@Override
	public boolean crearSemillero(Semillero semillero) {
		semilleroRepo.save(semillero);
		return semilleroRepo.existsById(semillero.getId());
	}
	@Override
	public boolean asignarLiderSemillero(Usuario lider, int id) {
		//
		//
		//
		//
		//
		//
		//
		//NO SEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
		Usuario lid = usuarioRepo.getById(lider.getCedula());
		if(lid!=null) {
			Semillero semi = semilleroRepo.getById(id);
			if(semi!=null) {
				//grupoIRepo.findByGrupoAndDirector(director_grupo, id);
				semi.setLiderSemillero(lid);;
				semilleroRepo.save(semi);
				return true;
			}
			else {
				logger.info("el usuario no existe");
				return false;
			}
		}else {
			logger.info("el usuario no existe");
			return false;
		}
	}

	@Override
	public List<Facultad> todasLasFacultades() {
		List<Facultad> facultades = facultadRepo.findAll();	
		return facultades;
	}

	@Override
	public boolean eliminarFacultad(int id) {
		if(programaRepo.findByFacultad(id).isEmpty()) {			
			return false;
		}
		facultadRepo.deleteById(id);
		return !facultadRepo.existsById(id);
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
		
		if(existeUsuario(usuario)) {
			List<Programa> programas = programaRepo.findByDirector(usuario);
			return programas;
		}else {
			return null;
		}
		
	}

	@Override
	public List<Programa> todosLosProgramasPorSemillero(Semillero semillero) {
		// TODO Auto-generated method stub
		//
		//
		//
		//
		//
		//NO SEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
		return null;
	}

	@Override
	public boolean eliminarPrograma(int id) {
		//programa por usuario y por materia
		if(programaRepo.findById(id).isPresent()) {
			//programa por usuario 
			if(usuarioRepo.findByPrograma(id).isEmpty()) {
				//programa por materia
				if(materiaRepo.findByPrograma(id).isEmpty()) {
					//NO SEEEEE COMO VALIDAR POR SEMILLERO Y PROGRAMASSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
					//
					//
					//
					//
					//					
					programaRepo.deleteById(id);
					return true;
				}
				else {
					return false;
				}
				
			}else {
				return false;
			}	
		}else {
			return false;
		}	
	}

	@Override
	public boolean crearPrograma(Programa programa) {
		programaRepo.save(programa);
		return programaRepo.existsById(programa.getId());
	}

	@Override
	public List<Materia> todasLasMaterias() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Materia> todasLasMateriasPorPrograma(Programa programa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminarMateria(String catalogo) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean crearMateria(Materia materia) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<Clase> todasLasClases() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Clase> clasesPorProfesor(Usuario profesor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Clase> clasesPorMateria(Materia materia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminarClase(int numero) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean crearClase(Clase clase) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Convocatoria> todasLasConvocatoriasAbiertas(String estado) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
	//METODOS DE SI EXISTE
	@Override
	public boolean existeUsuario(String cedula) {
			if(usuarioRepo.findById(cedula).isPresent()) {
				return true;		
			}else {
				return false;
			}
	}
	
	@Override
	public boolean asignarLineaAGrupo(GrupoInvestigacion grupoInvestigacion, LineaInvestigacion lineaInvestigacion) {
		grupoInvestigacion.getLineasInvestigacion().add(lineaInvestigacion);
		lineaInvestigacion.getGruposInvestigacion().add(grupoInvestigacion);		
		grupoIRepo.save(grupoInvestigacion);
		lineaRepo.save(lineaInvestigacion);
		
		List<GrupoInvestigacion> grupos = lineaRepo.getById(lineaInvestigacion.getNombre()).getGruposInvestigacion();
		return grupos.contains(lineaInvestigacion);
	}
	
	




	




	

	

	
	

}

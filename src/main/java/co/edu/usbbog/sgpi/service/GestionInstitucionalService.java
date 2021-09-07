package co.edu.usbbog.sgpi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usbbog.sgpi.model.Clase;
import co.edu.usbbog.sgpi.model.Convocatoria;
import co.edu.usbbog.sgpi.model.Facultad;
import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.LineaInvestigacion;
import co.edu.usbbog.sgpi.model.Materia;
import co.edu.usbbog.sgpi.model.Programa;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.Usuario;
import co.edu.usbbog.sgpi.repository.IClaseRepository;
import co.edu.usbbog.sgpi.repository.IFacultadRepository;
import co.edu.usbbog.sgpi.repository.IGrupoInvestigacionRepository;
import co.edu.usbbog.sgpi.repository.IMateriaRepository;
import co.edu.usbbog.sgpi.repository.IProgramaRepository;
import co.edu.usbbog.sgpi.repository.ISemilleroRepository;

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
	
	
	@Override
	public List<GrupoInvestigacion> todosLosGruposInvestigacion() {
		List<GrupoInvestigacion> grupoInvestigacion = grupoIRepo.findAll();
		return grupoInvestigacion;
	}

	@Override
	public boolean eliminarGrupoInvestigacion(int id) {
		grupoIRepo.findByGrupo(id);
		return !grupoIRepo.existsById(id);
	}

	@Override
	public boolean crearGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion) {
		grupoIRepo.save(grupoInvestigacion);	
		return grupoIRepo.existsById(grupoInvestigacion.getId());
	}

	@Override
	public List<Semillero> todosLosSemilleros() {
		List<Semillero> semilleros = semilleroRepo.findAll();
		return semilleros;
	}

	@Override
	public Optional<Semillero> todosLosSemillerosPorGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion) {
		Optional<Semillero> semilleroPorGrupoI = semilleroRepo.findByGrupoInvestigacion(grupoInvestigacion);
		return semilleroPorGrupoI;
	}

	@Override
	public Optional<Semillero> todosLosSemillerosPorLiderSemillero(Usuario lider) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Semillero> todosLosSemillerosPorLineaInvestigacion(LineaInvestigacion lineaInvestigacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminarSemillero(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean crearSemillero(Semillero semillero) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Facultad> todasLasFacultades() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminarFacultad(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean crearFacultad(Facultad facultad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Programa> todosLosProgramas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Programa> todosLosProgramasPorFacultad(Facultad facultad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Programa> todosLosProgramasPorDirector(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Programa> todosLosProgramasPorSemillero(Semillero semillero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminarPrograma(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean crearPrograma(Programa programa) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean crearPrograma(Materia materia) {
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

}

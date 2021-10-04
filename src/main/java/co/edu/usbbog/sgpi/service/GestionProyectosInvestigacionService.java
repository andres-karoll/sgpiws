package co.edu.usbbog.sgpi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usbbog.sgpi.model.Clase;
import co.edu.usbbog.sgpi.model.Comentario;
import co.edu.usbbog.sgpi.model.Compra;
import co.edu.usbbog.sgpi.model.Facultad;
import co.edu.usbbog.sgpi.model.MacroProyecto;
import co.edu.usbbog.sgpi.model.Participantes;
import co.edu.usbbog.sgpi.model.Presupuesto;
import co.edu.usbbog.sgpi.model.Producto;
import co.edu.usbbog.sgpi.model.Programa;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.TipoProyecto;
import co.edu.usbbog.sgpi.model.Usuario;
import co.edu.usbbog.sgpi.repository.IParticipantesRepository;
import co.edu.usbbog.sgpi.repository.IProyectoRepository;
import co.edu.usbbog.sgpi.repository.ISemilleroRepository;
import co.edu.usbbog.sgpi.repository.ITipoProyectoRepository;
@Service
public class GestionProyectosInvestigacionService implements IGestionProyectosInvestigacionService {
	@Autowired
	private IProyectoRepository iProyectoRepository;
	@Autowired 
	private ITipoProyectoRepository iTipoProyectoRepository;
	@Autowired 
	private ISemilleroRepository iSemilleroRepository;
	@Autowired
	private IParticipantesRepository iParticipantesRepository;
	@Override
	public List<Proyecto> todosLosProyectos() {
		TipoProyecto tipoPro=iTipoProyectoRepository.getById("Investigacion");
		List<Proyecto> proyectos = tipoPro.getProyectos();
		if (proyectos.equals(null)) {
			proyectos = new ArrayList<Proyecto>();
		}
		return proyectos;
	}

	

	@Override
	public List<Proyecto> todosLosProyectosPorSemillero(int semilleroId) {
		Semillero semillero =iSemilleroRepository.getById(semilleroId);
		if(semillero.equals(null)) {
			return null;
		}
		List<Proyecto> proyectos=semillero.getProyectos();
		if (proyectos.equals(null)) {
			proyectos = new ArrayList<Proyecto>();
		}
		return proyectos;
	}

	@Override
	public List<Proyecto> todosLosProyectosPorFacultad(Facultad facultad) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Proyecto> todosLosProyectosPorPrograma(Programa programa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminarProyecto(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean crearProyecto(Proyecto proyecto,String tipo,Participantes participante, String rol,String semillero) {
		Semillero se=iSemilleroRepository.getById(Integer.parseInt(semillero));
		if (iProyectoRepository.existsById(proyecto.getId())) {
			return false;
		}
		TipoProyecto tp = iTipoProyectoRepository.getById(tipo);
		if (tp == null) {
			return false;
		}
		try {
			proyecto.setSemillero(se);
			proyecto.setMacroProyecto(null);
		} catch (Exception e) {
			
			proyecto.setMacroProyecto(null);
		}
		if(tp.getNombre().equals("Investigacion")) {
		participante.setRol(rol);
		proyecto.setTipoProyecto(tp);
		iProyectoRepository.save(proyecto);
		iParticipantesRepository.save(participante);
		return iProyectoRepository.existsById(proyecto.getId());
		}else {
			return false;
		}
	}

	@Override
	public boolean asignarTipoProyecto(Proyecto proyecto, TipoProyecto investigacion) {
		
		return false;
	}

	@Override
	public List<Producto> todosLosProductos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> todosLosProductos(Proyecto proyecto) {
		List<Producto> productos=proyecto.getProductos();
		if (productos.equals(null)) {
			productos = new ArrayList<Producto>();
		}
		return productos;
	}

	@Override
	public boolean eliminarProductos(int id) {
		if (iProyectoRepository.existsById(id)) {
			iProyectoRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean crearProducto(Producto producto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Usuario> todosLosParticipantesPorProyecto(Proyecto proyecto) {
		
		return null;
	}

	@Override
	public boolean crearParticipante(Participantes participante) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarParticipante(LocalDate fecha_inicio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Comentario> ComentariosPorProducto(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminarComentario(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean crearComentario(Comentario comentario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Presupuesto> PresupuestoPorProyecto(Proyecto proyecto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminarPresupuesto(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean crearPresupuesto(Presupuesto presupuesto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Compra> CompraPorPresupuesto(Presupuesto presupuesto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminarCompra(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean crearCompra(Compra compra) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean asignarMacroProyecto(MacroProyecto macroProyecto) {
		// TODO Auto-generated method stub
		return false;
	}

}

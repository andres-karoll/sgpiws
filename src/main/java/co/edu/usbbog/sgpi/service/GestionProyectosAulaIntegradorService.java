package co.edu.usbbog.sgpi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usbbog.sgpi.model.AreaConocimiento;
import co.edu.usbbog.sgpi.model.Clase;
import co.edu.usbbog.sgpi.model.Comentario;
import co.edu.usbbog.sgpi.model.Evento;
import co.edu.usbbog.sgpi.model.MacroProyecto;
import co.edu.usbbog.sgpi.model.Materia;
import co.edu.usbbog.sgpi.model.Participaciones;
import co.edu.usbbog.sgpi.model.ParticipacionesPK;
import co.edu.usbbog.sgpi.model.Participantes;
import co.edu.usbbog.sgpi.model.ParticipantesPK;
import co.edu.usbbog.sgpi.model.Producto;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.TipoProyecto;
import co.edu.usbbog.sgpi.model.TipoUsuario;
import co.edu.usbbog.sgpi.model.Usuario;
import co.edu.usbbog.sgpi.repository.IAreaConocimientoRepository;
import co.edu.usbbog.sgpi.repository.IClaseRepository;
import co.edu.usbbog.sgpi.repository.IComentarioRepository;
import co.edu.usbbog.sgpi.repository.IEventoRepository;
import co.edu.usbbog.sgpi.repository.IMacroProyectoRepository;
import co.edu.usbbog.sgpi.repository.IParticipacionesRepository;
import co.edu.usbbog.sgpi.repository.IParticipantesRepository;
import co.edu.usbbog.sgpi.repository.IProductoRepository;
import co.edu.usbbog.sgpi.repository.IProyectoRepository;
import co.edu.usbbog.sgpi.repository.ISemilleroRepository;
import co.edu.usbbog.sgpi.repository.ITipoProyectoRepository;
import co.edu.usbbog.sgpi.repository.ITipoUsuarioRepository;
import co.edu.usbbog.sgpi.repository.IUsuarioRepository;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Service
public class GestionProyectosAulaIntegradorService implements IGestionProyectosAulaIntegradorService {
	@Autowired
	private IProyectoRepository iProyectoRepository;
	@Autowired
	private IClaseRepository iClaseRepository;
	@Autowired
	ITipoUsuarioRepository iTipoUsuarioRepository;
	@Autowired
	private ITipoProyectoRepository iTipoProyectoRepository;
	@Autowired
	private IMacroProyectoRepository iMacroProyectoRepository;
	@Autowired
	private ISemilleroRepository iSemilleroRepository;
	@Autowired
	private IUsuarioRepository iUsuarioRepository;
	@Autowired
	private IParticipantesRepository iParticipantesRepository;
	@Autowired
	private IProductoRepository iProductoRepository;
	@Autowired
	private IComentarioRepository iComentarioRepository;
	@Autowired
	private IEventoRepository iEventoRepository;
	@Autowired
	private IParticipacionesRepository iParticipacionesRepository;
	@Autowired
	private IAreaConocimientoRepository iAreaConocimientoRepository;

	@Override
	public List<Proyecto> todosLosProyectos() {
		List<Proyecto> proyecto = iProyectoRepository.findAll();
		if (proyecto.equals(null)) {
			proyecto = new ArrayList<Proyecto>();
		}

		return proyecto;
	}

	@Override
	public List<Proyecto> todosLosProyectosPorClase(String clase) {
		Clase cla = iClaseRepository.getById(Integer.parseInt(clase));
		List<Proyecto> proyectos = cla.getProyectos();
		if (proyectos.equals(null)) {
			proyectos = new ArrayList<Proyecto>();
		}
		return proyectos;
	}

	@Override
	public List<Proyecto> todosLosProyectosPorTipoProyecto(String tipo) {
		TipoProyecto tipoProyecto = iTipoProyectoRepository.getById(tipo);
		List<Proyecto> proyectos = tipoProyecto.getProyectos();
		if (proyectos.equals(null)) {
			proyectos = new ArrayList<Proyecto>();
		}
		return proyectos;
	}

	@Override
	public boolean eliminarProyecto(int id) {
		if (iProyectoRepository.existsById(id)) {
			iProyectoRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public boolean crearProyecto(Proyecto proyecto, String tipo, Participantes participante, String rol, String clase) {
		Clase clas = iClaseRepository.getById(Integer.parseInt(clase));
		if (!iClaseRepository.existsById(clas.getNumero())) {
			return false;
		}
		if (iProyectoRepository.existsById(proyecto.getId())) {
			return false;
		}
		TipoProyecto tp = iTipoProyectoRepository.getById(tipo);
		if (tp == null) {
			return false;
		}
		try {
			proyecto.setSemillero(null);
			proyecto.setMacroProyecto(null);
		} catch (Exception e) {
			proyecto.setSemillero(null);
			proyecto.setMacroProyecto(null);
		}
		participante.setRol(rol);
		proyecto.setTipoProyecto(tp);
		if (clas.getProyectos() == null) {
			clas.setProyectos(new ArrayList<Proyecto>());
		}
		clas.getProyectos().add(proyecto);
		iProyectoRepository.save(proyecto);
		iClaseRepository.save(clas);
		iParticipantesRepository.save(participante);
		return iProyectoRepository.existsById(proyecto.getId());
	}

	@Override
	public boolean asignarTipoProyecto(Proyecto proyecto, TipoProyecto aulaIntegrador) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Producto> todosLosProductos(Proyecto proyecto) {
		List<Producto> productos = proyecto.getProductos();
		if (productos.equals(null)) {
			productos = new ArrayList<Producto>();
		}
		return productos;
	}

	@Override
	public boolean eliminarProducto(int id) {
		Producto producto = iProductoRepository.getById(id);
		System.out.println(producto.getId());
		if (producto.equals(null)) {
			return false;
		} else {
			iProductoRepository.deleteById(id);
			return true;
		}

	}

	@Override
	public boolean crearProducto(Producto producto) {
		if (iProductoRepository.existsById(producto.getId())) {
			return false;
		} else {
			iProductoRepository.save(producto);
			return iProductoRepository.existsById(producto.getId());
		}
	}

	@Override
	public Proyecto buscarProyecto(int proyectoId) {
		Proyecto proyecto = iProyectoRepository.getById(proyectoId);
		return proyecto;
		
	}

	@Override
	public Producto buscarProducto(int productoId) {
		Producto producto = iProductoRepository.getById(productoId);
		return producto;
	}

	@Override
	public List<Usuario> todosLosParticipantesPorProyecto(Proyecto proyecto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean crearParticipante(Participantes participante, String rol) {
		participante.setRol(rol);
		iParticipantesRepository.save(participante);
		return iParticipantesRepository.existsById(participante.getParticipantesPK());
	}
	@Override
	public boolean eliminarParticipante(Participantes participante, String fecha,int id,String cedula) {
		Proyecto pro =iProyectoRepository.getById(id);
		List<Participantes> part= pro.getParticipantes();
		Usuario usu=iUsuarioRepository.getById(cedula);
		for (Iterator iterator = part.iterator(); iterator.hasNext();) {
			Participantes participantes = (Participantes) iterator.next();
		 if(participantes.getUsuario().getCedula().equals(usu.getCedula())) {
			 participantes.setFechaFin(fecha);
		 }	
		}
		return true;
		
	}
	@Override
	public boolean eliminarParticipante(LocalDate fecha_inicio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarComentario(int id) {
		Comentario comentario = iComentarioRepository.getById(id);
		System.out.println(comentario.getId());
		if (comentario.equals(null)) {
			return false;
		} else {
			iComentarioRepository.deleteById(id);
			return true;
		}
	}

	@Override
	public boolean crearComentario(Comentario comentario, String cedula) {
		Usuario usu = iUsuarioRepository.getById(cedula);
		List<TipoUsuario> tipo = usu.getTiposUsuario();
		TipoUsuario profesor = iTipoUsuarioRepository.getById("profesor");
		tipo.contains(profesor);
		List<Clase> clases = usu.getClases();
		if (clases != null) {
			if (iComentarioRepository.existsById(comentario.getId())) {
				return false;
			} else {
				iComentarioRepository.save(comentario);
				return iComentarioRepository.existsById(comentario.getId());
			}
		}
		return false;
	}

	@Override
	public List<Comentario> ComentariosPorProducto(int productoid) {
		Producto producto = iProductoRepository.getById(productoid);
		List<Comentario> comentarios = producto.getComentarios();
		if (comentarios.equals(null)) {
			comentarios = new ArrayList<Comentario>();
		}
		return comentarios;
	}

	@Override
	public boolean asignarCalificacion(int comentarioid,double calificacion) {
		Comentario comentario=iComentarioRepository.getById(comentarioid);
		if(comentario!=null) {
		comentario.setCalificacion(calificacion);
		iComentarioRepository.save(comentario);
		return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean eliminarCalificacion(int comentarioid) {
		Comentario comentario=iComentarioRepository.getById(comentarioid);
		if(comentario!=null) {
		comentario.setCalificacion(null);
		iComentarioRepository.save(comentario);
		return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean asignarProyectosaMaterias(Proyecto proyecto, Materia materia) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean participarEvento(Participaciones participaciones, LocalDate fecha, String reconocimiento) {

		if (participaciones != null) {
			participaciones.setFechaPart(fecha);
			try {
				participaciones.setReconocimientos(reconocimiento);
			} catch (Exception e) {
				participaciones.setReconocimientos(null);
			}
			iParticipacionesRepository.save(participaciones);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Participaciones> buscarParticipaciones(int proyecto) {
		Proyecto pro = iProyectoRepository.getById(proyecto);
		List<Participaciones> participaciones = pro.getParticipaciones();
		if (participaciones.equals(null)) {
			participaciones = new ArrayList<Participaciones>();
		}
		return participaciones;
	}

	@Override
	public boolean agregarAntecedente(Proyecto proyecto, Proyecto antecedente) {
		if (antecedente != null && proyecto != null) {
			if (proyecto.getFechaInicio().isAfter(antecedente.getFechaFin())) {
				if (proyecto.getAntecedentes().isEmpty()) {
					
					proyecto.setAntecedentes(new ArrayList<Proyecto>());
					proyecto.getAntecedentes().add(antecedente);
					iProyectoRepository.save(proyecto);
				} else {
					if (!proyecto.getAntecedentes().contains(proyecto)) {
						return false;
					} else {
						proyecto.getAntecedentes().add(antecedente);
						iProyectoRepository.save(proyecto);
					}
				}
			} else {
				return false;
			}
			return !proyecto.getAntecedentes().contains(antecedente);
		} else {
			return false;
		}
	}

	@Override
	public boolean agregarAreaConocimiento(int proyecto, int area) {
		Proyecto pro = iProyectoRepository.getById(proyecto);
		AreaConocimiento are = iAreaConocimientoRepository.getById(area);
		if (are.equals(null) || pro.equals(null)) {
			return false;
		} else {
			are.getProyectos().add(pro);
			iAreaConocimientoRepository.save(are);
			return true;
		}

	}

	@Override
	public List<AreaConocimiento> buscarAreasProyecto(int proyecto) {
		Proyecto pro = iProyectoRepository.getById(proyecto);
		List<AreaConocimiento> areas = pro.getAreasConocimiento();
		if (areas.equals(null)) {
			areas = new ArrayList<AreaConocimiento>();
		}
		return areas;
	}

	@Override
	public List<JSONObject> proyectosParticipante(String cedula) {
		List<JSONObject> x = iProyectoRepository.proyectosParticipa(cedula);
		return x;
	}

	@Override
	public boolean actualizarProyecto(int id, String titulo, String descripcion, String metodologia,
			String justificacion) {

		Proyecto pro=iProyectoRepository.getById(id);
		if(titulo!=null) {
			pro.setTitulo(titulo);
		}
		if(descripcion!=null) {
			pro.setDescripcion(descripcion);
		}
		if(metodologia!=null) {
			pro.setMetodologia(metodologia);
		}
		if(justificacion!=null) {
			pro.setJustificacion(justificacion);
		}
		iProyectoRepository.save(pro);
		return iProyectoRepository.existsById(pro.getId());
	}
	
	
}
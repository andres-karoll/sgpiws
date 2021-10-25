package co.edu.usbbog.sgpi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usbbog.sgpi.model.AreaConocimiento;
import co.edu.usbbog.sgpi.model.Clase;
import co.edu.usbbog.sgpi.model.Comentario;
import co.edu.usbbog.sgpi.model.Compra;
import co.edu.usbbog.sgpi.model.Convocatoria;
import co.edu.usbbog.sgpi.model.Facultad;
import co.edu.usbbog.sgpi.model.MacroProyecto;
import co.edu.usbbog.sgpi.model.Participaciones;
import co.edu.usbbog.sgpi.model.Participantes;
import co.edu.usbbog.sgpi.model.Presupuesto;
import co.edu.usbbog.sgpi.model.Producto;
import co.edu.usbbog.sgpi.model.Programa;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.ProyectosConvocatoria;
import co.edu.usbbog.sgpi.model.ProyectosConvocatoriaPK;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.TipoProyecto;
import co.edu.usbbog.sgpi.model.TipoUsuario;
import co.edu.usbbog.sgpi.model.Usuario;
import co.edu.usbbog.sgpi.repository.IAreaConocimientoRepository;
import co.edu.usbbog.sgpi.repository.IComentarioRepository;
import co.edu.usbbog.sgpi.repository.ICompraRepository;
import co.edu.usbbog.sgpi.repository.IConvocatoriaRepository;
import co.edu.usbbog.sgpi.repository.IParticipacionesRepository;
import co.edu.usbbog.sgpi.repository.IParticipantesRepository;
import co.edu.usbbog.sgpi.repository.IPresupuestoRepository;
import co.edu.usbbog.sgpi.repository.IProductoRepository;
import co.edu.usbbog.sgpi.repository.IProyectoConvocatoriaRepository;
import co.edu.usbbog.sgpi.repository.IProyectoRepository;
import co.edu.usbbog.sgpi.repository.ISemilleroRepository;
import co.edu.usbbog.sgpi.repository.ITipoProyectoRepository;
import co.edu.usbbog.sgpi.repository.ITipoUsuarioRepository;
import co.edu.usbbog.sgpi.repository.IUsuarioRepository;
import net.minidev.json.JSONObject;
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
	@Autowired
	private IProductoRepository iProductoRepository;
	@Autowired
	private IComentarioRepository iComentarioRepository;
	@Autowired
	private IUsuarioRepository iUsuarioRepository;
	@Autowired
	private ITipoUsuarioRepository iTipoUsuarioRepository;
	@Autowired
	private IPresupuestoRepository iPresupuestoRepository;
	@Autowired 
	private ICompraRepository iCompraRepository;
	@Autowired
	private IParticipacionesRepository iParticipacionesRepository;
	@Autowired
	private IAreaConocimientoRepository iAreaConocimientoRepository;
	@Autowired
	private IConvocatoriaRepository iConvocatoriaRepository;
	@Autowired
	private IProyectoConvocatoriaRepository iProyectoConvocatoriaRepository;
	@Override
	public List<Proyecto> todosLosProyectosSemillero() {
		TipoProyecto tipoPro=iTipoProyectoRepository.getById("semillero");
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
		if (iProyectoRepository.existsById(id)) {
			iProyectoRepository.deleteById(id);
			return true;
		}
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
			proyecto.setMacroProyecto(null);
		} catch (Exception e) {
			proyecto.setMacroProyecto(null);
		}
		proyecto.setSemillero(se);
		participante.setRol(rol);
		proyecto.setTipoProyecto(tp);
		iProyectoRepository.save(proyecto);
		iParticipantesRepository.save(participante);
		return iProyectoRepository.existsById(proyecto.getId());
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
		if (iProductoRepository.existsById(producto.getId())) {
			return false;
		} else {
			iProductoRepository.save(producto);
			return iProductoRepository.existsById(producto.getId());
		}
	}

	@Override
	public List<Participantes> todosLosParticipantesPorProyecto(Proyecto proyecto) {
		List<Participantes> participantes=proyecto.getParticipantes();
		if (participantes.equals(null)) {
			participantes = new ArrayList<Participantes>();
		}
		return participantes;

	}
	@Override
	public boolean crearParticipante(Participantes participante,String rol) {
		participante.setRol(rol);
		iParticipantesRepository.save(participante);
		return iParticipantesRepository.existsById(participante.getParticipantesPK());
	}
	@Override
	public boolean eliminarParticipante(LocalDate fecha_inicio) {
		// TODO Auto-generated method stub
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
	public boolean crearComentario(Comentario comentario,String cedula) {
		Usuario usu = iUsuarioRepository.getById(cedula);
		List<TipoUsuario> tipo = usu.getTiposUsuario();
		TipoUsuario profesor = iTipoUsuarioRepository.getById("profesor");
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
	public List<Presupuesto> PresupuestoPorProyecto(Proyecto proyecto) {
		List<Presupuesto> presupuestos=proyecto.getPresupuestos();
		if (presupuestos.equals(null)) {
			presupuestos = new ArrayList<Presupuesto>();
		}
		return presupuestos;
	}
	@Override
	public boolean eliminarPresupuesto(int id) {
		Presupuesto presupuesto=iPresupuestoRepository.getById(id);
		if(presupuesto!=null) {
		iPresupuestoRepository.delete(presupuesto);
		return true;
		}else {
			return false;
		}
	}
	@Override
	public boolean crearPresupuesto(Presupuesto presupuesto,String cedula) {
		Usuario usu = iUsuarioRepository.getById(cedula);
		List<TipoUsuario> tipo = usu.getTiposUsuario();
		TipoUsuario administrativo = iTipoUsuarioRepository.getById("Administrativo");
		if(administrativo!=null) {
			if (iPresupuestoRepository.existsById(presupuesto.getId())) {
				return false;
			} else {
				iPresupuestoRepository.save(presupuesto);
				return iPresupuestoRepository.existsById(presupuesto.getId());
			}
		}else {
			return false;
		}
	}
	@Override
	public List<Compra> CompraPorPresupuesto(Presupuesto presupuesto) {
		List<Compra> compras=presupuesto.getCompras();
		if (compras.equals(null)) {
			compras = new ArrayList<Compra>();
		}
		return compras;
		
	}

	@Override
	public boolean eliminarCompra(int id) {
		Compra compra=iCompraRepository.getById(id);
		if(compra!=null) {
			iCompraRepository.delete(compra);
		return true;
		}else {
			return false;
		}
		
	}

	@Override
	public boolean crearCompra(Compra compra) {
		if(iCompraRepository.existsById(compra.getId())) {
			return false;
		}else {
			iCompraRepository.save(compra);
		}
		return iCompraRepository.existsById(compra.getId());
	}

	@Override
	public boolean asignarMacroProyecto(MacroProyecto macroProyecto) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean participarConvocatoria(ProyectosConvocatoria proyectosConvocatoria,String estado) {	
		proyectosConvocatoria.setIdProyecto(estado);
		iProyectoConvocatoriaRepository.save(proyectosConvocatoria); 
		return iProyectoConvocatoriaRepository.existsById(proyectosConvocatoria.getProyectosConvocatoriaPK());
	}
	@Override
	public List<JSONObject> todosLosProyectosUsuarioSemillero(String cedula) {
		List<JSONObject> proyectos=iProyectoRepository.proyectosParticipaSemillero(cedula);
		
		return proyectos;
	}
	@Override
	public List<JSONObject> proyectosParticipanteSemillero(String cedula) {
		List<JSONObject> x = iProyectoRepository.proyectosParticipaSemillero(cedula);
		return x;
	}
}
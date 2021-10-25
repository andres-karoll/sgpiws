package co.edu.usbbog.sgpi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usbbog.sgpi.model.Comentario;
import co.edu.usbbog.sgpi.model.Evento;
import co.edu.usbbog.sgpi.model.MacroProyecto;
import co.edu.usbbog.sgpi.model.Producto;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.repository.IComentarioRepository;
import co.edu.usbbog.sgpi.repository.IProductoRepository;
import co.edu.usbbog.sgpi.repository.IProyectoRepository;

@Service
public class GestionProductosService implements IGestionProductosService{

	@Autowired
	private IProductoRepository productoRepo;
	
	@Autowired
	private IProyectoRepository proyectoRepo;
	
	@Autowired
	private IComentarioRepository comentarioRepo;
	
	@Override
	public List<Producto> todosLosProductos() {
		List<Producto> productos= productoRepo.findAll();
		return productos;
	}

	@Override
	public List<Producto> todosLosProductosPorProyecto(int proyecto) {
		Proyecto pro = proyectoRepo.getById(proyecto);
		List<Producto> productos = pro.getProductos();
		if(productos.equals(null)) {
			productos = new ArrayList<Producto>();
		}
		return productos;
	}
	

	@Override
	public boolean eliminarProductos(int producto) {
		List<Comentario> com = comentarioRepo.findByProducto(producto);
		boolean pro = productoRepo.existsById(producto);
		if(com.isEmpty() && pro == true) {
			productoRepo.deleteById(producto);
			return !productoRepo.existsById(producto);
		}
		return false;
	}


	@Override
	public boolean crearProducto(Producto producto, int proyecto) {
		Proyecto proyec = proyectoRepo.getById(proyecto);
		if(proyec == null) {
			return false;
		}
		producto.setProyecto(proyec);
		productoRepo.save(producto);
		return true;
	}

	@Override
	public List<Comentario> ComentariosPorProducto(int producto_id) {
		Producto pro = productoRepo.getById(producto_id);
		List<Comentario> comentarios = pro.getComentarios();
		if(comentarios.equals(null)) {
			 comentarios = new ArrayList<Comentario>();
		}
		return comentarios;
	}

	@Override
	public boolean eliminarComentario(int id) {
		boolean com = comentarioRepo.existsById(id);
		if(com ==true) {
			comentarioRepo.deleteById(id);
			return !comentarioRepo.existsById(id);
			
		}
		return false;
	}

	@Override
	public boolean crearComentario(Comentario comentario, int producto_id) {
		Producto pro = productoRepo.getById(producto_id);
		if(pro == null) {
			return false;
		}
		comentario.setProductoId(pro);
		comentarioRepo.save(comentario);
		return true;
	}

	@Override
	public boolean asignarCalificacion(double calificacion, int id) {
		boolean comentario = comentarioRepo.existsById(id);
		if(comentario == false) {
			return false;
		}
		comentarioRepo.asignarCalificacion(calificacion, id);
		return true;
	}

	@Override
	public boolean eliminarCalificacion(int id) {
		boolean comentario = comentarioRepo.existsById(id);
		if(comentario == false) {
			return false;
		}
		comentarioRepo.eliminarCalificacion(id);
		return true;
	}

	@Override
	public boolean asignarMacroProyecto(MacroProyecto macroProyecto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean asignarVisibilidadProyecto(int visibilidad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Evento> todosLosEventosPorProyecto(Proyecto proyecto) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

	
}
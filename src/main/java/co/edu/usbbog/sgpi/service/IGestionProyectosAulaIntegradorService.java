package co.edu.usbbog.sgpi.service;

import java.time.LocalDate;
import java.util.List;

import co.edu.usbbog.sgpi.model.Clase;
import co.edu.usbbog.sgpi.model.Comentario;
import co.edu.usbbog.sgpi.model.Materia;
import co.edu.usbbog.sgpi.model.Participantes;
import co.edu.usbbog.sgpi.model.Producto;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.TipoProyecto;
import co.edu.usbbog.sgpi.model.Usuario;

public interface IGestionProyectosAulaIntegradorService {
	
	public List<Proyecto> todosLosProyectos();
	public List<Proyecto> todosLosProyectosPorClase(Clase clase);
	public List<Proyecto> todosLosProyectosPorTipoProyecto(TipoProyecto tipoProyecto);
	public boolean eliminarProyecto(int id);
	public boolean crearProyecto(Proyecto proyecto);
	public boolean asignarTipoProyecto(Proyecto proyecto, TipoProyecto aulaIntegrador);
	//actualizar Proyectos
	
	public List<Producto> todosLosProductos();
	public List<Producto> todosLosProductos(Proyecto proyecto);
	public boolean eliminarProductos(int id);
	public boolean crearProducto(Producto producto);
	//actualizar Productos
	
	public List<Usuario> todosLosParticipantesPorProyecto(Proyecto proyecto);
	public boolean crearParticipante(Participantes participante);
	public boolean eliminarParticipante(LocalDate fecha_inicio);
	//actualizar participante
	
	public List<Comentario> ComentariosPorProducto(Producto producto);
	public boolean eliminarComentario(int id);
	public boolean crearComentario(Comentario comentario);
	//actualizar comentario
	
	//LA NOTA????
		public List<Comentario> todosLosComentariosPorProducto(Producto producto);
		public boolean asignarCalificacion(double calificacion);
		public boolean eliminarCalificacion(Comentario comentario);
		
	//El sistema deberá poder asignar proyectos de clase a distintas materias.
		public boolean asignarProyectosaMaterias(Proyecto proyecto, Materia materia);
}

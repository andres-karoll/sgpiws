package co.edu.usbbog.sgpi.service;

import java.time.LocalDate;
import java.util.List;

import co.edu.usbbog.sgpi.model.Clase;
import co.edu.usbbog.sgpi.model.Comentario;
import co.edu.usbbog.sgpi.model.Materia;
import co.edu.usbbog.sgpi.model.Participaciones;
import co.edu.usbbog.sgpi.model.Participantes;
import co.edu.usbbog.sgpi.model.Producto;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.TipoProyecto;
import co.edu.usbbog.sgpi.model.Usuario;

public interface IGestionProyectosAulaIntegradorService {
	
	public List<Proyecto> todosLosProyectos();
	public List<Proyecto> todosLosProyectosPorClase(String clase);
	public List<Proyecto> todosLosProyectosPorTipoProyecto(String tipo);
	public boolean eliminarProyecto(int id);
	public boolean crearProyecto(Proyecto proyecto,String tipo,Participantes participante,String rol,String clase);
	public boolean asignarTipoProyecto(Proyecto proyecto, TipoProyecto aulaIntegrador);
	//actualizar Proyectos
	
	public List<Producto> todosLosProductos(Proyecto proyecto);
	public boolean eliminarProducto(int id);

	//actualizar Productos
	boolean crearProducto(Producto producto);
	public List<Usuario> todosLosParticipantesPorProyecto(Proyecto proyecto);
	public boolean crearParticipante(Participantes participante,String rol);
	public boolean eliminarParticipante(LocalDate fecha_inicio);
	//actualizar participante
	
	public List<Comentario> ComentariosPorProducto(int productoId);
	public boolean eliminarComentario(int id);
	public boolean crearComentario(Comentario comentario,String cedula);
	//actualizar comentario
	
	//LA NOTA????
		public boolean asignarCalificacion(double calificacion);
		public boolean eliminarCalificacion(Comentario comentario);
		
	//El sistema deberá poder asignar proyectos de clase a distintas materias.
		public boolean asignarProyectosaMaterias(Proyecto proyecto, Materia materia);
		public Proyecto buscarProyecto(int proyectoId);
		public Producto buscarProducto(int productoId);
		public boolean participarEvento(Participaciones participaciones, LocalDate parse, String asString);
		public List<Participaciones> buscarParticipaciones(int parseInt);
		public boolean agregarAntecedente(Proyecto proyeto, Proyecto antecedente);
		
}

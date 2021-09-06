package co.edu.usbbog.sgpi.service;

import java.time.LocalDate;
import java.util.List;

import co.edu.usbbog.sgpi.model.Comentario;
import co.edu.usbbog.sgpi.model.Participantes;
import co.edu.usbbog.sgpi.model.Producto;
import co.edu.usbbog.sgpi.model.Programa;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.TipoProyecto;
import co.edu.usbbog.sgpi.model.Usuario;

public interface IGestionTrabajosGradoService {
	
	public List<Proyecto> todosLosProyectos();
	public List<Proyecto> todosLosProyectosPorTipoProyecto(TipoProyecto tipoProyecto);
	public boolean eliminarProyecto(int id);
	public boolean crearProyecto(Proyecto proyecto);
	public boolean asignarTipoProyecto(Proyecto proyecto, TipoProyecto grado);
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
		
	//VALIDACIONES DE APROBACION????
		//El sistema debe permitir asignar un programa académico al respectivo trabajo de grado.
	public boolean asignarProgramaAcademico(Proyecto grado, Programa programa);
	
	//El sistema debe permitir realizar un listado de los trabajos de grado finalizados
	public List<Proyecto> todosLosProyectosFinalizados(String estado);
}

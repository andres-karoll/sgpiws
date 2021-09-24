package co.edu.usbbog.sgpi.service;



import java.util.List;


import co.edu.usbbog.sgpi.model.Comentario;

import co.edu.usbbog.sgpi.model.Evento;
import co.edu.usbbog.sgpi.model.MacroProyecto;

import co.edu.usbbog.sgpi.model.Producto;
import co.edu.usbbog.sgpi.model.Proyecto;



public interface IGestionProductosService {
	/*
	public List<TipoProyecto> todosLosTipoProyecto();
	public List<TipoProyecto> tipoProyectoPorProyecto(Proyecto proyecto);
	public boolean eliminarTipoProyecto(String nombre);
	public boolean crearTipoProyecto(TipoProyecto tipoProyecto);
	*/
	//actualizar tipoproyecto
	
	public List<Producto> todosLosProductos();
	public List<Producto> todosLosProductosPorProyecto(int proyecto);
	public boolean eliminarProductos(int id);
	public boolean crearProducto(Producto producto, int proyecto);
	//actualizar producto
	
	/*
	public List<Proyecto> todosLosProyectos();
	public List<Proyecto> todosLosProyectosPorClase(Clase clase);
	public List<Proyecto> todosLosProyectosPorSemillero(Semillero semillero);
	public List<Proyecto> todosLosProyectosPorTipoProyecto(TipoProyecto tipoProyecto);
	public boolean eliminarProyecto(int id);
	public boolean crearProyecto(Proyecto proyecto);
	//actualizar proyecto
	 */
	 
	/*
	public List<Convocatoria> todoasLasConvocatorias();
	public List<Convocatoria> todoasLasConvocatoriasPorProyecto(Proyecto proyecto);
	public List<DetalleConvocatoria> detallesPorConvocatoria(Convocatoria convocatoria);
	public boolean eliminarConvocatoria(int id);
	public boolean crearConvocatoria (Convocatoria convocatoria);
	//actualizar convocatoria
	*/
	

	/*
	public List<Usuario> todosLosParticipantesPorProyecto(Proyecto proyecto);
	public boolean crearParticipante(Participantes participante);
	public boolean eliminarParticipante(LocalDate fecha_inicio);
	//actualizar participante
	*/
	
	public List<Comentario> ComentariosPorProducto(int producto_id);
	public boolean eliminarComentario(int id);
	public boolean crearComentario(Comentario comentario, int producto_id);
	//actualizar comentario
	
	//LA NOTA????

	public boolean asignarCalificacion(double calificacion, int id);
	public boolean eliminarCalificacion(Comentario comentario);
	//actualizar nota
	
	
	//El sistema debe permitir asociar proyectos que estén relacionados
	public boolean asignarMacroProyecto(MacroProyecto macroProyecto);
	
	//El sistema debe tener etiquetas para la visibilidad de los proyectos estas serán público y privados
	public boolean asignarVisibilidadProyecto(int visibilidad);
	
	//Tener un historial de los eventos en los que en el que participo
	public List<Evento> todosLosEventosPorProyecto(Proyecto proyecto);
}

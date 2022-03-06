package co.edu.usbbog.sgpi.service;

import java.time.LocalDate;
import java.util.List;

import co.edu.usbbog.sgpi.model.AreaConocimiento;
import co.edu.usbbog.sgpi.model.Clase;
import co.edu.usbbog.sgpi.model.Comentario;
import co.edu.usbbog.sgpi.model.Evento;
import co.edu.usbbog.sgpi.model.MacroProyecto;
import co.edu.usbbog.sgpi.model.Materia;
import co.edu.usbbog.sgpi.model.Participaciones;
import co.edu.usbbog.sgpi.model.Participantes;
import co.edu.usbbog.sgpi.model.Producto;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.TipoProyecto;
import co.edu.usbbog.sgpi.model.Usuario;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public interface IGestionProyectosAulaIntegradorService {
	
	public List<Proyecto> todosLosProyectos();
	public List<Proyecto> todosLosProyectosPorClase(String clase);
	public List<Proyecto> todosLosProyectosPorTipoProyecto(String tipo);
	public boolean eliminarProyecto(int id);
	public boolean crearProyecto(Proyecto proyecto,String tipo,String rol,String clase,String cedula,LocalDate fecha,int macro);
	public boolean asignarTipoProyecto(Proyecto proyecto, TipoProyecto aulaIntegrador);
	//actualizar Proyectos
	
	public List<Producto> todosLosProductos(Proyecto proyecto);
	public boolean eliminarProducto(int id);

	//actualizar Productos
	boolean crearProducto(Producto producto);
	public List<Usuario> todosLosParticipantesPorProyecto(Proyecto proyecto);
	public boolean crearParticipante(Participantes participante,String rol,String cedula,int proyecto);
	//actualizar participante
	
	public List<Comentario> ComentariosPorProducto(int productoId);
	public boolean eliminarComentario(int id);
	public boolean crearComentario(Comentario comentario,String cedula);
	//actualizar comentario
	
	//LA NOTA????
		public boolean asignarCalificacion(int comentarioid,double calificacion);
		public boolean eliminarCalificacion(int comentarioid);
		
	//El sistema deberá poder asignar proyectos de clase a distintas materias.
		public boolean asignarProyectosaMaterias(Proyecto proyecto, Materia materia);
		public Proyecto buscarProyecto(int proyectoId);
		public Producto buscarProducto(int productoId);
		public boolean participarEvento(Participaciones participaciones, LocalDate parse, String asString);
		public boolean participarEventoExtrerna(Evento evento,int proyecto, LocalDate fecha, String reconocimiento,String entidad, String sitio_web, String url_memoria);
		public List<Participaciones> buscarParticipaciones(int parseInt);
		public boolean agregarAntecedente(Proyecto proyeto, Proyecto antecedente);
		public boolean agregarAreaConocimiento(int proyecto, int area);
		public List<AreaConocimiento> buscarAreasProyecto(int proyecto);
		public List<JSONObject> proyectosParticipanteClase(String cedula);
		public boolean actualizarProyecto(int id, String titulo, String descripcion, String metodologia,String justificacion);
		public List<Participantes> listarParticipantesPorPoryecto(int id);		
		public boolean actualizarParticipante(int id, String cedula, LocalDate fechafin);
		public List<AreaConocimiento> listarAreaConocimiento();
		public List<JSONObject> listarEvento();
		public List<AreaConocimiento> areasConocimientoProyecto(int proyecto);
		public List<MacroProyecto> macroProyectosAbiertos();
		public boolean eliminarArea(int area,int proyecto);
		public boolean crearMacro(MacroProyecto macro);
		public boolean cerrarMacro(int parseInt);
		public boolean modificarMacro(int macro,String nombre,String descripcion);
		public List<JSONObject> tusProyectosConvocatoria(int convocatoria ,int id);
}

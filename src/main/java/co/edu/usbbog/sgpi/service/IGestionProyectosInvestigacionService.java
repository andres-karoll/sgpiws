package co.edu.usbbog.sgpi.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import co.edu.usbbog.sgpi.model.AreaConocimiento;
import co.edu.usbbog.sgpi.model.Comentario;
import co.edu.usbbog.sgpi.model.Compra;
import co.edu.usbbog.sgpi.model.Facultad;
import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.MacroProyecto;
import co.edu.usbbog.sgpi.model.Participaciones;
import co.edu.usbbog.sgpi.model.Participantes;
import co.edu.usbbog.sgpi.model.Presupuesto;
import co.edu.usbbog.sgpi.model.Producto;
import co.edu.usbbog.sgpi.model.Programa;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.ProyectosConvocatoria;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.TipoProyecto;
import co.edu.usbbog.sgpi.model.Usuario;
import net.minidev.json.JSONObject;

public interface IGestionProyectosInvestigacionService {

	public List<Proyecto> todosLosProyectosSemillero();
	public List<Proyecto> todosLosProyectosPorSemillero(int semillero);
	public List<Proyecto> todosLosProyectosPorFacultad(Facultad facultad);
	public List<Proyecto> todosLosProyectosPorPrograma(Programa programa);
	public boolean eliminarProyecto(int id);
	public boolean asignarTipoProyecto(Proyecto proyecto, TipoProyecto investigacion);
	
	//actualizar Proyectos
	
	public List<Producto> todosLosProductos();
	public List<Producto> todosLosProductos(Proyecto proyecto);
	public boolean eliminarProductos(int id);
	public boolean crearProducto(Producto producto);
	//actualizar Productos
	
	public List<Participantes> todosLosParticipantesPorProyecto(Proyecto proyecto);
	public boolean crearParticipante(Participantes participante,String rol);
	public boolean eliminarParticipante(LocalDate fecha_inicio);
	//actualizar participante
	
	public List<Comentario> ComentariosPorProducto(int productoid);
	public boolean eliminarComentario(int id);
	public boolean crearComentario(Comentario comentario,String cedula);
	public boolean asignarCalificacion(int comentarioid,double calificacion);
	public boolean eliminarCalificacion(int comentarioid);
	//actualizar comentario
	
	public List<Presupuesto> PresupuestoPorProyecto(Proyecto proyecto);
	public boolean eliminarPresupuesto(int id);
	public boolean crearPresupuesto(Presupuesto presupuesto,String cedula);
	//actualizar presupuesto
	
	public List<Compra> CompraPorPresupuesto(Presupuesto presupuesto);
	public boolean eliminarCompra(int id);
	public boolean crearCompra(Compra compra);
	//actualizar compra
	
	//El sistema debe permitir asociar proyectos que estén relacionados
		public boolean asignarMacroProyecto(MacroProyecto macroProyecto);
		public Proyecto buscarProyecto(int parseInt);
		public Producto buscarProducto(int parseInt);
		boolean eliminarProducto(int id);
		boolean participarEvento(Participaciones participaciones, LocalDate fecha, String reconocimiento);
		public List<Participaciones> buscarParticipaciones(int parseInt);
		public boolean agregarAntecedente(Proyecto proyecto, Proyecto antecedente);
		public boolean agregarAreaConocimiento(int parseInt, int parseInt2);
		public List<AreaConocimiento> buscarAreasProyecto(int parseInt);
		public boolean participarConvocatoria(ProyectosConvocatoria proyectosConvocatoria,String estado);
		public List<JSONObject> todosLosProyectosUsuarioSemillero(String cedula);
		public List<JSONObject> proyectosParticipanteSemillero(String cedula);
		public boolean verificarSemillero(String cedula);
		public List<Proyecto> buscarAntecedentes(int parseInt);
		public boolean crearProyecto(Proyecto proyecto, String asString, String asString2, String asString3,
				String asString4, LocalDate parse, String asString5,int macro);

}

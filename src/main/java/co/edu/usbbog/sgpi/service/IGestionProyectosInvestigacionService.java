package co.edu.usbbog.sgpi.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import co.edu.usbbog.sgpi.model.Comentario;
import co.edu.usbbog.sgpi.model.Compra;
import co.edu.usbbog.sgpi.model.Facultad;
import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.MacroProyecto;
import co.edu.usbbog.sgpi.model.Participantes;
import co.edu.usbbog.sgpi.model.Presupuesto;
import co.edu.usbbog.sgpi.model.Producto;
import co.edu.usbbog.sgpi.model.Programa;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.TipoProyecto;
import co.edu.usbbog.sgpi.model.Usuario;

public interface IGestionProyectosInvestigacionService {

	public List<Proyecto> todosLosProyectos();
	public List<Proyecto> todosLosProyectosPorSemillero(int semillero);
	public List<Proyecto> todosLosProyectosPorFacultad(Facultad facultad);
	public List<Proyecto> todosLosProyectosPorPrograma(Programa programa);
	public boolean eliminarProyecto(int id);
	public boolean crearProyecto(Proyecto proyecto,String tipo,Participantes participante, String rol,String semillero);
	public boolean asignarTipoProyecto(Proyecto proyecto, TipoProyecto investigacion);
	
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
	
	public List<Presupuesto> PresupuestoPorProyecto(Proyecto proyecto);
	public boolean eliminarPresupuesto(int id);
	public boolean crearPresupuesto(Presupuesto presupuesto);
	//actualizar presupuesto
	
	public List<Compra> CompraPorPresupuesto(Presupuesto presupuesto);
	public boolean eliminarCompra(int id);
	public boolean crearCompra(Compra compra);
	//actualizar compra
	
	//El sistema debe permitir asociar proyectos que estén relacionados
		public boolean asignarMacroProyecto(MacroProyecto macroProyecto);
}

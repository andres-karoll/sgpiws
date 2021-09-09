package co.edu.usbbog.sgpi.service;

import java.util.List;
import java.util.Optional;

import co.edu.usbbog.sgpi.model.Clase;
import co.edu.usbbog.sgpi.model.Convocatoria;
import co.edu.usbbog.sgpi.model.Facultad;
import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.LineaInvestigacion;
import co.edu.usbbog.sgpi.model.Materia;
import co.edu.usbbog.sgpi.model.Programa;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.TipoProyecto;
import co.edu.usbbog.sgpi.model.Usuario;
import net.minidev.json.JSONObject;

 

public interface IGestionInstitucionalService {
	
	public boolean existeUsuario (String cedula);
	
	public List<GrupoInvestigacion> todosLosGruposInvestigacion();
	public boolean eliminarGrupoInvestigacion(int id);
	public boolean crearGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion);
	public boolean asignarDirector(Usuario director,int id);
	//actualizar grupoInvestigacion
	
	public List<Semillero> todosLosSemilleros();
	public List<Semillero> todosLosSemillerosPorGrupoInvestigacion(int grupoInvestigacion);
	public List<Semillero> todosLosSemillerosPorLiderSemillero(String lider);
	public List<Semillero> todosLosSemillerosPorLineaInvestigacion(String lineaInvestigacion);
	public boolean eliminarSemillero(int id);
	public boolean crearSemillero(Semillero semillero);
	public boolean asignarLiderSemillero(Usuario lider, int id);
	//actualizar semillero
	
	public List<Facultad> todasLasFacultades();
	public boolean eliminarFacultad(int id);
	public boolean crearFacultad(Facultad facultad);
	//actualizarFacultad
	
	public List<Programa> todosLosProgramas();
	public List<Programa> todosLosProgramasPorFacultad(Facultad facultad);
	public List<Programa> todosLosProgramasPorDirector(Usuario usuario);
	public List<Programa> todosLosProgramasPorSemillero(Semillero semillero);
	public boolean eliminarPrograma(int id);
	public boolean crearPrograma(Programa programa);
	//actualizarPrograma
	
	public List<Materia> todasLasMaterias();
	public List<Materia> todasLasMateriasPorPrograma(Programa programa);
	public boolean eliminarMateria(String catalogo);
	public boolean crearPrograma(Materia materia);
	//actualizar materia
	
	public List<Clase> todasLasClases();
	public List<Clase> clasesPorProfesor(Usuario profesor);
	public List<Clase> clasesPorMateria(Materia materia);
	public boolean eliminarClase(int numero);
	public boolean crearClase(Clase clase);
	//actualizar clase
	
	//Consultar convocatorias abiertas
	public List<Convocatoria> todasLasConvocatoriasAbiertas(String estado);
	
	//Consultar postulaciones del semillero para poder aceptarlas o denegarlas?????
	
	//Realizar postulación de un proyecto de semillero a una convocatoria vigente?????
	
	//Consultar solicitudes de compras hechas por el semillerista o el investigador en formación para poder aprobarlas o denegarlas?????
	
	
	
}

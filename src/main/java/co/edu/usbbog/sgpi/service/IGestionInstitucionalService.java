package co.edu.usbbog.sgpi.service;

import java.util.List;
import java.util.Optional;

import co.edu.usbbog.sgpi.model.AreaConocimiento;
import co.edu.usbbog.sgpi.model.Clase;
import co.edu.usbbog.sgpi.model.Convocatoria;
import co.edu.usbbog.sgpi.model.Evento;
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
	public boolean asignarProgramaAGrupoInvestigacion(int programa, int grupo_investigacion);
	public List<Programa> programaDelGrupo(int grupo_investigacion);
	public boolean desasignarProgramaAGrupoInvestigacion(int programa, int grupo_investigacion);
	public boolean asignarLineaAGrupoInvestigacion (String linea_investigacion, int grupo_investigacion);
	public List<LineaInvestigacion> lineaDelGrupo(int grupo_investigacion);
	public boolean desasignarLineaAGrupoInvestigacion (String linea_investigacion, int grupo_investigacion);
	//actualizar grupoInvestigacion
	
	public List<Semillero> todosLosSemilleros();
	public List<Semillero> todosLosSemillerosPorGrupoInvestigacion(int grupoInvestigacion);
	public List<Semillero> todosLosSemillerosPorLiderSemillero(String lider);
	public List<Semillero> todosLosSemillerosPorLineaInvestigacion(String lineaInvestigacion);
	public boolean eliminarSemillero(int id);
	public boolean crearSemillero(Semillero semillero);
	public boolean asignarSemilleroAPrograma(int programa, int semillero);
	public List<Programa> programaDelSemillero(int semillero);
	public boolean desasignarSemilleroAPrograma(int programa, int semillero);
	//actualizar semillero
	
	public List<Facultad> todasLasFacultades();
	public boolean eliminarFacultad(int id);
	public boolean crearFacultad(Facultad facultad);
	//actualizarFacultad
	
	public List<Programa> todosLosProgramas();
	public List<Programa> todosLosProgramasPorFacultad(int facultad);
	public List<Programa> todosLosProgramasPorDirector(String usuario);
	public boolean eliminarPrograma(int id);
	public boolean crearPrograma(Programa programa, int facultad, String director);
	
	public List<GrupoInvestigacion> gruposDelPrograma(int programa);
	public List<Semillero> semillerosDelPrograma(int programa);
	//actualizarPrograma
	
	public List<Materia> todasLasMaterias();
	public List<Materia> todasLasMateriasPorPrograma(int programa);
	public boolean eliminarMateria(String catalogo);
	public boolean crearMateria(Materia materia);
	//actualizar materia
	
	public List<Clase> todasLasClases();
	public List<Clase> clasesPorProfesor(String profesor);
	public List<Clase> clasesPorMateria(String materia);
	public boolean eliminarClase(int numero);
	public boolean crearClase(Clase clase, String materia, String profesor);
	public boolean asignarProyectosAClase(int proyecto, int clase);
	public boolean desasignarProyectosAClase(int proyecto, int clase);
	public List<Proyecto> proyectosPorClase(int clase);
	//actualizar clase
	
	
	public List<LineaInvestigacion> todasLasLineas();
	public boolean crearLinea(LineaInvestigacion linea);
	public boolean eliminarLinea(String nombre);
	//lineas investigacion
	
	
	
	
	public List<AreaConocimiento> todasLasAreasConocimiento();
	public boolean crearArea(AreaConocimiento area, String gran_area);
	public boolean eliminarArea(int id);
	//areas de conocimiento
	
	
	
	
	
	
	public List<Evento> todosLosEventos();
	public boolean crearEvento(Evento evento, String entidad, String sitio_web, String url_memoria);
	public boolean eliminarEvento(int id);
	
	
	
	//Consultar convocatorias abiertas
	public List<Convocatoria> todasLasConvocatoriasAbiertas(String estado);
	public Convocatoria convocatoriaPorID(int id);
	
	//Listar usuarios de un semillero
	public List<Usuario> usuariosPorSemillero(int semillero);

	//proyectos por semillero
	public List<Proyecto> proyectosPorSemillero(int semillero);

	
}

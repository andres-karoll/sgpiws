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
	public boolean asignarProgramaAGrupoInvestigacion(int programa, int grupo_investigacion);
	public List<JSONObject> programaDelGrupo(int grupo_investigacion);
	public boolean desasignarProgramaAGrupoInvestigacion(int programa, int grupo_investigacion);
	public boolean asignarLineaAGrupoInvestigacion (String linea_investigacion, int grupo_investigacion);
	public List<JSONObject> lineaDelGrupo(int grupo_investigacion);
	public boolean desasignarLineaAGrupoInvestigacion (String linea_investigacion, int grupo_investigacion);
	//actualizar grupoInvestigacion
	
	public List<Semillero> todosLosSemilleros();
	public List<Semillero> todosLosSemillerosPorGrupoInvestigacion(int grupoInvestigacion);
	public List<Semillero> todosLosSemillerosPorLiderSemillero(String lider);
	public List<Semillero> todosLosSemillerosPorLineaInvestigacion(String lineaInvestigacion);
	public boolean eliminarSemillero(int id);
	public boolean crearSemillero(Semillero semillero);
	public boolean asignarSemilleroAPrograma(int programa, int semillero);
	public List<JSONObject> programaDelSemillero(int semillero);
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
	public boolean crearPrograma(Programa programa, int facultad);
	
	public List<JSONObject> gruposDelPrograma(int grupo_investigacion);
	public List<JSONObject> semillerosDelPrograma(int grupo_investigacion);
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
	public boolean crearClase(Clase clase, String materia);
	public boolean asignarProyectosAClase(int proyecto, int clase);
	public boolean desasignarProyectosAClase(int proyecto, int clase);
	public List<JSONObject> proyectosPorClase(int clase);
	//actualizar clase
	
	//Consultar convocatorias abiertas
	public List<Convocatoria> todasLasConvocatoriasAbiertas(String estado);
	
	//Consultar postulaciones del semillero para poder aceptarlas o denegarlas?????
	
	//Realizar postulación de un proyecto de semillero a una convocatoria vigente?????
	
	//Consultar solicitudes de compras hechas por el semillerista o el investigador en formación para poder aprobarlas o denegarlas?????
	
	
	
}

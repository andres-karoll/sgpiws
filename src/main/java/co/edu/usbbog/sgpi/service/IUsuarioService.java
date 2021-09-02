package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.Clase;
import co.edu.usbbog.sgpi.model.Facultad;
import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.Programa;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.TipoUsuario;
import co.edu.usbbog.sgpi.model.Usuario;



public interface IUsuarioService {
	public List<Usuario> todosLosUsuarios();
	public List<Usuario> todosLosUsuariosPorSemillero(Semillero semillero);
	public List<Usuario> todosLosUsuariosPorTipoUsuario(TipoUsuario tipoUsuario);
	public List<Usuario> todosLosUsuariosPorClase(Clase clase);
	public List<Usuario> todosLosUsuariosPorPrograma(Programa programa);
	public List<Usuario> todosLosUsuariosPorGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion);
	public List<Usuario> todosLosUsuariosPorFacultad(Facultad facultad);
	public boolean eliminarUsuario(String cedula);
	public boolean crearUsuario(Usuario usuario);
}

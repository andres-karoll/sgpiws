package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.TipoUsuario;
import co.edu.usbbog.sgpi.model.Usuario;


public interface ITipoUsuarioService {
	public List<TipoUsuario> todosLosTipoUsuario();
	public List<TipoUsuario> todosLosTipoUsuarioPorUsuario(Usuario usuario);
	public boolean eliminarTipoUsuario(String nombre);
	public boolean crearTipoUsuario(TipoUsuario tipoUsuario);
}

package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.TipoProyecto;
import co.edu.usbbog.sgpi.model.Usuario;

public interface IPublicoService {
	public List<Proyecto> todosLosProyectosMarcadosPublicos(int visibilidad);
	public List<Usuario> todosLosUsuariosParaLogin(String correo_est, String contrasena);
	public List<TipoProyecto> todosLosTiposProyecto();
}

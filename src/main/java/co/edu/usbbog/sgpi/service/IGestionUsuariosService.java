package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.GrupoInvestigacion;
import co.edu.usbbog.sgpi.model.LineaInvestigacion;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.TipoUsuario;
import co.edu.usbbog.sgpi.model.Usuario;

public interface IGestionUsuariosService {
	
	public List<Usuario> todosLosUsuarios();
	public boolean eliminarUsuario(String cedula);
	public boolean crearUsuario(Usuario usuario);
	//actualizar campos de usuario
	
	public List<Semillero> todosLosSemilleros();
	public List<Semillero> todosLosSemillerosPorGrupoInvestigacion(GrupoInvestigacion grupoInvestigacion);
	public List<Semillero> todosLosSemillerosPorLider(Usuario lider);
	public List<Semillero> todosLosSemillerosPorLineaInvestigacion(LineaInvestigacion lineaInvestigacion);
	public boolean eliminarSemillero(int id);
	public boolean crearSemillero(Semillero semillero);
	//actualizar campos de semillero
	
	public List<TipoUsuario> todosLosTipoUsuario();
	public boolean eliminarTipoUsuario(String nombre);
	public boolean crearTipoUsuario(TipoUsuario tipoUsuario);
	//actualizar campos de tipousuario
	
	public List<Usuario> todosLosUsuariosPorTipoUsuario(TipoUsuario tipoUsuario);
	//public List<Usuario> todosLosUsuariosPorProyecto(Proyecto proyecto);
	public boolean asignarTipoUsuario(Usuario usuario, TipoUsuario tipo_usuario);
	public boolean eliminarTipoUsuarioAsignado(Usuario usuario, TipoUsuario tipo_usuario);
	//actualizar el tipo de usuario de un usuario
	
	//requerimientos que no estan en el arbol
	//El sistema debe permitir al administrador validar la cuenta de usuario de manera que pueda ser usada
	public boolean validarCuenta(String cedula, String visibilidad);
	//El sistema deberá permitir cambiar la contraseña del usuario de manera que él pueda escoger la contraseña de su preferencia
	public boolean cambiarContrasenaUsuario(String contrasena);
	
}

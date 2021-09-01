package co.edu.usbbog.sgpi.service;

import java.util.List;
import co.edu.usbbog.sgpi.model.Comentario;
import co.edu.usbbog.sgpi.model.Producto;


public interface IComentarioService {
	public List<Comentario> todosLosComentariosPorProducto(Producto producto);
	public boolean eliminarComentario(int id);
	public boolean crearComentario(Comentario comentario);
	
	//public boolean asignarProducto (Producto producto);
}

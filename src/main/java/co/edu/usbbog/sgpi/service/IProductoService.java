package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.Producto;
import co.edu.usbbog.sgpi.model.Proyecto;

public interface IProductoService {
	public List<Producto> todosLosProductos();
	public List<Producto> todosLosProductosPorProyecto(Proyecto proyecto);
	public boolean eliminarProducto(int id);
	public boolean crearProducto(Producto producto);
}

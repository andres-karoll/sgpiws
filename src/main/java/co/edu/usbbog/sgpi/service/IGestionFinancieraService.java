package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.Compra;
import co.edu.usbbog.sgpi.model.Presupuesto;
import co.edu.usbbog.sgpi.model.Proyecto;

public interface IGestionFinancieraService {
	public List<Presupuesto> PresupuestoPorProyecto(int proyecto);
	public boolean eliminarPresupuesto(int id);
	public boolean crearPresupuesto(Presupuesto presupuesto);
	//actualizar presupuesto
	
	public List<Compra> CompraPorPresupuesto(int presupuesto);
	public boolean eliminarCompra(int id);
	public boolean crearCompra(Compra compra);
	public boolean autorizarCompra(int Estado);
	//actualizar compra
}

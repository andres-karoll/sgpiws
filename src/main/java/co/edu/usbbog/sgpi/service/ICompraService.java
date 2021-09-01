package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.Compra;
import co.edu.usbbog.sgpi.model.Presupuesto;

public interface ICompraService {
	public List<Compra> todasLasComprasDelPresupuesto(Presupuesto presupuesto);
	public boolean eliminarCompra(int id);
	public boolean crearCompra(Compra compra);
}

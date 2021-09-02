package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.Presupuesto;
import co.edu.usbbog.sgpi.model.Proyecto;


public interface IPresupuestoService {
	public List<Presupuesto> todosLosPresupuestos();
	public List<Presupuesto> todosLosPresupuestosPorProyecto(Proyecto proyecto);
	public boolean eliminarPresupuesto(int id);
	public boolean crearPresupuesto(Presupuesto presupuesto);
}

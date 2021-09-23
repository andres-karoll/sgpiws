package co.edu.usbbog.sgpi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usbbog.sgpi.model.Compra;
import co.edu.usbbog.sgpi.model.Presupuesto;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.repository.ICompraRepository;
import co.edu.usbbog.sgpi.repository.IPresupuestoRepository;
import co.edu.usbbog.sgpi.repository.IProyectoRepository;
import net.minidev.json.JSONObject;

@Service
public class GestionFinancieraService implements IGestionFinancieraService{

	@Autowired
	private IPresupuestoRepository presupuestoRepo;
	
	@Autowired
	private IProyectoRepository proyectoRepo;
	
	@Autowired
	private ICompraRepository compraRepo;
	
	@Override
	public List<Presupuesto> PresupuestoPorProyecto(int proyecto) {
		Proyecto pro = proyectoRepo.getById(proyecto);
		List<Presupuesto> x = new ArrayList<>();
		if(pro != null) {
			List<Presupuesto> presupuesto = presupuestoRepo.findByProyecto(proyecto);
			return presupuesto;		
		} else {
			return x;
		}
	}

	@Override
	public boolean eliminarPresupuesto(int id) {
		List<Compra> compras = compraRepo.findByPresupuesto(id);
		boolean presupuesto = presupuestoRepo.existsById(id);
		
		if(compras.isEmpty() && presupuesto==true	 ) {
			presupuestoRepo.deleteById(id);
			return !presupuestoRepo.existsById(id);
			
		}
		return false;
	}

	@Override
	public boolean crearPresupuesto(Presupuesto presupuesto) {
		if(presupuestoRepo.existsById(presupuesto.getId())) {
			System.out.println("ya existe y no se puede guardar");
			return false;
			
		}else {
		System.out.println(" se guardado");
		presupuestoRepo.save(presupuesto);
		return true;}
	}

	

 
	@Override
	public List<Compra> CompraPorPresupuesto(int presupuesto) {
		Presupuesto pre = presupuestoRepo.getById(presupuesto);
		List<Compra> x = new ArrayList<>();
		if(pre != null) {
			List<Compra> compras = compraRepo.findByPresupuesto(presupuesto);
			return compras;
		}
		return x;
	}

	@Override
	public boolean eliminarCompra(int id) {
		boolean compras = compraRepo.existsById(id);
		
		if(compras==true) {
			compraRepo.deleteById(id);
			return !compraRepo.existsById(id);
		}
		return false;
	}

	@Override
	public boolean crearCompra(Compra compra) {
		if(compraRepo.existsById(compra.getId())) {
			return false;
		}
		else {
			compraRepo.save(compra);
			return true;
		}
	}

	@Override
	public boolean autorizarCompra(int Estado) {
		// TODO Auto-generated method stub
		return false;
	}



}

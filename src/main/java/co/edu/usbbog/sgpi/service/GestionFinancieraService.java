package co.edu.usbbog.sgpi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

		presupuestoRepo.save(presupuesto);
		return presupuestoRepo.existsById(presupuesto.getId());
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
	public boolean crearCompra(Compra compra,int presupuesto) {
		
		Presupuesto presu = presupuestoRepo.getById(presupuesto);
		if(presu == null) {
			return false;
		}else {
	
			compra.setPresupuesto(presu);
			compraRepo.save(compra);
		}
		return compraRepo.existsById(compra.getId());
	}

	
	
	
	@Override
	public boolean autorizarCompra(int Estado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean realziarCompra(int compra,String codigo,LocalDate fechaCompra,String link,Double valor) {
		Compra comp=compraRepo.getById(compra);
		comp.setCodigoCompra(codigo);
		comp.setFechaCompra(fechaCompra);
		comp.setLink(link);
		comp.setValor(valor);
		compraRepo.save(comp);
		return compraRepo.existsById(comp.getId());
	}

	


	

	



}

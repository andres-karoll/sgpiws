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
		List<Presupuesto> a = PresupuestoPorProyecto(presupuesto.getProyecto().getId());
		if(a !=null) {
			presupuestoRepo.save(presupuesto);
		System.out.println("hola");
		}
			
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
	public boolean realziarCompra(int compra,String codigo,LocalDate fechaCompra,Double valor,int estado) {
		Compra comp=compraRepo.getById(compra);
		
		
		
		//JSONObject salida = new JSONObject();
		JSONObject presupuestototal = presupuestoTotal(comp.getPresupuesto().getId());
		JSONObject comprastotales = new JSONObject();
		if(comprasTotales(comp.getPresupuesto().getId()) == null) {
			comprastotales.put("SUM(valor)", "0");
		}else {
			comprastotales = comprasTotales(comp.getPresupuesto().getId());
		}
		String pre= presupuestototal.getAsString("monto");
		String com = comprastotales.getAsString("SUM(valor)");

		double presupuesto = Double.parseDouble(pre);
		double compras = Double.parseDouble(com);
		
		if(presupuesto<(compras+valor)) {
			return false;
		}

		if(fechaCompra.isBefore(comp.getFechaSolicitud())) {
			return false;
		}
		comp.setCodigoCompra(codigo);
		comp.setFechaCompra(fechaCompra);
		comp.setEstado(estado);
		comp.setValor(valor);
		compraRepo.save(comp);
		
		return true;
	}

	@Override
	public boolean actualizarestadoCompra(int compra,int estado) {
		Compra comp=compraRepo.getById(compra);
		if(!compraRepo.existsById(compra)) {
			return false;
		}
		comp.setEstado(estado);
		compraRepo.save(comp);

		return compraRepo.existsById(comp.getId());
	}
	
	@Override
	public JSONObject comprasTotales(int presupuesto) {
		JSONObject comp = compraRepo.ComprasTotalesDelPresupuesto(presupuesto);
		return comp;
	}

	@Override
	public JSONObject presupuestoTotal(int presupuesto) {
		JSONObject presu = compraRepo.Presupuestototal(presupuesto);
		return presu;
	}

	@Override
	public List<JSONObject> comprasPorEstado(int estado, int presupuesto) {
		List<JSONObject> com = compraRepo.ComprasPorEstado(estado, presupuesto);
		return com;
	}

	


	

	



}

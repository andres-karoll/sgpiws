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
	public boolean crearCompra(Compra compra, String codigo_compra, double valor, LocalDate fecha_compra, String link,
			int presupuesto) {
		Presupuesto presu = presupuestoRepo.getById(presupuesto);
		if(presu == null) {
			return false;
		}
		if(fecha_compra.isBefore(compra.getFechaSolicitud())) {
			return false;
		}
		try {
		compra.setCodigoCompra(codigo_compra);
		compra.setValor(valor);
		compra.setFechaCompra(fecha_compra);
		compra.setLink(link);
		compra.setPresupuesto(presu);
		}catch (Exception e) {
			compra.setCodigoCompra(null);
			compra.setValor(null);
			compra.setFechaCompra(null);
			compra.setLink(null);
		}

		compraRepo.save(compra);
		return true;

	}

	
	
	
	@Override
	public boolean autorizarCompra(int Estado) {
		// TODO Auto-generated method stub
		return false;
	}

	


	

	



}

package co.edu.usbbog.sgpi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usbbog.sgpi.model.AreaConocimiento;
import co.edu.usbbog.sgpi.model.Comentario;
import co.edu.usbbog.sgpi.model.LineaInvestigacion;
import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.TipoProyecto;
import co.edu.usbbog.sgpi.repository.IAreaConocimientoRepository;
import co.edu.usbbog.sgpi.repository.IProyectoRepository;
import co.edu.usbbog.sgpi.repository.ITipoProyectoRepository;
import co.edu.usbbog.sgpi.repository.ITipoUsuarioRepository;


@Service
public class GestionFiltroProyectos implements IGestionFiltroProyectosService{

	@Autowired
	private IProyectoRepository proyectoRepo;
	
	@Autowired
	private ITipoProyectoRepository tipoProRepo;
	
	@Autowired
	private IAreaConocimientoRepository areaRepo;
	@Autowired
	private ITipoProyectoRepository iTipoProyectoRepository;
	
	@Override
	public List<Proyecto> todosLosProyectos() {
		List<Proyecto> proyectos = proyectoRepo.findAll();
		return proyectos;
	}

	@Override
	public List<Proyecto> todosLosProyectosPorLugar() {
		// TODO Auto-generated method stub
		//
		//
		//
		//
		//
		//
		//
		return null;}
	

	@Override
	public List<Proyecto> todosLosProyectosPorTipoProyecto(String tipo_proyecto) {
		List<Proyecto> x = new ArrayList<>();
		if(!tipoProRepo.existsById(tipo_proyecto)) {
			return x;
		}
		List<Proyecto> proyectos = proyectoRepo.findByTipoProyecto(tipo_proyecto);
		return proyectos;
	}



	@Override
	public List<Proyecto> todosLosProyectosPorAreaConocimiento(int areaConocimiento) {
		List<Proyecto> x = new ArrayList<>();
		if(!areaRepo.existsById(areaConocimiento)) {
			
			return x;
		}
		List<Proyecto> proyectos = proyectoRepo.findByAreaConocimiento(areaConocimiento);
		return proyectos;
	}
	
	
	@Override
	public List<Proyecto> todosLosProyectosPorNombre(String titulo) {
		List<Proyecto> x = new ArrayList<>();
		if(proyectoRepo.findByTitulo(titulo) == null) {			
			return x;
		}
		List<Proyecto> proyectos = proyectoRepo.findByTitulo(titulo);
		return proyectos;
	}

	@Override
	public List<Proyecto> todosLosProyectosPorLinea(String lineainvestigacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoProyecto> todosLosTiposProyecto() {
		List<TipoProyecto> tipo=iTipoProyectoRepository.findAll();
		if (tipo.equals(null)) {
			tipo = new ArrayList<TipoProyecto>();
		}
		return tipo;
	}

	

	

	
}

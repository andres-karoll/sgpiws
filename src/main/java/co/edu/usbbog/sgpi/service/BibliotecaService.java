package co.edu.usbbog.sgpi.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.usbbog.sgpi.model.Proyecto;
import co.edu.usbbog.sgpi.model.TipoProyecto;
import co.edu.usbbog.sgpi.repository.IProyectoRepository;
import co.edu.usbbog.sgpi.repository.ITipoProyectoRepository;

@Service
public class BibliotecaService implements IBibliotecaService{

	
	@Autowired
	private IProyectoRepository proyectoRepo;
	
	
	private ITipoProyectoRepository tipoProRepo;
	
	private static Logger logger = LoggerFactory.getLogger(BibliotecaService.class);
	
	@Override
	public List<Proyecto> todosLosProyectosDeGrado() {
		List<Proyecto> proyectos = proyectoRepo.findByTipoProyectoGrado();
		return proyectos;
	}

	@Override
	public List<Proyecto> todosLosProyectosTerminados(String grado, String estado) {
		List<Proyecto> x = new ArrayList<>();
		if(grado!= null) {
			List<Proyecto> estados = proyectoRepo.findByEstado(estado);
			if(estado!=null) {
				List<Proyecto> proyectoTerminado = proyectoRepo.findByTipoProyectoAndEstado(grado,estado);
				return proyectoTerminado;
			}
			else {
				logger.info("El estado no existe");
				return x;
			}
		}
		else {		
			logger.info("El tipo no existe");
			return x;
		}
	}


	
	


}

package co.edu.usbbog.sgpi.service;

import java.util.List;

import co.edu.usbbog.sgpi.model.Materia;
import co.edu.usbbog.sgpi.model.Programa;



public interface IMateriaService {
	public List<Materia> todasLasMaterias();
	public List<Materia> todasLasMateriasPorPrograma(Programa programa);
}

package co.edu.usbbog.sgpi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbbog.sgpi.model.TipoUsuario;

public interface ITipoUsuarioRepository extends JpaRepository<TipoUsuario,String> {
	@Query(value = "SELECT * FROM sgpi_db.tipo_usuario where nombre!=\"Admin\" and nombre!=\"Estudiante inactivo\";", nativeQuery = true)
	List<TipoUsuario> listarRoles();

}

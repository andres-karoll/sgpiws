/**
 * 
 */
package co.edu.usbbog.sgpi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbbog.sgpi.model.Programa;
import co.edu.usbbog.sgpi.model.Usuario;

/**
 * @author 57310
 *
 */
public interface IUsuarioRepository extends JpaRepository<Usuario, String> {

	//solo para consultar por programa
			@Query(value = "SELECT * FROM usuario where programa_id = ?1", nativeQuery = true)
			List<Usuario> findByPrograma(int programa_id);
}

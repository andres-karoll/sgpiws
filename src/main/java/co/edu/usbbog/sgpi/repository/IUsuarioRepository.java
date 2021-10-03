/**
 * 
 */
package co.edu.usbbog.sgpi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import co.edu.usbbog.sgpi.model.Programa;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import co.edu.usbbog.sgpi.model.Usuario;
import net.minidev.json.JSONObject;

/**
 * @author 57310
 *
 */
public interface IUsuarioRepository extends JpaRepository<Usuario, String> {

	//solo para consultar por programa
			@Query(value = "SELECT * FROM usuario where programa_id = ?1", nativeQuery = true)
			List<Usuario> findByPrograma(int programa_id);
	
	//@Query(value = "SELECT tipo_usuario FROM sgpi_db.usuarios where usuario = ?1 && tipo_usuario = ?2 ", nativeQuery = true)
	//JSONObject findByUsuario(String usuario,String tipo);
	
	@Modifying
	@Transactional
	@Query(value= "UPDATE `sgpi_db`.`usuario` SET `semillero_id` = null WHERE (`cedula` = ?1)", nativeQuery=true)
	void setSemilleroById(String cedula);
	@Modifying
	@Transactional
	@Query(value= "DELETE FROM `sgpi_db`.`usuarios` WHERE (`usuario` = ?1) and (`tipo_usuario` = ?2)", nativeQuery=true)
	void deleteUsuariosById(String cedula, String nombre);
	
	
	@Modifying
	@Transactional
	@Query(value="UPDATE `sgpi_db`.`grupo_investigacion` SET `director_grupo` = null WHERE (`id` = ?1)",nativeQuery=true)
	void deleteDirectorById(String grupo);
	@Query(value = "select * from usuario where correo_est=?1 && contrasena=?2" , nativeQuery = true)
	JSONObject Login(String correo, String contrasena);
	
}

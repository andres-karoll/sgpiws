/**
 * 
 */
package co.edu.usbbog.sgpi.repository;

import java.util.ArrayList;
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
	@Query(value = "select * from usuario,usuarios where usuario.correo_est=?1 && usuario.contrasena=?2	 && usuarios.tipo_usuario=?3 && usuario.cedula=usuarios.usuario" , nativeQuery = true)
	JSONObject Login(String correo, String contrasena,String tipo );
	
	@Query(value = "select * from usuario where usuario.correo_est=?1", nativeQuery = true)
	Usuario JSONObject(String correo);
	
	@Query(value = "select * from usuarios where tipo_usuario= \"Estudiante inactivo\"", nativeQuery = true)
	JSONObject getByTipoEstudianteInactivo();
	
	@Query(value = "select * from usuarios where tipo_usuario= \"Estudiante activo\"", nativeQuery = true)
	JSONObject getByTipoEstudianteActivo();	
	
	@Query(value = "select * from usuarios where tipo_usuario= \"Egresado\"", nativeQuery = true)
	JSONObject getByTipoEstudianteEgresado();
	
	@Query(value = "select * from usuarios where tipo_usuario= \"Investigador formacion\"", nativeQuery = true)
	JSONObject getByTipoInvestigadorFormacion();
	
	@Query(value = "select * from usuarios where tipo_usuario= \"Personal biblioteca\"", nativeQuery = true)
	JSONObject getByTipoPersonalBiblioteca();
	
	@Query(value = "select * from usuarios where tipo_usuario= \"Personal publicaciones\"", nativeQuery = true)
	JSONObject getByTipoPersonalPublicaciones();
	
	@Query(value = "select * from usuarios where tipo_usuario= \"Semillerista\"", nativeQuery = true)
	JSONObject getByTipoSemillerista();
	
	@Query(value = "select * from usuario where usuario.correo_est=?1", nativeQuery = true)
	Usuario getByCorreo(String correo);
	
	@Modifying
	@Transactional
	@Query(value= "delete from usuarios where usuario = ?1 and tipo_usuario= ?2", nativeQuery=true)
	void deleteTipo(String usuario, String tipo);

}

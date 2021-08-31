/**
 * 
 */
package co.edu.usbbog.sgpi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbbog.sgpi.model.Usuario;

/**
 * @author 57310
 *
 */
public interface IUsuarioRepository extends JpaRepository<Usuario, String> {

}

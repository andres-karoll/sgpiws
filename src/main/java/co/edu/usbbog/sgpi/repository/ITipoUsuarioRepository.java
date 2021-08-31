package co.edu.usbbog.sgpi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbbog.sgpi.model.TipoUsuario;

public interface ITipoUsuarioRepository extends JpaRepository<TipoUsuario,String> {

}

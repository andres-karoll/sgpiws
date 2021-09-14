package co.edu.usbbog.sgpi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbbog.sgpi.model.Semillero;
import co.edu.usbbog.sgpi.model.Usuario;

public interface ISemilleroRepository extends JpaRepository<Semillero, Integer>{
	@Modifying
	@Transactional
	@Query(value="UPDATE `sgpi_db`.`semillero` SET `lider_semillero` = null WHERE (`id` = ?1)",nativeQuery=true)
	void deleteLiderSemilleroById(String semillero);

}

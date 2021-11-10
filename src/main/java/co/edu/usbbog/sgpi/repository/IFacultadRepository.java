package co.edu.usbbog.sgpi.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbbog.sgpi.model.Facultad;

public interface IFacultadRepository extends JpaRepository<Facultad, Integer>{
	@Transactional
	@Modifying
	@Query(value= "UPDATE `sgpi_db`.`facultad` SET `decano` = ?1 WHERE (`id` = ?2)", nativeQuery=true)
	void setDecanoById(String decanoId,String string);
	@Transactional
	@Modifying
	@Query(value= "UPDATE `sgpi_db`.`facultad` SET `coor_inv` = ?1 WHERE (`id` = ?2)", nativeQuery=true)
	void setCoorInvById(String coorInvId,String facultadId);
	@Modifying
	@Transactional
	@Query(value= "UPDATE `sgpi_db`.`facultad` SET `decano` = null WHERE (`id` = ?1);", nativeQuery=true)
	void deleteDecanoById(String facultad);
	@Modifying
	@Transactional
	@Query(value="UPDATE `sgpi_db`.`facultad` SET `coor_inv` = null WHERE (`id` = ?1)",nativeQuery=true)
	void deleteCoorInvById(String facultad);

	
}

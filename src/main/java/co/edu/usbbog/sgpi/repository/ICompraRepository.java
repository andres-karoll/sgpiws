package co.edu.usbbog.sgpi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbbog.sgpi.model.Compra;
import co.edu.usbbog.sgpi.model.Presupuesto;
import net.minidev.json.JSONObject;

public interface ICompraRepository extends JpaRepository<Compra, Integer>{


			@Query(value = "SELECT * FROM sgpi_db.compra where presupuesto = ?1", nativeQuery = true)
			List<Compra> findByPresupuesto(int proyecto);
			

			@Query(value = "SELECT * FROM sgpi_db.compra where presupuesto = ?1", nativeQuery = true)
			List<Compra> ActualizarEstado(int proyecto);
}

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
			
			@Query(value = "SELECT SUM(valor) FROM sgpi_db.compra WHERE presupuesto = ?1", nativeQuery = true)
			JSONObject ComprasTotalesDelPresupuesto(int presupuesto);
			
			@Query(value = "SELECT monto FROM sgpi_db.presupuesto WHERE id = ?1", nativeQuery = true)
			JSONObject Presupuestototal(int presupuesto);
			
			@Query(value = "SELECT * FROM sgpi_db.compra WHERE estado = ?1 and presupuesto = ?2", nativeQuery = true)
			List<JSONObject> ComprasPorEstado(int estado, int presupuesto);
			
			
}

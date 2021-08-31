package co.edu.usbbog.sgpi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.usbbog.sgpi.model.Producto;

public interface IProductoRepository extends JpaRepository<Producto, Integer>{

}

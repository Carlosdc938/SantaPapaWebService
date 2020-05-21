package com.example.minimarket2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.minimarket2.entity.Producto;
import com.example.minimarket2.entity.Usuario;


@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
	Producto findFirstByCodigo(String codigo);
	
	@Query(value="select * from producto where stock<4", nativeQuery=true)
	List<Producto> listaproducto();
	
	@Query(value = "select * from producto order by cantidad_vendida desc",nativeQuery = true)
	List<Producto> listaproductosmayor();
}

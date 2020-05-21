package com.example.minimarket2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.minimarket2.entity.ProductoVendido;

public interface ProductoVendidoRepository extends JpaRepository<ProductoVendido, Integer> {

}

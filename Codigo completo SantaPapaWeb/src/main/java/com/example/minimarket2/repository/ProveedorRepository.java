package com.example.minimarket2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.minimarket2.entity.Proveedor;


@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{

}

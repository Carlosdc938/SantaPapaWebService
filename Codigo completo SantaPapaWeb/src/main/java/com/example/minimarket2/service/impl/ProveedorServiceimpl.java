package com.example.minimarket2.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.minimarket2.entity.Proveedor;
import com.example.minimarket2.repository.ProveedorRepository;
import com.example.minimarket2.service.ProveedorService;

@Service
public class ProveedorServiceimpl implements ProveedorService{

	@Autowired
	private ProveedorRepository proveedorRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Proveedor> findAll() throws Exception {
		
		return proveedorRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Proveedor> findById(Integer id) throws Exception {
		
		return proveedorRepository.findById(id);
	}

	@Transactional
	@Override
	public Proveedor save(Proveedor entity) throws Exception {
		
		return proveedorRepository.save(entity);
	}

	@Transactional
	@Override
	public Proveedor update(Proveedor entity) throws Exception {
		
		return proveedorRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		
		proveedorRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		
		proveedorRepository.deleteAll();
	}
}

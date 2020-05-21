package com.example.minimarket2.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.minimarket2.entity.ProductoVendido;
import com.example.minimarket2.repository.ProductoVendidoRepository;
import com.example.minimarket2.service.ProductoVendidoService;

@Service
public class ProductoVendidoServiceimpl implements ProductoVendidoService {

	@Autowired
	ProductoVendidoRepository productoVendidoRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<ProductoVendido> findAll() throws Exception {
		return productoVendidoRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<ProductoVendido> findById(Integer id) throws Exception {
		return productoVendidoRepository.findById(id);
	}

	@Transactional
	@Override
	public ProductoVendido save(ProductoVendido entity) throws Exception {
		return productoVendidoRepository.save(entity);
	}

	@Transactional
	@Override
	public ProductoVendido update(ProductoVendido entity) throws Exception {
		return productoVendidoRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		productoVendidoRepository.deleteById(id);		
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		productoVendidoRepository.deleteAll();
	}

}

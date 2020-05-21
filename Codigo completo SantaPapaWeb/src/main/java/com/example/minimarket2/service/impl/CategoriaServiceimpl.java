package com.example.minimarket2.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.minimarket2.entity.Categoria;
import com.example.minimarket2.repository.CategoriaRepository;
import com.example.minimarket2.service.CategoriaService;

@Service
public class CategoriaServiceimpl implements CategoriaService{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Categoria> findAll() throws Exception {
		return categoriaRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Categoria> findById(String id) throws Exception {
		return categoriaRepository.findById(id);
	}

	@Transactional
	@Override
	public Categoria save(Categoria entity) throws Exception {
		return categoriaRepository.save(entity);
	}

	@Transactional
	@Override
	public Categoria update(Categoria entity) throws Exception {
		return categoriaRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(String id) throws Exception {
	
		categoriaRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		categoriaRepository.deleteAll();
	}

}

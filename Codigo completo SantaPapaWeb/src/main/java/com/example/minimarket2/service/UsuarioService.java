package com.example.minimarket2.service;

import java.util.List;
import java.util.Optional;

import com.example.minimarket2.entity.Usuario;

public interface UsuarioService extends CrudService<Usuario, Long> {
	Optional<Usuario> findByUsername(String username) throws Exception;
	
	List<Usuario> listatrabajador() throws Exception; 
}
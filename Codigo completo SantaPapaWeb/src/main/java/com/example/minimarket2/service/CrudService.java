package com.example.minimarket2.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {
	List<T> findAll() throws Exception; //selet*where
	Optional<T> findById( ID id ) throws Exception; //busca elemento por un id y devuelvew un optional
	T save( T entity ) throws Exception; //grabar
	T update( T entity ) throws Exception;	//recibe un entity
	void deleteById( ID id ) throws Exception; // borrar un elemento por id
	void deleteAll() throws Exception; //borrar todo
}
package com.example.minimarket2.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.minimarket2.entity.Producto;

public interface ProductoService extends CrudService<Producto, Integer> {
	Producto findFirstByCodigo(String string);

	boolean createPdf(List<Producto> productos, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);
	
	List<Producto> listaproductosmayor() throws Exception; 
}

package com.example.minimarket2.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.minimarket2.entity.ProductoVendido;
import com.example.minimarket2.entity.Venta;

public interface VentaService extends CrudService<Venta, Integer> {

	boolean createPdf(List<Venta> ventas, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);

	boolean createPdfCom(Venta venta, ServletContext context, HttpServletRequest request,
			HttpServletResponse response);
	
}

package com.example.minimarket2.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.minimarket2.entity.Producto;
import com.example.minimarket2.entity.Venta;
import com.example.minimarket2.service.ProductoService;
import com.example.minimarket2.service.VentaService;

@Controller
public class PdfController {
	@Autowired
	private VentaService ventaService;

	@Autowired
	private ProductoService productoService;

	@Autowired
	private ServletContext context;

	@GetMapping(value = "/createPdf")
	public void createPdf(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Venta> ventas = ventaService.findAll();
			boolean isFlag = ventaService.createPdf(ventas, context, request, response);
			if (isFlag) {
				String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "ventas" + ".pdf");
				filedownload(fullPath, response, "ventas.pdf");
			}
		} catch (Exception e) {

		}
	}

	private void filedownload(String fullPath, HttpServletResponse response, String fileName) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename=" + fileName);
				OutputStream outputStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outputStream.close();
				file.delete();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@GetMapping(value = "/createPdfProductos")
	public void createPdfProductos(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Producto> productos = productoService.findAll();
			boolean isFlag = productoService.createPdf(productos, context, request, response);
			if (isFlag) {
				String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "productos" + ".pdf");
				filedownload2(fullPath, response, "productos.pdf");
			}
		} catch (Exception e) {

		}
	}

	@GetMapping(value = "/createPdfComprobante/{id}")
	public void createPdfComprobante(@PathVariable("id") int id, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Optional<Venta> optional= ventaService.findById(id);
			if (optional.isPresent()) {
				boolean isFlag = ventaService.createPdfCom(optional.get(), context, request, response);
				if (isFlag) {
					String fullPath = request.getServletContext().getRealPath("/resources/reports/" + "recibo"+".pdf");
					filedownload3(fullPath, response, "recibo.pdf");
				}
			}
		} catch (Exception e) {

		}
	}

	private void filedownload2(String fullPath, HttpServletResponse response, String fileName) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename=" + fileName);
				OutputStream outputStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outputStream.close();
				file.delete();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void filedownload3(String fullPath, HttpServletResponse response, String fileName) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		if (file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename=" + fileName);
				OutputStream outputStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outputStream.close();
				file.delete();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

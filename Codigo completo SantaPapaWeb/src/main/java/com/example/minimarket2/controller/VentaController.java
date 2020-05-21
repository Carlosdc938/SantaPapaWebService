package com.example.minimarket2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.minimarket2.service.VentaService;

@Controller
@RequestMapping(path = "/venta")
public class VentaController {
	@Autowired
	private VentaService ventaService;
	
	@GetMapping
	public String inicio(Model model) {
		try {
			  model.addAttribute("ventas", ventaService.findAll());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "venta/ver_ventas";
	}
}

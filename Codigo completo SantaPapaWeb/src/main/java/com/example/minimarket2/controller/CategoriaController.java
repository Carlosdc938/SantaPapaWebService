package com.example.minimarket2.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.example.minimarket2.entity.Categoria;
import com.example.minimarket2.service.CategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaService;
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Categoria> categorias= categoriaService.findAll();
			model.addAttribute("categorias",categorias);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/categoria/inicio";
	}
	
	@GetMapping("/edit/{id}")
	public String editar (@PathVariable("id") String id, Model model) {
		try {
			Optional<Categoria> optional = categoriaService.findById(id);
			if(optional.isPresent()) {
				
				model.addAttribute("categoria", optional.get());
			}else {
				return "redirect:/categoria";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/categoria/edit";
	}
	@PostMapping("/save")
	public String save(@ModelAttribute("categoria") Categoria categoria,Model model, SessionStatus status) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		try {
			categoria.setUsuarioCreacion(username);
			categoriaService.save(categoria);
			status.setComplete();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/categoria";
	}
	
	@PostMapping("/saveedit")
	public String saveedit(@ModelAttribute("categoria") Categoria categoria,Model model, SessionStatus status) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		try {
			categoria.setUsuarioActualizacion(username);
			categoria.setFechaActualizacion(obtenerFechaActual());
			categoriaService.save(categoria);
			status.setComplete();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/categoria";
	}
	
	static String obtenerFechaActual() {
        String formato = "yyyy-MM-dd";
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
        LocalDateTime ahora = LocalDateTime.now();
        return formateador.format(ahora);
    }
	
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Categoria categoria = new Categoria();
		model.addAttribute("categoria",categoria);
		return "/categoria/nuevo";
	}
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id") String id, Model model) {
		try {
			Optional<Categoria> categoria= categoriaService.findById(id);
			if(categoria.isPresent()) {
				categoriaService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel","ERROR");
			try {
				List<Categoria> categorias = categoriaService.findAll();
				model.addAttribute("categorias",categorias);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			return "/categoria/inicio";
		}
		return "redirect:/categoria";
	}
	@GetMapping("/info/{id}")
	public String info (@PathVariable("id") String id, Model model) {
		try {
			Optional<Categoria> categoria = categoriaService.findById(id);
			if(categoria.isPresent()) {
				model.addAttribute("categoria",categoria.get());
			}else {
				return "redirect:/categoria";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/categoria/info";
	}
}

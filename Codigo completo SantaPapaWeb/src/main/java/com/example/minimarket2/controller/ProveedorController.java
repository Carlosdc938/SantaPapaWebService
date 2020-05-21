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

import com.example.minimarket2.entity.Proveedor;
import com.example.minimarket2.service.ProveedorService;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
	@Autowired
	private ProveedorService proveedorService;

	@GetMapping
	public String inicio(Model model) {
		try {
			List<Proveedor> proveedores= proveedorService.findAll();
			model.addAttribute("proveedores", proveedores);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/proveedor/inicio";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Proveedor> optional = proveedorService.findById(id);
			if (optional.isPresent()) {

				model.addAttribute("proveedor", optional.get());
			} else {
				return "redirect:/proveedor";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/proveedor/edit";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("proveedor") Proveedor proveedor, Model model, SessionStatus status) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		try {
			proveedor.setUsuarioCreacion(username);
			proveedorService.save(proveedor);
			status.setComplete();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/proveedor";
	}
	
	@PostMapping("/saveedit")
	public String saveedit(@ModelAttribute("proveedor") Proveedor proveedor, Model model, SessionStatus status) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		try {
			proveedor.setFechaActualizacion(obtenerFechaActual());
			proveedor.setUsuarioActualizacion(username);
			proveedorService.save(proveedor);
			status.setComplete();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/proveedor";
	}
	
	static String obtenerFechaActual() {
        String formato = "yyyy-MM-dd";
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
        LocalDateTime ahora = LocalDateTime.now();
        return formateador.format(ahora);
    }
	
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Proveedor proveedor = new Proveedor();
		model.addAttribute("proveedor", proveedor);
		return "/proveedor/nuevo";
	}

	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Proveedor> proveedor = proveedorService.findById(id);
			if (proveedor.isPresent()) {
				proveedorService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel", "ERROR");
			try {
				List<Proveedor> proveedores = proveedorService.findAll();
				model.addAttribute("proveedores", proveedores);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			return "/proveedor/inicio";
		}
		return "redirect:/proveedor";
	}

	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") int id, Model model) {
		try {
			Optional<Proveedor> proveedor= proveedorService.findById(id);
			if (proveedor.isPresent()) {
				model.addAttribute("proveedor", proveedor.get());
			} else {
				return "redirect:/proveedor";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/proveedor/info";
	}
}

package com.example.minimarket2.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
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
import com.example.minimarket2.entity.Producto;
import com.example.minimarket2.service.CategoriaService;
import com.example.minimarket2.service.ProductoService;
import com.example.minimarket2.service.impl.NotificationService;

import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	private Logger logger = (Logger) LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private NotificationService notificationService;

	@GetMapping
	public String inicio(Model model) {
		try {
			List<Producto> productos = productoService.findAll();
			model.addAttribute("productos", productos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/producto/inicio";
	}

	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Producto> optional = productoService.findById(id);
			if (optional.isPresent()) {
				List<Categoria> categorias = categoriaService.findAll();
				model.addAttribute("producto", optional.get());
				model.addAttribute("categorias", categorias);
				try {
					notificationService.sendNotification();
				}catch (MailException e) {
					logger.info("Error al enviar el mensaje: "+e.getMessage());
				}
			} else {
				return "redirect:/producto";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/producto/edit";
	}
	
	@GetMapping("/productosmayor")
	public String camarero(Model model) {
		try {
			List<Producto> producto = productoService.listaproductosmayor();
			model.addAttribute("productos", producto);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/producto/productosmayor";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute("producto") Producto producto, Model model, SessionStatus status) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		try {
			producto.setUsuarioCreacion(username);
			productoService.save(producto);
			status.setComplete();
			try {
				notificationService.sendNotification();
			}catch (MailException e) {
				logger.info("Error al enviar el mensaje: "+e.getMessage());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/producto";
	}
	
	@PostMapping("/saveedit")
	public String saveedit(@ModelAttribute("producto") Producto producto, Model model, SessionStatus status) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String username = authentication.getName();
			producto.setFechaActualizacion(obtenerFechaActual());
			producto.setUsuarioActualizacion(username);
			productoService.save(producto);
			status.setComplete();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/producto";
	}
	
	static String obtenerFechaActual() {
        String formato = "yyyy-MM-dd";
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
        LocalDateTime ahora = LocalDateTime.now();
        return formateador.format(ahora);
    }

	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Producto producto = new Producto();
		model.addAttribute("producto", producto);
		try {
			List<Categoria> categorias = categoriaService.findAll();
			model.addAttribute("categorias", categorias);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/producto/nuevo";
	}

	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Producto> producto = productoService.findById(id);
			if (producto.isPresent()) {
				productoService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel", "ERROR");
			try {
				List<Producto> productos = productoService.findAll();
				model.addAttribute("productos", productos);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			return "/producto/inicio";
		}
		return "redirect:/producto";
	}

	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") int id, Model model) {
		try {
			Optional<Producto> producto = productoService.findById(id);
			if (producto.isPresent()) {
				model.addAttribute("producto", producto.get());
			} else {
				return "redirect:/producto";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/producto/info";
	}
}

package com.example.minimarket2.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.minimarket2.entity.Usuario;
import com.example.minimarket2.service.UsuarioService;
import com.example.minimarket2.service.impl.NotificationService;

import ch.qos.logback.classic.Logger;


@Controller
@RequestMapping("/usuario")
@SessionAttributes({"usuario"})
public class UsuarioController {
	@Autowired
	NotificationService notificationService;
	
	private Logger logger = (Logger) LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@GetMapping("/register")
	public String register(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		return "/usuario/register";
	}
	
	@GetMapping("/registercliente")
	public String registerCliente(Model model) {
		Usuario usuario = new Usuario();
		model.addAttribute("usuario", usuario);
		return "/usuario/registercliente";
	}
	
	@GetMapping("/trabajador")
	public String camarero(Model model) {
		try {
			List<Usuario> usuario = usuarioService.listatrabajador();
			model.addAttribute("usuarios", usuario);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/usuario/trabajador";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("usuario") Usuario usuario, 
			Model model, SessionStatus status) {
		
		try {
			// Verificar que el username ya exista.
			Optional<Usuario> optional 
				= usuarioService.findByUsername(usuario.getUsername());
			if(optional.isPresent()) {
				model.addAttribute("dangerRegister"
						, "ERROR - El username " 
							+ usuario.getUsername() 
							+ " ya existe ");
				return "/usuario/register";
			} else {
				usuario.setPassword(passwordEncoder
						.encode( usuario.getPassword() ));
				usuario.setCargo("trabajador");
				usuario.addAuthority("ROLE_TRABAJADOR");
				usuarioService.save(usuario);
				status.setComplete();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR");
		}
		return "redirect:/usuario/trabajador";
	}
	
	@PostMapping("/savecliente")
	public String saveCliente(@ModelAttribute("usuario") Usuario usuario, 
			Model model, SessionStatus status) {
		
		try {
			// Verificar que el username ya exista.
			Optional<Usuario> optional 
				= usuarioService.findByUsername(usuario.getUsername());
			if(optional.isPresent()) {
				model.addAttribute("dangerRegister"
						, "ERROR - El username " 
							+ usuario.getUsername() 
							+ " ya existe ");
				return "/usuario/register";
			} else {
				usuario.setPassword(passwordEncoder
						.encode( usuario.getPassword() ));
				usuario.setCargo("cliente");
				usuario.addAuthority("ROLE_CLIENTE");
				usuarioService.save(usuario);
				try {
					notificationService.sendNotificationCliente(usuario);
				}catch (MailException e) {
					logger.info("Error al enviar el mensaje: "+e.getMessage());
				}
				status.setComplete();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR");
		}
		return "/login";
	}
	
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id") Long id, Model model) {
		try {
			Optional<Usuario> usuario = usuarioService.findById(id);
			if(usuario.isPresent()) {
				usuarioService.deleteById(id);
			}
		} catch (Exception e) {
			
			model.addAttribute("dangerDel", "ERROR - Violación contra el principio de Integridad referencia");
			try {
				List<Usuario> usuario = usuarioService.findAll();
				model.addAttribute("usuario", usuario);
			} catch (Exception e2) {
				// TODO: handle exception
			} 
			return "/usuario/trabajador";
		}
		return "redirect:/usuario/trabajador";
	}
	
	@GetMapping("verid")
	public String verId(Model model) {
		// Para obtener el username
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		try {
			Optional<Usuario> optional 
				= usuarioService.findByUsername(username);
			if(optional.isPresent()) {
				model.addAttribute("usuario", optional.get());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "/usuario/verid";
	}
}
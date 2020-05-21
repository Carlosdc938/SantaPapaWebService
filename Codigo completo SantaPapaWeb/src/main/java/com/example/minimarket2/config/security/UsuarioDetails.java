package com.example.minimarket2.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.minimarket2.entity.Usuario;

// Implementación de una clase detail que manipula al usuario
public class UsuarioDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	// @Autowired
	private Usuario usuario;	
	public UsuarioDetails(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		
		// Extraer la lista de las Authorities
		this.usuario.getAuthorities().forEach(authority -> {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
			grantedAuthorities.add(grantedAuthority);
		});

		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return this.usuario.getPassword();
	}

	@Override
	public String getUsername() {
		return this.usuario.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.usuario.isEnable();
	}
	
	// Datos de la Clase Usuari
	public String getNombres() {
		return this.usuario.getNombre();
	}
	public String getApellidos() {
		return this.usuario.getApellido();
	}
	public String getDireccion() {
		return this.usuario.getDireccion();
	}
	public Integer getTelefono() {
		return this.usuario.getTelefono();
	}
	public String getCorreo() {
		return this.usuario.getCorreo();
	}
	public String getCargo() {
		return this.usuario.getCargo();
	}
	public String getFechaCreacion() {
		return this.usuario.getFechaCreacion();
	}
	public String getHoraCreacion() {
		return this.usuario.getHoraCreacion();
	}
	public Long getId() {
		return this.usuario.getId();
	}
}

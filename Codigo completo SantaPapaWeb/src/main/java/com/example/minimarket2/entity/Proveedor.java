package com.example.minimarket2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROVEEDOR")
public class Proveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nom_proveedor", length = 25, nullable = false)
	private String nombre;
		
	@Column(name = "estado", length = 1)
	private String estado;
	
	@Column(name = "fecha_Creacion")
	private String fechaCreacion;
	
	@Column(name ="hora_Creacion")
	private String horaCreacion;
	
	@Column(name = "usuario_Creacion", length = 25)
	private String usuarioCreacion;
	
	@Column(name = "fecha_Actualizacion")
	private String fechaActualizacion;
	
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@Column(name = "usuario_Actualizacion", length = 25)
	private String usuarioActualizacion;
	
	@Column(name = "Direccion_proveedor", length = 50, nullable = true)
	private String direccion;
	
	@Column(name = "telefono",length = 9)
	private Integer telefono;
	
	@Column(name = "RUC",length = 11)
	private Integer ruc;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Usuario usuario;

	public Proveedor() {
		this.fechaCreacion=Utiles.obtenerFechaActual();
		this.horaCreacion=Utiles.obtenerHoraActual();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getHoraCreacion() {
		return horaCreacion;
	}

	public void setHoraCreacion(String horaCreacion) {
		this.horaCreacion = horaCreacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}


	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public String getUsuarioActualizacion() {
		return usuarioActualizacion;
	}


	public void setUsuarioActualizacion(String usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public Integer getTelefono() {
		return telefono;
	}


	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}


	public Integer getRuc() {
		return ruc;
	}


	public void setRuc(Integer ruc) {
		this.ruc = ruc;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}

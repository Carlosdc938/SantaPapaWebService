package com.example.minimarket2.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIA")
public class Categoria {

	@Id
	@Column(name = "categoria_id",length = 5)
	private String id;

	@Column(name = "nom_categoria", length = 25)
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
	
	@Column(name = "usuario_Actualizacion", length = 25)
	private String usuarioActualizacion;
	
	@OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
	private List<Producto> productos;

	public Categoria() {
		this.productos=new ArrayList<>();
		this.fechaCreacion=Utiles.obtenerFechaActual();
		this.horaCreacion=Utiles.obtenerHoraActual();
	}

	public void addProducto(Producto producto) {
		producto.setCategoria(this);
		this.productos.add(producto);
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
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


	public String getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getUsuarioActualizacion() {
		return usuarioActualizacion;
	}

	public void setUsuarioActualizacion(String usuarioActualizacion) {
		this.usuarioActualizacion = usuarioActualizacion;
	}

	public List<Producto> getProducto() {
		return productos;
	}

	public void setProducto(List<Producto> producto) {
		this.productos = producto;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	
}

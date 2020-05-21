package com.example.minimarket2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTO")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nombre", length = 25)
	private String nombre;

	@Column(name = "codigo")
	private String codigo;

	@Column(name = "precio")
	private Float precio;

	@Column(name = "stock")
	private Float stock;
	
	@Column(name = "cantidad")
	private Float cantidad=0.f;
	
	@Column(name = "cantidad_vendida")
	private Float cantidadVendida=0.f;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@Column(name = "fecha_Creacion")
	private String fechaCreacion;

	@Column(name = "hora_Creacion")
	private String horaCreacion;

	@Column(name = "usuario_Creacion", length = 25)
	private String usuarioCreacion;

	@Column(name = "fecha_Actualizacion")
	private String fechaActualizacion;

	@Column(name = "usuario_Actualizacion", length = 25)
	private String usuarioActualizacion;
	
	public Producto() {
		this.fechaCreacion=Utiles.obtenerFechaActual();
		this.horaCreacion=Utiles.obtenerHoraActual();
	}
	public Producto(String nombre, String codigo, Float precio, Float stock, Integer id) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.stock = stock;
        this.id = id;
    }
	 public Producto(String nombre, String codigo, Float precio, Float stock) {
	        this.nombre = nombre;
	        this.codigo = codigo;
	        this.precio = precio;
	        this.stock = stock;
	 }
	
	public boolean sinStock() {
		return this.stock<=0;
	}
	
	
	public void restarStock(Float stock) {
        this.stock -= stock;
    }
	
	public void cantidadVendida(Float cantidadVendida) {
        this.cantidadVendida += cantidadVendida;
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	public Float getStock() {
		return stock;
	}
	public void setStock(Float stock) {
		this.stock = stock;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
	public Float getCantidadVendida() {
		return cantidadVendida;
	}
	public void setCantidadVendida(Float cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}
	public Float getCantidad() {
		return cantidad;
	}
	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}
	
	
}

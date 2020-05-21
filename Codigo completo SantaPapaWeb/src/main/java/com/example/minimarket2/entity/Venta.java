package com.example.minimarket2.entity;

import javax.persistence.*;

import java.util.List;

@Entity
public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String fechaYHora;

	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL)
	private List<ProductoVendido> productos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Venta() {
		this.fechaYHora = Utiles.obtenerFechaActual();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getTotal() {
		Float total = 0f;
		for (ProductoVendido productoVendido : this.productos) {
			total += productoVendido.getTotal();
		}
		return total;
	}

	public String getFechaYHora() {
		return fechaYHora;
	}

	public void setFechaYHora(String fechaYHora) {
		this.fechaYHora = fechaYHora;
	}

	public List<ProductoVendido> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoVendido> productos) {
		this.productos = productos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}

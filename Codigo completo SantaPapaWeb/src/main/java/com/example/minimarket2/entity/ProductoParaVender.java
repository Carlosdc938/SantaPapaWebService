package com.example.minimarket2.entity;

public class ProductoParaVender extends Producto{
	 private Float cantidadd;

	    public ProductoParaVender(String nombre, String codigo, Float precio, Float stock, Integer id, Float cantidadd) {
	        super(nombre, codigo, precio, stock, id);
	        this.cantidadd = cantidadd;
	    }

	    public ProductoParaVender(String nombre, String codigo, Float precio, Float stock, Float cantidad) {
	        super(nombre, codigo, precio,cantidad);
	        this.cantidadd = cantidad;
	    }

	    public void aumentarCantidad() {
	        this.cantidadd++;
	    }

	    public Float getCantidad() {
	        return cantidadd;
	    }

	    public Float getTotal() {
	        return this.getPrecio() * this.cantidadd;
	    }
}

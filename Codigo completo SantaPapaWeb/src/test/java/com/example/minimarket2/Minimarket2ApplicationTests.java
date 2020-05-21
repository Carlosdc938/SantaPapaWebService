package com.example.minimarket2;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.minimarket2.entity.Categoria;
import com.example.minimarket2.entity.Producto;
import com.example.minimarket2.entity.Proveedor;
import com.example.minimarket2.repository.CategoriaRepository;
import com.example.minimarket2.repository.ProductoRepository;
import com.example.minimarket2.repository.ProveedorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Minimarket2ApplicationTests {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private ProveedorRepository proveedorRepository;
	
	
	@Test
	public void contextLoads() {
	
		try {
		/*
	 **************---------------------**************	
	 
		//Proveedor
		
		Proveedor nro1 = new Proveedor();
		nro1.setCodigo("A01");
		nro1.setNombre("Samuel");
		nro1.setApellidos("Lazo");
		nro1.setDireccion("Jr lambayeque 3031 ");
		nro1.setTelefono(9089890);
		
		Proveedor nro2 = new Proveedor();
		nro2.setCodigo("A02");
		nro2.setNombre("rwerew");
		nro2.setApellidos("Lopez");
		nro2.setDireccion("Jr piura 432 ");
		nro2.setTelefono(971036);
		
		Proveedor nro3 = new Proveedor();
		nro3.setCodigo("A03");
		nro3.setNombre("Benjamin");
		nro3.setApellidos("Ferreyra");
		nro3.setDireccion("Jr ancash 431 ");
		nro3.setTelefono(25153);
		
		nro1 = proveedorRepository.save(nro1);
		nro2 = proveedorRepository.save(nro2);
		nro3 = proveedorRepository.save(nro3);
		
		//Dueño
		
		Dueño du1 = new Dueño();
		du1.setCodigo("B01");
		du1.setNombre("Pedro");
		du1.setCorreo("sdsa@hotmail.com");
		du1.setDireccion("Jr concha 2122");
		du1.setTelefono(9284125);
		du1.setFechaNacimiento(new Date(1984, 10, 1));
		
		du1 = dueñoRepository.save(du1);
		
		// Vendedor 
		
		Vendedor vendedor1 = new Vendedor();
		vendedor1.setCodigo("C01");
		vendedor1.setNombre("Nakamura");
		vendedor1.setTelefono(9784512);
		vendedor1.setDireccion("Jr sada 584");
		vendedor1.setFechaNacimiento(new Date(1962, 1, 2));
		
		
		Vendedor vendedor2 = new Vendedor();
		vendedor2.setCodigo("C02");
		vendedor2.setNombre("Jose");
		vendedor2.setTelefono(9700312);
		vendedor2.setDireccion("Jr rrwecx 584");
		vendedor2.setFechaNacimiento(new Date(1980, 7, 7));
		
		vendedor1 = vendedorRepository.save(vendedor1);
		vendedor2 = vendedorRepository.save(vendedor2);
		
		
		//Cliente
		
		Cliente cliente1 = new Cliente();
		cliente1.setCodigo("D01");
		cliente1.setNombre("Carlos");
		cliente1.setApellido("Diaz");
		cliente1.setDireccion("Av dsad 823");
		cliente1.setTelefono(981218);
		
		cliente1 = clienteRepository.save(cliente1);
		
		//Comprobante
		
		Comprobante cp1 = new Comprobante();
		cp1.setCodigo("E01");
		cp1.setFechaEmision(new Date(2019, 10, 10));
		
		cp1 = comprobanteRepository.save(cp1);
		
		//Pedido
		
		Pedido pd1 = new Pedido();
		pd1.setCodigo("F01");
		pd1.setCantidad_pedido(100);
		
		pd1 = pedidoRepository.save(pd1);
		
		
		Producto
		
		Producto pr1= new Producto();
		pr1.setNombre("Leche Gloria six-pack");
		
		Producto pr2= new Producto();
		pr2.setNombre("papas");
		Producto pr3= new Producto();
		pr3.setNombre("gaseosa");
		
		pr1 = productoRepository.save(pr1);
		pr2 = productoRepository.save(pr2);
		pr3 = productoRepository.save(pr3);
		
		//categoria
		
		Categoria categoria1 = new Categoria();
		categoria1.setId("C01");
		categoria1.setNombre("Snacks");
		
		Categoria categoria2 = new Categoria();
		categoria2.setId("C02");
		categoria2.setNombre("Lacteos");
		
		Categoria categoria3 = new Categoria();
		categoria3.setId("C03");
		categoria3.setNombre("Bebidas");
		
		categoria1 = categoriaRepository.save(categoria1);
		categoria2 = categoriaRepository.save(categoria2);
		categoria3= categoriaRepository.save(categoria3);
		
		//Relacion Producto-Categoria
		pr1.setCategoria(categoria1);
		pr2.setCategoria(categoria2);
		
		//Relacion Categoria-Producto
		categoria3.addProducto(pr3);
		
		productoRepository.save(pr1);
		productoRepository.save(pr2);
		productoRepository.save(pr3);
		categoriaRepository.save(categoria1);
		categoriaRepository.save(categoria2);
		categoriaRepository.save(categoria3);*/
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

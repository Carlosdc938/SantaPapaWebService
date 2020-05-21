package com.example.minimarket2.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.minimarket2.entity.Producto;
import com.example.minimarket2.service.ProductoService;



@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {
	@Autowired
	private ProductoService productoService;
	
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto> > fetchProductos(){
		try {
			List<Producto> productos = productoService.findAll();
			return new ResponseEntity<List<Producto>>(productos,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Producto>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> saveProducto(@RequestBody Producto producto){
		try {
			Producto newProducto = productoService.save(producto);
			return new ResponseEntity<Producto>(newProducto,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,	
								produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< Producto > updateProducto(@PathVariable ("id") Integer id,
			@RequestBody Producto producto){
		
		try {
			if(id.equals(producto.getId())) {
				Optional<Producto> optional = productoService.findById(id);
					if(optional.isPresent()) {
						Producto updateProducto = productoService.update(producto);
						return new ResponseEntity<Producto>(updateProducto, HttpStatus.OK);
					}else {
						return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
					}
			}else {
				return new ResponseEntity<Producto>(HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity< Producto > deleteProducto(@PathVariable ("id") Integer id){
		try {
			Optional<Producto> optional = productoService.findById(id);
			if(optional.isPresent()) {
				productoService.deleteById(id);
				return new ResponseEntity<Producto>(HttpStatus.OK);
	}else {
		return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	} catch (Exception e) {
		return new ResponseEntity<Producto>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
	
	
	
}

package com.everis.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.everis.dto.ProductoDTO;
import com.everis.dto.ProductoReducidoDTO;
import com.everis.entidad.ImagenProducto;
import com.everis.entidad.Producto;
import com.everis.entidad.TipoProducto;
import com.everis.exception.ResourceNotFoundException;
import com.everis.exception.ValidacionException;
import com.everis.service.ProductoService;

@RestController
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@GetMapping("/productos")
	public Iterable<ProductoDTO> obtenerProductos() {
		Iterable<Producto> listaProductosEntidad = productoService.obtenerProductos();
		ArrayList<ProductoDTO> listaProductosDTO = new ArrayList<ProductoDTO>();
		listaProductosEntidad.forEach((Producto producto) -> {
			ProductoDTO productoDTO = new ModelMapper().map(producto, ProductoDTO.class);
			listaProductosDTO.add(productoDTO);
		});
		return listaProductosDTO;
	}

	@GetMapping("/productos/{id}")
	public ProductoDTO obtenerProductoPorId(@PathVariable Long id) throws ResourceNotFoundException {
		return new ModelMapper().map(productoService.obtenerProductoPorId(id), ProductoDTO.class);
	}
	
	@PostMapping("/productos")
	public ProductoDTO guardarProducto(@Valid @RequestBody ProductoReducidoDTO productoReducidoDTO)
			throws ValidacionException, ResourceNotFoundException {
		Producto productoEntidad = new ModelMapper().map(productoReducidoDTO, Producto.class);

		TipoProducto tipoProductoEntidad = new TipoProducto();
		tipoProductoEntidad.setCodigo(productoReducidoDTO.getCodigoProducto());
		
		System.out.println("Codigo de producto:"+productoReducidoDTO.getCodigoProducto());
		
		ImagenProducto imagenProductoEntidad = new ImagenProducto();
		imagenProductoEntidad.setRutaImagen(productoReducidoDTO.getRutaImagen());
		imagenProductoEntidad.setRutaThumbnail(productoReducidoDTO.getRutaThumbnail());

		productoEntidad.setTipoProducto(tipoProductoEntidad);
		productoEntidad.setImagenProducto(imagenProductoEntidad);
		return new ModelMapper().map(productoService.guardarProducto(productoEntidad), ProductoDTO.class);
	}

}
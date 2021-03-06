package com.everis.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.everis.dto.ProductoDTO;
import com.everis.dto.ProductoReducidoDTO;
import com.everis.dto.TipoProductoDTO;
import com.everis.entidad.ImagenProducto;
import com.everis.entidad.Producto;
import com.everis.entidad.TipoProducto;
import com.everis.exception.ResourceNotFoundException;
import com.everis.exception.ValidacionException;
import com.everis.service.FeignService;
import com.everis.service.ProductoService;
import com.everis.service.TipoProductoService;

@RestController
@RefreshScope
public class ProductoController {

	@Autowired
	private ProductoService productoService;

	@Autowired
	private TipoProductoService tipoProductoService;

	@Autowired
	private FeignService feignService;

	@Value("${igv}")
	private String igv;

	@GetMapping("/productos")
	public Iterable<ProductoDTO> obtenerProductos() {
		Iterable<Producto> listaProductosEntidad = productoService.obtenerProductos();
		ArrayList<ProductoDTO> listaProductosDTO = new ArrayList<ProductoDTO>();
		listaProductosEntidad.forEach((Producto producto) -> {
			ProductoDTO productoDTO = new ModelMapper().map(producto, ProductoDTO.class);
			try {
				productoDTO.setCantidadStock(
						feignService.obtenerCantidadProductosEnTodaLaTienda(producto.getId()).getCantidad());
			} catch (Exception e) {
				productoDTO.setCantidadStock(0L);
			}
			listaProductosDTO.add(productoDTO);
		});
		return listaProductosDTO;
	}

	@GetMapping("/productos/{id}")
	public ProductoDTO obtenerProductoPorId(@PathVariable Long id) throws ResourceNotFoundException {
		ProductoDTO productoDTO = new ModelMapper().map(productoService.obtenerProductoPorId(id), ProductoDTO.class);
		productoDTO.setCantidadStock(feignService.obtenerCantidadProductosEnTodaLaTienda(id).getCantidad());
		return productoDTO;
	}

	@PostMapping("/productos")
	public ProductoDTO guardarProducto(@Valid @RequestBody ProductoReducidoDTO productoReducidoDTO)
			throws ValidacionException, ResourceNotFoundException {
		Producto productoEntidad = new ModelMapper().map(productoReducidoDTO, Producto.class);

		TipoProducto tipoProductoEntidad = new TipoProducto();
		tipoProductoEntidad.setCodigo(productoReducidoDTO.getCodigoProducto());

		ImagenProducto imagenProductoEntidad = new ImagenProducto();
		imagenProductoEntidad.setRutaImagen(productoReducidoDTO.getRutaImagen());
		imagenProductoEntidad.setRutaThumbnail(productoReducidoDTO.getRutaThumbnail());

		productoEntidad.setTipoProducto(tipoProductoEntidad);
		productoEntidad.setImagenProducto(imagenProductoEntidad);

		return new ModelMapper().map(productoService.guardarProducto(productoEntidad), ProductoDTO.class);
	}

	@GetMapping("/tipoproductos")
	public Iterable<TipoProductoDTO> obtenerTipoProductos() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Iterable<TipoProducto> listaTipoProductos = tipoProductoService.obtenerTipoProductos();
		ArrayList<TipoProductoDTO> listaProductosDTO = new ArrayList<TipoProductoDTO>();
		listaTipoProductos.forEach((tipoProducto) -> {
			TipoProductoDTO tipoProductoDTO = modelMapper.map(tipoProducto, TipoProductoDTO.class);
			listaProductosDTO.add(tipoProductoDTO);
		});
		return listaProductosDTO;
	}

	@GetMapping("/igv")
	public String obtenerIGV() {
		return "El igv es:" + igv;
	}

}
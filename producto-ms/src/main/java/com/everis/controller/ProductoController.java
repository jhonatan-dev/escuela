package com.everis.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.everis.dto.CantidadStockDTO;
import com.everis.dto.ProductoDTO;
import com.everis.dto.ProductoReducidoDTO;
import com.everis.entidad.ImagenProducto;
import com.everis.entidad.Producto;
import com.everis.entidad.TipoProducto;
import com.everis.exception.ResourceNotFoundException;
import com.everis.exception.ValidacionException;
import com.everis.service.ProductoService;

@RestController
@RefreshScope
public class ProductoController {

	@Autowired
	private DiscoveryClient client;

	@Autowired
	private ProductoService productoService;

	public CantidadStockDTO getCantidad(String service, Long idProducto) {
		List<ServiceInstance> list = client.getInstances(service);
		if (list != null && list.size() > 0) {
			int rand = (int) Math.round(Math.random()*10) % list.size();
			URI uri = list.get(rand).getUri();
			if (uri != null) {
				return (new RestTemplate()).getForObject(uri.toString() + "/stock/acumulado/producto/{idProducto}",
						CantidadStockDTO.class, idProducto);
			}
		}
		return null;
	}
	
	@Value("${igv}") 
	private String igv;

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
		ProductoDTO productoDTO = new ModelMapper().map(productoService.obtenerProductoPorId(id), ProductoDTO.class);
		productoDTO.setCantidadStock(getCantidad("stock-ms", id).getCantidad());
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
		//return new ModelMapper().map(productoEntidad, ProductoDTO.class);
	}

	@GetMapping("/igv")
	public String obtenerIGV() {
		return "El igv es:" + igv;
	}

}
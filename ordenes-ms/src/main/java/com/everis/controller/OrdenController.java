package com.everis.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.everis.dto.CantidadStockDTO;
import com.everis.dto.OrdenDTO;
import com.everis.dto.OrdenReducidaDTO;
import com.everis.dto.ProductoDTO;
import com.everis.entidad.Orden;
import com.everis.exception.ResourceNotFoundException;
import com.everis.exception.ValidacionException;
import com.everis.service.OrdenService;

@RestController
@RefreshScope
public class OrdenController {

	@Autowired
	private DiscoveryClient client;

	@Autowired
	private OrdenService ordenService;

	public CantidadStockDTO getCantidadStock(String service, Long idProducto) {
		List<ServiceInstance> list = client.getInstances(service);
		if (list != null && list.size() > 0) {
			int rand = (int) Math.round(Math.random() * 10) % list.size();
			URI uri = list.get(rand).getUri();
			if (uri != null) {
				return (new RestTemplate()).getForObject(uri.toString() + "/stock/acumulado/producto/{idProducto}",
						CantidadStockDTO.class, idProducto);
			}
		}
		return null;
	}

	public ProductoDTO getProducto(String service, Long idProducto) {
		List<ServiceInstance> list = client.getInstances(service);
		if (list != null && list.size() > 0) {
			int rand = (int) Math.round(Math.random() * 10) % list.size();
			URI uri = list.get(rand).getUri();
			if (uri != null) {
				return (new RestTemplate()).getForObject(uri.toString() + "/productos/{idProducto}", ProductoDTO.class,
						idProducto);
			}
		}
		return null;
	}

	@PostMapping("/ordenes")
	public OrdenDTO guardarOrden(@Valid @RequestBody OrdenReducidaDTO ordenReducidaDTO)
			throws ValidacionException, ResourceNotFoundException {
		ModelMapper modelMapper = new ModelMapper();

		for (int i = 0; i < ordenReducidaDTO.getDetalle().size(); i++) {
			if (ordenReducidaDTO.getDetalle().get(i).getCantidad() > getCantidadStock("almacen-ms",
					ordenReducidaDTO.getDetalle().get(i).getIdProducto()).getCantidad()) {
				throw new ValidacionException("Cantidad del Producto con id "
						+ ordenReducidaDTO.getDetalle().get(i).getIdProducto() + " supera a su stock disponible.");
			}
		}

		Orden ordenEntidad = new ModelMapper().map(ordenReducidaDTO, Orden.class);

		ordenEntidad.setId(null);//Le pongo id nulo porque el modelMapper por alguna razón le pone un valor a este atributo.
		ordenEntidad.setFecha(new Date());
		ordenEntidad.setTotal(new BigDecimal(0));

		for (int i = 0; i < ordenEntidad.getDetalle().size(); i++) {
			ProductoDTO productoDTO = getProducto("producto-ms", ordenEntidad.getDetalle().get(i).getIdProducto());
			ordenEntidad.getDetalle().get(i).setId(null); //Le pongo id nulo porque el modelMapper por alguna razón le pone un valor a este atributo.
			ordenEntidad.getDetalle().get(i).setPrecio(productoDTO.getPrecio());
			BigDecimal totalDetalle = new BigDecimal(0);
			totalDetalle = ordenEntidad.getDetalle().get(i).getPrecio()
					.multiply(new BigDecimal(ordenEntidad.getDetalle().get(i).getCantidad()));
			ordenEntidad.setTotal(ordenEntidad.getTotal().add(totalDetalle));
		}
		return modelMapper.map(ordenService.guardarOrden(ordenEntidad), OrdenDTO.class);
	}
}

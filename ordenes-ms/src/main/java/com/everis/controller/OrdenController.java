package com.everis.controller;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.everis.dto.CantidadStockDTO;
import com.everis.dto.OrdenDTO;
import com.everis.dto.OrdenReducidaDTO;
import com.everis.dto.ProductoDTO;
import com.everis.entidad.Orden;
import com.everis.exception.ValidacionException;
import com.everis.feign.AlmacenClient;
import com.everis.feign.ProductoClient;
import com.everis.service.OrdenService;

@RestController
@RefreshScope
public class OrdenController {

	@Autowired
	private DiscoveryClient client;

	@Autowired
	private OrdenService ordenService;

	@Autowired
	private ProductoClient productoClient;

	@Autowired
	private AlmacenClient almacenClient;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/ordenes")
	public OrdenDTO guardarOrden(@Valid @RequestBody OrdenReducidaDTO ordenReducidaDTO) throws Exception {

		ModelMapper modelMapper = new ModelMapper();

		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		for (int i = 0; i < ordenReducidaDTO.getDetalle().size(); i++) {
			if (ordenReducidaDTO.getDetalle().get(i).getCantidad() > almacenClient
					.obtenerCantidadProductosEnTodaLaTienda(ordenReducidaDTO.getDetalle().get(i).getIdProducto())
					.getCantidad()) {
				throw new ValidacionException("Cantidad del Producto con id "
						+ ordenReducidaDTO.getDetalle().get(i).getIdProducto() + " supera a su stock disponible.");
			}
		}

		Orden ordenEntidad = modelMapper.map(ordenReducidaDTO, Orden.class);

		ordenEntidad.setFecha(new Date());
		ordenEntidad.setTotal(new BigDecimal(0));

		for (int i = 0; i < ordenEntidad.getDetalle().size(); i++) {
			ProductoDTO productoDTO = productoClient
					.obtenerProductoPorId(ordenEntidad.getDetalle().get(i).getIdProducto());
			ordenEntidad.getDetalle().get(i).setPrecio(productoDTO.getPrecio());
			BigDecimal totalDetalle = new BigDecimal(0);
			totalDetalle = ordenEntidad.getDetalle().get(i).getPrecio()
					.multiply(new BigDecimal(ordenEntidad.getDetalle().get(i).getCantidad()));
			ordenEntidad.setTotal(ordenEntidad.getTotal().add(totalDetalle));

			// Ya no sería necesario porque se usa el CustomInterceptor del paquete Util
			// ordenEntidad.getDetalle().get(i).setOrden(ordenEntidad);
		}

		Orden ordenRegistrada = ordenService.guardarOrden(ordenEntidad);
		
		almacenClient.actualizarStock(modelMapper.map(ordenRegistrada, OrdenReducidaDTO.class));

		return modelMapper.map(ordenRegistrada, OrdenDTO.class);
	}
}

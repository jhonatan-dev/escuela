package com.everis.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.everis.dto.CantidadStockDTO;
import com.everis.exception.ResourceNotFoundException;

@FeignClient("almacen-ms") // nombre de la aplicación del microservicio
public interface AlmacenClient {

	@GetMapping("/stock/acumulado/producto/{idProducto}")
	public CantidadStockDTO obtenerCantidadProductosEnTodaLaTienda(@PathVariable Long idProducto)
			throws ResourceNotFoundException;
}

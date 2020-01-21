package com.everis.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.everis.dto.CantidadStockDTO;
import com.everis.dto.OrdenReducidaDTO;
import com.everis.exception.ResourceNotFoundException;

@FeignClient("almacen-ms") // nombre de la aplicación del microservicio
public interface AlmacenClient {

	@GetMapping("/stock/acumulado/producto/{idProducto}")
	public CantidadStockDTO obtenerCantidadProductosEnTodaLaTienda(@PathVariable Long idProducto)
			throws ResourceNotFoundException;

	@PutMapping("/stock/actualizar")
	public void actualizarStock(@RequestBody OrdenReducidaDTO ordenReducidaDTO)
			throws ResourceNotFoundException, Exception;
}

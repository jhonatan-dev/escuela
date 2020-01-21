/**
 * 
 */
package com.everis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.everis.dto.CantidadStockDTO;
import com.everis.exception.ResourceNotFoundException;
import com.everis.feign.AlmacenClient;
import com.everis.service.FeignService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

public class FeignServiceImpl implements FeignService {

	@Autowired
	private AlmacenClient almacenClient;

	@HystrixCommand(fallbackMethod = "obtenerCantidadPorDefecto")
	@Override
	public CantidadStockDTO obtenerCantidadProductosEnTodaLaTienda(Long idProducto) throws ResourceNotFoundException {
		return almacenClient.obtenerCantidadProductosEnTodaLaTienda(idProducto);
	}
	
	public CantidadStockDTO obtenerCantidadPorDefecto(Long idProducto) throws ResourceNotFoundException {
		return new CantidadStockDTO(0L);
	}
	
}

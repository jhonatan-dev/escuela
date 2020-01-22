package com.everis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.dto.CantidadStockDTO;
import com.everis.exception.ResourceNotFoundException;
import com.everis.feign.AlmacenClient;
import com.everis.service.FeignService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class FeignServiceImpl implements FeignService {

	@Autowired
	private AlmacenClient almacenClient;

	@HystrixCommand(fallbackMethod = "obtenerCantidadProductosEnTodaLaTienda", groupKey = "obtenerCantidadProductosEnTodaLaTienda", commandKey = "obtenerCantidadProductosEnTodaLaTienda", threadPoolKey = "obtenerCantidadProductosEnTodaLaTienda", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5") }, threadPoolProperties = {
					@HystrixProperty(name = "queueSizeRejectionThreshold", value = "5") })
	@Override
	public CantidadStockDTO obtenerCantidadProductosEnTodaLaTienda(Long idProducto) throws ResourceNotFoundException {
		return almacenClient.obtenerCantidadProductosEnTodaLaTienda(idProducto);
	}

	public CantidadStockDTO obtenerCantidadPorDefecto(Long idProducto) throws ResourceNotFoundException {
		return new CantidadStockDTO(0L);
	}

}

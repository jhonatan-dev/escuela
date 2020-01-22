package com.everis.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.dto.CantidadStockDTO;
import com.everis.dto.OrdenReducidaDTO;
import com.everis.dto.ProductoDTO;
import com.everis.exception.ResourceNotFoundException;
import com.everis.feign.AlmacenClient;
import com.everis.feign.ProductoClient;
import com.everis.service.FeignService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class FeignServiceImpl implements FeignService {

	@Autowired
	private ProductoClient productoClient;

	@Autowired
	private AlmacenClient almacenClient;

	@HystrixCommand(fallbackMethod = "obtenerCantidadProductosEnTodaLaTiendaPorDefecto")
	@Override
	public CantidadStockDTO obtenerCantidadProductosEnTodaLaTienda(Long idProducto) throws ResourceNotFoundException {
		return almacenClient.obtenerCantidadProductosEnTodaLaTienda(idProducto);
	}

	public CantidadStockDTO obtenerCantidadProductosEnTodaLaTiendaPorDefecto(Long idProducto)
			throws ResourceNotFoundException {
		return new CantidadStockDTO(0L);
	}

	@HystrixCommand(fallbackMethod = "obtenerProductoPorIdPorDefecto")
	@Override
	public ProductoDTO obtenerProductoPorId(Long id) throws ResourceNotFoundException {
		return productoClient.obtenerProductoPorId(id);
	}
	
	public ProductoDTO obtenerProductoPorIdPorDefecto(Long id) throws ResourceNotFoundException {
		ProductoDTO productoDTO = new ProductoDTO();
		productoDTO.setPrecio(new BigDecimal(0));
		return productoDTO;
	}
	
	@HystrixCommand(fallbackMethod = "actualizarStockPorDefecto")
	@Override
	public void actualizarStock(OrdenReducidaDTO ordenReducidaDTO) throws ResourceNotFoundException, Exception {
		almacenClient.actualizarStock(ordenReducidaDTO);
	}
	
	@HystrixCommand(fallbackMethod = "actualizarStockPorDefecto")
	public void actualizarStockPorDefecto(OrdenReducidaDTO ordenReducidaDTO) throws ResourceNotFoundException, Exception {
		return;
	}
	
}

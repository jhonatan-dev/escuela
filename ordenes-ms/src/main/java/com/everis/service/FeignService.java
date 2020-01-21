package com.everis.service;

import com.everis.dto.CantidadStockDTO;
import com.everis.dto.OrdenReducidaDTO;
import com.everis.dto.ProductoDTO;
import com.everis.exception.ResourceNotFoundException;

public interface FeignService {

	public CantidadStockDTO obtenerCantidadProductosEnTodaLaTienda(Long idProducto) throws ResourceNotFoundException;

	public void actualizarStock(OrdenReducidaDTO ordenReducidaDTO) throws ResourceNotFoundException, Exception;

	public ProductoDTO obtenerProductoPorId(Long id) throws ResourceNotFoundException;
}

package com.everis.service;

import com.everis.dto.CantidadStockDTO;
import com.everis.exception.ResourceNotFoundException;

public interface FeignService {

	public CantidadStockDTO obtenerCantidadProductosEnTodaLaTienda(Long idProducto) throws ResourceNotFoundException;
}

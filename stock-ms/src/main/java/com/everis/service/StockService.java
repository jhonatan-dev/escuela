package com.everis.service;

import com.everis.exception.ResourceNotFoundException;

public interface StockService {

	public Long obtenerCantidadProductosEnTodaLaTienda(Long idProducto) throws ResourceNotFoundException;

	public Long obtenerCantidadProductosEnTiendaPorSuId(Long idProducto, Long idTienda)
			throws ResourceNotFoundException;
}

package com.everis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.everis.exception.ResourceNotFoundException;
import com.everis.repository.StockRepository;
import com.everis.service.StockService;

@Transactional(readOnly = true)
@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepository;

	@Override
	public Long obtenerCantidadProductosEnTodaLaTienda(Long idProducto) throws ResourceNotFoundException {
		return stockRepository.obtenerCantidadProductosEnTodaLaTienda(idProducto)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format("No se encontró el producto con el id %s en la BD.", idProducto)));
	}

	@Override
	public Long obtenerCantidadProductosEnTiendaPorSuId(Long idProducto, Long idTienda)
			throws ResourceNotFoundException {
		return stockRepository.obtenerCantidadProductosEnTiendaPorSuId(idProducto, idTienda)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format("No se encontró productos de la tienda con el id %s en la BD.", idTienda)));

	}
}

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
		if (stockRepository.existsByIdProducto(idProducto)) {
			return stockRepository.countByIdProducto(idProducto);
		} else {
			throw new ResourceNotFoundException(
					String.format("No se encontró un producto con el id %s en la BD.", idProducto));
		}
	}

	@Override
	public Long obtenerCantidadProductosEnTiendaPorSuId(Long idProducto, Long idTienda)
			throws ResourceNotFoundException {
		if (!stockRepository.existsByIdProducto(idProducto)) {
			throw new ResourceNotFoundException(
					String.format("No se encontró un producto con el id %s en la BD.", idProducto));
		}
		if (!stockRepository.existsByIdTienda(idTienda)) {
			throw new ResourceNotFoundException(
					String.format("No se encontró una tienda con el id %s en la BD.", idTienda));
		}
		return stockRepository.countByIdProductoAndIdTienda(idProducto, idTienda);
	}

}

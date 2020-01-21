package com.everis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.everis.entidad.Stock;
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
		return stockRepository.obtenerCantidadProductosEnTodaLaTienda(idProducto).orElse(new Long(0));
	}

	@Override
	public Long obtenerCantidadProductosEnTiendaPorSuId(Long idProducto, Long idTienda)
			throws ResourceNotFoundException {
		return stockRepository.obtenerCantidadProductosEnTiendaPorSuId(idProducto, idTienda).orElse(new Long(0));
		/*
		 * return stockRepository.obtenerCantidadProductosEnTiendaPorSuId(idProducto,
		 * idTienda) .orElseThrow(() -> new ResourceNotFoundException(
		 * String.format("No se encontró productos de la tienda con el id %s en la BD.",
		 * idTienda)));
		 */
	}

	@Transactional(readOnly = false)
	@Override
	public void actualizarStock(Stock stock) throws ResourceNotFoundException {

		Iterable<Stock> listaStocksBD = stockRepository.findByIdProductoOrderByCantidadDesc(stock.getIdProducto());

		Long cantidadEnviada = stock.getCantidad();

		for (Stock stockEntidad : listaStocksBD) {
			if (cantidadEnviada == 0L) {
				break;
			}
			if (cantidadEnviada >= stockEntidad.getCantidad()) {
				cantidadEnviada -= stockEntidad.getCantidad();
				stockEntidad.setCantidad(0L);
			} else {
				stockEntidad.setCantidad(stockEntidad.getCantidad() - cantidadEnviada);
				cantidadEnviada = 0L;
			}
		}

		stockRepository.saveAll(listaStocksBD);
	}
}

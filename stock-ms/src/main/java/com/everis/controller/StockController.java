package com.everis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.everis.dto.CantidadStockDTO;
import com.everis.exception.ResourceNotFoundException;
import com.everis.service.StockService;

@RestController
@RefreshScope
public class StockController {

	@Autowired
	private StockService stockService;

	@GetMapping("/stock/acumulado/producto/{idProducto}")
	public CantidadStockDTO obtenerCantidadProductosEnTodaLaTienda(@PathVariable Long idProducto)
			throws ResourceNotFoundException {
		Long stockEntidades = stockService.obtenerCantidadProductosEnTodaLaTienda(idProducto);
		CantidadStockDTO stockProductoTiendaDTO = new CantidadStockDTO();
		stockProductoTiendaDTO.setCantidad(stockEntidades);
		return stockProductoTiendaDTO;
	}

	@GetMapping("/stock/producto/{idProducto}/tienda/{idTienda}")
	public CantidadStockDTO obtenerCantidadProductosEnTiendaPorSuId(@PathVariable Long idProducto,
			@PathVariable Long idTienda) throws ResourceNotFoundException {
		Long stockEntidades = stockService.obtenerCantidadProductosEnTiendaPorSuId(idProducto, idTienda);
		CantidadStockDTO stockDTO = new CantidadStockDTO();
		stockDTO.setCantidad(stockEntidades);
		return stockDTO;
	}

}

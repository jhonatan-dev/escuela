package com.everis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.everis.dto.StockDTO;
import com.everis.dto.StockProductoTiendaDTO;
import com.everis.exception.ResourceNotFoundException;
import com.everis.service.StockService;

@RestController
@RefreshScope
public class StockController {

	@Autowired
	private StockService stockService;
	
	
	
	@GetMapping("/stock/acumulado/producto/{idProducto}")
	public StockProductoTiendaDTO obtenerCantidadProductosEnTodaLaTienda(@PathVariable Long idProducto) throws ResourceNotFoundException {
		Long stockEntidades = stockService.obtenerCantidadProductosEnTodaLaTienda(idProducto);
		StockProductoTiendaDTO stockProductoTiendaDTO = new StockProductoTiendaDTO();
		stockProductoTiendaDTO.setIdProducto(idProducto);
		stockProductoTiendaDTO.setCantidad(stockEntidades);
		return stockProductoTiendaDTO;
	}
	
	@GetMapping("/stock/producto/{idProducto}/tienda/{idTienda}")
	public StockDTO obtenerCantidadProductosEnTiendaPorSuId(@PathVariable Long idProducto, @PathVariable Long idTienda) throws ResourceNotFoundException {
		Long stockEntidades = stockService.obtenerCantidadProductosEnTiendaPorSuId(idProducto, idTienda);
		StockDTO stockDTO = new StockDTO();
		stockDTO.setIdProducto(idProducto);
		stockDTO.setCantidad(stockEntidades);
		stockDTO.setIdTienda(idTienda);
		return stockDTO;
	}
	
}

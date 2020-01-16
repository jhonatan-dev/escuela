package com.everis.repository;

import org.springframework.stereotype.Repository;

import com.everis.entidad.Stock;
import com.everis.util.CustomRepository;

@Repository
public interface StockRepository extends CustomRepository<Stock, Long> {
	
	public boolean existsByIdProducto(Long idProducto);
	
	public boolean existsByIdTienda(Long idTienda);
	
	public long countByIdProducto(Long idProducto);

	public long countByIdProductoAndIdTienda(Long idProducto, Long idTienda);
}

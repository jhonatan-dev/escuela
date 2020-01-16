package com.everis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.everis.entidad.Stock;
import com.everis.util.CustomRepository;

@Repository
public interface StockRepository extends CustomRepository<Stock, Long> {

	public boolean existsByIdProducto(Long idProducto);

	public boolean existsByIdTienda(Long idTienda);

	@Query("select sum(s.cantidad) from Stock s where s.idProducto = ?1")
	public Optional<Long> obtenerCantidadProductosEnTodaLaTienda(Long idProducto);

	@Query("select sum(s.cantidad) from Stock s where s.idProducto = ?1 and s.idTienda = ?2")
	public Optional<Long> obtenerCantidadProductosEnTiendaPorSuId(Long idProducto, Long idTienda);

}

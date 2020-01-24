package com.everis.repository;

import org.springframework.stereotype.Repository;

import com.everis.entidad.DetalleOrden;
import com.everis.util.CustomRepository;

@Repository
public interface DetalleOrdenRepository extends CustomRepository<DetalleOrden, Long> {
	public Iterable<DetalleOrden> findByidProductoOrderByIdAsc(Long idProducto);
}
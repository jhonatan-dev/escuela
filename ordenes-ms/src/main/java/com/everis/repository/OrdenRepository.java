package com.everis.repository;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.everis.entidad.Orden;
import com.everis.util.CustomRepository;

@Repository
public interface OrdenRepository extends CustomRepository<Orden, Long> {
	
	public Iterable<Orden> findByFechaEnvioGreaterThanEqualOrderByIdAsc(Date fechaEnvio);
	
	//@Query(value="select o from Orden o where o.detalle.idProducto = ?1")
	public Iterable<Orden> findByDetalle_IdProducto(Long idProducto);
}
package com.everis.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.everis.entidad.TipoProducto;
import com.everis.util.CustomRepository;

@Repository
public interface TipoProductoRepository extends CustomRepository<TipoProducto, Long> {

	public Optional<TipoProducto> findByCodigo(String codigo);
}

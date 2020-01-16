package com.everis.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.everis.entidad.Producto;
import com.everis.util.CustomRepository;

@Repository
public interface ProductoRepository extends CustomRepository<Producto, Long> {
	
	public Optional<Producto> findByCodigo(String codigo);
}
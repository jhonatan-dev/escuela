package com.everis.repository;

import org.springframework.stereotype.Repository;

import com.everis.entidad.Producto;
import com.everis.util.CustomRepository;

@Repository
public interface ProductoRepository extends CustomRepository<Producto, Long> {
	
}
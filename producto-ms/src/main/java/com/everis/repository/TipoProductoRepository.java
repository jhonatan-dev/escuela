package com.everis.repository;

import org.springframework.stereotype.Repository;

import com.everis.entidad.TipoProducto;
import com.everis.util.CustomRepository;

@Repository
public interface TipoProductoRepository extends CustomRepository<TipoProducto, Long> {

}

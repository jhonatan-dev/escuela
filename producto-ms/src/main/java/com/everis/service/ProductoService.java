package com.everis.service;

import com.everis.entidad.Producto;
import com.everis.exception.ResourceNotFoundException;
import com.everis.exception.ValidacionException;

public interface ProductoService {

	public Iterable<Producto> obtenerProductos();

	public Producto guardarProducto(Producto producto) throws ValidacionException;

	public Producto obtenerProductoPorId(Long id) throws ResourceNotFoundException;

}

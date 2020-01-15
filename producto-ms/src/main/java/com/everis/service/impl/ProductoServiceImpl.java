package com.everis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.everis.entidad.Producto;
import com.everis.exception.ResourceNotFoundException;
import com.everis.exception.ValidacionException;
import com.everis.repository.ProductoRepository;
import com.everis.service.ProductoService;

@Transactional(readOnly = true)
@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public Iterable<Producto> obtenerProductos() {
		return productoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public Producto guardarProducto(Producto producto) throws ValidacionException {
		Producto nuevo = productoRepository.save(producto);
		productoRepository.refresh(nuevo);
		return nuevo;
	}

	@Override
	public Producto obtenerProductoPorId(Long id) throws ResourceNotFoundException {
		return productoRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("No se encontró el id %s en la BD.", id)));
	}

}

package com.everis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.everis.entidad.TipoProducto;
import com.everis.repository.TipoProductoRepository;
import com.everis.service.TipoProductoService;

@Transactional(readOnly = true)
@Service
public class TipoProductoServiceImpl implements TipoProductoService {

	@Autowired
	private TipoProductoRepository tipoProductoRepository;
	
	@Override
	public Iterable<TipoProducto> obtenerTipoProductos() {
		return tipoProductoRepository.findAll();
	}

}

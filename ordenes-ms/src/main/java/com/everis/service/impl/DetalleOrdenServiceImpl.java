package com.everis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.everis.repository.DetalleOrdenRepository;
import com.everis.service.DetalleOrdenService;

@Transactional(readOnly = true)
@Service
public class DetalleOrdenServiceImpl implements DetalleOrdenService {

	@Autowired
	private DetalleOrdenRepository detalleOrdenRepository;

	@Override
	public boolean eliminarDetalleDeOrden(Long idOrden) {
		detalleOrdenRepository.deleteByOrden_id(idOrden);
		return true;
	}
	
}

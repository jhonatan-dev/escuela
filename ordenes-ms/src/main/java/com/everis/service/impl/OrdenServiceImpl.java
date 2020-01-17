package com.everis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.everis.entidad.Orden;
import com.everis.exception.ResourceNotFoundException;
import com.everis.exception.ValidacionException;
import com.everis.repository.OrdenRepository;
import com.everis.service.OrdenService;

@Transactional(readOnly = true)
@Service
public class OrdenServiceImpl implements OrdenService {

	@Autowired
	private OrdenRepository ordenRepository;

	@Override
	@Transactional(readOnly = false)
	public Orden guardarOrden(Orden orden) throws ValidacionException, ResourceNotFoundException {
		return ordenRepository.save(orden);
	}
}

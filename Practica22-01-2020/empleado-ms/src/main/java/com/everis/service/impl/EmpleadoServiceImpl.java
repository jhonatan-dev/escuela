package com.everis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.everis.entidad.Empleado;
import com.everis.exception.ResourceNotFoundException;
import com.everis.repository.EmpleadoRepository;
import com.everis.service.EmpleadoService;

@Transactional(readOnly = true)
@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Override
	public Empleado obtenerEmpleadoPorId(Long id) throws ResourceNotFoundException {
		return empleadoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
				String.format("No se encontró un Empleado con código %s en la BD.", id)));
	}

}

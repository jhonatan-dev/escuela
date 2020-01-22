package com.everis.service;

import com.everis.entidad.Empleado;
import com.everis.exception.ResourceNotFoundException;

public interface EmpleadoService {
	public Empleado obtenerEmpleadoPorId(Long id) throws ResourceNotFoundException;
}

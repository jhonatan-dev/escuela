package com.everis.service;

import com.everis.entidad.Empresa;
import com.everis.exception.ResourceNotFoundException;

public interface EmpresaService {

	public Empresa obtenerEmpresaPorId(Long id) throws ResourceNotFoundException;

}

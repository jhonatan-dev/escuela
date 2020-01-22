package com.everis.service;

import com.everis.dto.EmpresaDTO;
import com.everis.exception.ResourceNotFoundException;

public interface FeignService {

	public EmpresaDTO obtenerEmpresaPorId(Long idEmpresa) throws ResourceNotFoundException;
}
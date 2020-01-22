package com.everis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.everis.entidad.Empresa;
import com.everis.exception.ResourceNotFoundException;
import com.everis.repository.EmpresaRepository;
import com.everis.service.EmpresaService;

@Transactional(readOnly = true)
@Service
public class EmpresaServiceImpl implements EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public Empresa obtenerEmpresaPorId(Long id) throws ResourceNotFoundException {
		return empresaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
				String.format("No se encontró una Empresa con código %s en la BD.", id)));
	}

}

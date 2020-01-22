package com.everis.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.everis.dto.EmpresaDTO;
import com.everis.exception.ResourceNotFoundException;
import com.everis.service.EmpresaService;

@RestController
@RefreshScope
public class EmpresaController {
	
	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping("/empresa/{idEmpresa}")
	public EmpresaDTO obtenerEmpresaPorId(@PathVariable Long idEmpresa) throws ResourceNotFoundException {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(empresaService.obtenerEmpresaPorId(idEmpresa), EmpresaDTO.class);
	}
	
}

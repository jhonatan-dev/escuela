package com.everis.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.everis.dto.EmpresaDTO;
import com.everis.exception.ResourceNotFoundException;

@FeignClient("empresa-ms") // nombre de la aplicación del microservicio
public interface EmpresaClient {

	@GetMapping("/empresa/{idEmpresa}")
	public EmpresaDTO obtenerEmpresaPorId(@PathVariable Long idEmpresa) throws ResourceNotFoundException;
	
}

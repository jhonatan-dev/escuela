package com.everis.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.everis.dto.ProductoDTO;
import com.everis.exception.ResourceNotFoundException;

@FeignClient("producto-ms") // nombre de la aplicación del microservicio
public interface ProductoClient {

	@GetMapping("/productos/{id}")
	public ProductoDTO obtenerProductoPorId(@PathVariable Long id) throws ResourceNotFoundException;
}

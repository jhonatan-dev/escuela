package com.everis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.dto.EmpresaDTO;
import com.everis.exception.ResourceNotFoundException;
import com.everis.feign.EmpresaClient;
import com.everis.service.FeignService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class FeignServiceImpl implements FeignService {

	@Autowired
	private EmpresaClient empresaClient;

	@HystrixCommand(fallbackMethod = "obtenerEmpresaPorDefecto", groupKey = "obtenerEmpresaPorDefecto", commandKey = "obtenerEmpresaPorDefecto", threadPoolKey = "obtenerEmpresaPorDefecto", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5") }, threadPoolProperties = {
					@HystrixProperty(name = "queueSizeRejectionThreshold", value = "5") })
	@Override
	public EmpresaDTO obtenerEmpresaPorId(Long idEmpresa) throws ResourceNotFoundException {
		return empresaClient.obtenerEmpresaPorId(idEmpresa);
	}

	public EmpresaDTO obtenerEmpresaPorDefecto(Long idEmpresa) throws ResourceNotFoundException {
		EmpresaDTO empresaDTO = new EmpresaDTO();
		empresaDTO.setId(0L);
		empresaDTO.setNombre("");
		empresaDTO.setRuc("");
		empresaDTO.setDireccion("");
		return empresaDTO;
	}

}

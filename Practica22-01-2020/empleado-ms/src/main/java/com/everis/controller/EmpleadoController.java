package com.everis.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.everis.dto.EmpleadoDTO;
import com.everis.entidad.Empleado;
import com.everis.exception.ResourceNotFoundException;
import com.everis.service.EmpleadoService;
import com.everis.service.FeignService;

@RestController
@RefreshScope
public class EmpleadoController {

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private FeignService feignService;

	@GetMapping("/empleado/{idEmpleado}")
	public EmpleadoDTO obtenerEmpleadoPorId(@PathVariable Long idEmpleado) throws ResourceNotFoundException {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Empleado empleadoEntidad = empleadoService.obtenerEmpleadoPorId(idEmpleado);
		EmpleadoDTO empleadoDTO = modelMapper.map(empleadoEntidad, EmpleadoDTO.class);
		empleadoDTO.setEmpresa(feignService.obtenerEmpresaPorId(empleadoEntidad.getIdEmpresa()).getNombre());
		return empleadoDTO;
	}

}

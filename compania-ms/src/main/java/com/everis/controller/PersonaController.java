package com.everis.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.everis.dto.PersonaDTO;
import com.everis.dto.PersonaReducidaDTO;
import com.everis.entidad.Persona;
import com.everis.exception.ResourceNotFoundException;
import com.everis.exception.ValidacionException;
import com.everis.service.PersonaService;

@RestController
@RefreshScope
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@Value("${igv}")
	private String igv;
	
	// @RequestMapping(value="/personas",method=RequestMethod.GET)
	@GetMapping("/personas")
	public Iterable<PersonaDTO> obtenerPersonas() {
		Iterable<Persona> listado = personaService.obtenerPersonas();
		List<PersonaDTO> response = new ArrayList<PersonaDTO>();
		ModelMapper modelMapper = new ModelMapper();
		listado.forEach(persona -> {
			// Transformar Entidad a DTO
			PersonaDTO personaDTO = modelMapper.map(persona, PersonaDTO.class);
			// Agregar a la lista
			response.add(personaDTO);
		});
		return response;
	}

	// personas - POST
	@PostMapping("/personas")
	@ResponseStatus(HttpStatus.CREATED)
	public PersonaDTO guardarPersona(@Valid @RequestBody PersonaReducidaDTO persona) throws ValidacionException {
		ModelMapper modelMapper = new ModelMapper();
		Persona personaEntidad = personaService.guardarPersona(modelMapper.map(persona, Persona.class));
		PersonaDTO personaDTO = modelMapper.map(personaEntidad, PersonaDTO.class);
		return personaDTO;
	}

	// personas/{id} - GET
	@GetMapping("/personas/{id}")
	public PersonaReducidaDTO obtenerPersonaPorId(@PathVariable Long id) throws ResourceNotFoundException {
		Persona personaEntidad = personaService.obtenerPersonaPorId(id);
		ModelMapper modelMapper = new ModelMapper();
		PersonaReducidaDTO personaReducidaDTO = modelMapper.map(personaEntidad, PersonaReducidaDTO.class);
		return personaReducidaDTO;
	}
	
	@GetMapping("/igv")
	public String obtenerIGV() {
		return "El igv es:" + igv;
	}
	
	/*
	 * @Value("${palabra}") String palabra;
	 * 
	 * @RequestMapping(value="/palabra",method=RequestMethod.GET) public String
	 * obtenerPalabra() { return "La palabra obtenida es: " + palabra; }
	 */
}
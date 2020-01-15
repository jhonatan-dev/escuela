package com.everis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.everis.entidad.Persona;
import com.everis.exception.ValidacionException;
import com.everis.exception.ResourceNotFoundException;
import com.everis.repository.PersonaRepository;
import com.everis.service.PersonaService;

@Service
@Transactional(readOnly = true)
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public Iterable<Persona> obtenerPersonas() {
		return personaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public Persona guardarPersona(Persona persona) throws ValidacionException {

		if (personaRepository.findByDni(persona.getDni()).isPresent()) {
			throw new ValidacionException("Existe una persona con el mismo dni.");
		}

		Persona nuevo = personaRepository.save(persona);
		personaRepository.refresh(nuevo);
		return nuevo;
	}

	@Override
	public Persona obtenerPersonaPorId(Long id) throws ResourceNotFoundException {
		return personaRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("No se encontró el id %s en la BD.", id)));
	}

}

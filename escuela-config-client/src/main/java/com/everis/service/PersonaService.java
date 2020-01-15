package com.everis.service;

import com.everis.entidad.Persona;
import com.everis.exception.ValidacionException;
import com.everis.exception.ResourceNotFoundException;

public interface PersonaService {

	public Iterable<Persona> obtenerPersonas();

	public Persona guardarPersona(Persona persona) throws ValidacionException;

	public Persona obtenerPersonaPorId(Long id) throws ResourceNotFoundException;
}

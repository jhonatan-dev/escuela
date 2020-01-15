package com.everis.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.everis.entidad.Persona;
import com.everis.util.CustomRepository;

@Repository
public interface PersonaRepository extends CustomRepository<Persona, Long> {
	
	public Optional<Persona> findByDni(String dni);
	
}

package com.everis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.everis.entidad.Compania;
import com.everis.entidad.Persona;
import com.everis.exception.ResourceNotFoundException;
import com.everis.repository.CompaniaRepository;
import com.everis.repository.PersonaRepository;
import com.everis.service.CompaniaService;

@Transactional(readOnly = true)
@Service
public class CompaniaServiceImpl implements CompaniaService {

	@Autowired
	private CompaniaRepository companiaRepository;

	@Autowired
	private PersonaRepository personaRepository;
	
	@Override
	public Iterable<Compania> obtenerCompanias() {
		return companiaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public Compania guardarCompania(Compania compania) {
		return companiaRepository.save(compania);
	}

	@Override
	public Compania obtenerCompaniaPorId(Long id) throws ResourceNotFoundException {
		return companiaRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("No se encontró el id %s en la BD.", id)));
	}

	@Override
	@Transactional(readOnly = false)
	public boolean actualizarCompania(Compania compania) throws ResourceNotFoundException, Exception {
		companiaRepository.findById(compania.getId()).orElseThrow(
				() -> new ResourceNotFoundException(String.format("No se encontró una compañía con el id %s en la BD.", compania.getId())));
		try {
			companiaRepository.update(compania);
			return true;
		} catch (Exception e) {
			throw new Exception("No se pudo actualizar la compañía");
		}
	}

	@Override
	@Transactional(readOnly = false)
	public Compania asociarPersonaCompania(Long idCompania, Long idPersona) throws ResourceNotFoundException {
		Persona persona = personaRepository.findById(idPersona).orElseThrow(() -> new ResourceNotFoundException(String.format("No se encontró la persona con el id:%s indicado.", idPersona)));
		Compania compania = companiaRepository.findById(idCompania).orElseThrow(() -> new ResourceNotFoundException(String.format("No se encontró la compañía con el id:%s indicado.", idCompania)));
		persona.setCompania(compania);
		personaRepository.save(persona);
		return companiaRepository.findById(idCompania).get();
	}
	
}
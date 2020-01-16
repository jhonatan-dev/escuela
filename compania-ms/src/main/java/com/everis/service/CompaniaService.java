package com.everis.service;

import com.everis.entidad.Compania;
import com.everis.exception.ResourceNotFoundException;

public interface CompaniaService {

	public Iterable<Compania> obtenerCompanias();
	
	public Compania guardarCompania(Compania compania);
	
	public Compania obtenerCompaniaPorId(Long id) throws ResourceNotFoundException;
	
	public Compania asociarPersonaCompania(Long idCompania, Long idPersona) throws ResourceNotFoundException;
	
	public boolean actualizarCompania(Compania compania) throws ResourceNotFoundException, Exception;
	
}

package com.everis.service;
import com.everis.entidad.Orden;
import com.everis.exception.ResourceNotFoundException;
import com.everis.exception.ValidacionException;

public interface OrdenService {

	public Orden guardarOrden(Orden orden) throws ValidacionException, ResourceNotFoundException;
	
}

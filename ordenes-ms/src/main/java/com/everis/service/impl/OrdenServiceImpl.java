package com.everis.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.everis.entidad.Orden;
import com.everis.exception.ResourceNotFoundException;
import com.everis.exception.ValidacionException;
import com.everis.repository.OrdenRepository;
import com.everis.service.OrdenService;

@Transactional(readOnly = true)
@Service
public class OrdenServiceImpl implements OrdenService {

	@Autowired
	private OrdenRepository ordenRepository;

	@Override
	@Transactional(readOnly = false)
	public Orden guardarOrden(Orden orden) throws ValidacionException, ResourceNotFoundException {
		return ordenRepository.save(orden);
	}

	@Override
	public Iterable<Orden> obtenerTodasLasOrdenes() throws ResourceNotFoundException {
		return ordenRepository.findAll();
	}

	@Override
	public Iterable<Orden> obtenerOrdenesPorFechaEnvioMayoroIgual(Date fechaEnvio) throws ResourceNotFoundException {
		return ordenRepository.findByFechaEnvioGreaterThanEqualOrderByIdAsc(fechaEnvio);
	}

	@Override
	public Iterable<Orden> obtenerDetalleOrdenDeProducto(Long idProducto) {
		return ordenRepository.findByDetalle_IdProducto(idProducto);
	}
	
}

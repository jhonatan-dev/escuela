package com.everis.service;

import java.util.Date;

import com.everis.entidad.Orden;
import com.everis.exception.ResourceNotFoundException;
import com.everis.exception.ValidacionException;

public interface OrdenService {

	public Orden guardarOrden(Orden orden) throws ValidacionException, ResourceNotFoundException;

	public Orden obtenerOrdenPorId(Long idOrden) throws ResourceNotFoundException;

	public Iterable<Orden> obtenerTodasLasOrdenes() throws ResourceNotFoundException;

	public Iterable<Orden> obtenerOrdenesPorFechaEnvioMayoroIgual(Date fechaEnvio) throws ResourceNotFoundException;

	public Iterable<Orden> obtenerDetalleOrdenDeProducto(Long idProducto);

	public boolean eliminarOrden(Long idOrden) throws ResourceNotFoundException;

	public Orden actualizarOrden(Orden orden) throws ValidacionException, ResourceNotFoundException;

}

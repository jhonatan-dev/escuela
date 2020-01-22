package com.everis.repository;

import org.springframework.stereotype.Repository;

import com.everis.entidad.Empleado;
import com.everis.util.CustomRepository;

@Repository
public interface EmpleadoRepository  extends CustomRepository<Empleado, Long>  {
}

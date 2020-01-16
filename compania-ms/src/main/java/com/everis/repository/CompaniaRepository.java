package com.everis.repository;

import org.springframework.stereotype.Repository;

import com.everis.entidad.Compania;
import com.everis.util.CustomRepository;

@Repository
public interface CompaniaRepository extends CustomRepository<Compania, Long> {
	
}

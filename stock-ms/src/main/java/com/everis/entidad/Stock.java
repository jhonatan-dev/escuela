package com.everis.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * JavaBean
 * Clase Pública con atributos privados con sus respectivos Getters y Setters y su constructor.
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long idProducto;

	private Long idTienda;
	
	private Long cantidad;
}

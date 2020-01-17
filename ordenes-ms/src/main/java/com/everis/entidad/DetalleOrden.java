package com.everis.entidad;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class DetalleOrden {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long idProducto;

	@Column
	private Long cantidad;

	//@ManyToOne(fetch = FetchType.LAZY)
	//private Orden orden;

	@Column
	private BigDecimal precio;
}
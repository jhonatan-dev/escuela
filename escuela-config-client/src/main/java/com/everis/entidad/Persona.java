package com.everis.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Persona {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nombre;

	@Column
	private String apellido1;

	@Column
	private String apellido2;

	@Column(unique = true)
	private String dni;
	
	@ManyToOne
	private Compania compania;
}

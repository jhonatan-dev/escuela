package com.everis.entidad;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nombre;

	@Column(unique = true )
	private String codigo;

	@Column
	private String descripcion;

	@Column
	private BigDecimal precio;

	@ManyToOne
	private TipoProducto tipoProducto;

	@OneToOne(cascade = CascadeType.ALL)
	private ImagenProducto imagenProducto;

	@Column
	private Boolean activo;
}

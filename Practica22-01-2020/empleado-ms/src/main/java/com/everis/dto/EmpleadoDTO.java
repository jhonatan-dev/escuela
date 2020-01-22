package com.everis.dto;

import java.util.Date;

import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDTO {

	private Long id;

	private String nombre;

	@JsonProperty(value = "apellido_paterno")
	private String apellidoPaterno;

	@JsonProperty(value = "apellido_materno")
	private String apellidoMaterno;

	private String dni;

	@PastOrPresent
	@JsonProperty(value = "fecha_ingreso")
	private Date fechaIngreso;

	private boolean activo;

	private String empresa;

}

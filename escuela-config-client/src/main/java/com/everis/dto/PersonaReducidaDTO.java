package com.everis.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaReducidaDTO {

	@NotNull
	@NotBlank
	@Size(max = 20)
	private String nombre;

	@NotNull
	@NotBlank
	@Size(max = 50)
	@JsonProperty(value = "apellido_paterno")
	private String apellido1;

	@Size(max = 50)
	@JsonProperty(value = "apellido_materno")
	private String apellido2;

	@NotNull
	@NotBlank
	@Size(min = 8, max = 8)
	private String dni;
}

package com.everis.dto;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrdenFechaEnvioDTO {

	@NotNull
	@JsonProperty(value = "fecha_envio")
	@FutureOrPresent(message = "Fecha de envío no puede ser una fecha pasada.")
	private Date fechaEnvio;

}

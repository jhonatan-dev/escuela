package com.everis.dto;

import java.util.Date;
import java.util.List;

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
public class OrdenReducidaDTO {

	@NotNull
	@JsonProperty(value = "id_cliente")
	private Long idCliente;

	@NotNull
	@JsonProperty(value = "fecha_envio")
	@FutureOrPresent(message = "Fecha de envío no puede ser una fecha pasada.")
	private Date fechaEnvio;

	@NotNull
	private List<DetalleOrdenReducidaDTO> detalle;
}

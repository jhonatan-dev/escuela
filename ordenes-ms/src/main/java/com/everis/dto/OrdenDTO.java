package com.everis.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.FutureOrPresent;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrdenDTO {

	private Long id;

	@JsonProperty(value = "id_cliente")
	private Long idCliente;

	private Date fecha;

	@JsonProperty(value = "fecha_envio")
	@FutureOrPresent
	private Date fechaEnvio;

	private BigDecimal total;

	private List<DetalleOrdenDTO> detalle;

}

package com.everis.dto;

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
public class DetalleOrdenReducidaDTO {

	@NotNull
	@JsonProperty(value = "id_producto")
	private Long idProducto;

	@NotNull
	private Long cantidad;

}

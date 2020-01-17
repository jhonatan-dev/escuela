package com.everis.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleOrdenDTO {
	
	private Long id;
	
	@JsonProperty(value = "id_producto")
	private Long idProducto;
	
	private Long cantidad;
	
	private BigDecimal precio;
}

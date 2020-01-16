package com.everis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
	
	@JsonProperty(value = "producto_id")
	private Long idProducto;
	
	@JsonProperty(value = "tienda_id")
	private Long idTienda;
	
	private Long cantidad;

}

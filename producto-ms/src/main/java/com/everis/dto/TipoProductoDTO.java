package com.everis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TipoProductoDTO {
	private Long id;
	private String nombre;
	private String codigo;

}

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
public class CompaniaReducidaDTO {
	private String nombre;
	private String ruc;
	
	@JsonProperty(value = "razon_social")
	private String razonSocial;
}

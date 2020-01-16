package com.everis.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CompaniaDTO {
	private Long id;
	private String nombre;
	private String ruc;
	
	@JsonProperty(value = "razon_social")
	private String razonSocial;
	
	private List<PersonaReducidaDTO> personas;
}

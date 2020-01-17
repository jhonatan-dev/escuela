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
public class ProductoDTO {

	private Long id;

	private String nombre;

	private String codigo;

	private String descripcion;

	private BigDecimal precio;

	@JsonProperty(value = "tipo_producto")
	private TipoProductoDTO tipoProducto;

	@JsonProperty(value = "imagen_producto")
	private ImagenProductoDTO imagenProducto;

	private Boolean activo;
	
	@JsonProperty(value = "cantidad_stock")
	private Long cantidadStock;

}

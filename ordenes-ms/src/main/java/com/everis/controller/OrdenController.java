package com.everis.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.everis.dto.OrdenDTO;
import com.everis.dto.OrdenReducidaDTO;
import com.everis.dto.ProductoDTO;
import com.everis.entidad.Orden;
import com.everis.exception.ResourceNotFoundException;
import com.everis.exception.ValidacionException;
import com.everis.service.FeignService;
import com.everis.service.OrdenService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RefreshScope
public class OrdenController {

	@Autowired
	private OrdenService ordenService;

	@Autowired
	private FeignService feignService;

	@ApiOperation(value = "Guardar una orden de venta", notes = "Al guardar una orden se verificará el stock en los almacenes de cada producto.", response = OrdenDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Se registró correctamente la orden.", response = OrdenDTO.class),
			@ApiResponse(code = 404, message = "Recurso no encontrado.", response = ResourceNotFoundException.class),
			@ApiResponse(code = 200, message = "Validación de negocio.", response = ValidacionException.class) })
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/ordenes")
	public OrdenDTO guardarOrden(@Valid @RequestBody OrdenReducidaDTO ordenReducidaDTO)
			throws ValidacionException, ResourceNotFoundException, Exception {

		ModelMapper modelMapper = new ModelMapper();

		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		for (int i = 0; i < ordenReducidaDTO.getDetalle().size(); i++) {
			if (ordenReducidaDTO.getDetalle().get(i).getCantidad() > feignService
					.obtenerCantidadProductosEnTodaLaTienda(ordenReducidaDTO.getDetalle().get(i).getIdProducto())
					.getCantidad()) {
				throw new ValidacionException("Cantidad del Producto con id "
						+ ordenReducidaDTO.getDetalle().get(i).getIdProducto() + " supera a su stock disponible.");
			}
		}

		Orden ordenEntidad = modelMapper.map(ordenReducidaDTO, Orden.class);

		ordenEntidad.setFecha(new Date());
		ordenEntidad.setTotal(new BigDecimal(0));

		for (int i = 0; i < ordenEntidad.getDetalle().size(); i++) {
			ProductoDTO productoDTO = feignService
					.obtenerProductoPorId(ordenEntidad.getDetalle().get(i).getIdProducto());
			ordenEntidad.getDetalle().get(i).setPrecio(productoDTO.getPrecio());
			BigDecimal totalDetalle = new BigDecimal(0);
			totalDetalle = ordenEntidad.getDetalle().get(i).getPrecio()
					.multiply(new BigDecimal(ordenEntidad.getDetalle().get(i).getCantidad()));
			ordenEntidad.setTotal(ordenEntidad.getTotal().add(totalDetalle));

			// Ya no sería necesario porque se usa el CustomInterceptor del paquete Util
			// ordenEntidad.getDetalle().get(i).setOrden(ordenEntidad);
		}

		Orden ordenRegistrada = ordenService.guardarOrden(ordenEntidad);

		feignService.actualizarStock(modelMapper.map(ordenRegistrada, OrdenReducidaDTO.class));

		return modelMapper.map(ordenRegistrada, OrdenDTO.class);
	}

	@GetMapping("/ordenes")
	public Iterable<OrdenDTO> obtenerTodasLasOrdenes() throws ResourceNotFoundException {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Iterable<Orden> listaOrdenEntidades = ordenService.obtenerTodasLasOrdenes();
		ArrayList<OrdenDTO> listaProductosDTO = new ArrayList<OrdenDTO>();
		listaOrdenEntidades.forEach((ordenEntidad) -> {
			OrdenDTO ordenDTO = modelMapper.map(ordenEntidad, OrdenDTO.class);
			listaProductosDTO.add(ordenDTO);
		});
		return listaProductosDTO;
	}

	@GetMapping("/orden/listado/{fechaEnvio}") /* Listar ordenes con fechas de envío sea igual o superior a esa */
	public Iterable<OrdenDTO> listado(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaEnvio)
			throws ResourceNotFoundException {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Iterable<Orden> listaOrdenEntidades = ordenService.obtenerOrdenesPorFechaEnvioMayoroIgual(fechaEnvio);
		ArrayList<OrdenDTO> listaProductosDTO = new ArrayList<OrdenDTO>();
		listaOrdenEntidades.forEach((ordenEntidad) -> {
			OrdenDTO ordenDTO = modelMapper.map(ordenEntidad, OrdenDTO.class);
			listaProductosDTO.add(ordenDTO);
		});
		return listaProductosDTO;
	}

	@GetMapping("/orden/detalle/{idProducto}")
	public Iterable<OrdenDTO> obtenerDetalleOrden(@PathVariable Long idProducto) {
		Iterable<Orden> listaOrdenEntidades = ordenService.obtenerDetalleOrdenDeProducto(idProducto);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		ArrayList<OrdenDTO> listaOrdenDTO = new ArrayList<OrdenDTO>();
		listaOrdenEntidades.forEach((ordenEntidad) -> {
			OrdenDTO ordenDTO = modelMapper.map(ordenEntidad, OrdenDTO.class);
			listaOrdenDTO.add(ordenDTO);
		});
		return listaOrdenDTO;
	}
	
}

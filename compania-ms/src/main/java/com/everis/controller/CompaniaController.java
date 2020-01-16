package com.everis.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.everis.dto.CompaniaDTO;
import com.everis.dto.CompaniaReducidaDTO;
import com.everis.entidad.Compania;
import com.everis.exception.ResourceNotFoundException;
import com.everis.service.CompaniaService;

@RestController
@RefreshScope
public class CompaniaController {

	@Autowired
	private CompaniaService companiaService;

	// @RequestMapping(value="/companias",method=RequestMethod.GET)
	@GetMapping("/companias")
	public List<CompaniaDTO> obtenerCompanias() {
		ModelMapper modelMapper = new ModelMapper();
		return StreamSupport.stream(companiaService.obtenerCompanias().spliterator(), false)
				.map(c -> modelMapper.map(c, CompaniaDTO.class)).collect(Collectors.toList());
		/*
		 * // Defino mi variable de retorno List<CompaniaDTO> response = new
		 * ArrayList<CompaniaDTO>(); // Obtengo las compañías del services
		 * Iterable<Compania> listado = companiaService.obtenerCompanias(); // Defino la
		 * instancia del modelMapper ModelMapper modelMapper = new ModelMapper(); //
		 * Recorro mi listado y agrego a mi variable de returno listado.forEach(compania
		 * -> { // Transformar Entidad a DTO CompaniaDTO companiaDTO =
		 * modelMapper.map(compania, CompaniaDTO.class); // Agregar a la lista
		 * response.add(companiaDTO); }); return response;
		 */
	}

	// companias - POST
	@PostMapping("/companias")
	@ResponseStatus(HttpStatus.CREATED)
	public CompaniaReducidaDTO guardarCompania(@RequestBody Compania compania) {
		Compania companiaEntidad = companiaService.guardarCompania(compania);
		ModelMapper modelMapper = new ModelMapper();
		CompaniaReducidaDTO companiaReducidaDTO = modelMapper.map(companiaEntidad, CompaniaReducidaDTO.class);
		return companiaReducidaDTO;
	}

	// companias/{id} - GET
	@GetMapping("/companias/{id}")
	public CompaniaDTO obtenerCompaniaPorId(@PathVariable Long id) throws ResourceNotFoundException {
		Compania companiaEntidad = companiaService.obtenerCompaniaPorId(id);
		CompaniaDTO companiaDTO = new ModelMapper().map(companiaEntidad, CompaniaDTO.class);
		return companiaDTO;
	}

	@PutMapping("/compania/{idCompania}/persona/{idPersona}")
	public CompaniaDTO asociarPersona(@PathVariable Long idCompania, @PathVariable Long idPersona) throws ResourceNotFoundException {
		Compania companiaEntidad = companiaService.asociarPersonaCompania(idCompania, idPersona);
		CompaniaDTO companiaDTO = new ModelMapper().map(companiaEntidad, CompaniaDTO.class);
		return companiaDTO;
	}

	@PutMapping("/compania/{id}")
	public boolean actualizarCompania(@PathVariable Long id, @RequestBody CompaniaReducidaDTO companiaDTO)
			throws ResourceNotFoundException, Exception {
		ModelMapper modelMapper = new ModelMapper();
		Compania companiaEntidad = modelMapper.map(companiaDTO, Compania.class);
		companiaEntidad.setId(id);
		return companiaService.actualizarCompania(companiaEntidad);
	}

	/*
	 * @Value("${palabra}") String palabra;
	 * 
	 * @RequestMapping(value="/palabra",method=RequestMethod.GET) public String
	 * obtenerPalabra() { return "La palabra obtenida es: " + palabra; }
	 */
	
}
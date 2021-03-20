package com.fravega.challenge.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fravega.challenge.model.Sucursal;
import com.fravega.challenge.service.SucursalService;
import com.fravega.challenge.utils.ResponseError;

@Validated
@RestController
@RequestMapping("/sucursales")
public class SucursalController {

	@Autowired
	private SucursalService sucursalService;
	
	@GetMapping
	public ResponseEntity<List<Sucursal>> getSucursales() {
		List<Sucursal> sucursales = this.sucursalService.findAll();
		return new ResponseEntity<>(sucursales,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getSucursal(@PathVariable Integer id) {
		
		Optional<Sucursal> sucursal = this.sucursalService.findById(id);
		
		if(!sucursal.isPresent()) {
			ResponseError error = new ResponseError(HttpStatus.NOT_FOUND,"Sucursal no encontrada");
			return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		}		 
		return new ResponseEntity<>(sucursal.get(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createSucursal(@Valid @RequestBody Sucursal sucursal) {
		
		try {
			Sucursal sucursalGuardada = this.sucursalService.save(sucursal);			
			return new ResponseEntity<>(sucursalGuardada,HttpStatus.CREATED);
		}
		catch(Exception e) {
			System.out.println("Error al crear sucursal: "+ e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@GetMapping("/miSucursal")
	public ResponseEntity<?> getSucursalMasCercana(
			@RequestParam Double latitud , 
			@RequestParam Double longitud) {
		
		try {
			Sucursal sucursal = this.sucursalService.findByPosicion(latitud, longitud);				
			return new ResponseEntity<>(sucursal,HttpStatus.FOUND);
		}
		catch(Exception e) {
			System.out.println("Error al obtener sucursal más cercana: "+ e.getMessage());
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
}

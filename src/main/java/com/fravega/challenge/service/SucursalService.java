package com.fravega.challenge.service;

import java.util.List;
import java.util.Optional;

import com.fravega.challenge.model.Sucursal;

public interface SucursalService {
	
	public List<Sucursal> findAll();
	
	public Optional<Sucursal> findById(Integer id);
	
	public Sucursal findByPosicion(Double latitud, Double longitud);
	
	public Sucursal save(Sucursal sucursal);

}

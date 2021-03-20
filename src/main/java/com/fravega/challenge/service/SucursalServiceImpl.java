package com.fravega.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fravega.challenge.dao.SucursalDao;
import com.fravega.challenge.model.Sucursal;

@Service
public class SucursalServiceImpl implements SucursalService {

	@Autowired
	private SucursalDao sucursalDao;
	
	@Override
	public List<Sucursal> findAll() {
		return this.sucursalDao.findAll();
	}
	
	@Override
	public Optional<Sucursal> findById(Integer id) {
		return this.sucursalDao.findById(id);
	}
	
	@Override
	public Sucursal findByPosicion(Double latitud, Double longitud) {
		List<Sucursal> sucursales = this.sucursalDao.findAll();
		Double menorDistancia = Double.MAX_VALUE;
		Sucursal sucursalMasCercana = null;
		
		for( Sucursal sucursal : sucursales) {
			Double distancia = calcularDistancia(latitud, longitud, sucursal.getLatitud(), sucursal.getLongitud());
			if( distancia < menorDistancia ) {
				menorDistancia = distancia;
				sucursalMasCercana = sucursal;
			}
		}
		
		return sucursalMasCercana;
	}
	
	@Override
	public Sucursal save(Sucursal sucursal) {		
		return this.sucursalDao.save(sucursal);		
	}
	
	public Double calcularDistancia(Double latitud1, Double longitud1, Double latitud2, Double longitud2) {
		
		return Math.sqrt(Math.pow(latitud2 - latitud1, 2) + Math.pow(longitud2 - longitud1, 2));
	
	}
}

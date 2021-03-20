package com.fravega.challenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class SucursalServiceTest {
	
	@Test
	public void CalcularDistanciaTest_1() {		
		SucursalServiceImpl sucursalService = new SucursalServiceImpl();
		Double distanciaCalculada = sucursalService.calcularDistancia(50.0, 50.0, -50.0, -50.0);
		
		assertEquals(distanciaCalculada.doubleValue(),(Math.sqrt(20000.0)));
	}
	
	@Test
	public void CalcularDistanciaTest_2() {		
		SucursalServiceImpl sucursalService = new SucursalServiceImpl();
		Double distanciaCalculada = sucursalService.calcularDistancia(0.0, 50.0, -50.0, -50.0);
		
		assertEquals(distanciaCalculada.doubleValue(),(Math.sqrt(12500.0)));
	}
	
	
}

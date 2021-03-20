package com.fravega.challenge.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fravega.challenge.model.Sucursal;
import com.fravega.challenge.service.SucursalServiceImpl;

@WebMvcTest(SucursalController.class)
public class SucursalControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private SucursalServiceImpl sucursalServiceImpl;
	
	@Test
	public void CreateSucursalTestOK() throws Exception {
		
			Sucursal sucursalNueva = new Sucursal("Direccion 1",Double.valueOf(50),Double.valueOf(50));
			Sucursal sucursalGuardada = new Sucursal("Direccion 1",Double.valueOf(50),Double.valueOf(50));
			sucursalGuardada.setId(1);
			
			Mockito.when(sucursalServiceImpl.save(sucursalNueva)).thenReturn(sucursalGuardada);
			
			MvcResult mvcResult = mockMvc.perform(
					post("/sucursales")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(sucursalNueva)))
					.andExpect(status().isCreated())
					.andReturn();
			
			assertEquals(mvcResult.getResponse().getContentAsString(),objectMapper.writeValueAsString(sucursalGuardada));	
	}
	
	@Test	
	public void CreateSucursalTestError() throws Exception {
		
			Sucursal sucursalNueva = new Sucursal("Direccion 1",Double.valueOf(50),Double.valueOf(50));		
			Sucursal sucursalGuardada = new Sucursal("Direccion 1",Double.valueOf(50),Double.valueOf(50));
			sucursalGuardada.setId(1);
			
			Sucursal sucursalErronea = new Sucursal("Direccion 1",null,null);
			
			Mockito.when(sucursalServiceImpl.save(sucursalNueva)).thenReturn(sucursalGuardada);
			
			MvcResult mvcResult = mockMvc.perform(
					post("/sucursales")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(sucursalErronea)))
					.andExpect(status().isCreated())
					.andReturn();
			
			assertEquals(mvcResult.getResponse().getContentAsString(),objectMapper.writeValueAsString(sucursalGuardada));	
	}
}

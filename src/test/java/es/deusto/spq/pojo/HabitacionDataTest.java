package es.deusto.spq.pojo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HabitacionDataTest {
	
	HabitacionData habitacionData;
	
	@Before
	public void setUp() {
		habitacionData = new HabitacionData();
		habitacionData.setTipoHabitacion(EnumTipoHabitacion.ESTANDAR);
		habitacionData.setPersonas(2);
		habitacionData.setPrecio(110.5f);
	}
	
	@Test
	public void testGetTipoHabitacion() {
		assertEquals(EnumTipoHabitacion.ESTANDAR, habitacionData.getTipoHabitacion());
	}
	
	@Test
	public void testGetPersonas() {
		assertEquals(2, habitacionData.getPersonas());
	}
	
	@Test
	public void testGetPrecio() {
		//assertEquals(110.5f, habitacionData.getPrecio());
	}
}

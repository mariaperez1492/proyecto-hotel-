package es.deusto.spq.pojo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HotelDataTest {
	
	HotelData hotelData;
	
	@Before
	public void setUp() {
		hotelData = new HotelData();
		hotelData.setNombre("nombre");
		hotelData.setCiudad("ciudad");
		hotelData.setHabitaciones_disp(5);
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("nombre", hotelData.getNombre());
	}
	
	@Test
	public void testGetCiudad() {
		assertEquals("ciudad", hotelData.getCiudad());
	}
	
	@Test
	public void testGetHabitaciones_disp() {
		assertEquals(5, hotelData.getHabitaciones_disp());
	}
}

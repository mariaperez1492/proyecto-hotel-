package es.deusto.spq.server.jdo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


public class HotelTest {

	Hotel hotel;
	
	@Before
	public void setUp() {
		hotel = new Hotel();
		hotel.setNombre("nombre");
		hotel.setCiudad("ciudad");
		hotel.setHabitaciones_disp(5);
		hotel.setID(0);
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("nombre", hotel.getNombre());
	}
	
	@Test
	public void testGetCiudad() {
		assertEquals("ciudad", hotel.getCiudad());
	}
	
	@Test
	public void testGetHabitaciones_disp() {
		assertEquals(5, hotel.getHabitaciones_disp());
	}
	
	@Test
	public void testGestID() {
		assertEquals(0, hotel.getID());
	}
	
	
	
	
	
}

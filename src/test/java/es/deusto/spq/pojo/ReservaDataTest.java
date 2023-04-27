package es.deusto.spq.pojo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class ReservaDataTest {
	
	ReservaData reservaData;
	
	@Mock
    UsuarioData usuarioData;
	
	@Mock
	HotelData hotelData;
	
	@Mock
	HabitacionData habitacionData;
	
	@Before
	public void setUp() {
		reservaData = new ReservaData();
		reservaData.setCliente(usuarioData);
		reservaData.setHotel(hotelData);
		reservaData.setHabitacion(habitacionData);
		reservaData.setFecha_ini(null);
		reservaData.setFecha_fin(null);
	}
	
	@Test
	public void testGetCliente() {
		assertEquals(usuarioData, reservaData.getCliente());
	}
	
	@Test
	public void testGetHotel() {
		assertEquals(hotelData, reservaData.getHotel());
	}
	
	@Test
	public void testGetHabitacion() {
		assertEquals(habitacionData, reservaData.getHabitacion());
	}
	
	@Test
	public void testGetFecha_ini() {
		
	}
	
	@Test
	public void testGetFecha_Fin() {
		
	}
}

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
		reservaData.setFecha_ini("2022-06-01");
		reservaData.setFecha_fin("2022-06-15");
		reservaData.setPension("pension");
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
		assertEquals("2022-06-01", reservaData.getFecha_ini());
	}
	
	@Test
	public void testGetFecha_Fin() {
		assertEquals("2022-06-15", reservaData.getFecha_fin());
	}
}

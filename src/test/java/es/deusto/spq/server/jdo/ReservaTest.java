package es.deusto.spq.server.jdo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import es.deusto.spq.pojo.HabitacionData;
import es.deusto.spq.pojo.HotelData;
import es.deusto.spq.pojo.UsuarioData;



public class ReservaTest {
	
	Reserva reserva;
	
	@Mock
    Usuario usuario;
	
	@Mock
	Hotel hotel;
	
	@Mock
	Habitacion habitacion;
	
	@Before
	public void setUp() {
		reserva = new Reserva();
		reserva.setCliente(usuario);
		reserva.setHotel(hotel);
		reserva.setHabitacion(habitacion);
		reserva.setFecha_ini("2022-06-01");
		reserva.setFecha_fin("2022-06-15");
		reserva.setPension("pension");
	}
	
	@Test
	public void testGetCliente() {
		assertEquals(usuario, reserva.getCliente());
	}
	
	@Test
	public void testGetHotel() {
		assertEquals(hotel, reserva.getHotel());
	}
	
	@Test
	public void testGetHabitacion() {
		assertEquals(habitacion, reserva.getHabitacion());
	}
	
	@Test
	public void testGetFecha_ini() {
		assertEquals("2022-06-01", reserva.getFecha_ini());
	}
	
	@Test
	public void testGetFecha_Fin() {
		assertEquals("2022-06-15", reserva.getFecha_fin());
	}
	
	@Test
	public void testGetPension() {
		assertEquals("pension", reserva.getPension());
	}
}


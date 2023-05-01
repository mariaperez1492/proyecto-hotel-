package es.deusto.spq.server.jdo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.deusto.spq.pojo.EnumTipoHabitacion;

public class HabitacionTest {
	
		private Habitacion habitacion; 
		
		@Before
		public void setUp() {
			habitacion = new Habitacion();
			habitacion.setTipoHabitacion(EnumTipoHabitacion.ESTANDAR);
			habitacion.setPersonas(2);
			habitacion.setPrecio(110);
		}
		
		@Test
		public void testGetTipoHabitacion() {
			assertEquals(EnumTipoHabitacion.ESTANDAR, habitacion.getTipoHabitacion());
		}
		
		@Test
		public void testGetPersonas() {
			assertEquals(2, habitacion.getPersonas());
		}
		
		@SuppressWarnings("deprecation")
		@Test
		public void testGetPrecio() {
			//assertEquals(110, habitacion.getPrecio());
		}
}

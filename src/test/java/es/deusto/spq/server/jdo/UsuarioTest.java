package es.deusto.spq.server.jdo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {
	
	private Usuario usuario;
	
	@Before
	public void setUp() {
		usuario = new Usuario();
		usuario.setDni("dni");
		usuario.setNombre("nombre");
		usuario.setContrasenya("contrasenya");
	}
	
	@Test
	public void testGetDni() {
		assertEquals("dni", usuario.getDni());
	}
	
	@Test 
	public void testSetDni() {
		usuario.setDni("new-dni");
		assertEquals("new-dni", usuario.getDni());
	}
	
	@Test 
	public void testGetNombre() {
		assertEquals("nombre", usuario.getNombre());
	}
	
	@Test
	public void testSetNombre() {
		usuario.setNombre("new-nombre");
		assertEquals("new-nombre", usuario.getNombre());
	}
	
	@Test
	public void testGetContrasenya() {
		assertEquals("contrasenya", usuario.getContrasenya());
	}
	
	@Test
	public void testSetContrasenya() {
		usuario.setContrasenya("new-contra");
		assertEquals("new-contra", usuario.getContrasenya());
	}
}

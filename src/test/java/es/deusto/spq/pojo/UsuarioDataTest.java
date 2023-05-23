package es.deusto.spq.pojo;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class UsuarioDataTest {

	UsuarioData usuarioData;
	
	@Before
	public void setUp() {
		usuarioData = new UsuarioData(); 
		usuarioData.setDni("dni");
		usuarioData.setNombre("nombre");
		usuarioData.setContrasenya("contrasenya");
		usuarioData.setTipoUsuario(EnumTipoUsuario.CLIENTE);
	}
	
	
	
	@Test
	public void testGetDni() {
		assertEquals("dni", usuarioData.getDni());
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("nombre", usuarioData.getNombre());
	}
	
	@Test
	public void testGetContrasenya() {
		assertEquals("contrasenya", usuarioData.getContrasenya());
	}
	
	@Test
	public void testGetTipoUsuario() {
		assertEquals(EnumTipoUsuario.CLIENTE, usuarioData.getTipoUsuario());
	}
}

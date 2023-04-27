package es.deusto.spq.server.jdo;

import org.junit.Before;

public class UsuarioTest {
	
	private Usuario usuario;
	
	@Before
	public void setUp() {
		usuario = new Usuario();
		usuario.setDni("dni");
		usuario.setNombre("nombre");
		usuario.setContrasenya("contrasenya");
	}

}

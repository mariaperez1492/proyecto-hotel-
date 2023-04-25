package es.deusto.spq;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import es.deusto.spq.pojo.UsuarioData;
import es.deusto.spq.server.Resource;

public class ResourceTest 
{
	@Mock
    Resource resource;
	
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		resource = new Resource();
	}
	
	@Test
	public void testLoginUserAuthorized() throws Exception {
		String dni = "0000000A";
		String contrasenya = "1234567Ab*";
		
		Response response = resource.loginUser(dni, contrasenya);
		
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
	}
	
	@Test
	public void testLoginUserUnauthorized() throws Exception {
		String dni = "0000000B";
		String contrasenya = "1234567Ab*";
		
		Response response = resource.loginUser(dni, contrasenya);
		
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
	}
	
	@Test
	public void testRegisterUser() throws Exception {
		String dni = "0000000C";
		String nombre = "usuario";
		String contrasenya = "1234567Ab*";
		UsuarioData usuarioData = new UsuarioData(dni, nombre, contrasenya);
		
		Response response = resource.registerUser(usuarioData);
		
		 assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void testgetHoteles() throws Exception {
		Response response = resource.getHoteles();
	}
	
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
}

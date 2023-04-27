package es.deusto.spq.client;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import es.deusto.spq.client.gui.VentLogin;
import es.deusto.spq.client.gui.VentRegistro;
import es.deusto.spq.server.jdo.Usuario;

public class VentTest {
	
	@Mock
    private Client client;

	@Mock(answer=Answers.RETURNS_DEEP_STUBS)
	private WebTarget webTarget;
	
	private VentLogin ventLogin;
	private VentRegistro ventRegistro;
	
	@Before 
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		try (MockedStatic<ClientBuilder> clientBuilder = Mockito.mockStatic(ClientBuilder.class)) {
            clientBuilder.when(ClientBuilder::newClient).thenReturn(client);
            when(client.target("http://localhost:8080/rest/resource")).thenReturn(webTarget);

            ventLogin = new VentLogin("localhost", "8080");
            ventRegistro = new VentRegistro("localhost", "8080");
        }
	}
	
	@Test
	public void loginUserTest() {
		when(webTarget.path("login")).thenReturn(webTarget);
		
		Response response = Response.ok().build();
		when(webTarget.queryParam("dni", "11111111A").queryParam("contrasenya", "password")
				.request(MediaType.APPLICATION_JSON).get()).thenReturn(response);
		
		assertTrue(ventLogin.loginUser("11111111A", "password"));
		
		verify(webTarget.queryParam("dni", "11111111A").queryParam("contrasenya", "password")
                .request(MediaType.APPLICATION_JSON)).get();
	}
	
	@Test
	public void registerUserTest() {
		when(webTarget.path("login")).thenReturn(webTarget);
		
//		Usuario usuario = new Usuario();
//		usuario.setDni("11111111A");
//		usuario.setNombre("testUser");
//		usuario.setContrasenya("password");
//		Entity<Usuario> entity = Entity.entity(usuario, MediaType.APPLICATION_JSON);
//		
//		 Response response = Response.ok().build();
//		 when(webTarget.request(MediaType.APPLICATION_JSON).post(entity)).thenReturn(response);
//
//		 assertTrue(ventRegistro.registerUser(usuario));
//		 
//		 verify(webTarget.request(MediaType.APPLICATION_JSON)).post(entity);
	}

}

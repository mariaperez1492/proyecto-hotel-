package es.deusto.spq.client;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
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

public class VentLoginTest {
	
	@Mock
    private Client client;

	@Mock(answer=Answers.RETURNS_DEEP_STUBS)
	private WebTarget webTarget;
	
	private VentLogin ventLogin;
	
	@Before 
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		try (MockedStatic<ClientBuilder> clientBuilder = Mockito.mockStatic(ClientBuilder.class)) {
            clientBuilder.when(ClientBuilder::newClient).thenReturn(client);
            when(client.target("http://localhost:8080/rest/resource")).thenReturn(webTarget);

            ventLogin = new VentLogin("localhost", "8080");
        }
	}
	
	@Test
	public void ventLoginTest() {
		when(webTarget.path("login")).thenReturn(webTarget);
		
		Response response = Response.ok().build();
		when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);

	}

}

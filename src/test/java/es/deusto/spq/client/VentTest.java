package es.deusto.spq.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.deusto.spq.client.gui.VentHabitacion;
import es.deusto.spq.client.gui.VentListado;
import es.deusto.spq.client.gui.VentLogin;
import es.deusto.spq.client.gui.VentRegistro;
import es.deusto.spq.pojo.HotelData;
import es.deusto.spq.pojo.UsuarioData;
import es.deusto.spq.server.jdo.Usuario;

public class VentTest {
	
	@Mock
    private Client client;

	@Mock(answer=Answers.RETURNS_DEEP_STUBS)
	private WebTarget webTarget;
	
	@Captor
    private ArgumentCaptor<Entity<UsuarioData>> usuarioDataEntityCaptor;
	
	private VentLogin ventLogin;
	private VentRegistro ventRegistro;
	private VentListado ventListado;
	private VentHabitacion ventHabitacion;
	
	@Before 
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		try (MockedStatic<ClientBuilder> clientBuilder = Mockito.mockStatic(ClientBuilder.class)) {
            clientBuilder.when(ClientBuilder::newClient).thenReturn(client);
            when(client.target("http://localhost:8080/rest/resource")).thenReturn(webTarget);

            ventLogin = new VentLogin("localhost", "8080");
            ventRegistro = new VentRegistro("localhost", "8080");
            ventListado = new VentListado("localhost", "8080");
        }
	}
	
	@Test
	public void loginUserTest() {
		when(webTarget.path("login")).thenReturn(webTarget);
		
		Response response = Response.ok().build();
		when(webTarget.queryParam("dni", "11111111A").queryParam("contrasenya", "password")
				.request(MediaType.APPLICATION_JSON).get()).thenReturn(response);
		
		assertEquals(1, ventLogin.loginUser("11111111A", "password"));
		assertEquals(2, ventLogin.loginUser("0000", "admin"));
		
		verify(webTarget.queryParam("dni", "11111111A").queryParam("contrasenya", "password")
                .request(MediaType.APPLICATION_JSON)).get();
	}
	
	@Test
	public void registerUserTest() {
		when(webTarget.path("register")).thenReturn(webTarget);
		
		Usuario usuario = new Usuario();
		usuario.setDni("11111111A");
		usuario.setNombre("testUser");
		usuario.setContrasenya("password");
		Entity<Usuario> entity = Entity.entity(usuario, MediaType.APPLICATION_JSON);
		
		 Response response = Response.ok().build();
		 when(webTarget.request(MediaType.APPLICATION_JSON).post(entity)).thenReturn(response);

		 assertTrue(ventRegistro.registerUser(usuario));
		 
		 verify(webTarget.request(MediaType.APPLICATION_JSON)).post(entity);
	}
	
	@Test
	public void getHotelesTest() throws JsonProcessingException {
		when(webTarget.path("getHoteles")).thenReturn(webTarget);
		
//		List<HotelData> expectedHoteles = new ArrayList<>();
//	    expectedHoteles.add(new HotelData("Hotel 1", "Dirección 1", 4));
//	    expectedHoteles.add(new HotelData("Hotel 2", "Dirección 2", 4));
//
//	    String jsonHoteles = new ObjectMapper().writeValueAsString(expectedHoteles);
//
//	    Response response = Response.ok().entity(jsonHoteles).build();
//
//	    when(webTarget.request(MediaType.APPLICATION_JSON).get()).thenReturn(response);
//	    
//	    List<HotelData> actualHoteles = ventListado.getHoteles();
//
//	    assertEquals(expectedHoteles.size(), actualHoteles.size());
//	    assertEquals(expectedHoteles.get(0).getNombre(), actualHoteles.get(0).getNombre());
//	    assertEquals(expectedHoteles.get(0).getCiudad(), actualHoteles.get(0).getCiudad());
//	    assertEquals(expectedHoteles.get(1).getNombre(), actualHoteles.get(1).getNombre());
//	    assertEquals(expectedHoteles.get(1).getCiudad(), actualHoteles.get(1).getCiudad());
	}
	
	@Test
	public void getHabitacionesTest() {
		when(webTarget.path("getHabitaciones")).thenReturn(webTarget);
		
	}

}

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

import es.deusto.spq.client.gui.PanelHoteles;
import es.deusto.spq.client.gui.PanelReservas;
import es.deusto.spq.client.gui.VentHabitacion;
import es.deusto.spq.client.gui.VentListado;
import es.deusto.spq.client.gui.VentListadoAdmin;
import es.deusto.spq.client.gui.VentLogin;
import es.deusto.spq.client.gui.VentPago;
import es.deusto.spq.client.gui.VentRegistro;
import es.deusto.spq.pojo.HotelData;
import es.deusto.spq.pojo.UsuarioData;
import es.deusto.spq.server.jdo.Usuario;

@SuppressWarnings("unused")
public class VentTest {
	
	@Mock
    private Client client;

	@Mock(answer=Answers.RETURNS_DEEP_STUBS)
	private WebTarget webTarget;
	
	@Captor
    private ArgumentCaptor<Entity<UsuarioData>> usuarioDataEntityCaptor;
	
	private PanelHoteles pHoteles;
	private PanelReservas pReservas;
	private VentHabitacion ventHabitacion;
	private VentListado ventListado;
	private VentListadoAdmin ventListadoAdmin;
	private VentLogin ventLogin;
	private VentPago ventPago;
	private VentRegistro ventRegistro;

	
	@Before 
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
		try (MockedStatic<ClientBuilder> clientBuilder = Mockito.mockStatic(ClientBuilder.class)) {
            clientBuilder.when(ClientBuilder::newClient).thenReturn(client);
            when(client.target("http://localhost:8080/rest/resource")).thenReturn(webTarget);

            pHoteles = new PanelHoteles("localhost", "8080");
            pReservas = new PanelReservas("localhost", "8080");
            //ventHabitacion = new VentHabitacion("localhost", "8080");
            //ventListado = new VentListado("localhost", "8080");
            ventListadoAdmin = new VentListadoAdmin("localhost", "8080");
            //ventPago = new VentPago();
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
		
		assertEquals(1, ventLogin.loginUser("11111111A", "password"));
		
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
		
		 List<HotelData> expectedHotels = new ArrayList<>();
		 expectedHotels.add(new HotelData("hotel1", "address1", 5));
		 expectedHotels.add(new HotelData("hotel2", "address2", 7));
	     
	     String mockResponse = new ObjectMapper().writeValueAsString(expectedHotels);
	     
	     Response response = Response.ok(mockResponse).build();
	     when(webTarget.request(MediaType.APPLICATION_JSON).get()).thenReturn(response);
	    
	     //assertEquals(expectedHotels, ventListado.getHoteles());));
	}
	
	@Test
	public void getHabitacionesTest() {
		when(webTarget.path("getHabitaciones")).thenReturn(webTarget);
		
	}

}

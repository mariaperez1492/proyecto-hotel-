package es.deusto.spq.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import es.deusto.spq.pojo.HotelData;
import es.deusto.spq.pojo.UsuarioData;
import es.deusto.spq.server.jdo.Hotel;
import es.deusto.spq.server.jdo.Usuario;

public class ResourceTest 
{
	private Resource resource;
	
	@Mock
	private PersistenceManager persistenceManager;

	@Mock
	private Transaction transaction;
	 
	@Before
	public void setUp() {
		 MockitoAnnotations.openMocks(this);
		 
		 try (MockedStatic<JDOHelper> jdoHelper = Mockito.mockStatic(JDOHelper.class)) {
			 PersistenceManagerFactory pmf = mock(PersistenceManagerFactory.class);
	         jdoHelper.when(() -> JDOHelper.getPersistenceManagerFactory("datanucleus.properties")).thenReturn(pmf);
	            
	         when(pmf.getPersistenceManager()).thenReturn(persistenceManager);
	         when(persistenceManager.currentTransaction()).thenReturn(transaction);

	         resource = new Resource();
	      }
	}
	
	@Test
	public void testLoginUserAuthorized() throws Exception {
		String param1 = "dni";
		String param2 = "password";
		
		Usuario usuario = spy(Usuario.class);
		usuario.setDni("dni");
		usuario.setContrasenya("password");
		when(persistenceManager.getObjectById(Usuario.class, param1)).thenReturn(usuario);
		
		when(transaction.isActive()).thenReturn(true);
		
		Response response = resource.loginUser(param1, param2);
		
		assertEquals(Response.Status.OK, response.getStatusInfo());
		assertNotNull(response.getEntity());
	}
	
	@Test
	public void testLoginUserUnauthorized() throws Exception {
		String param1 = "dni";
		String param2 = "password-incorrect";
		
		Usuario usuario = spy(Usuario.class);
		usuario.setDni("dni");
		usuario.setContrasenya("password");
		when(persistenceManager.getObjectById(Usuario.class, param1)).thenReturn(usuario);
		
		when(transaction.isActive()).thenReturn(true);
		
		Response response = resource.loginUser(param1, param2);
		
		assertEquals(Response.Status.UNAUTHORIZED, response.getStatusInfo());
		assertNull(response.getEntity());
	}
	
	@Test
	public void testRegisterUserSuccess() throws Exception {
		UsuarioData usuarioData = new UsuarioData();
		usuarioData.setDni("dni");
		usuarioData.setNombre("nombre");
		usuarioData.setContrasenya("password");
		
		Usuario usuario = spy(Usuario.class);
		when(persistenceManager.getObjectById(Usuario.class, usuarioData.getDni())).thenReturn(usuario);
		
		@SuppressWarnings("unchecked") Query<Usuario> query = mock(Query.class);
        when(persistenceManager.newQuery(Usuario.class, "dni == dniParam")).thenReturn(query);
        
        List<Usuario> usuarios = new ArrayList<Usuario>();
        when(query.execute(usuarioData.getDni())).thenReturn(usuarios);
        
        Response response = resource.registerUser(usuarioData);
        
        assertEquals(Response.Status.OK, response.getStatusInfo());
	}
	
	@Test
	public void getHotelesTest() throws Exception {
		Hotel hotel1 = spy(Hotel.class);
		hotel1.setNombre("Hotel 1");
		hotel1.setCiudad("Ciudad 1");
		hotel1.setHabitaciones_disp(30);
		
		Hotel hotel2 = spy(Hotel.class);
		hotel2.setNombre("Hotel 2");
		hotel2.setCiudad("Ciudad 2");
		hotel2.setHabitaciones_disp(15);
		
		@SuppressWarnings("unchecked") Query<Hotel> query = mock(Query.class);
        when(persistenceManager.newQuery(Hotel.class)).thenReturn(query);
		
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(hotel1);
        hotels.add(hotel2);
        when(query.executeList()).thenReturn(hotels);
        
        Response response = resource.getHoteles();
        
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        
        @SuppressWarnings("unchecked")List<HotelData> hotelDataList = (List<HotelData>) response.getEntity();
        
        assertEquals(hotelDataList.size(), hotels.size());
        assertEquals(hotelDataList.get(0).getNombre(), hotel1.getNombre());
        assertEquals(hotelDataList.get(0).getCiudad(), hotel1.getCiudad());
        assertEquals(hotelDataList.get(0).getHabitaciones_disp(), hotel1.getHabitaciones_disp());
        assertEquals(hotelDataList.get(1).getNombre(), hotel2.getNombre());
        assertEquals(hotelDataList.get(1).getCiudad(), hotel2.getCiudad());
        assertEquals(hotelDataList.get(1).getHabitaciones_disp(), hotel2.getHabitaciones_disp());
	}
	
	@Test
	public void getHabitacionesTest() throws Exception {
		//Habitacion habitacion1 = new Habitacion();
	}
	
	@Test
	public void getReservasTest() throws Exception {
		
	}
	
}


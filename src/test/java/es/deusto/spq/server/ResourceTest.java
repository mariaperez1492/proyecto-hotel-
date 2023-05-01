package es.deusto.spq.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.ws.rs.core.Response;
import javax.xml.crypto.Data;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import es.deusto.spq.pojo.EnumTipoHabitacion;
import es.deusto.spq.pojo.EnumTipoUsuario;
import es.deusto.spq.pojo.HabitacionData;
import es.deusto.spq.pojo.HotelData;
import es.deusto.spq.pojo.ReservaData;
import es.deusto.spq.pojo.UsuarioData;
import es.deusto.spq.server.jdo.Habitacion;
import es.deusto.spq.server.jdo.Hotel;
import es.deusto.spq.server.jdo.Reserva;
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
		Habitacion habitacion1 = new Habitacion();
		habitacion1.setTipoHabitacion(EnumTipoHabitacion.ESTANDAR);
		habitacion1.setPersonas(2);
		habitacion1.setPrecio(105.5f);
		
		Habitacion habitacion2 = new Habitacion();
		habitacion2.setTipoHabitacion(EnumTipoHabitacion.DELUXE);
		habitacion1.setPersonas(1);
		habitacion1.setPrecio(30.5f);
		
		@SuppressWarnings("unchecked") Query<Habitacion> query = mock(Query.class);
        when(persistenceManager.newQuery(Habitacion.class)).thenReturn(query);
        
        List<Habitacion> habitaciones = new ArrayList<>();
        habitaciones.add(habitacion1);
        habitaciones.add(habitacion2);
        when(query.executeList()).thenReturn(habitaciones);
        
        Response response = resource.getHabitaciones();
        
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        
        @SuppressWarnings("unchecked")List<HabitacionData> habitacionDataList = (List<HabitacionData>) response.getEntity();
        assertEquals(habitacionDataList.size(), habitaciones.size());
        assertEquals(habitacionDataList.get(0).getTipoHabitacion(), habitacion1.getTipoHabitacion());
        assertEquals(habitacionDataList.get(0).getPersonas(), habitacion1.getPersonas());
        //assertEquals(habitacionDataList.get(0).getPrecio(), habitacion1.getPrecio());
	}
	
	@Test
	public void getReservasTest() throws Exception {
		Reserva reserva1 = spy(Reserva.class);
		
		Hotel hotel1 = new Hotel("Hotel 1", "Madrid", 0);
		reserva1.setHotel(hotel1);
		Usuario usuario1 = new Usuario("12345678A","Pepe", "Hola-123456",EnumTipoUsuario.CLIENTE);
		reserva1.setCliente(usuario1);
		Habitacion hab1 = new Habitacion(EnumTipoHabitacion.DELUXE,10,20 );
		reserva1.setHabitacion(hab1);
		reserva1.setFecha_ini("14/07/2023");
		reserva1.setFecha_fin("09/08/2023");
		
		Reserva reserva2 = spy(Reserva.class);
		
		Hotel hotel2 = new Hotel("Hotel 2", "Barcelona", 0);
		reserva2.setHotel(hotel2);
		Usuario usuario2 = new Usuario("09876543R","Laura", "Hola-123456",EnumTipoUsuario.CLIENTE);
		reserva2.setCliente(usuario2);
		Habitacion hab2 = new Habitacion(EnumTipoHabitacion.ESTANDAR,20,30 );
		reserva2.setHabitacion(hab2);
		reserva2.setFecha_ini("10/07/2023");
		reserva2.setFecha_fin("10/08/2023");
		
		@SuppressWarnings("unchecked") Query<Reserva> query = mock(Query.class);
        when(persistenceManager.newQuery(Reserva.class)).thenReturn(query);
		
        List<Reserva> reservas = new ArrayList<>();
        reservas.add(reserva1);
        reservas.add(reserva2);
        when(query.executeList()).thenReturn(reservas);
        
        Response response = resource.getReservas();
        
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        
        @SuppressWarnings("unchecked")List<ReservaData> reservaDataList = (List<ReservaData>) response.getEntity();
        
        assertEquals(reservaDataList.size(), reservas.size());
        
//        assertEquals(reservaDataList.get(0).getCliente(), reserva1.getCliente());
//        assertEquals(reservaDataList.get(0).getHotel(), reserva1.getHotel());
//        assertEquals(reservaDataList.get(0).getHabitacion(), reserva1.getHabitacion());
        assertEquals(reservaDataList.get(0).getFecha_ini(), reserva1.getFecha_ini());
        assertEquals(reservaDataList.get(0).getFecha_fin(), reserva1.getFecha_fin());
//        assertEquals(reservaDataList.get(1).getCliente(), reserva2.getCliente());
//        assertEquals(reservaDataList.get(1).getHotel(), reserva2.getHotel());
//        assertEquals(reservaDataList.get(1).getHabitacion(), reserva2.getHabitacion());
        assertEquals(reservaDataList.get(1).getFecha_ini(), reserva2.getFecha_ini());
        assertEquals(reservaDataList.get(1).getFecha_fin(), reserva2.getFecha_fin());
	}
	
	@Test
	public void makeReservationTest() throws Exception {
		UsuarioData usuarioData = new UsuarioData();
		HotelData hotelData = new HotelData();
		HabitacionData habitacionData = new HabitacionData();
		String fecha_ini = "2023-01-05";
		String fecha_fin = "2023-01-20";
		
		ReservaData reservaData = new ReservaData(usuarioData, hotelData, habitacionData, 
				fecha_ini, fecha_fin);
		
		Response response = resource.makeReservation(reservaData);
		
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
	
}


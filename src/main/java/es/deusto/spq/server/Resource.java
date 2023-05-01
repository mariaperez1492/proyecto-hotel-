package es.deusto.spq.server;

import javax.jdo.PersistenceManager;

import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.jdo.JDOHelper;
import javax.jdo.Transaction;
import javax.swing.JOptionPane;

import es.deusto.spq.server.jdo.Habitacion;
import es.deusto.spq.server.jdo.Hotel;
import es.deusto.spq.server.jdo.Reserva;
import es.deusto.spq.server.jdo.Usuario;
import es.deusto.spq.pojo.UsuarioData;
import es.deusto.spq.pojo.HabitacionData;
import es.deusto.spq.pojo.HotelData;
import es.deusto.spq.pojo.ReservaData;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@Path("/resource")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {

	protected static final Logger logger = LogManager.getLogger();

	private PersistenceManager pm=null;
	private Transaction tx=null;

	public Resource() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
	}

	@GET
	@Path("/login")
	public Response loginUser(@QueryParam("dni") String dni, @QueryParam("contrasenya") String contrasenya) {
		try {
			tx.begin();
			Usuario cliente = null;
	
			try {
	            cliente = pm.getObjectById(Usuario.class, dni);
	        } catch (javax.jdo.JDOObjectNotFoundException jonfe) {
	            logger.info("Exception launched: {}", jonfe.getMessage());
	        }
			
			if (cliente != null && cliente.getContrasenya().equals(contrasenya)) { 
				String token = UUID.randomUUID().toString();
	            tx.commit();
	            return Response.ok().entity(token).build();
	        } else {
	            tx.commit();
	            return Response.status(Response.Status.UNAUTHORIZED).build();
	        }
		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		} finally {
			if (tx.isActive()) {
	            tx.rollback();
	        }
		}
	}

	/**
	 * Comprueba que el dni no se encuentra en la bd, en caso de que no crea un nuevo usuario
	 * @param clienteData
	 * @return
	 */
	
	@POST
	@Path("/register")
	public Response registerUser(UsuarioData clienteData) {
		try
        {	
            tx.begin();
            logger.info("Checking whether the user already exits or not: '{}'", clienteData.getDni());
            Usuario cliente = null;
			try {
				cliente = pm.getObjectById(Usuario.class, clienteData.getDni());
				Query<Usuario> query = pm.newQuery(Usuario.class, "dni == dniParam");
				query.declareParameters("String dniParam");
				@SuppressWarnings("unchecked")
				List<Usuario> clientes = (List<Usuario>) query.execute(clienteData.getDni());
				if (!clientes.isEmpty()) {
				    cliente = clientes.get(0);
				}
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}

			logger.info("Cliente: {}", cliente);
			
			if (cliente != null) {
				logger.info("User already exists", cliente);
				
			} else {
				logger.info("Creating user: {}", cliente);
				cliente = new Usuario(clienteData.getDni(), clienteData.getNombre(), clienteData.getContrasenya(), clienteData.getTipoUsuario());
				pm.makePersistent(cliente);					 
				logger.info("Cliente created: {}", cliente);
			}
			tx.commit();
			return Response.ok().build();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
		}
	}
	
	/**
	 * Obtiene todos los datos de la tabla de hoteles de la bd. 
	 * @return
	 */
	
	@GET
	@Path("/getHoteles")
	public Response getHoteles() {
		List<HotelData> list = new ArrayList<>();
		
		try {
			tx.begin();
	        Query<Hotel> query = pm.newQuery(Hotel.class);
	        List<Hotel> hotels = query.executeList();
	        
	        for (Hotel h : hotels) {
	            HotelData hotelData = new HotelData(h.getNombre(), h.getCiudad(), h.getHabitaciones_disp());
	            list.add(hotelData);
	        }
	        logger.info("Retrieved hotels from database: " + list.size());
	        tx.commit();
			
		} catch (Exception e) {
			logger.error("Error retrieving hotels from database", e);
		
		} finally {
			if (tx.isActive()) {
		        tx.rollback();
		    }
		}
		return Response.ok(list).build();
	}
	
	/**
	 * Obtiene todos los datos de la tabla de reservas de la bd. 
	 * @return
	 */
	
	@GET
	@Path("/getReservas")
	public Response getReservas() {
		List<ReservaData> list = new ArrayList<>();
		
		try {
			tx.begin();
	        Query<Reserva> query = pm.newQuery(Reserva.class);
	        List<Reserva> reservas = query.executeList();
	        
	        for (Reserva r : reservas) {
	            ReservaData reservaData = new ReservaData(r.getCliente(), r.getHotel(), r.getHabitacion(), r.getFecha_ini(), r.getFecha_fin());
	            list.add(reservaData);
	        }
	        logger.info("Retrieved reservas from database: " + list.size());
	        tx.commit();
			
		} catch (Exception e) {
			logger.error("Error retrieving reservas from database", e);
		
		} finally {
			if (tx.isActive()) {
		        tx.rollback();
		    }
		}
		return Response.ok(list).build();
	}
	
	/**
	 * Obtiene todos los datos de la tabla de habitaciones de la bd. 
	 * @return
	 */
	
	@GET
	@Path("/getHabitaciones")
	public Response getHabitaciones() {
		List<HabitacionData> list = new ArrayList<>();
		
		try {
			tx.begin();
	        Query<Habitacion> query = pm.newQuery(Habitacion.class);
	        List<Habitacion> listaHabitacion = query.executeList();
	        
	        for (Habitacion h : listaHabitacion) {
	            HabitacionData habitacionData = new HabitacionData(h.getTipoHabitacion(), h.getPersonas(), h.getPrecio());
	            list.add(habitacionData);
	        }
	        logger.info("Retrieved listaHabitacion from database: " + list.size());
	        tx.commit();
			
		} catch (Exception e) {
			logger.error("Error retrieving listaHabitacion from database", e);
		
		} finally {
			if (tx.isActive()) {
		        tx.rollback();
		    }
		}
		return Response.ok(list).build();
	}	
	
	@POST
	@Path("/reserve")
	public Response makeReservation(ReservaData reservaData) {
		try {
			tx.begin();
			
			Reserva reserva = new Reserva(reservaData.getCliente(), reservaData.getHotel(),
					reservaData.getHabitacion(), reservaData.getFecha_ini(), reservaData.getFecha_fin());
			
			pm.makePersistent(reserva);
			tx.commit();
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		} finally {
			if (tx.isActive()) {
	            tx.rollback();
	        }
			pm.close();
		}
	}
}

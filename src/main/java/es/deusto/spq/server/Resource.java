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

/**
 * Clase que representa el recurso RESTful para la gestión de hoteles y reservas.
 */
@Path("/resource")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {

    protected static final Logger logger = LogManager.getLogger();

    private PersistenceManager pm = null;
    private Transaction tx = null;

    /**
     * Constructor de la clase Resource.
     * Inicializa el PersistenceManager y la transacción.
     */
    public Resource() {
        PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
        this.pm = pmf.getPersistenceManager();
        this.tx = pm.currentTransaction();
    }

    /**
     * Método para autenticar a un usuario.
     * 
     * @param dni El DNI del usuario.
     * @param contrasenya La contraseña del usuario.
     * @return Respuesta HTTP que indica el éxito o fracaso de la autenticación.
     */
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
     * Método para registrar un nuevo usuario.
     * 
     * @param clienteData Datos del cliente a registrar.
     * @return Respuesta HTTP que indica el éxito o fracaso del registro.
     */
    @POST
    @Path("/register")
    public Response registerUser(UsuarioData clienteData) {
        try {
            tx.begin();
            logger.info("Checking whether the user already exists or not: '{}'", clienteData.getDni());
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
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
        }
    }

    /**
     * Método para obtener la lista de hoteles.
     * 
     * @return Respuesta HTTP que contiene la lista de hoteles.
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
     * Método para obtener la lista de reservas.
     * 
     * @return Respuesta HTTP que contiene la lista de reservas.
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

                UsuarioData u = new UsuarioData(r.getCliente().getDni(), r.getCliente().getNombre(),
                        r.getCliente().getDni(), r.getCliente().getTipoUsuario());

                HotelData h = new HotelData(r.getHotel().getNombre(), r.getHotel().getCiudad(),
                        r.getHotel().getHabitaciones_disp());

                HabitacionData hb = new HabitacionData(r.getHabitacion().getTipoHabitacion(),
                        r.getHabitacion().getPersonas(), r.getHabitacion().getPrecio());

                ReservaData reservaData = new ReservaData(u, h, hb, r.getFecha_ini(), r.getFecha_fin(),
                        r.getPension(), r.getPrecio());
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
     * Método para obtener la lista de habitaciones.
     * 
     * @return Respuesta HTTP que contiene la lista de habitaciones.
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
                HabitacionData habitacionData = new HabitacionData(h.getTipoHabitacion(), h.getPersonas(),
                        h.getPrecio());
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

    /**
     * Método para realizar una reserva.
     * 
     * @param reservaData Los datos de la reserva.
     * @return Respuesta HTTP que indica el resultado de la reserva.
     */
    @POST
    @Path("/reserve")
    public Response makeReservation(ReservaData reservaData) {
        try {
            tx.begin();
            Usuario usuario = pm.getObjectById(Usuario.class, reservaData.getCliente().getDni());
            Hotel hotel = pm.getObjectById(Hotel.class, 1);
            Habitacion habitacion = pm.getObjectById(Habitacion.class, 1);
        
            Reserva reserva = new Reserva(usuario, reservaData.getFecha_ini(), reservaData.getFecha_fin(), hotel,
                    habitacion, reservaData.getPension(), reservaData.getPrecio());

            pm.makePersistent(reserva);
            tx.commit();
            return Response.status(Response.Status.OK).build();

        } catch (Exception e) {
        	 logger.error("Error making the reservation:", e);
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }
}

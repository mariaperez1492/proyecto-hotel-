package es.deusto.spq.server;

import javax.jdo.PersistenceManager;

import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.Transaction;

import es.deusto.spq.server.jdo.Cliente;
import es.deusto.spq.pojo.ClienteData;
import es.deusto.spq.pojo.HotelData;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;




@Path("/resource")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {

	protected static final Logger logger = LogManager.getLogger();

	private int cont = 0;
	private PersistenceManager pm=null;
	private Transaction tx=null;

	public Resource() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
	}

	/*@POST
	@Path("/sayMessage")
	public Response sayMessage(DirectMessage directMessage) {
		User user = null;
		try{
			tx.begin();
			logger.info("Creating query ...");
			
			try (Query<?> q = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE login == \"" + directMessage.getUserData().getLogin() + "\" &&  password == \"" + directMessage.getUserData().getPassword() + "\"")) {
				q.setUnique(true);
				user = (User)q.execute();
				
				logger.info("User retrieved: {}", user);
				if (user != null)  {
					Message message = new Message(directMessage.getMessageData().getMessage());
					user.getMessages().add(message);
					pm.makePersistent(user);					 
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		
		if (user != null) {
			cont++;
			logger.info(" * Client number: {}", cont);
			MessageData messageData = new MessageData();
			messageData.setMessage(directMessage.getMessageData().getMessage());
			return Response.ok(messageData).build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity("Login details supplied for message delivery are not correct").build();
		}
	}
	*/
	@POST
	@Path("/register")
	public Response registerUser(ClienteData clienteData) {
		try
        {	
            tx.begin();
            logger.info("Checking whether the user already exits or not: '{}'", clienteData.getDni());
            Cliente cliente = null;
			try {
				cliente = pm.getObjectById(Cliente.class, clienteData.getDni());
				Query<Cliente> query = pm.newQuery(Cliente.class, "dni == dniParam");
				query.declareParameters("String dniParam");
				List<Cliente> clientes = (List<Cliente>) query.execute(cliente.getDni());
				if (!clientes.isEmpty()) {
				    cliente = clientes.get(0);
				}
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}
			logger.info("Cliente: {}", cliente);
			if (cliente != null) {
				logger.info("Setting password user: {}", cliente);
				cliente.setContrasenya(clienteData.getContrasenya());
				logger.info("Password set user: {}", cliente);
				logger.info("Setting name user: {}", cliente);
				cliente.setNombre(clienteData.getNombre());
				logger.info("Name set user: {}", cliente);
			} else {
				logger.info("Creating user: {}", cliente);
				cliente = new Cliente(clienteData.getDni(), clienteData.getContrasenya(), clienteData.getNombre());
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

	@GET
	@Path("/getHoteles")
	public Response getHoteles() {
		List<HotelData> list = null;
		
		try {
			tx.begin();
			Query<HotelData> query = pm.newQuery(HotelData.class);
			list = query.executeList();
			tx.commit();
			
		} catch (Exception e) {
			logger.error("Error retrieving hotels from database", e);
			//return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		
		} finally {
			if (tx.isActive()) {
		        tx.rollback();
		    }
		}
		
		return Response.ok(list).build();
	}

	@GET
	@Path("/hello")
	@Produces(MediaType.TEXT_PLAIN)
	public Response sayHello() {
		return Response.ok("Hola mundo!").build();
	}
}

package es.deusto.spq.server;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.noconnor.junitperf.JUnitPerfRule;
import com.github.noconnor.junitperf.JUnitPerfTest;
import com.github.noconnor.junitperf.JUnitPerfTestRequirement;
import com.github.noconnor.junitperf.reporting.providers.HtmlReportGenerator;

import categories.IntegrationTest;
import categories.PerformanceTest;
import es.deusto.spq.pojo.EnumTipoUsuario;
import es.deusto.spq.pojo.UsuarioData;
import es.deusto.spq.server.jdo.Habitacion;
import es.deusto.spq.server.jdo.Hotel;
import es.deusto.spq.server.jdo.Reserva;
import es.deusto.spq.server.jdo.Usuario;

import static org.junit.Assert.assertEquals;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.glassfish.grizzly.http.server.HttpServer;

@Category({ IntegrationTest.class, PerformanceTest.class })
public class ResourcePerfTest {

	 private static final PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	    
	 private static HttpServer server;
	 private WebTarget target;
	 
	 @Rule
	 public JUnitPerfRule perfTestRule = new JUnitPerfRule(new HtmlReportGenerator("target/junitperf/report.html"));

	 @BeforeClass
	 public static void prepareTests() throws Exception {
		 server = Main.startServer();
		 
		 PersistenceManager pm = pmf.getPersistenceManager();
	     Transaction tx = pm.currentTransaction();
	     try {
	            tx.begin();
	            pm.makePersistent(new Usuario("0000000A","usuario", "1234567Ab*", EnumTipoUsuario.CLIENTE));
	            pm.makePersistent(new Hotel("Hotel Ritz", "Madrid", 50));
	            tx.commit();
	     } finally {
	            if (tx.isActive()) {
	                tx.rollback();
	            }
	            pm.close();
	     }
	 }
	 
	 @Before
	 public void setUp() {
		 Client c = ClientBuilder.newClient();
		 target = c.target(Main.BASE_URI).path("resource");
	 }
	 
	 @AfterClass
	 public static void tearDownServer() throws Exception {
		 server.shutdown();
		 
		 PersistenceManager pm = pmf.getPersistenceManager();
	     Transaction tx = pm.currentTransaction();
	     try {
	         tx.begin();
	         pm.newQuery(Usuario.class).deletePersistentAll();
	         pm.newQuery(Hotel.class).deletePersistentAll();
	         tx.commit();
	     } finally {
	          if (tx.isActive()) {
	              tx.rollback();
	          }
	          pm.close();
	        }
	 }
	
	@Test
	@JUnitPerfTest(threads = 10, durationMs = 2000)
	public void testLoginUserPerf() {
		String dni = "0000000A";
		String contrasenya= "1234567Ab*";
		
		Response response = target.path("login")
				.queryParam("dni", dni)
				.queryParam("contrasenya", contrasenya)
				.request(MediaType.APPLICATION_JSON)
				.get();
		
		assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
	}
	 
	@Test
	public void testLoginUserInt() {
		String dni = "0000000A";
		String contrasenya= "1234567Ab*";
		
		Response response = target.path("login")
				.queryParam("dni", dni)
				.queryParam("contrasenya", contrasenya)
				.request(MediaType.APPLICATION_JSON)
				.get();
		
		assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
	}
	 
	 @Test
	 @JUnitPerfTest(threads = 10, durationMs = 2000)
	 public void testRegisterUserPerf() {
		UsuarioData usuarioData = new UsuarioData();
		usuarioData.setDni("0000000B");
		usuarioData.setNombre("usuario2");
		usuarioData.setContrasenya("1234567Ab*");
		usuarioData.setTipoUsuario(EnumTipoUsuario.CLIENTE);
		
		Response response = target.path("register")
				.request(MediaType.APPLICATION_JSON)
	            .post(Entity.entity(usuarioData, MediaType.APPLICATION_JSON));
	 
		assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
	 }
	 
	 @Test
	 public void testRegisterUserInt() {
		UsuarioData usuarioData = new UsuarioData();
		usuarioData.setDni("0000000B");
		usuarioData.setNombre("usuario2");
		usuarioData.setContrasenya("1234567Ab*");
		usuarioData.setTipoUsuario(EnumTipoUsuario.CLIENTE);
			
		Response response = target.path("register")
				.request(MediaType.APPLICATION_JSON)
		        .post(Entity.entity(usuarioData, MediaType.APPLICATION_JSON));
		 
		assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
	}
	 
	 @Test
	 @JUnitPerfTest(threads = 10, durationMs = 2000)
	 public void testgetHotelesPerf() {
		 Response response = target.path("getHoteles")
				 .request(MediaType.APPLICATION_JSON)
				 .get();
		 
		 assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
	 }
	 
	 @Test
	 public void testgetHotelesInt() {
		 Response response = target.path("getHoteles")
				 .request(MediaType.APPLICATION_JSON)
				 .get();
		 
		 assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
	 
	 }

}

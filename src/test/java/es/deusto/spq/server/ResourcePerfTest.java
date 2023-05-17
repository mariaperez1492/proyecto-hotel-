package es.deusto.spq.server;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import com.github.noconnor.junitperf.JUnitPerfRule;
import com.github.noconnor.junitperf.JUnitPerfTest;
import com.github.noconnor.junitperf.JUnitPerfTestRequirement;
import com.github.noconnor.junitperf.reporting.providers.HtmlReportGenerator;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.glassfish.grizzly.http.server.HttpServer;


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
	 }
	 
	 @Before
	 public void setUp() {
	 
		 Client c = ClientBuilder.newClient();
		 target = c.target(Main.BASE_URI).path("resource");
	 }
	 
	 @AfterClass
	 public static void tearDownServer() throws Exception {
		 server.shutdown();
	 }

}

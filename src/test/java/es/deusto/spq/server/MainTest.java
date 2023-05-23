package es.deusto.spq.server;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MainTest {

    private HttpServer server;

    @Before
    public void setUp() throws IOException {
        server = Main.startServer();
    }

    @After
    public void tearDown() {
        server.shutdownNow();
    }

    @Test
    public void testStartServer() {
        assertTrue(server.isStarted());
    }
}

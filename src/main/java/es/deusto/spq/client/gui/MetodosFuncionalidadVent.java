package es.deusto.spq.client.gui;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.deusto.spq.pojo.HabitacionData;
import es.deusto.spq.pojo.HotelData;
import es.deusto.spq.pojo.ReservaData;
import es.deusto.spq.server.jdo.Usuario;

public class MetodosFuncionalidadVent {
	private static final long serialVersionUID = 1L;
	
	protected static final Logger logger = LogManager.getLogger();
	private Client client;
	private WebTarget webTarget;
	

	
		public boolean registerUser(Usuario usuarioDAO) {
			WebTarget registerUserWebTarget = webTarget.path("register");
			Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		
			Response response = invocationBuilder.post(Entity.entity(usuarioDAO, MediaType.APPLICATION_JSON));
		
			if (response.getStatus() == Status.OK.getStatusCode()) {
				return true;
			} else {
				logger.error("Error connecting with the server. Code: {}", response.getStatus());
				return false;
			}
		}
		
		public int loginUser(String dni, String contrasenya) {
			WebTarget target = webTarget.path("login")
					.queryParam("dni", dni)
					.queryParam("contrasenya", contrasenya);
				
			Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
				
			Response response = invocationBuilder.get();
				
			if (response.getStatus() == Response.Status.OK.getStatusCode()) {
				if (dni.equals("0000")) {
					return 2;
				} else {
					return 1;
				}
			} else {
				return 0;
			}
		}
		
		public List<HotelData> getHoteles() throws JsonMappingException, JsonProcessingException {
			WebTarget hotelTarget = webTarget.path("getHoteles");
			Invocation.Builder invocationBuilder = hotelTarget.request(MediaType.APPLICATION_JSON);
					
			Response response = invocationBuilder.get();
			ObjectMapper mapper = new ObjectMapper();
			
			List<HotelData> listData = mapper.readValue(response.readEntity(String.class), new TypeReference<List<HotelData>>(){});
			return listData;
		}
		
		public List<ReservaData> getReservas() throws JsonMappingException, JsonProcessingException {
			
			WebTarget reservaTarget = webTarget.path("getReservas");
			Invocation.Builder invocationBuilder = reservaTarget.request(MediaType.APPLICATION_JSON);
			
			Response response = invocationBuilder.get();
			ObjectMapper mapper = new ObjectMapper();
			
			List<ReservaData> listData = mapper.readValue(response.readEntity(String.class), new TypeReference<List<ReservaData>>(){});
			return listData;
		}
		
		public List<HabitacionData> getHabitaciones() throws JsonMappingException, JsonProcessingException {
			WebTarget habitacionTarget = webTarget.path("getHabitaciones");
			Invocation.Builder invocationBuilder = habitacionTarget.request(MediaType.APPLICATION_JSON);
					
			Response response = invocationBuilder.get();
			ObjectMapper mapper = new ObjectMapper();
			
			List<HabitacionData> listData = mapper.readValue(response.readEntity(String.class), new TypeReference<List<HabitacionData>>(){});
			return listData;
		}
	
	
}

package es.deusto.spq.server.jdo;

import java.sql.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import es.deusto.spq.pojo.UsuarioData;
import es.deusto.spq.pojo.HabitacionData;
import es.deusto.spq.pojo.HotelData;

@PersistenceCapable
public class Reserva {
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	int ID;
	@Persistent(defaultFetchGroup = "true")
	UsuarioData cliente; 
	@Persistent(defaultFetchGroup = "true")
	HotelData hotel;
	@Persistent(defaultFetchGroup = "true")
	HabitacionData habitacion; 
	String fecha_ini; 
	String fecha_fin;
	
	public Reserva(UsuarioData cliente, HotelData hotel, HabitacionData habitacion, String fecha_ini, String fecha_fin) {
		this.cliente = cliente;
		this.hotel = hotel;
		this.habitacion = habitacion;
		this.fecha_ini = fecha_ini;
		this.fecha_fin = fecha_fin;
	}

	public UsuarioData getCliente() {
		return cliente;
	}

	public void setCliente(UsuarioData cliente) {
		this.cliente = cliente;
	}

	public HotelData getHotel() {
		return hotel;
	}

	public void setHotel(HotelData hotel) {
		this.hotel = hotel;
	}

	public HabitacionData getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(HabitacionData habitacion) {
		this.habitacion = habitacion;
	}

	public String getFecha_ini() {
		return fecha_ini;
	}

	public void setFecha_ini(String fecha_ini) {
		this.fecha_ini = fecha_ini;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	@Override
	public String toString() {
		return "ReservaDAO [cliente=" + cliente + ", hotel=" + hotel + ", habitacion=" + habitacion + ", fecha_ini="
				+ fecha_ini + ", fecha_fin=" + fecha_fin + "]";
	} 
	
	
	
	
}

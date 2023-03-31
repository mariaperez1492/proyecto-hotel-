package es.deusto.spq.server.jdo;

import java.sql.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import es.deusto.spq.pojo.ClienteData;
import es.deusto.spq.pojo.HabitacionData;
import es.deusto.spq.pojo.HotelData;

@PersistenceCapable
public class Reserva {
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	int ID;
	@Persistent(defaultFetchGroup = "true")
	ClienteData cliente; 
	@Persistent(defaultFetchGroup = "true")
	HotelData hotel;
	@Persistent(defaultFetchGroup = "true")
	HabitacionData habitacion; 
	Date fecha_ini; 
	Date fecha_fin;
	
	public Reserva(ClienteData cliente, HotelData hotel, HabitacionData habitacion, Date fecha_ini, Date fecha_fin) {
		super();
		this.cliente = cliente;
		this.hotel = hotel;
		this.habitacion = habitacion;
		this.fecha_ini = fecha_ini;
		this.fecha_fin = fecha_fin;
	}

	public ClienteData getCliente() {
		return cliente;
	}

	public void setCliente(ClienteData cliente) {
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

	public Date getFecha_ini() {
		return fecha_ini;
	}

	public void setFecha_ini(Date fecha_ini) {
		this.fecha_ini = fecha_ini;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	@Override
	public String toString() {
		return "ReservaDAO [cliente=" + cliente + ", hotel=" + hotel + ", habitacion=" + habitacion + ", fecha_ini="
				+ fecha_ini + ", fecha_fin=" + fecha_fin + "]";
	} 
	
	
	
	
}

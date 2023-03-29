package es.deusto.spq.server.jdo;

import java.sql.Date;

import es.deusto.spq.pojo.Cliente;
import es.deusto.spq.pojo.Habitacion;
import es.deusto.spq.pojo.Hotel;

public class ReservaDAO {
	Cliente cliente; 
	Hotel hotel;
	Habitacion habitacion; 
	Date fecha_ini; 
	Date fecha_fin;
	
	public ReservaDAO(Cliente cliente, Hotel hotel, Habitacion habitacion, Date fecha_ini, Date fecha_fin) {
		super();
		this.cliente = cliente;
		this.hotel = hotel;
		this.habitacion = habitacion;
		this.fecha_ini = fecha_ini;
		this.fecha_fin = fecha_fin;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
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

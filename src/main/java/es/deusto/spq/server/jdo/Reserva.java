package es.deusto.spq.server.jdo;

import java.sql.Date;
import java.util.ArrayList;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Reserva {
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	int ID;
	
	@Join
	@Persistent(defaultFetchGroup="true")
	Usuario cliente; 
	
	String fecha_ini; 
	String fecha_fin;
	
	@Join
	@Persistent(defaultFetchGroup="true")
	Hotel hotel;
	
	@Join
	@Persistent(defaultFetchGroup="true")
	Habitacion habitacion; 
	
	String pension;
	float precio;
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getPension() {
		return pension;
	}

	public void setPension(String pension) {
		this.pension = pension;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Reserva(Usuario cliente, String fecha_ini, String fecha_fin, Hotel hotel, Habitacion habitacion, String pension, float precio) {
		this.cliente = cliente;
		this.fecha_ini = fecha_ini;
		this.fecha_fin = fecha_fin;
		this.hotel = hotel;
		this.habitacion = habitacion;
		this.pension = pension;
		this.precio = precio;

	}
	
	public Reserva() {}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
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

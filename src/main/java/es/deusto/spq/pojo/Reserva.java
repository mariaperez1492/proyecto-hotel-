package es.deusto.spq.pojo;

import java.sql.Date;

public class Reserva {
	
	private Cliente cliente; 
	private Hotel hotel;
	private Habitacion habitacion;
	private Date fecha_ini;
	private Date fecha_fin;
	
	public Reserva(Cliente cliente, Hotel hotel, Habitacion habitacion, Date fecha_ini, Date fecha_fin) {
		super();
		this.cliente = cliente;
		this.hotel = hotel;
		this.habitacion = habitacion;
		this.fecha_ini = fecha_ini;
		this.fecha_fin = fecha_fin;
	}
	
	public Reserva() {
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
	
	
	
}

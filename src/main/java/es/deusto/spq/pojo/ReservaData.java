package es.deusto.spq.pojo;

import java.sql.Date;

public class ReservaData {
	
	private ClienteData cliente; 
	private HotelData hotel;
	private HabitacionData habitacion;
	private Date fecha_ini;
	private Date fecha_fin;
	
	public ReservaData(ClienteData cliente, HotelData hotel, HabitacionData habitacion, Date fecha_ini, Date fecha_fin) {
		super();
		this.cliente = cliente;
		this.hotel = hotel;
		this.habitacion = habitacion;
		this.fecha_ini = fecha_ini;
		this.fecha_fin = fecha_fin;
	}
	
	public ReservaData() {
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
	
	
	
}

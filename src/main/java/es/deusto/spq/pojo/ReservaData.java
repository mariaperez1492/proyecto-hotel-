package es.deusto.spq.pojo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReservaData {
	
	@JsonProperty("cliente")
	private UsuarioData cliente; 
	@JsonProperty("hotel")
	private HotelData hotel;
	@JsonProperty("habitacion")
	private HabitacionData habitacion;
	private String fecha_ini;
	private String fecha_fin;
	
	public ReservaData(UsuarioData cliente, HotelData hotel, HabitacionData habitacion, String fecha_ini, String fecha_fin) {
		super();
		this.cliente = cliente;
		this.hotel = hotel;
		this.habitacion = habitacion;
		this.fecha_ini = fecha_ini;
		this.fecha_fin = fecha_fin;
	}
	
	public ReservaData() {
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
	
	public long calcularDias() {
	    LocalDate fechaInicio = LocalDate.parse(this.fecha_ini);
	    LocalDate fechaFin = LocalDate.parse(this.fecha_fin);
	    return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
	}
}

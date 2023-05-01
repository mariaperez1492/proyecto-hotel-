package es.deusto.spq.pojo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReservaData {
	
	private UsuarioData cliente; 
	private String fecha_ini;
	private String fecha_fin;
	private HotelData hotel;
	private HabitacionData habitacion;
	private String pension;
	private float precio;
	
	public ReservaData(UsuarioData cliente, HotelData hotel, HabitacionData habitacion, String fecha_ini, String fecha_fin, String pension, float precio) {
		super();
		this.cliente = cliente;
		this.hotel = hotel;
		this.habitacion = habitacion;
		this.fecha_ini = fecha_ini;
		this.fecha_fin = fecha_fin;
		this.pension = pension;
		this.precio = precio;
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
}

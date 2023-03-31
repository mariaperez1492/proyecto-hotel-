package es.deusto.spq.server.jdo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Hotel {
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	int ID;
	String nombre; 
	String ciudad; 
	int habitaciones_disp;
	
	public Hotel(String nombre, String ciudad, int habitaciones_disp) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.habitaciones_disp = habitaciones_disp;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getHabitaciones_disp() {
		return habitaciones_disp;
	}

	public void setHabitaciones_disp(int habitaciones_disp) {
		this.habitaciones_disp = habitaciones_disp;
	}

	@Override
	public String toString() {
		return "HotelDAO [nombre=" + nombre + ", ciudad=" + ciudad + ", habitaciones_disp=" + habitaciones_disp + "]";
	}
	
	
	
	
	
}

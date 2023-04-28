package es.deusto.spq.server.jdo;

import javax.annotation.Generated;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import es.deusto.spq.pojo.EnumTipoHabitacion;

@PersistenceCapable
public class Habitacion {
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	int numHabitacion;
	EnumTipoHabitacion tipoHabitacion; 
	int personas; 
	float precio;
	
	public Habitacion(EnumTipoHabitacion tipoHabitacion, int personas, float precio) {
		super();
		this.tipoHabitacion = tipoHabitacion;
		this.personas = personas;
		this.precio = precio;
	}
	
	public Habitacion() { }

	public EnumTipoHabitacion getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(EnumTipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	public int getPersonas() {
		return personas;
	}

	public void setPersonas(int personas) {
		this.personas = personas;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "HabitacionDAO [tipoHabitacion=" + tipoHabitacion + ", personas=" + personas + ", precio=" + precio
				+ "]";
	} 
	
	
	
}

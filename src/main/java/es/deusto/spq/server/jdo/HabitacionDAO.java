package es.deusto.spq.server.jdo;

import javax.jdo.annotations.PersistenceCapable;

import es.deusto.spq.pojo.EnumTipoHabitacion;

@PersistenceCapable
public class HabitacionDAO {
	EnumTipoHabitacion tipoHabitacion; 
	int personas; 
	float precio;
	
	public HabitacionDAO(EnumTipoHabitacion tipoHabitacion, int personas, float precio) {
		super();
		this.tipoHabitacion = tipoHabitacion;
		this.personas = personas;
		this.precio = precio;
	}

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

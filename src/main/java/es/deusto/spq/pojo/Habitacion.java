package es.deusto.spq.pojo;

public class Habitacion {
			
	private EnumTipoHabitacion tipoHabitacion;
	private int personas; 
	private float precio;
	
	
	public Habitacion(EnumTipoHabitacion tipoHabitacion, int personas, float precio) {
		super();
		this.tipoHabitacion = tipoHabitacion;
		this.personas = personas;
		this.precio = precio;
	} 
	
	public Habitacion() {
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
	
	
	
	
}

package es.deusto.spq.pojo;

public class HotelData {
	
	private String nombre; 
	private String ciudad; 
	private int habitaciones_disp;
	
	public HotelData(String nombre, String ciudad, int habitaciones_disp) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.habitaciones_disp = habitaciones_disp;
	}
	
	public HotelData() {
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
	
	
	
	
}

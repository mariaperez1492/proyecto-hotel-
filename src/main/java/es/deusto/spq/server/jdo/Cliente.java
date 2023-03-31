package es.deusto.spq.server.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Cliente {
	@PrimaryKey
	String dni;
	String nombre;
	String contrasenya;
	
	public Cliente(String dni, String nombre, String contrasenya) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.contrasenya = contrasenya;
	}

	public Cliente() {
		super();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}


	@Override
	public String toString() {
		return "ClienteDAO [dni=" + dni + ", nombre=" + nombre + ", contrasenya=" + contrasenya + "]";
	} 
	
	
	
	

}

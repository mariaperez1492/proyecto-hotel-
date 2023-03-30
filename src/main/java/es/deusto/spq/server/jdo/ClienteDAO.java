package es.deusto.spq.server.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class ClienteDAO {
	@PrimaryKey
	String dni;
	String nombre; 
	String contrasenya;
	
	
	public ClienteDAO(String dni, String nombre, String contrasenya) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.contrasenya = contrasenya;
	}
	


	public ClienteDAO() {
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

package es.deusto.spq.server.jdo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

import es.deusto.spq.pojo.EnumTipoUsuario;

@PersistenceCapable
public class Usuario {
	@PrimaryKey
	String dni;
	String nombre;
	String contrasenya;
	EnumTipoUsuario tipoUsuario;
	
	
	public Usuario(String dni, String nombre, String contrasenya, EnumTipoUsuario tipoUsuario) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.contrasenya = contrasenya;
		this.tipoUsuario = tipoUsuario;
	}

	public Usuario() {
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

	public EnumTipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(EnumTipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}

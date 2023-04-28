package es.deusto.spq.pojo;

public class UsuarioData {
	private String dni;
	private String nombre;
	private String contrasenya;
	private EnumTipoUsuario tipoUsuario;
		
		
	public UsuarioData(String dni, String nombre, String contrasenya, EnumTipoUsuario tipoUsuario) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.contrasenya = contrasenya;
		this.tipoUsuario = tipoUsuario;
	}

	public UsuarioData(String dni, String contrasenya) {
		super();
		this.dni = dni;
		this.contrasenya = contrasenya;
	}

	public UsuarioData() {
		// required by serialization
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

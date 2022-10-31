package clases.proyecto;

public class Usuario {
    
	private int codigoUsuario;
	private String nombres, apellidos, telefono;
	private int cargo;
	
	public Usuario(int codigoUsuario, String nombres, String apellidos, String telefono,int cargo) {
		this.codigoUsuario = codigoUsuario;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.cargo = cargo;
	}
	
	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getCargo() {
		return cargo;
	}

	public void setCargo(int cargo) {
		this.cargo = cargo;
	}
	
}

package clases.proyecto;

public class Categoria {

	private int codigoCategoria;
	private String nombreCategoria;

	public Categoria(int codigoCategoria, String nombreCategoria) {
		this.codigoCategoria = codigoCategoria;
		this.nombreCategoria = nombreCategoria;
	}

	public int getCodigoCategoria() {
		return codigoCategoria;
	}
	public void setCodigoCategoria(int codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	
}




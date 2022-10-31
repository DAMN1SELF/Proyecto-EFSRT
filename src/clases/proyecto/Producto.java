package clases.proyecto;

public class Producto {
	
	private int codigoProducto;
	private String nombreProducto;
	private int stockProducto;
	private int unidadMedida;
	private int codigoCategoria;

	public Producto(int codigoProducto, String nombreProducto, int stockProducto, int unidadMedida,
			int codigoCategoria) {
		this.codigoProducto = codigoProducto;
		this.nombreProducto = nombreProducto;
		this.stockProducto = stockProducto;
		this.unidadMedida = unidadMedida;
		this.codigoCategoria = codigoCategoria;
	}
	
	public int getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(int codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public int getStockProducto() {
		return stockProducto;
	}

	public void setStockProducto(int stockProducto) {
		this.stockProducto = stockProducto;
	}

	public int getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(int unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public int getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(int codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}


	
}




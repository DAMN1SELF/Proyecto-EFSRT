package clases.proyecto;

import java.util.Date;

public class Ingreso {


	private int codIngreso;
	private int codProducto;
	private Date fechaIngreso;
	private int cantidad;
	private String observacion;
	
	
	public Ingreso(int codIngreso, int codProducto, Date fechaIngreso, int cantidad, 
			String observacion) {
		this.codIngreso = codIngreso;
		this.codProducto = codProducto;
		this.fechaIngreso = fechaIngreso;
		this.cantidad = cantidad;
		this.observacion = observacion;
	}
	
	
	public int getCodIngreso() {
		return codIngreso;
	}
	public void setCodIngreso(int codIngreso) {
		this.codIngreso = codIngreso;
	}
	public int getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	

	
}
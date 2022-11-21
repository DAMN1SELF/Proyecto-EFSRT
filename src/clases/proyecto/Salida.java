package clases.proyecto;

import java.util.Date;

public class Salida {


	private int codSalida;
	private int codProducto;
	private Date fechaSalida;
	private int cantidad;
	private String observacion;
	private int codReserva;
	
	public Salida(int codSalida,int codReserva, int codProducto, Date fechaSalida, int cantidad, 
			String observacion) {
		this.codSalida=codSalida;
		this.codReserva=codReserva;
		this.codProducto = codProducto;
		this.fechaSalida=fechaSalida;
		this.cantidad = cantidad;
		this.observacion = observacion;
	}
	
	

	public int getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
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


	public int getCodReserva() {
		return codReserva;
	}


	public void setCodReserva(int codReserva) {
		this.codReserva = codReserva;
	}



	public int getCodSalida() {
		return codSalida;
	}



	public void setCodSalida(int codSalida) {
		this.codSalida = codSalida;
	}



	public Date getFechaSalida() {
		return fechaSalida;
	}



	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	

	
}
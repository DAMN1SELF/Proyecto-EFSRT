package clases.proyecto;
import java.util.Date;

public class Reserva {

	private int codigoReserva;
	private Date fechaReserva;
	private int codigoSala;
	private String fechaInicio;
	private String fechaFin;
	private int nroAsistentes;
	private int codigoUsuarioReserva;
	private String observacion;

	public Reserva(
			int codigoReserva,
			Date fechaReserva,
			int codigoSala,
			String fechaInicio, 
			String fechaFin,
			int nroAsistentes,
			int codigoUsuarioReserva, 
			String observacion
			) {
		this.codigoReserva = codigoReserva;
		this.fechaReserva = fechaReserva;
		this.codigoSala = codigoSala;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.nroAsistentes = nroAsistentes;
		this.codigoUsuarioReserva = codigoUsuarioReserva;
		this.observacion = observacion;
	}

	public int getCodigoReserva() {
		return codigoReserva;
	}

	public void setCodigoReserva(int codigoReserva) {
		this.codigoReserva = codigoReserva;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public int getCodigoSala() {
		return codigoSala;
	}

	public void setCodigoSala(int codigoSala) {
		this.codigoSala = codigoSala;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public int getNroAsistentes() {
		return nroAsistentes;
	}

	public void setNroAsistentes(int nroAsistentes) {
		this.nroAsistentes = nroAsistentes;
	}

	public int getCodigoUsuarioReserva() {
		return codigoUsuarioReserva;
	}

	public void setCodigoUsuarioReserva(int codigoUsuarioReserva) {
		this.codigoUsuarioReserva = codigoUsuarioReserva;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

}
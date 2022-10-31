package clases.proyecto;

public class Sala {
    
	private int codigoSala, aforo;
	private String nombreSala;
	private boolean enMantenimiento;
	
    public Sala(int codigoSala, String nombreSala,int aforo,boolean enMantenimiento) {
		this.codigoSala = codigoSala;
		this.aforo = aforo;
		this.nombreSala = nombreSala;
		this.enMantenimiento=enMantenimiento;
	}
	public int getCodigoSala() {
		return codigoSala;
	}
	public void setCodigoSala(int codigoSala) {
		this.codigoSala = codigoSala;
	}
	public int getAforo() {
		return aforo;
	}
	public void setAforo(int aforo) {
		this.aforo = aforo;
	}
	public String getNombreSala() {
		return nombreSala;
	}
	public void setNombreSala(String nombreSala) {
		this.nombreSala = nombreSala;
	}
    public boolean isEnMantenimiento() {
        return enMantenimiento;
    }
 
    public void setEnMantenimiento(boolean enMantenimiento) {
        this.enMantenimiento = enMantenimiento;
    }
    public boolean getEnMantenimiento() {
        return enMantenimiento ;
    }
}

package arreglos.proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import clases.proyecto.Reserva;

public class ArregloReservas{

    private ArrayList<Reserva> Reserva;

    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    
    DateFormat format2 = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    public ArregloReservas() {
        Reserva = new ArrayList<Reserva>();
        cargarReservas();
    }

    public void adicionar(Reserva x) {
        Reserva.add(x);
        grabarReservas();
    }

    public int tamanio() {
        return Reserva.size();
    }

    public Reserva obtener(int i) {
        return Reserva.get(i);
    }

    public Reserva buscar(int codigoReserva) {
        for (int i = 0; i < tamanio(); i++)
            if (obtener(i).getCodigoReserva() == codigoReserva)
                return obtener(i);
        return null;
    }

    public void eliminar(Reserva x) {
        Reserva.remove(x);
        grabarReservas();
    }

    public void actualizarArchivo() {
        grabarReservas();
    }

    private void grabarReservas() {
        try {
            PrintWriter pw;
            String linea;
            Reserva x;
            
        
            pw = new PrintWriter(new FileWriter("Reservas.txt"));
            for (int i = 0; i < tamanio(); i++) {
                x = obtener(i);
                linea = 
                x.getCodigoReserva() + ";" +
                format.format(x.getFechaReserva()) + ";" +
                x.getCodigoSala() + ";" +
                x.getFechaInicio() + ";" +
                x.getFechaFin() + ";" +
                x.getNroAsistentes() + ";" +
                x.getCodigoUsuarioReserva() + ";" +
                x.getObservacion()  ;
                pw.println(linea);
            }
            pw.close();
        } catch (Exception e) {
        }
    }

    private void cargarReservas() {
        try {
            BufferedReader br;
            String linea;
            String[] s;
            int codigoReserva,codigoSala,nroAsistentes, codigoUsuarioReserva;
            Date fechaReserva ;
            String fechaInicio,fechaFin;
            String observacion;
            br = new BufferedReader(new FileReader("Reservas.txt"));
            while ((linea = br.readLine()) != null) {
                s = linea.split(";");
                codigoReserva = Integer.parseInt(s[0].trim());
                fechaReserva = format.parse(s[1].trim());
                codigoSala = Integer.parseInt(s[2].trim());
                fechaInicio = s[3].trim();
                fechaFin =  s[4].trim();
                nroAsistentes = Integer.parseInt(s[5].trim());
                codigoUsuarioReserva = Integer.parseInt(s[6].trim());
                observacion=s[7].trim();
                adicionar(new Reserva(codigoReserva,fechaReserva,codigoSala,fechaInicio,fechaFin,nroAsistentes,codigoUsuarioReserva,observacion));
            }
            br.close();
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
    }

    public int codigoCorrelativo() {
        if (tamanio() == 0)
            return 9000;
        else
            return obtener(tamanio() - 1).getCodigoReserva() + 1;
    }

}
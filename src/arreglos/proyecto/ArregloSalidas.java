package arreglos.proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import clases.proyecto.Salida;

public class ArregloSalidas{

    private ArrayList<Salida> Salida;

    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    
    DateFormat format2 = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    public ArregloSalidas() {
        Salida = new ArrayList<Salida>();
        cargarSalidas();
    }

    public void adicionar(Salida x) {
        Salida.add(x);
        grabarSalidas();
    }

    public int tamanio() {
        return Salida.size();
    }

    public Salida obtener(int i) {
        return Salida.get(i);
    }

    public Salida buscar(int codigoSalida) {
        for (int i = 0; i < tamanio(); i++)
            if (obtener(i).getCodSalida() == codigoSalida)
                return obtener(i);
        return null;
    }

    public void eliminar(Salida x) {
        Salida.remove(x);
        grabarSalidas();
    }

    public void actualizarArchivo() {
        grabarSalidas();
    }

    private void grabarSalidas() {
        try {
            PrintWriter pw;
            String linea;
            Salida x;
            
        
            pw = new PrintWriter(new FileWriter("Salidas.txt"));
            for (int i = 0; i < tamanio(); i++) {
                x = obtener(i);
                linea = 
                x.getCodSalida() + ";" +
                x.getCodReserva()+ ";" +
                x.getCodProducto() + ";" +
                format.format(x.getFechaSalida()) + ";" +
                x.getCantidad() + ";" +
                x.getObservacion().replace("\n","▀")  ;
                pw.println(linea);
            }
            pw.close();
        } catch (Exception e) {
        }
    }

    private void cargarSalidas() {
        try {
            BufferedReader br;
            String linea;
            String[] s;
            int codigoSalida,codigoProducto,cantidad,codigoReserva;
            Date fechaSalida ;
            String observacion;
            br = new BufferedReader(new FileReader("Salidas.txt"));
            while ((linea = br.readLine()) != null) {
                s = linea.split(";");
                codigoSalida = Integer.parseInt(s[0].trim());
                codigoReserva= Integer.parseInt(s[1].trim());
                codigoProducto = Integer.parseInt(s[2].trim());
                fechaSalida = format.parse(s[3].trim());
                cantidad = Integer.parseInt(s[4].trim());
                observacion= s[5].trim().replace("▀", "\n");
                adicionar(new Salida(codigoSalida,codigoReserva,codigoProducto,fechaSalida,cantidad,observacion));
            }
            br.close();
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
    }

    public int codigoCorrelativo() {
        if (tamanio() == 0)
            return 100;
        else
            return obtener(tamanio() - 1).getCodSalida() + 1;
    }

}
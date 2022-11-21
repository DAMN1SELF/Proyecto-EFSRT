package arreglos.proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import clases.proyecto.Ingreso;

public class ArregloIngresos{

    private ArrayList<Ingreso> Ingreso;

    DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    
    DateFormat format2 = new SimpleDateFormat("dd/MM/yyyy hh:mm");

    public ArregloIngresos() {
        Ingreso = new ArrayList<Ingreso>();
        cargarIngresos();
    }

    public void adicionar(Ingreso x) {
        Ingreso.add(x);
        grabarIngresos();
    }

    public int tamanio() {
        return Ingreso.size();
    }

    public Ingreso obtener(int i) {
        return Ingreso.get(i);
    }

    public Ingreso buscar(int codigoIngreso) {
        for (int i = 0; i < tamanio(); i++)
            if (obtener(i).getCodIngreso() == codigoIngreso)
                return obtener(i);
        return null;
    }

    public void eliminar(Ingreso x) {
        Ingreso.remove(x);
        grabarIngresos();
    }

    public void actualizarArchivo() {
        grabarIngresos();
    }

    private void grabarIngresos() {
        try {
            PrintWriter pw;
            String linea;
            Ingreso x;
            
        
            pw = new PrintWriter(new FileWriter("Ingresos.txt"));
            for (int i = 0; i < tamanio(); i++) {
                x = obtener(i);
                linea = 
                x.getCodIngreso() + ";" +
                x.getCodProducto() + ";" +
                format.format(x.getFechaIngreso()) + ";" +
                x.getCantidad() + ";" +
                x.getObservacion().replace("\n","▀")  ;
                pw.println(linea);
            }
            pw.close();
        } catch (Exception e) {
        }
    }

    private void cargarIngresos() {
        try {
            BufferedReader br;
            String linea;
            String[] s;
            int codigoIngreso,codigoProducto,cantidad;
            Date fechaIngreso ;
            String observacion;
            br = new BufferedReader(new FileReader("Ingresos.txt"));
            while ((linea = br.readLine()) != null) {
                s = linea.split(";");
                codigoIngreso = Integer.parseInt(s[0].trim());
                codigoProducto = Integer.parseInt(s[1].trim());
                fechaIngreso = format.parse(s[2].trim());
                cantidad = Integer.parseInt(s[3].trim());
                observacion= s[4].trim().replace("▀", "\n");
                adicionar(new Ingreso(codigoIngreso,codigoProducto,fechaIngreso,cantidad,observacion));
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
            return obtener(tamanio() - 1).getCodIngreso() + 1;
    }

}
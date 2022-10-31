package arreglos.proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import clases.proyecto.Sala;

public class ArregloSalas{

    private ArrayList<Sala> Sala;

    public ArregloSalas() {
        Sala = new ArrayList<Sala>();
        cargarSalas();
    }

    public void adicionar(Sala x) {
        Sala.add(x);
        grabarSalas();
    }

    public int tamanio() {
        return Sala.size();
    }

    public Sala obtener(int i) {
        return Sala.get(i);
    }

    public Sala buscar(int codigoSala) {
        for (int i = 0; i < tamanio(); i++)
            if (obtener(i).getCodigoSala() == codigoSala)
                return obtener(i);
        return null;
    }

    public void eliminar(Sala x) {
        Sala.remove(x);
        grabarSalas();
    }

    public void actualizarArchivo() {
        grabarSalas();
    }

    private void grabarSalas() {
        try {
            PrintWriter pw;
            String linea;
            Sala x;
            pw = new PrintWriter(new FileWriter("Salas.txt"));
            for (int i = 0; i < tamanio(); i++) {
                x = obtener(i);
                linea = 
                x.getCodigoSala() + ";" +
                x.getNombreSala() + ";" +
                x.getAforo() + ";" +
                x.getEnMantenimiento() ;
                pw.println(linea);
            }
            pw.close();
        } catch (Exception e) {
        }
    }

    private void cargarSalas() {
        try {
            BufferedReader br;
            String linea;
            String[] s;
            int codigoSala,aforo;
            boolean enMantenimiento;
            String nombreSala;
            br = new BufferedReader(new FileReader("Salas.txt"));
            while ((linea = br.readLine()) != null) {
                s = linea.split(";");
                codigoSala = Integer.parseInt(s[0].trim());
                nombreSala = s[1].trim();
                aforo = Integer.parseInt(s[2].trim());
                enMantenimiento=Boolean.parseBoolean(s[3].trim());
                
                adicionar(new Sala(codigoSala, nombreSala, aforo,enMantenimiento));
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public int codigoCorrelativo() {
        if (tamanio() == 0)
            return 5001;
        else
            return obtener(tamanio() - 1).getCodigoSala() + 1;
    }

}
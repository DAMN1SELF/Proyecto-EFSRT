package arreglos.proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import clases.proyecto.Categoria;

public class ArregloCategorias{

    private ArrayList<Categoria> Categoria;

    public ArregloCategorias() {
        Categoria = new ArrayList<Categoria>();
        cargarCategorias();
    }

    public void adicionar(Categoria x) {
        Categoria.add(x);
        grabarCategorias();
    }

    public int tamanio() {
        return Categoria.size();
    }

    public Categoria obtener(int i) {
        return Categoria.get(i);
    }

    public Categoria buscar(int codigoCategoria) {
        for (int i = 0; i < tamanio(); i++)
            if (obtener(i).getCodigoCategoria() == codigoCategoria)
                return obtener(i);
        return null;
    }

    public void eliminar(Categoria x) {
        Categoria.remove(x);
        grabarCategorias();
    }

    public void actualizarArchivo() {
        grabarCategorias();
    }

    private void grabarCategorias() {
        try {
            PrintWriter pw;
            String linea;
            Categoria x;
            pw = new PrintWriter(new FileWriter("Categorias.txt"));
            for (int i = 0; i < tamanio(); i++) {
                x = obtener(i);
                linea = 
                x.getCodigoCategoria() + ";" +
                x.getNombreCategoria() ;
                pw.println(linea);
            }
            pw.close();
        } catch (Exception e) {
        }
    }

    private void cargarCategorias() {
        try {
            BufferedReader br;
            String linea;
            String[] s;
            int codigoCategoria;
            String nombreCategoria;
            br = new BufferedReader(new FileReader("Categorias.txt"));
            while ((linea = br.readLine()) != null) {
                s = linea.split(";");
                codigoCategoria = Integer.parseInt(s[0].trim());
                nombreCategoria = s[1].trim();
                adicionar(new Categoria(codigoCategoria,nombreCategoria));
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public int codigoCorrelativo() {
        if (tamanio() == 0)
            return 9001;
        else
            return obtener(tamanio() - 1).getCodigoCategoria() + 1;
    }

}
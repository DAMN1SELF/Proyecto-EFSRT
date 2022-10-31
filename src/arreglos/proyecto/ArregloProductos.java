package arreglos.proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import clases.proyecto.Producto;

public class ArregloProductos{

    private ArrayList<Producto> Producto;

    public ArregloProductos() {
        Producto = new ArrayList<Producto>();
        cargarProductos();
    }

    public void adicionar(Producto x) {
        Producto.add(x);
        grabarProductos();
    }

    public int tamanio() {
        return Producto.size();
    }

    public Producto obtener(int i) {
        return Producto.get(i);
    }

    public Producto buscar(int codigoProducto) {
        for (int i = 0; i < tamanio(); i++)
            if (obtener(i).getCodigoProducto() == codigoProducto)
                return obtener(i);
        return null;
    }

    public void eliminar(Producto x) {
        Producto.remove(x);
        grabarProductos();
    }

    public void actualizarArchivo() {
        grabarProductos();
    }

    private void grabarProductos() {
        try {
            PrintWriter pw;
            String linea;
            Producto x;
            pw = new PrintWriter(new FileWriter("Productos.txt"));
            for (int i = 0; i < tamanio(); i++) {
                x = obtener(i);
                linea = 
                x.getCodigoProducto() + ";" +
                x.getNombreProducto() + ";" +
                x.getStockProducto() + ";" +
                x.getUnidadMedida() + ";" +
                x.getCodigoCategoria();
                pw.println(linea);
            }
            pw.close();
        } catch (Exception e) {
        }
    }

    private void cargarProductos() {
        try {
            BufferedReader br;
            String linea;
            String[] s;
            int codigoProducto,stockProducto,unidadMedida,codigoCategoria;
            String nombreProducto;
            br = new BufferedReader(new FileReader("Productos.txt"));
            while ((linea = br.readLine()) != null) {
                s = linea.split(";");
                codigoProducto = Integer.parseInt(s[0].trim());
                nombreProducto = s[1].trim();
                stockProducto = Integer.parseInt(s[2].trim());
                unidadMedida = Integer.parseInt(s[3].trim());
                codigoCategoria = Integer.parseInt(s[4].trim());
                adicionar(new Producto(codigoProducto, nombreProducto, stockProducto, unidadMedida, codigoCategoria));
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public int codigoCorrelativo() {
        if (tamanio() == 0)
            return 5001;
        else
            return obtener(tamanio() - 1).getCodigoProducto() + 1;
    }

}
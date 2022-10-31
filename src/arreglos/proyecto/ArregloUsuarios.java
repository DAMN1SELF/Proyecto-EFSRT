package arreglos.proyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import clases.proyecto.Usuario;

public class ArregloUsuarios{

    private ArrayList<Usuario> Usuario;

    public ArregloUsuarios() {
        Usuario = new ArrayList<Usuario>();
        cargarUsuarios();
    }

    public void adicionar(Usuario x) {
        Usuario.add(x);
        grabarUsuarios();
    }

    public int tamanio() {
        return Usuario.size();
    }

    public Usuario obtener(int i) {
        return Usuario.get(i);
    }

    public Usuario buscar(int codigoUsuario) {
        for (int i = 0; i < tamanio(); i++)
            if (obtener(i).getCodigoUsuario() == codigoUsuario)
                return obtener(i);
        return null;
    }

    public void eliminar(Usuario x) {
        Usuario.remove(x);
        grabarUsuarios();
    }

    public void actualizarArchivo() {
        grabarUsuarios();
    }

    private void grabarUsuarios() {
        try {
            PrintWriter pw;
            String linea;
            Usuario x;
            pw = new PrintWriter(new FileWriter("Usuarios.txt"));
            for (int i = 0; i < tamanio(); i++) {
                x = obtener(i);
                linea = x.getCodigoUsuario() + ";" +
                        x.getNombres() + ";" +
                        x.getApellidos() + ";" +
                        x.getTelefono() + ";" +
                        x.getCargo();
                pw.println(linea);
            }
            pw.close();
        } catch (Exception e) {
        }
    }

    private void cargarUsuarios() {
        try {
            BufferedReader br;
            String linea;
            String[] s;
            int codigoUsuario;
            String nombres, apellidos, telefono;
            int cargo;
            br = new BufferedReader(new FileReader("Usuarios.txt"));
            while ((linea = br.readLine()) != null) {
                s = linea.split(";");
                codigoUsuario = Integer.parseInt(s[0].trim());
                nombres = s[1].trim();
                apellidos = s[2].trim();
                telefono = s[3].trim();
                cargo = Integer.parseInt(s[4].trim());
                adicionar(new Usuario(codigoUsuario, nombres, apellidos, telefono, cargo));
            }
            br.close();
        } catch (Exception e) {
        }
    }

    public int codigoCorrelativo() {
        if (tamanio() == 0)
            return 7001;
        else
            return obtener(tamanio() - 1).getCodigoUsuario() + 1;
    }

}
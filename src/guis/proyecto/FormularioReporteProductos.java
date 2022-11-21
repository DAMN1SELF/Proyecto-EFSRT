package guis.proyecto;


import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import arreglos.proyecto.ArregloProductos;
import clases.proyecto.Producto;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class FormularioReporteProductos extends JDialog implements ActionListener, KeyListener, MouseListener {
    private JScrollPane scrollPane;
    private DefaultTableModel modelo;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormularioReporteProductos dialog = new FormularioReporteProductos();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public FormularioReporteProductos() {
        setResizable(false);
        setTitle("Reportes | Consulta de STOCK");
        setBounds(100, 100, 710, 567);
        getContentPane().setLayout(null);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 45, 675, 472);
        getContentPane().add(scrollPane);
        
        reporte = new JTextArea();
        scrollPane.setViewportView(reporte);
        
        btnReporte = new JButton("Consultar");
        btnReporte.addActionListener(this);
        btnReporte.setForeground(Color.BLUE);
        btnReporte.setBounds(273, 11, 100, 23);
        getContentPane().add(btnReporte);
        ManejarBotones(true);
        ManejarFormulario(false);
    }

    ArregloProductos ap = new ArregloProductos();
    private JTextArea reporte;
    private JButton btnReporte;

    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == btnReporte) {
            actionPerformedBtnReporte(arg0);
        }
    }

    private void ManejarBotones(boolean estado) {
    }

    private void ManejarFormulario(boolean estado) {

    }

    public void keyPressed(KeyEvent arg0) {
    }

    public void keyReleased(KeyEvent arg0) {
        arg0.consume();
        // editarFila();
    }

    public void keyTyped(KeyEvent arg0) {
    }

    public void mouseClicked(MouseEvent arg0) {
    }


    public void mouseExited(MouseEvent arg0) {
    }

    public void mousePressed(MouseEvent arg0) {
    }

    public void mouseReleased(MouseEvent arg0) {
    }

    void mensaje(String s) {
        JOptionPane.showMessageDialog(this, s, "Informaci�n", 0);
    }

    // POLIFORMISMO
    void error(String s, JTextField txt) {
        mensaje(s);
        txt.setText("");
        txt.requestFocus();
    }

    void error(String s, JComboBox<String> cbo) {
        mensaje(s);
        cbo.requestFocus();
    }



    int anchoColumna(int porcentaje) {
        return porcentaje * scrollPane.getWidth() / 100;
    }

    int confirmar(String s) {
        return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


    protected void actionPerformedBtnReporte(ActionEvent arg0) {

        Producto p;
        reporte.setText("--------------------------------------------" + "\n");
        int contador =0;
        for (int i = 0; i < ap.tamanio(); i++) {
            p=ap.obtener(i);
            contador++;
            reporte.append(
            		"CODIGO PRODUCTO >	"+p.getCodigoProducto()+ "\n"+
            				"PRODUCTO >		"+p.getNombreProducto() +"\n"+
            				"STOCK >		"+ p.getStockProducto()+"\n"+
            				"--------------------------------------------"+"\n"
            		);
        }
        
        reporte.append(" TOTAL DE PRODUCTOS > "+ contador );
        
    }
}
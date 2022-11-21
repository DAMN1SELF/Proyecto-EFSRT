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
import arreglos.proyecto.ArregloReservas;
import arreglos.proyecto.ArregloSalas;
import arreglos.proyecto.ArregloSalidas;
import clases.proyecto.Producto;
import clases.proyecto.Reserva;
import clases.proyecto.Sala;
import clases.proyecto.Salida;

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

public class FormularioReporteSalas extends JDialog implements ActionListener, KeyListener, MouseListener {
    private JLabel lblTelefono;
    private JTextField txtSala;
    private JTextField txtCodigoSala;
    private JScrollPane scrollPane;
    private DefaultTableModel modelo;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormularioReporteSalas dialog = new FormularioReporteSalas();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public FormularioReporteSalas() {
        setResizable(false);
        setTitle("Mantenimiento | Matricula");
        setBounds(100, 100, 807, 528);
        getContentPane().setLayout(null);

        lblTelefono = new JLabel("Codigo Sala");
        lblTelefono.setBounds(10, 10, 94, 23);
        getContentPane().add(lblTelefono);

        txtSala = new JTextField();
        txtSala.setBounds(324, 10, 457, 23);
        getContentPane().add(txtSala);
        txtSala.setColumns(10);

        txtCodigoSala = new JTextField();
        txtCodigoSala.setBounds(94, 10, 100, 23);
        getContentPane().add(txtCodigoSala);
        txtCodigoSala.setColumns(10);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 119, 771, 359);
        getContentPane().add(scrollPane);
        
        reporte = new JTextArea();
        scrollPane.setViewportView(reporte);

        btnBuscar = new JButton("Buscar Sala");
        btnBuscar.addActionListener(this);
        btnBuscar.setForeground(Color.BLUE);
        btnBuscar.setBounds(201, 10, 118, 23);
        getContentPane().add(btnBuscar);
        
        btnReporte = new JButton("Consultar");
        btnReporte.addActionListener(this);
        btnReporte.setForeground(Color.BLUE);
        btnReporte.setBounds(267, 85, 100, 23);
        getContentPane().add(btnReporte);
        
        lblCodigoProducto = new JLabel("Codigo Producto");
        lblCodigoProducto.setBounds(10, 44, 94, 23);
        getContentPane().add(lblCodigoProducto);
        
        txtCodigoProducto = new JTextField();
        txtCodigoProducto.setColumns(10);
        txtCodigoProducto.setBounds(94, 44, 100, 23);
        getContentPane().add(txtCodigoProducto);
        
        btnBuscarProducto = new JButton("Buscar Producto");
        btnBuscarProducto.addActionListener(this);
        btnBuscarProducto.setForeground(Color.BLUE);
        btnBuscarProducto.setBounds(201, 44, 118, 23);
        getContentPane().add(btnBuscarProducto);
        
        txtProducto = new JTextField();
        txtProducto.setColumns(10);
        txtProducto.setBounds(324, 44, 457, 23);
        getContentPane().add(txtProducto);
        ManejarBotones(true);
        ManejarFormulario(false);
    }


    private JButton btnBuscar;
    private JTextArea reporte;
    private JButton btnReporte;
    private JLabel lblCodigoProducto;
    private JTextField txtCodigoProducto;
    private JButton btnBuscarProducto;
    private JTextField txtProducto;

    public void actionPerformed(ActionEvent arg0) {
    	if (arg0.getSource() == btnBuscarProducto) {
    		actionPerformedBtnBuscarProducto(arg0);
    	}
        if (arg0.getSource() == btnReporte) {
            actionPerformedBtnReporte(arg0);
        }
        if (arg0.getSource() == btnBuscar) {
            actionPerformedBtnBuscar(arg0);
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


 

    void limpieza() {
        txtCodigoSala.setText("");
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


    String leerNombreMatricula() {
        return txtSala.getText().trim();
    }

    int leerHoras() {
        /*
         * try {
         * 
         * if(valor.equals("")){
         * return -1;
         * }else {
         * return Integer.parseInt(valor);
         * }
         * } catch (Exception e) {
         * return -1;
         * }
         */

        String valor = txtCodigoSala.getText().trim();
        Pattern pat = Pattern.compile("[0-9]*");
        Matcher mat = pat.matcher(valor);
        if (mat.matches()) {
            return Integer.parseInt(valor);
        } else {
            return -1;
        }
    }

    
    int leerInteger(JTextField txt) {
        if (txt.getText().trim().equals("")) {
           return 0;
        }else {
           return Integer.parseInt(txt.getText().trim());
        }
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

    protected void actionPerformedBtnBuscar(ActionEvent arg0) {
        
       String codigo = txtCodigoSala.getText().trim();
       if (codigo.trim().equals("")) {
           return;
       }
       ArregloSalas aa= new ArregloSalas();
       Sala a;
       String sala="";
       for (int i = 0; i < aa.tamanio(); i++) {
           a = aa.obtener(i);
           if(Integer.parseInt(codigo) == a.getCodigoSala()) {
        	   sala= a.getCodigoSala() +" - " +a.getNombreSala(); 
           }
       }
       txtSala.setText(sala);
    }
    
    protected void actionPerformedBtnBuscarProducto(ActionEvent arg0) {
	    String codigo = txtCodigoProducto.getText().trim();
	       if (codigo.trim().equals("")) {
	           return;
	       }
	       ArregloProductos aa= new ArregloProductos();
	       Producto a;
	       String Producto="";
	       for (int i = 0; i < aa.tamanio(); i++) {
	           a = aa.obtener(i);
	           if(Integer.parseInt(codigo) == a.getCodigoProducto()) {
	        	   Producto= a.getCodigoProducto() +" - " +a.getNombreProducto(); 
	           }
	       }
	       txtProducto.setText(Producto);
	}
    
    protected void actionPerformedBtnReporte(ActionEvent arg0) {
        
        int codigoSala=leerInteger(txtCodigoSala);
        if( codigoSala <=0) {
            return;
        }
        int codigoProducto=leerInteger(txtCodigoProducto);
        if( codigoProducto <=0) {
            return;
        }
        
        ArregloSalas as= new ArregloSalas();
        Sala s=as.obtener(codigoSala);
        
        ArregloProductos ap=new ArregloProductos();
        Producto p;
        
        ArregloReservas ar = new ArregloReservas();
        Reserva r;
        
        ArregloSalidas asa = new ArregloSalidas();
        Salida sa;
        reporte.setText("");
        
        
        for (int i = 0; i < as.tamanio(); i++) {
        	
        }
        /*
        reporte.append("EL ALUMNO> " + txtSala.getText()+"\n"); 
        

        for (int i = 0; i < am.tamanio(); i++) {
           
            m=am.obtener(i);
            //m = am.obtener(i);
            //c = ac.obtener(m.getCodigoCurso());
          //  System.out.println(c.getCodigoCurso()+ ":"+c.getNombres());
            
                for (int posCurso = 0; posCurso < ac.tamanio(); posCurso++) {
                    c = ac.buscar(m.getCodigoCurso());
                    if( codigoBusqueda == m.getCodigoAlumno()) {
                      System.err.println(c.getCodigoCurso()+ ":"+c.getNombres());
                      reporte.append(c.getCodigoCurso()+ ":"+c.getNombres() +"\n"); 
                      break;
                    }
                }
         
        }
        */
    }
	
}
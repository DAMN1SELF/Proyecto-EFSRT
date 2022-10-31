package guis.proyecto;

import arreglos.proyecto.ArregloSalas;
import clases.proyecto.Sala;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;

public class FormularioSala extends JDialog implements ActionListener, KeyListener, MouseListener {

    private JLabel lblImgSala;
    private JLabel lblCodigoSala;
    private JLabel lblCapacidad;
    private JLabel lblNombre;
    private JTextField txtCodigoSala;
    private JTextField txtCapacidad;
    private JTextField txtNombre;
    private JScrollPane scrollPane;
    private JButton btnAceptar;
    private JButton btnAdicionar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JTable tblSala;
    private DefaultTableModel modelo;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormularioSala dialog = new FormularioSala();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FormularioSala() {
        setResizable(false);
        setTitle("Mantenimiento | Sala");
        setBounds(100, 100, 710, 410);
        getContentPane().setLayout(null);

        lblImgSala = new JLabel();
        lblImgSala.setIcon(new ImageIcon("imagenes/dlgSala.png"));
        lblImgSala.setBounds(400, 10, 88, 89);
        getContentPane().add(lblImgSala);

        lblCodigoSala = new JLabel("C\u00F3digo");
        lblCodigoSala.setBounds(10, 10, 110, 23);
        getContentPane().add(lblCodigoSala);

        lblCapacidad = new JLabel("Capacidad");
        lblCapacidad.setBounds(10, 72, 70, 23);
        getContentPane().add(lblCapacidad);

        lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(10, 44, 70, 23);
        getContentPane().add(lblNombre);

        txtCodigoSala = new JTextField();
        txtCodigoSala.setBounds(90, 10, 85, 23);
        getContentPane().add(txtCodigoSala);
        txtCodigoSala.setColumns(10);

        txtCapacidad = new JTextField();
        txtCapacidad.setBounds(90, 72, 200, 23);
        getContentPane().add(txtCapacidad);
        txtCapacidad.setColumns(10);

        txtNombre = new JTextField();
        txtNombre.setBounds(90, 44, 200, 23);
        getContentPane().add(txtNombre);
        txtNombre.setColumns(10);

        btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(this);
        btnAceptar.addMouseListener(this);
        btnAceptar.setForeground(Color.BLUE);
        btnAceptar.setBounds(190, 10, 100, 23);
        getContentPane().add(btnAceptar);

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(this);
        btnAdicionar.addMouseListener(this);
        btnAdicionar.setForeground(Color.BLUE);
        btnAdicionar.setBounds(535, 10, 150, 23);
        getContentPane().add(btnAdicionar);

        btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(this);
        btnModificar.addMouseListener(this);
        btnModificar.setForeground(Color.BLUE);
        btnModificar.setBounds(535, 35, 150, 23);
        getContentPane().add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this);
        btnEliminar.addMouseListener(this);
        btnEliminar.setForeground(Color.BLUE);
        btnEliminar.setBounds(535, 60, 150, 23);
        getContentPane().add(btnEliminar);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 170, 675, 195);
        getContentPane().add(scrollPane);

        tblSala = new JTable();
        tblSala.addKeyListener(this);
        tblSala.addMouseListener(this);
        tblSala.setFillsViewportHeight(true);
        scrollPane.setViewportView(tblSala);

        modelo = new DefaultTableModel();
        modelo.addColumn("CODIGO");
        modelo.addColumn("SALA");
        modelo.addColumn("AFORO");
        modelo.addColumn("ESTADO");
        tblSala.setModel(modelo);
        txtCodigoSala.setEditable(false);
        
        chk_Mantenimiento = new JCheckBox("En Mantenimiento");
        chk_Mantenimiento.setBounds(90, 102, 158, 23);
        getContentPane().add(chk_Mantenimiento);
        habilitarEntradas(false);
        ajustarAnchoColumnas();
        listar();
        editarFila();
    }

    ArregloSalas ap = new ArregloSalas();
    private JCheckBox chk_Mantenimiento;

    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == btnAceptar) {
            actionPerformedBtnAceptar(arg0);
        }
        if (arg0.getSource() == btnEliminar) {
            actionPerformedBtnEliminar(arg0);
        }
        if (arg0.getSource() == btnModificar) {
            actionPerformedBtnModificar(arg0);
        }
        if (arg0.getSource() == btnAdicionar) {
            actionPerformedBtnAdicionar(arg0);
        }
    }

    protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
        btnAdicionar.setEnabled(false);
        btnModificar.setEnabled(true);
        btnAceptar.setEnabled(true);
        limpieza();
        habilitarEntradas(true);
        txtNombre.requestFocus();
    }

    protected void actionPerformedBtnModificar(ActionEvent arg0) {
        btnAdicionar.setEnabled(true);
        btnModificar.setEnabled(false);
        if (ap.tamanio() == 0) {
            btnAceptar.setEnabled(false);
            habilitarEntradas(false);
            mensaje("No existen salas");
        } else {
            editarFila();
            btnAceptar.setEnabled(true);
            habilitarEntradas(true);
            txtNombre.requestFocus();
        }
    }

    protected void actionPerformedBtnEliminar(ActionEvent arg0) {
        btnAdicionar.setEnabled(true);
        btnModificar.setEnabled(true);
        btnAceptar.setEnabled(false);
        if (ap.tamanio() == 0)
            mensaje("No existen salas");
        else {
            editarFila();
            habilitarEntradas(false);
            int ok = confirmar("Desea eliminar el registro ?");
            if (ok == 0) {
                ap.eliminar(ap.buscar(Integer.parseInt(leer(txtCodigoSala))));
                listar();
                editarFila();
            }
        }
    }

    protected void actionPerformedBtnAceptar(ActionEvent arg0) {
        String codigoSala = leer(txtCodigoSala);
        String capacidadSala = leer(txtCapacidad);
        String nombreSala = leer(txtNombre);
        boolean estado=leer(chk_Mantenimiento);

     
        if (nombreSala.equals("")) {
            error("Ingrese Nombre de sala correcto", txtNombre);
            return;
        }
        if (capacidadSala.equals("")) {
            error("Ingrese Capacidad de sala correcto", txtCapacidad);
            return;
        }
   

        if (btnAdicionar.isEnabled() == false) {
            Sala nuevo = new Sala(
                    Integer.parseInt(codigoSala),
                    nombreSala,
                    Integer.parseInt(capacidadSala),
                    estado
                    );
            ap.adicionar(nuevo);
            btnAdicionar.setEnabled(true);
        }
        if (btnModificar.isEnabled() == false) {
            Sala p = ap.buscar(Integer.parseInt(codigoSala));
            p.setNombreSala(nombreSala);
            p.setAforo(Integer.parseInt(capacidadSala));
            p.setEnMantenimiento(estado);
            ap.actualizarArchivo();
            btnModificar.setEnabled(true);
        }
        listar();
        habilitarEntradas(false);
    }

    public void keyPressed(KeyEvent arg0) {
    }

    public void keyReleased(KeyEvent arg0) {
        arg0.consume();
        editarFila();
    }

    public void keyTyped(KeyEvent arg0) {
    }

    public void mouseClicked(MouseEvent arg0) {
        if (arg0.getSource() == tblSala) {
            mouseClickedTblSala(arg0);
        }
    }

    public void mouseEntered(MouseEvent arg0) {
        if (arg0.getSource() == btnAceptar) {
            mouseEnteredBtnAceptar(arg0);
        }
        if (arg0.getSource() == btnEliminar) {
            mouseEnteredBtnEliminar(arg0);
        }
        if (arg0.getSource() == btnModificar) {
            mouseEnteredBtnModificar(arg0);
        }
        if (arg0.getSource() == btnAdicionar) {
            mouseEnteredBtnAdicionar(arg0);
        }
        if (arg0.getSource() == tblSala) {
            mouseEnteredTblSala(arg0);
        }
    }

    protected void mouseClickedTblSala(MouseEvent arg0) {
        habilitarEntradas(false);
        habilitarBotones(true);
        editarFila();
    }

    protected void mouseEnteredTblSala(MouseEvent arg0) {
        tblSala.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    protected void mouseEnteredBtnAdicionar(MouseEvent arg0) {
        btnAdicionar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    protected void mouseEnteredBtnModificar(MouseEvent arg0) {
        btnModificar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    protected void mouseEnteredBtnEliminar(MouseEvent arg0) {
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    protected void mouseEnteredBtnAceptar(MouseEvent arg0) {
        btnAceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    void ajustarAnchoColumnas() {
        TableColumnModel tcm = tblSala.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(anchoColumna(10));
        tcm.getColumn(1).setPreferredWidth(anchoColumna(40));
        tcm.getColumn(2).setPreferredWidth(anchoColumna(30));
        tcm.getColumn(3).setPreferredWidth(anchoColumna(40));
    }

    void listar() {
        int posFila = 0;
        if (modelo.getRowCount() > 0)
            posFila = tblSala.getSelectedRow();
        if (modelo.getRowCount() == ap.tamanio() - 1)
            posFila = ap.tamanio() - 1;
        if (posFila == ap.tamanio())
            posFila--;
        modelo.setRowCount(0);
        Sala p;
        for (int i = 0; i < ap.tamanio(); i++) {
            p = ap.obtener(i);
            Object[] fila = {
                    p.getCodigoSala(),
                    p.getNombreSala(),
                    p.getAforo() + " PERSONAS",
                   (p.getEnMantenimiento()==true)?"EN MANTENIMIENTO":"HABILITADO"
            };
            modelo.addRow(fila);
        }
        if (ap.tamanio() > 0)
            tblSala.getSelectionModel().setSelectionInterval(posFila, posFila);
    }

    void editarFila() {
        if (ap.tamanio() == 0)
            limpieza();
        else {
            Sala p = ap.obtener(tblSala.getSelectedRow());
            txtCodigoSala.setText( p.getCodigoSala()+"");
            txtCapacidad.setText(p.getAforo() + "");
            txtNombre.setText(p.getNombreSala());
            
            if (p.getEnMantenimiento()==true) {
                chk_Mantenimiento.setSelected(true);
            }else {
                chk_Mantenimiento.setSelected(false);
            }
           
            
        }
    }

    void limpieza() {
        txtCodigoSala.setText("" + ap.codigoCorrelativo());
        txtCapacidad.setText("");
        txtNombre.setText("");
        chk_Mantenimiento.setSelected(false);
    }

    void mensaje(String s) {
        JOptionPane.showMessageDialog(this, s, "Información", 0);
    }

    void error(String s, JTextField txt) {
        mensaje(s);
        txt.setText("");
        txt.requestFocus();
    }

    void habilitarEntradas(boolean sino) {
        btnAceptar.setEnabled(sino);
        txtCapacidad.setEditable(sino);
        txtNombre.setEditable(sino);
    }

    void habilitarBotones(boolean sino) {
        btnAdicionar.setEnabled(sino);
        btnModificar.setEnabled(sino);
    }

    String leer(JTextField tf) {

        return tf.getText().trim();
    }
    boolean leer(JCheckBox chk) {

     return chk.isSelected();
    }
    String leerApellidos() {
        return txtNombre.getText().trim();
    }

    int anchoColumna(int porcentaje) {
        return porcentaje * scrollPane.getWidth() / 100;
    }

    int confirmar(String s) {
        return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
}
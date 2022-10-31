package guis.proyecto;

import arreglos.proyecto.ArregloCategorias;
import arreglos.proyecto.ArregloProductos;
import clases.proyecto.Categoria;
import clases.proyecto.Producto;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
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
import javax.swing.JComboBox;

public class FormularioProducto extends JDialog implements ActionListener, KeyListener, MouseListener {
    
    private JLabel lblImgProducto;
    private JLabel lblCodigoProducto;
    private JLabel lblCapacidad;
    private JLabel lblNombre;
    private JTextField txtCodigoProducto;
    private JTextField txtStock;
    private JTextField txtNombre;
    private JScrollPane scrollPane;
    private JButton btnAceptar;
    private JButton btnAdicionar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JTable tblProducto;
    private DefaultTableModel modelo;
    private JComboBox<String> cboCategoria;
    private JLabel lblUnidadMed;
    private JComboBox<String> cboUnidadMED;
    private JLabel lblCategoria;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FormularioProducto dialog = new FormularioProducto();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public FormularioProducto() {
        setResizable(false);
        setTitle("Mantenimiento | Producto");
        setBounds(100, 100, 710, 410);
        getContentPane().setLayout(null);

        lblImgProducto = new JLabel();
        lblImgProducto.setIcon(new ImageIcon("imagenes/dlgProducto.png"));
        lblImgProducto.setBounds(400, 10, 88, 89);
        getContentPane().add(lblImgProducto);

        lblCodigoProducto = new JLabel("C\u00F3digo");
        lblCodigoProducto.setBounds(10, 10, 110, 23);
        getContentPane().add(lblCodigoProducto);

        lblCapacidad = new JLabel("Stock");
        lblCapacidad.setBounds(10, 76, 70, 23);
        getContentPane().add(lblCapacidad);

        lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(10, 44, 70, 23);
        getContentPane().add(lblNombre);

        txtCodigoProducto = new JTextField();
        txtCodigoProducto.setBounds(90, 10, 85, 23);
        getContentPane().add(txtCodigoProducto);
        txtCodigoProducto.setColumns(10);

        txtStock = new JTextField();
        txtStock.setBounds(90, 74, 200, 23);
        getContentPane().add(txtStock);
        txtStock.setColumns(10);

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

        tblProducto = new JTable();
        tblProducto.addKeyListener(this);
        tblProducto.addMouseListener(this);
        tblProducto.setFillsViewportHeight(true);
        scrollPane.setViewportView(tblProducto);

        modelo = new DefaultTableModel();
        modelo.addColumn("CODIGO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("STOCK");
        modelo.addColumn("UNIDAD");
        modelo.addColumn("COD CATEGORIA");
       // modelo.addColumn("CATEGORIA");
        tblProducto.setModel(modelo);
        txtCodigoProducto.setEditable(false);
        
        cboCategoria = new JComboBox<String>();
        cboCategoria.setBounds(90, 136, 200, 23);
        getContentPane().add(cboCategoria);
        
        lblUnidadMed = new JLabel("Unidad Med.");
        lblUnidadMed.setBounds(10, 102, 70, 23);
        getContentPane().add(lblUnidadMed);
        
        cboUnidadMED = new JComboBox<String>();
        cboUnidadMED.setBounds(90, 108, 200, 23);
        getContentPane().add(cboUnidadMED);
        cboUnidadMED.setModel(new DefaultComboBoxModel(UnidadMedida.values()));
        
        lblCategoria = new JLabel("Categoria");
        lblCategoria.setBounds(10, 136, 70, 23);
        getContentPane().add(lblCategoria);
        listarCategorias();
        habilitarEntradas(false);
        ajustarAnchoColumnas();
        listar();
        editarFila();
       
    }

    private void listarCategorias() {
        ArregloCategorias ac = new ArregloCategorias();
        Categoria c;
        for (int i=0; i<ac.tamanio(); i++) {
            c = ac.obtener(i);
            cboCategoria.addItem(c.getNombreCategoria());
        } 
    }

    ArregloProductos ap = new ArregloProductos();


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
        txtStock.requestFocus();
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
            txtStock.requestFocus();
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
                ap.eliminar(ap.buscar(Integer.parseInt(leer(txtCodigoProducto))));
                listar();
                editarFila();
            }
        }
    }

    protected void actionPerformedBtnAceptar(ActionEvent arg0) {
        String codigoProducto = leer(txtCodigoProducto);
        String capacidadProducto = leer(txtStock);
        String nombreProducto = leer(txtNombre);
        int unidadMedida=cboUnidadMED.getSelectedIndex();
        int codigoCategoria=cboCategoria.getSelectedIndex();
        
        if (codigoProducto.equals("")) {
            error("Ingrese Codigo de sala correcto", txtCodigoProducto);
            return;
        }
        if (nombreProducto.equals("")) {
            error("Ingrese Nombre de sala correcto", txtNombre);
            return;
        }if (capacidadProducto.equals("")) {
            error("Ingrese Capacidad de sala correcto", txtStock);
            return;
        }
     
        //GUARDAR EN EL TXT 
        if (btnAdicionar.isEnabled() == false) {
            Producto nuevo = new Producto(
                    Integer.parseInt(codigoProducto),
                    nombreProducto,
                    Integer.parseInt(capacidadProducto),
                    unidadMedida,
                    codigoCategoria);
            ap.adicionar(nuevo);
            btnAdicionar.setEnabled(true);
        }
        //MODIFICAR EN EL TXT
        else if  (btnModificar.isEnabled() == false) {
            Producto p = ap.buscar(Integer.parseInt(codigoProducto));
            p.setNombreProducto(nombreProducto);
            p.setStockProducto(Integer.parseInt(capacidadProducto));
            p.setUnidadMedida(unidadMedida);
            p.setCodigoCategoria(codigoCategoria);
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
        if (arg0.getSource() == tblProducto) {
            mouseClickedTblProducto(arg0);
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
        if (arg0.getSource() == tblProducto) {
            mouseEnteredTblProducto(arg0);
        }
    }

    protected void mouseClickedTblProducto(MouseEvent arg0) {
        habilitarEntradas(false);
        habilitarBotones(true);
        editarFila();
    }

    protected void mouseEnteredTblProducto(MouseEvent arg0) {
        tblProducto.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
        TableColumnModel tcm = tblProducto.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(anchoColumna(10));
        tcm.getColumn(1).setPreferredWidth(anchoColumna(40));
        tcm.getColumn(2).setPreferredWidth(anchoColumna(40));
        tcm.getColumn(3).setPreferredWidth(anchoColumna(40));
        tcm.getColumn(4).setPreferredWidth(anchoColumna(40));
    }

    void listar() {
        int posFila = 0;
        if (modelo.getRowCount() > 0)
            posFila = tblProducto.getSelectedRow();
        if (modelo.getRowCount() == ap.tamanio() - 1)
            posFila = ap.tamanio() - 1;
        if (posFila == ap.tamanio())
            posFila--;
        modelo.setRowCount(0);
        Producto p;

        ArregloCategorias ac = new ArregloCategorias();
        Categoria c;
        for (int i = 0; i < ap.tamanio(); i++) {
            p = ap.obtener(i);
            c= ac.obtener(p.getCodigoCategoria());
           
            
            
            Object[] fila = {
                    p.getCodigoProducto(),
                    p.getNombreProducto(),
                    p.getStockProducto(),
                    UnidadMedida.values()[p.getUnidadMedida()],
                    c.getNombreCategoria()
            };
            modelo.addRow(fila);
        }
        if (ap.tamanio() > 0)
            tblProducto.getSelectionModel().setSelectionInterval(posFila, posFila);
    }

    void editarFila() {
        if (ap.tamanio() == 0)
            limpieza();
        else {
            Producto p = ap.obtener(tblProducto.getSelectedRow());
            txtCodigoProducto.setText( p.getCodigoProducto()+"");
            txtStock.setText(p.getStockProducto() + "");
            txtNombre.setText(p.getNombreProducto());
            cboCategoria.setSelectedIndex(p.getCodigoCategoria());
            cboUnidadMED.setSelectedIndex(p.getUnidadMedida());
            
        }
    }

    void limpieza() {
        txtCodigoProducto.setText("" + ap.codigoCorrelativo());
        txtStock.setText("");
        txtNombre.setText("");
        cboCategoria.setSelectedIndex(0);
        cboUnidadMED.setSelectedIndex(0);
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
        txtStock.setEditable(sino);
        txtNombre.setEditable(sino);
    }

    //OBTENER LOS DATOS DEL FORMULARIO Y SI ES NECESARIO CONVERTIR A OTRO TIPO DE DATO
    void habilitarBotones(boolean sino) {
        btnAdicionar.setEnabled(sino);
        btnModificar.setEnabled(sino);
    }

    String leer(JTextField tf) {

        return tf.getText().trim();
    }

    int leerEnteros(JTextField tf) {
        return Integer.parseInt(tf.getText().trim()) ;
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
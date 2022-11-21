package guis.proyecto;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import arreglos.proyecto.ArregloUsuarios;
import clases.proyecto.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FormularioUsuarios extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextField txtCodigo;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtApellidos2;
	private JButton btnAceptar;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private JTable tblUsuario;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioUsuarios frame = new FormularioUsuarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormularioUsuarios() {
		setTitle("Mantenimiento| Usuarios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("Codigo");
		lblNewLabel.setBounds(10, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Nombres");
		lblNewLabel_1.setBounds(10, 36, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Apellidos");
		lblNewLabel_2.setBounds(10, 61, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Telefono");
		lblNewLabel_3.setBounds(10, 86, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Cargo");
		lblNewLabel_4.setBounds(10, 111, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(66, 8, 80, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombres = new JTextField();
		txtNombres.setBounds(66, 33, 139, 20);
		contentPane.add(txtNombres);
		txtNombres.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(66, 58, 139, 20);
		contentPane.add(txtApellidos);
		txtApellidos.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(66, 83, 139, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtApellidos2 = new JTextField();
		txtApellidos2.setBounds(230, 58, 139, 20);
		contentPane.add(txtApellidos2);
		txtApellidos2.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.setBounds(175, 7, 89, 23);
		contentPane.add(btnAceptar);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.setBounds(418, 7, 89, 23);
		contentPane.add(btnAdicionar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(418, 52, 89, 23);
		contentPane.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(418, 102, 89, 23);
		contentPane.add(btnEliminar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 140, 524, 110);
		contentPane.add(scrollPane);
		
		tblUsuario = new JTable();
		tblUsuario.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblUsuario);
		
		modelo = new DefaultTableModel();
		modelo.addColumn("CÓDIGO");
		modelo.addColumn("NOMBRES");
		modelo.addColumn("APELLIDOS");
		modelo.addColumn("TELÉFONO");
		modelo.addColumn("Cargo");
		tblUsuario.setModel(modelo);
txtCodigo.setEditable(false);
cboCargo = new JComboBox();
cboCargo.setModel(new DefaultComboBoxModel(new String[] {"EMPLEADO", "JEFE"}));
cboCargo.setBounds(66, 107, 139, 22);
contentPane.add(cboCargo);
		
		habilitarEntradas(false);
		ajustarAnchoColumnas();
		listar();
		editarFila();
	}
//  Declaración global
	ArregloUsuarios ap = new ArregloUsuarios();
	private JComboBox cboCargo;
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(e);
		}
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnModificar) {
			actionPerformedBtnModificar(e);
		}
		if (e.getSource() == btnAceptar) {
			actionPerformedBtnAceptar(e);
		}
	}
	protected void actionPerformedBtnAceptar(ActionEvent e) {
		int codigoUsuario = leerCodigoUsuario();
		String nombres = leerNombres();
		if (nombres.length() > 0) {
			String apellidos = leerApellidos();
			if (apellidos.length() > 0) {
				String telefono = leerTelefono();
				if (telefono.length() > 0) {
					int  cargo = leerCargo();
					 {
						if (btnAdicionar.isEnabled() == false) {
							Usuario nuevo = new Usuario(codigoUsuario, nombres, apellidos, telefono, cargo);
							ap.adicionar(nuevo);
							btnAdicionar.setEnabled(true);
						}	
						if (btnModificar.isEnabled() == false) {
							Usuario p = ap.buscar(codigoUsuario);
							p.setNombres(nombres);
							p.setApellidos(apellidos);
							p.setTelefono(telefono);
							p.setCargo(cargo);
							ap.actualizarArchivo();
							btnModificar.setEnabled(true);
						}
						listar();
						habilitarEntradas(false);
					}
					
				}
				else
					error("Ingrese TELÉFONO correcto", txtTelefono);		
			}
			else
				error("ingrese APELLIDOS correctos", txtApellidos);
		}
		else
			error("ingrese NOMBRES correctos", txtNombres);		
	}
	protected void actionPerformedBtnModificar(ActionEvent e) {
		btnAdicionar.setEnabled(true);
		btnModificar.setEnabled(false);
		if (ap.tamanio() == 0) {
			btnAceptar.setEnabled(false);
			habilitarEntradas(false);
			mensaje("No existen Usuarios");	
		}
		else {
			editarFila();
			btnAceptar.setEnabled(true);
			habilitarEntradas(true);
			txtNombres.requestFocus();
		}
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		btnAdicionar.setEnabled(true);
		btnModificar.setEnabled(true);
		btnAceptar.setEnabled(false);
		if (ap.tamanio() == 0)
			mensaje("No existen Usuarios");
		else {
			editarFila();
			habilitarEntradas(false);
			int ok = confirmar("¿ Desea eliminar el registro ?");
			if (ok == 0) {
				ap.eliminar(ap.buscar(leerCodigoUsuario()));
				listar();
				editarFila();
			}
		}
	}
	protected void actionPerformedBtnAdicionar(ActionEvent e) {
		btnAdicionar.setEnabled(false);
		btnModificar.setEnabled(true);
		btnAceptar.setEnabled(true);
		limpieza();
		habilitarEntradas(true);
		txtNombres.requestFocus();
	}
	
	
	void listar() {
		int posFila = 0;
		if (modelo.getRowCount() > 0)
			posFila = tblUsuario.getSelectedRow();
		if (modelo.getRowCount() == ap.tamanio() - 1)
			posFila = ap.tamanio() - 1;
		if (posFila == ap.tamanio())
			posFila --;
		modelo.setRowCount(0);
		Usuario p;
		for (int i=0; i<ap.tamanio(); i++) {
			p = ap.obtener(i);
			Object[] fila = { p.getCodigoUsuario(),
					          p.getNombres(),
					          p.getApellidos(),
					          p.getTelefono(),
					          p.getCargo() };
			modelo.addRow(fila);
		}
		if (ap.tamanio() > 0)
			tblUsuario.getSelectionModel().setSelectionInterval(posFila, posFila);
	}
	
	void editarFila() {
		if (ap.tamanio() == 0)
			limpieza();
		else {
			Usuario p = ap.obtener(tblUsuario.getSelectedRow());
			txtCodigo.setText("" + p.getCodigoUsuario());
			txtNombres.setText(p.getNombres());
			txtApellidos.setText(p.getApellidos());
			txtTelefono.setText(p.getTelefono());
			cboCargo.setSelectedIndex(p.getCargo());
	}
	}
	void limpieza() {
		txtCodigo.setText("" + ap.codigoCorrelativo());
		txtNombres.setText("");
		txtApellidos.setText("");
		txtApellidos2.setText("");
		txtTelefono.setText("");
		cboCargo.setSelectedIndex(0);
	}
	
//  Métodos tipo void (con parámetros)
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
		txtNombres.setEditable(sino);
		txtApellidos.setEditable(sino);
		txtTelefono.setEditable(sino);
		txtApellidos2.setEditable(sino);
	}
	void habilitarBotones(boolean sino) {
		btnAdicionar.setEnabled(sino);
		btnModificar.setEnabled(sino);
	}
//  Métodos tipo void (sin parámetros)
	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblUsuario.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(10));  // codigoPaciente
		tcm.getColumn(1).setPreferredWidth(anchoColumna(30));  // nombres
		tcm.getColumn(2).setPreferredWidth(anchoColumna(30));  // apellidos
		tcm.getColumn(3).setPreferredWidth(anchoColumna(15));  // teléfono
		tcm.getColumn(4).setPreferredWidth(anchoColumna(15));  // dni
	}
//  Métodos que retornan valor (sin parámetros)
	int leerCodigoUsuario() {
		return Integer.parseInt(txtCodigo.getText().trim());
	}
	String leerNombres() {
		return txtNombres.getText().trim();
	}
	String leerApellidos() {
		return txtApellidos.getText().trim();
	}
	String leerTelefono() {
		return txtTelefono.getText().trim();
	}
	 int leerCargo() {
	        return cboCargo.getSelectedIndex();
	    }
	//  Métodos que retornan valor (con parámetros)
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
	
	
}

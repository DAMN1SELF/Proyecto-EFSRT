package guis.proyecto;

import arreglos.proyecto.ArregloIngresos;
import arreglos.proyecto.ArregloProductos;
import arreglos.proyecto.ArregloSalas;
import arreglos.proyecto.ArregloUsuarios;
import clases.proyecto.Ingreso;
import clases.proyecto.Producto;
import clases.proyecto.Usuario;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class FormularioIngresos extends JDialog implements ActionListener, KeyListener, MouseListener, ItemListener {
	private JLabel lblcodigoIngreso;
	private JTextField txtcodigoIngreso;
	private JScrollPane scrollPane;
	private JButton btnAceptar;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JTable tblReserva;
	private DefaultTableModel modelo;
	private JLabel lblCategoria;
	SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	ArregloIngresos ai = new ArregloIngresos();
	ArregloSalas ap2 = new ArregloSalas();
	private JDateChooser fechaRegistro;
	private JLabel lblFechaIngreso;
	private JTextField txtCantidad;
	private JLabel lblNombre_1;
	private JTextField txtStockActual;
	private JLabel lblNombre_2;
	private JTextArea txtObservacion;
	private JButton btnBuscar_1;
	private JTextField txtBusqueda;
	private JTextField txtProducto;
	private JLabel lblProducto;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioIngresos dialog = new FormularioIngresos();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormularioIngresos() {
		setResizable(false);
		setTitle("Almacen | Ingresos");
		setBounds(100, 100, 721, 499);
		getContentPane().setLayout(null);

		txtStockActual = new JTextField();
		txtStockActual.setEditable(false);
		txtStockActual.setColumns(10);
		txtStockActual.setBounds(309, 73, 78, 23);
		getContentPane().add(txtStockActual);

		lblcodigoIngreso = new JLabel("C\u00F3digo");
		lblcodigoIngreso.setBounds(10, 10, 110, 23);
		getContentPane().add(lblcodigoIngreso);

		txtcodigoIngreso = new JTextField();
		txtcodigoIngreso.setBounds(90, 10, 85, 23);
		getContentPane().add(txtcodigoIngreso);
		txtcodigoIngreso.setColumns(10);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.addMouseListener(this);
		btnAceptar.setForeground(Color.BLUE);
		btnAceptar.setBounds(191, 10, 100, 23);
		getContentPane().add(btnAceptar);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.addMouseListener(this);
		btnAdicionar.setForeground(Color.BLUE);
		btnAdicionar.setBounds(536, 106, 150, 29);
		getContentPane().add(btnAdicionar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.addMouseListener(this);
		btnModificar.setForeground(Color.BLUE);
		btnModificar.setBounds(536, 140, 150, 29);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.addMouseListener(this);
		btnEliminar.setForeground(Color.BLUE);
		btnEliminar.setBounds(536, 170, 150, 29);
		getContentPane().add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 210, 675, 231);
		getContentPane().add(scrollPane);

		tblReserva = new JTable();
		tblReserva.addKeyListener(this);
		tblReserva.addMouseListener(this);
		tblReserva.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblReserva);

		modelo = new DefaultTableModel();
		modelo.addColumn("CODIGO");
		modelo.addColumn("PRODUCTO");
		modelo.addColumn("FECHA");
		modelo.addColumn("CANTIDAD");
		tblReserva.setModel(modelo);
		txtcodigoIngreso.setEditable(false);

		lblCategoria = new JLabel("Observacion");
		lblCategoria.setBounds(10, 136, 85, 23);
		getContentPane().add(lblCategoria);

		fechaRegistro = new JDateChooser();
		fechaRegistro.setDateFormatString("dd/MM/YYYY");
		fechaRegistro.setBounds(466, 10, 137, 20);
		Date date = new Date();
		fechaRegistro.setDate(date);
		getContentPane().add(fechaRegistro);

		lblFechaIngreso = new JLabel("Fecha Salida");
		lblFechaIngreso.setBounds(332, 10, 110, 23);
		getContentPane().add(lblFechaIngreso);

		txtCantidad = new JTextField();
		txtCantidad.setColumns(10);
		txtCantidad.setBounds(90, 73, 85, 23);
		getContentPane().add(txtCantidad);

		lblNombre_1 = new JLabel("Cantidad");
		lblNombre_1.setBounds(10, 73, 70, 23);
		getContentPane().add(lblNombre_1);

		lblNombre_2 = new JLabel("Stock Actual");
		lblNombre_2.setBounds(201, 73, 70, 23);
		getContentPane().add(lblNombre_2);

		txtObservacion = new JTextArea();
		txtObservacion.setBounds(90, 108, 436, 90);
		getContentPane().add(txtObservacion);

		btnBuscar_1 = new JButton("Buscar");
		btnBuscar_1.addActionListener(this);
		btnBuscar_1.setForeground(Color.BLUE);
		btnBuscar_1.setBounds(191, 41, 100, 23);
		getContentPane().add(btnBuscar_1);

		txtBusqueda = new JTextField();
		txtBusqueda.setColumns(10);
		txtBusqueda.setBounds(90, 41, 85, 23);
		getContentPane().add(txtBusqueda);

		txtProducto = new JTextField();
		txtProducto.setEditable(false);
		txtProducto.setColumns(10);
		txtProducto.setBounds(309, 41, 297, 23);
		getContentPane().add(txtProducto);

		lblProducto = new JLabel("Producto");
		lblProducto.setBounds(10, 41, 70, 23);
		getContentPane().add(lblProducto);

		ajustarAnchoColumnas();

		habilitarEntradas(false);
		listar();
		editarFila();

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBuscar_1) {
			actionPerformedBtnBuscar_1(arg0);
		}
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
	}

	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		btnAdicionar.setEnabled(true);
		btnModificar.setEnabled(false);
		if (ai.tamanio() == 0) {
			btnAceptar.setEnabled(false);
			habilitarEntradas(false);
			mensaje("No existen salas");
		} else {
			editarFila();
			btnAceptar.setEnabled(true);
			habilitarEntradas(true);
		}
	}

	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		btnAdicionar.setEnabled(true);
		btnModificar.setEnabled(true);
		btnAceptar.setEnabled(false);
		if (ai.tamanio() == 0)
			mensaje("No existen salas");
		else {
			editarFila();
			habilitarEntradas(false);
			int ok = confirmar("Desea eliminar el registro ?");
			if (ok == 0) {
				ai.eliminar(ai.buscar(Integer.parseInt(leer(txtcodigoIngreso))));
				listar();
				editarFila();
			}
		}
	}

	protected void actionPerformedBtnAceptar(ActionEvent arg0) {
		String codigoIngreso = leer(txtcodigoIngreso);

		Date fechaReserva = leer(fechaRegistro);
		if (fechaReserva == null) {
			error("Seleccionar una fecha de reserva", fechaRegistro);
			return;
		}

		String codProducto = leer(txtBusqueda);
		if (codProducto.equals("") || txtProducto.getText().trim().equals("")) {
			error("Buscar un Producto registrado.", txtBusqueda);
			return;
		}

		int cantidad = leerNumero();
		if (cantidad < 0) {
			txtCantidad.setText("");
			error("Ingresar una cantidad valida", txtCantidad);
			return;
		}
		String observacion = txtObservacion.getText().trim();
		

		if (btnAdicionar.isEnabled() == false) {
			Ingreso nuevo = new Ingreso(Integer.parseInt(codigoIngreso), Integer.parseInt(codProducto), fechaReserva,
					cantidad, observacion);
			ai.adicionar(nuevo);
			btnAdicionar.setEnabled(true);

		} else if (btnModificar.isEnabled() == false) {
			Ingreso p = ai.buscar(Integer.parseInt(codigoIngreso));
			p.setObservacion(observacion);
			p.setCodProducto(Integer.parseInt(codProducto));
			p.setCantidad(cantidad);
			//p.setFechaIngreso(fechaReserva);

			ai.actualizarArchivo();
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
		if (arg0.getSource() == tblReserva) {
			mouseClickedTblReserva(arg0);
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
		if (arg0.getSource() == tblReserva) {
			mouseEnteredTblReserva(arg0);
		}
	}

	protected void mouseClickedTblReserva(MouseEvent arg0) {
		habilitarEntradas(false);
		habilitarBotones(true);
		editarFila();
	}

	protected void mouseEnteredTblReserva(MouseEvent arg0) {
		tblReserva.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
		TableColumnModel tcm = tblReserva.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(5));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(25));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(10));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(10));
	}

	void listar() {
		try {

			int posFila = 0;
			if (modelo.getRowCount() > 0)
				posFila = tblReserva.getSelectedRow();
			if (modelo.getRowCount() == ai.tamanio() - 1)
				posFila = ai.tamanio() - 1;
			if (posFila == ai.tamanio())
				posFila--;
			modelo.setRowCount(0);

			ArregloProductos as = new ArregloProductos();
			Ingreso in;
			Producto p;
			for (int i = 0; i < ai.tamanio(); i++) {
				in = ai.obtener(i);
				p = as.buscar(in.getCodProducto());
				
				Object[] fila = {
						in.getCodIngreso(),
						p.getCodigoProducto() + "-" + p.getNombreProducto(),
						formatoFecha.format(in.getFechaIngreso()), 
						in.getCantidad() };
				modelo.addRow(fila);
			}
			if (ai.tamanio() > 0)
				tblReserva.getSelectionModel().setSelectionInterval(posFila, posFila);
		} catch (Exception e) {
			System.out.println("listar " + e.getMessage());
		}
	}

	void editarFila() {
		if (ai.tamanio() == 0)
			limpieza();
		else {

			Ingreso p = ai.obtener(tblReserva.getSelectedRow());
			txtcodigoIngreso.setText(p.getCodIngreso() + "");
			txtBusqueda.setText(p.getCodProducto() + "");
			txtCantidad.setText(p.getCantidad() + "");
			txtObservacion.setText(p.getObservacion());
			fechaRegistro.setDate(p.getFechaIngreso());

		}
	}

	void limpieza() {
		txtcodigoIngreso.setText("" + ai.codigoCorrelativo());
		txtCantidad.setText("");
		txtBusqueda.setText("");
		Date date = new Date();
		fechaRegistro.setDate(date);
	}

	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Información", 0);
	}

	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}

	void error(String s, JDateChooser fec) {
		mensaje(s);
		fec.setDate(null);
		fec.requestFocus();
	}

	void error(String s, JComboBox<?> cbo) {
		mensaje(s);
		cbo.setSelectedIndex(0);
		cbo.requestFocus();
	}

	void habilitarEntradas(boolean sino) {
		btnAceptar.setEnabled(sino);
	}

	void habilitarBotones(boolean sino) {
		btnAdicionar.setEnabled(sino);
		btnModificar.setEnabled(sino);
	}

	int leerNumero() {

		try {
			String valor = txtCantidad.getText().trim();
			Pattern pat = Pattern.compile("[0-9]*");
			Matcher mat = pat.matcher(valor);
			if (mat.matches()) {
				return Integer.parseInt(valor);
			} else {
				return 0;
			}
		} catch (Exception e) {
			return -1;
		}
	}

	String leer(JTextField tf) {

		return tf.getText().trim();
	}

	Date leer(JDateChooser dato) {

		return dato.getDate();
	}

	String leerApellidos() {
		return txtBusqueda.getText().trim();
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}

	protected void actionPerformedBtnBuscar_1(ActionEvent arg0) {
		
		String valor = txtBusqueda.getText().trim();
		try {

			if (valor.equals("")) {
				mensaje("Ingresar un codigo de PRODUCTO");
				return;
			}

			ArregloProductos au = new ArregloProductos();
			Producto u = au.buscar(Integer.parseInt(valor));

			if (u == null) {
				txtProducto.setText("");
				mensaje("No se encontro registros con el codigo " + valor);
			} else {
				txtProducto.setText(u.getCodigoProducto() + " " + u.getNombreProducto());
				txtStockActual.setText(u.getStockProducto()+"");
			}
		} catch (Exception e) {
			mensaje("No se encontro registros con el codigo " + valor);
		}
	}
}
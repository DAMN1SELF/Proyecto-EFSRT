package guis.proyecto;

import arreglos.proyecto.ArregloReservas;
import arreglos.proyecto.ArregloSalas;
import arreglos.proyecto.ArregloUsuarios;
import clases.proyecto.Reserva;
import clases.proyecto.Sala;
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
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class FormularioReserva extends JDialog implements ActionListener, KeyListener, MouseListener, ItemListener {
	private JLabel lblCodigoReserva;
	private JLabel lblSala;
	private JLabel lblNombre;
	private JTextField txtCodigoReserva;
	private JTextField txtCodigoUsuario;
	private JScrollPane scrollPane;
	private JButton btnAceptar;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JTable tblReserva;
	private DefaultTableModel modelo;
	private JLabel lblCategoria;
	SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	ArregloReservas ap = new ArregloReservas();
	ArregloSalas ap2 = new ArregloSalas();
	private JComboBox<String> cboSala;
	private JTextField txtUsuario;
	private JButton btnBuscar;
	private JDateChooser fechaRegistro;
	private JLabel lblFechaReserva;
	private JLabel lblHoraInicio;
	private JLabel lblHoraFin;
	private JComboBox<String> cboHoraIni;
	private JComboBox<String> cboHoraFin;
	private JComboBox<String> cboMinIni;
	private JComboBox<String> cboMinFin;
	private JComboBox<String> cboZHIni;
	private JComboBox<String> cboZHFin;
	private JTextField txtNroAsistentes;
	private JLabel lblNombre_1;
	private JTextField txtAforo;
	private JLabel lblNombre_2;
	private JTextArea txtObservacion;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioReserva dialog = new FormularioReserva();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormularioReserva() {
		setResizable(false);
		setTitle("Mantenimiento | Reserva");
		setBounds(100, 100, 792, 491);
		getContentPane().setLayout(null);

		txtAforo = new JTextField();
		txtAforo.setEditable(false);
		txtAforo.setColumns(10);
		txtAforo.setBounds(232, 110, 78, 23);
		getContentPane().add(txtAforo);

		lblCodigoReserva = new JLabel("C\u00F3digo");
		lblCodigoReserva.setBounds(10, 10, 110, 23);
		getContentPane().add(lblCodigoReserva);

		lblSala = new JLabel("Sala");
		lblSala.setBounds(10, 76, 70, 23);
		getContentPane().add(lblSala);

		lblNombre = new JLabel("Solicitante");
		lblNombre.setBounds(10, 44, 70, 23);
		getContentPane().add(lblNombre);

		txtCodigoReserva = new JTextField();
		txtCodigoReserva.setBounds(90, 10, 85, 23);
		getContentPane().add(txtCodigoReserva);
		txtCodigoReserva.setColumns(10);

		txtCodigoUsuario = new JTextField();
		txtCodigoUsuario.setBounds(90, 44, 85, 23);
		getContentPane().add(txtCodigoUsuario);
		txtCodigoUsuario.setColumns(10);

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
		btnAdicionar.setBounds(616, 10, 150, 23);
		getContentPane().add(btnAdicionar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.addMouseListener(this);
		btnModificar.setForeground(Color.BLUE);
		btnModificar.setBounds(616, 44, 150, 23);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.addMouseListener(this);
		btnEliminar.setForeground(Color.BLUE);
		btnEliminar.setBounds(616, 76, 150, 23);
		getContentPane().add(btnEliminar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 247, 675, 194);
		getContentPane().add(scrollPane);

		tblReserva = new JTable();
		tblReserva.addKeyListener(this);
		tblReserva.addMouseListener(this);
		tblReserva.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblReserva);

		modelo = new DefaultTableModel();
		modelo.addColumn("CODIGO");
		modelo.addColumn("FEC.RESERVA");
		modelo.addColumn("HORA INICIO");
		modelo.addColumn("HORA FIN");
		modelo.addColumn("SALA");
		modelo.addColumn("SOLICITANTE");
		// modelo.addColumn("CATEGORIA");
		tblReserva.setModel(modelo);
		txtCodigoReserva.setEditable(false);

		lblCategoria = new JLabel("Observacion");
		lblCategoria.setBounds(10, 136, 85, 23);
		getContentPane().add(lblCategoria);

		cboSala = new JComboBox<String>();
		cboSala.addItemListener(this);
		cboSala.setBounds(90, 76, 220, 23);
		getContentPane().add(cboSala);
		listarSala();

		txtUsuario = new JTextField();
		txtUsuario.setEditable(false);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(309, 44, 297, 23);
		getContentPane().add(txtUsuario);

		fechaRegistro = new JDateChooser();
		fechaRegistro.setDateFormatString("dd/MM/YYYY");
		fechaRegistro.setBounds(466, 10, 137, 20);
		getContentPane().add(fechaRegistro);

		lblFechaReserva = new JLabel("Fecha Reserva");
		lblFechaReserva.setBounds(332, 10, 110, 23);
		getContentPane().add(lblFechaReserva);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setForeground(Color.BLUE);
		btnBuscar.setBounds(191, 44, 100, 23);
		getContentPane().add(btnBuscar);

		lblHoraInicio = new JLabel("Hora Inicio");
		lblHoraInicio.setBounds(339, 79, 78, 23);
		getContentPane().add(lblHoraInicio);

		lblHoraFin = new JLabel("Hora Fin");
		lblHoraFin.setBounds(344, 112, 78, 23);
		getContentPane().add(lblHoraFin);

		cboHoraIni = new JComboBox<String>();
		cboHoraIni.setModel(new DefaultComboBoxModel(
				new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		cboHoraIni.setBounds(422, 79, 54, 23);
		getContentPane().add(cboHoraIni);

		cboHoraFin = new JComboBox<String>();
		cboHoraFin.setModel(new DefaultComboBoxModel(
				new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
		cboHoraFin.setBounds(422, 112, 54, 23);
		getContentPane().add(cboHoraFin);

		cboMinIni = new JComboBox<String>();
		cboMinIni.setModel(new DefaultComboBoxModel(new String[] { "00", "15", "30", "45" }));
		cboMinIni.setBounds(485, 79, 54, 23);
		getContentPane().add(cboMinIni);

		cboMinFin = new JComboBox<String>();
		cboMinFin.setModel(new DefaultComboBoxModel(new String[] { "00", "15", "30", "45" }));
		cboMinFin.setBounds(485, 112, 54, 23);
		getContentPane().add(cboMinFin);

		cboZHIni = new JComboBox<String>();
		cboZHIni.setModel(new DefaultComboBoxModel(new String[] { "AM", "PM" }));
		cboZHIni.setBounds(549, 79, 54, 23);
		getContentPane().add(cboZHIni);

		cboZHFin = new JComboBox<String>();
		cboZHFin.setModel(new DefaultComboBoxModel(new String[] { "AM", "PM" }));
		cboZHFin.setBounds(549, 112, 54, 23);
		getContentPane().add(cboZHFin);

		txtNroAsistentes = new JTextField();
		txtNroAsistentes.setColumns(10);
		txtNroAsistentes.setBounds(105, 110, 70, 23);
		getContentPane().add(txtNroAsistentes);

		lblNombre_1 = new JLabel("Nro Asistentes");
		lblNombre_1.setBounds(10, 110, 85, 23);
		getContentPane().add(lblNombre_1);

		lblNombre_2 = new JLabel("Aforo");
		lblNombre_2.setBounds(181, 112, 36, 23);
		getContentPane().add(lblNombre_2);

		txtObservacion = new JTextArea();
		txtObservacion.setBounds(90, 146, 436, 90);
		getContentPane().add(txtObservacion);

		ajustarAnchoColumnas();
		
		  habilitarEntradas(false); 
		  listar();
		  editarFila();
		

	}


	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBuscar) {
			actionPerformedBtnBuscar(arg0);
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
		// txtStock.requestFocus();
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
			// txtStock.requestFocus();
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
				ap.eliminar(ap.buscar(Integer.parseInt(leer(txtCodigoReserva))));
				listar();
				editarFila();
			}
		}
	}

	protected void actionPerformedBtnAceptar(ActionEvent arg0) {
        String codigoReserva = leer(txtCodigoReserva);
        
        Date fechaReserva=leer(fechaRegistro);
        if (fechaReserva==null) {
        	error("Seleccionar una fecha de reserva", fechaRegistro);
             return;
        }
   
        
        String codigoSolicitante = leer(txtCodigoUsuario);
        
        if (codigoSolicitante.equals("") || txtUsuario.getText().trim().equals("") ) {
            error("Buscar un solicitante habilitado.", txtCodigoUsuario);
            return;
        }
        
        int codigoSala=leerCodigoComboSala();
        if(codigoSala==0) {
        	error("Seleccionar una Sala", cboSala);
            return;
        }
        
        int nroAsistentes=leerAsistentes();
        int aforo = Integer.parseInt(txtAforo.getText());
        if(nroAsistentes>aforo) {
        	error("Sobrepasa el aforo de " + aforo, cboSala);
            return;
        }
        
        
        String fechaInicio=cboHoraIni.getSelectedItem()+":"+cboMinIni.getSelectedItem()+" "+cboZHIni.getSelectedItem();
        String fechaFin=cboHoraFin.getSelectedItem()+":"+cboMinFin.getSelectedItem()+" "+cboZHFin.getSelectedItem();
        
        
        String observacion = txtObservacion.getText().trim();
        
        if (btnAdicionar.isEnabled() == false) {
            Reserva nuevo = new Reserva(
            Integer.parseInt(codigoReserva),
            fechaReserva,
            codigoSala,
            fechaInicio,
            fechaFin,
            nroAsistentes,
            Integer.parseInt(codigoSolicitante),
            observacion);                                        
            ap.adicionar(nuevo);
            btnAdicionar.setEnabled(true);
            
        
        } else if (btnModificar.isEnabled() == false) {
            
            Reserva p = ap.buscar(Integer.parseInt(codigoReserva));
            p.setCodigoReserva(Integer.parseInt(codigoReserva));
            p.setFechaReserva(fechaReserva);
            p.setCodigoSala(codigoSala);
            p.setFechaInicio(fechaInicio);
            p.setFechaFin(fechaFin);
            p.setNroAsistentes(nroAsistentes);
            p.setCodigoUsuarioReserva(Integer.parseInt(codigoSolicitante));
            p.setObservacion(observacion);
            
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
		tcm.getColumn(0).setPreferredWidth(anchoColumna(10));
		tcm.getColumn(1).setPreferredWidth(anchoColumna(15));
		tcm.getColumn(2).setPreferredWidth(anchoColumna(15));
		tcm.getColumn(3).setPreferredWidth(anchoColumna(15));
		tcm.getColumn(4).setPreferredWidth(anchoColumna(20));
		tcm.getColumn(5).setPreferredWidth(anchoColumna(20));
	}

	void listar() {
		try {

			int posFila = 0;
			if (modelo.getRowCount() > 0)
				posFila = tblReserva.getSelectedRow();
			if (modelo.getRowCount() == ap.tamanio() - 1)
				posFila = ap.tamanio() - 1;
			if (posFila == ap.tamanio())
				posFila--;
			modelo.setRowCount(0);
			
			ArregloSalas as= new ArregloSalas();
			ArregloUsuarios au = new ArregloUsuarios();
			Reserva p;
			Sala s;
			Usuario u;
			for (int i = 0; i < ap.tamanio(); i++) {
				p = ap.obtener(i);
				System.out.println(i);
				s= as.buscar(p.getCodigoSala());
				u=au.buscar(p.getCodigoUsuarioReserva());
				Object[] fila = { 
						p.getCodigoReserva(),
						formatoFecha.format(p.getFechaReserva()),
						p.getFechaInicio(),
						p.getFechaFin(),
						s.getCodigoSala() +"-"+ s.getNombreSala(),
						u.getCodigoUsuario()+"-"+ u.getNombres()+" "+u.getApellidos()};
				modelo.addRow(fila);
			}
			if (ap.tamanio() > 0)
				tblReserva.getSelectionModel().setSelectionInterval(posFila, posFila);
		} catch (Exception e) {
			System.out.println( "listar "+e.getMessage());
		}
		 
	}

	void editarFila() {
		if (ap.tamanio() == 0)
			limpieza();
		else {
			
			  Reserva p = ap.obtener(tblReserva.getSelectedRow());
			  txtCodigoReserva.setText( p.getCodigoReserva()+"");
			  txtCodigoUsuario.setText(p.getCodigoUsuarioReserva()+"");
			  txtNroAsistentes.setText(p.getNroAsistentes()+"");
			  txtObservacion.setText(p.getObservacion());
			  fechaRegistro.setDate(p.getFechaReserva());
			 
		}
	}

	void limpieza() {
		txtCodigoReserva.setText("" + ap.codigoCorrelativo());
		// txtStock.setText("");
		txtCodigoUsuario.setText("");
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

	int leerAsistentes() {

		try {
			String valor = txtNroAsistentes.getText().trim();
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
		return txtCodigoUsuario.getText().trim();
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

	private void listarSala() {
		ArregloSalas ac = new ArregloSalas();
		Sala c;
		cboSala.removeAllItems();
		cboSala.addItem("[SELECCIONAR CURSO]");
		for (int i = 0; i < ac.tamanio(); i++) {
			c = ac.obtener(i);

			if (c.getEnMantenimiento() == false)
				cboSala.addItem(c.getCodigoSala() + "-" + c.getNombreSala());
		}

	}

	protected void actionPerformedBtnBuscar(ActionEvent arg0) {
		String valor = txtCodigoUsuario.getText().trim();
		try {

			if (valor.equals("")) {
				mensaje("Ingresar un codigo de Solicitante");
				return;
			}

			ArregloUsuarios au = new ArregloUsuarios();
			Usuario u = au.buscar(Integer.parseInt(valor));

			if (u == null) {
				txtUsuario.setText("");
				mensaje("No se encontro registros con el codigo " + valor);
			} else {
				txtUsuario.setText(u.getNombres() + " " + u.getApellidos());
			}
		} catch (Exception e) {
			mensaje("No se encontro registros con el codigo " + valor);
		}

	}

	int leerCodigoComboSala() {
		String valorSeleccionado = (String) cboSala.getSelectedItem();

		if (valorSeleccionado == "[SELECCIONAR SEDE]") {
			return 0;
		} else {
			String codigo = valorSeleccionado.substring(0, valorSeleccionado.lastIndexOf("-"));
			return Integer.parseInt(codigo);
		}

	}

	public void itemStateChanged(ItemEvent e) {
		try {
			String valorSeleccionado = (String) cboSala.getSelectedItem();
			String codigo = valorSeleccionado.substring(0, valorSeleccionado.lastIndexOf("-"));
			System.out.println(codigo);
			ArregloSalas as = new ArregloSalas();
			Sala s = as.buscar(Integer.parseInt(codigo));
			if (s != null) {
				txtAforo.setText(s.getAforo() + "");
			} else {
				txtAforo.setText("0");
			}
		} catch (Exception e2) {
			txtAforo.setText("0");
		}
	}

}
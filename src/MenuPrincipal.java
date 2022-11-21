
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import guis.proyecto.FormularioIngresos;
import guis.proyecto.FormularioProducto;
import guis.proyecto.FormularioReporteProductos;
import guis.proyecto.FormularioReporteSalas;
import guis.proyecto.FormularioReserva;
import guis.proyecto.FormularioSala;
import guis.proyecto.FormularioSalida;
import guis.proyecto.FormularioUsuarios;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

@SuppressWarnings("serial")
public class MenuPrincipal extends JFrame implements ActionListener, AncestorListener {

	private JLabel lblFondo;
	private JMenuBar menuProyecto;
	private JMenu mnReporte;
	private JPanel contentPane;
	private JMenu mnMantenimiento_1;
	private JMenuItem mntm_1;
	private JMenu mnRegistro_1;
	private JMenuItem mntmReserva;
	private JMenu mnAlmacen;
	private JMenuItem mntmEntradas;
	private JMenuItem mntmSalidas;
	private JMenuItem mntm;
	private JMenuItem mntmEmpleados;
	private JMenuItem mntmConsumoPorSala;
	private JMenuItem mntmConsultarStock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
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
	public MenuPrincipal() {
		int ANCHO = 750, ALTO = 500, DX = 16, DY = 62;

		setResizable(false);
		setTitle("Proyecto EFSRT");
		setIconImage(new ImageIcon("imagenes/PrimaTaxi.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(ANCHO + DX, ALTO + DY);
		this.setLocationRelativeTo(null);

		menuProyecto = new JMenuBar();
		setJMenuBar(menuProyecto);

		mnMantenimiento_1 = new JMenu("Mantenimiento");
		menuProyecto.add(mnMantenimiento_1);

		mntm_1 = new JMenuItem("Salas");
		mntm_1.addActionListener(this);
		mnMantenimiento_1.add(mntm_1);
		
		mntm = new JMenuItem("Productos");
		mntm.addActionListener(this);
		mnMantenimiento_1.add(mntm);
		
		mntmEmpleados = new JMenuItem("Empleados");
		mntmEmpleados.addActionListener(this);
		mnMantenimiento_1.add(mntmEmpleados);

		mnRegistro_1 = new JMenu("Registro");
		menuProyecto.add(mnRegistro_1);

		mntmReserva = new JMenuItem("Reserva");
		mntmReserva.addActionListener(this);
		mnRegistro_1.add(mntmReserva);

		mnAlmacen = new JMenu("Almacen");
		menuProyecto.add(mnAlmacen);

		mntmEntradas = new JMenuItem("Entradas");
		mntmEntradas.addActionListener(this);
		mnAlmacen.add(mntmEntradas);

		mntmSalidas = new JMenuItem("Salidas");
		mntmSalidas.addActionListener(this);
		mnAlmacen.add(mntmSalidas);

		mnReporte = new JMenu("Reporte");
		menuProyecto.add(mnReporte);
		
		mntmConsultarStock = new JMenuItem("Consultar Stock");
		mntmConsultarStock.addActionListener(this);
		mnReporte.add(mntmConsultarStock);
		
		mntmConsumoPorSala = new JMenuItem("Consumo por Sala");
		mntmConsumoPorSala.addActionListener(this);
		mnReporte.add(mntmConsumoPorSala);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblFondo = new JLabel(new ImageIcon("imagenes/MN-Global.jpg"));
		lblFondo.setBounds(0, 0, ANCHO, ALTO);
		getContentPane().add(lblFondo);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmConsumoPorSala) {
			actionPerformedMntmConsumoPorSala(e);
		}
		if (e.getSource() == mntmConsultarStock) {
			actionPerformedMntmConsultarStock(e);
		}
		if (e.getSource() == mntmSalidas) {
			actionPerformedMntmSalidas(e);
		}
		if (e.getSource() == mntmEntradas) {
			actionPerformedMntmEntradas(e);
		}
		if (e.getSource() == mntmEmpleados) {
			actionPerformedMntmEmpleados(e);
		}
		if (e.getSource() == mntm) {
			actionPerformedMntm(e);
		}
		if (e.getSource() == mntmReserva) {
			actionPerformedMntmReserva(e);
		}
		if (e.getSource() == mntm_1) {
			actionPerformedMntmSala(e);
		}
	}

	protected void actionPerformedMntmSala(ActionEvent e) {
		FormularioSala dip = new FormularioSala();
		dip.setLocationRelativeTo(this);
		dip.setVisible(true);
	}

	protected void actionPerformedMntmReserva(ActionEvent e) {
		FormularioReserva dip = new FormularioReserva();
		dip.setLocationRelativeTo(this);
		dip.setVisible(true);
	}
	protected void actionPerformedMntm(ActionEvent e) {
		FormularioProducto dip = new FormularioProducto();
		dip.setLocationRelativeTo(this);
		dip.setVisible(true);
	}
	protected void actionPerformedMntmEmpleados(ActionEvent e) {
		FormularioUsuarios dip = new FormularioUsuarios();
		dip.setLocationRelativeTo(this);
		dip.setVisible(true);
	}
	protected void actionPerformedMntmEntradas(ActionEvent e) {
		FormularioIngresos dip = new FormularioIngresos();
		dip.setLocationRelativeTo(this);
		dip.setVisible(true);
	}
	protected void actionPerformedMntmSalidas(ActionEvent e) {
		FormularioSalida dip = new FormularioSalida();
		dip.setLocationRelativeTo(this);
		dip.setVisible(true);
	}
	protected void actionPerformedMntmConsultarStock(ActionEvent e) {
		FormularioReporteProductos dip = new FormularioReporteProductos();
		dip.setLocationRelativeTo(this);
		dip.setVisible(true);
	}
	protected void actionPerformedMntmConsumoPorSala(ActionEvent e) {
		FormularioReporteSalas dip = new FormularioReporteSalas();
		dip.setLocationRelativeTo(this);
		dip.setVisible(true);
	}
	
	@Override
	public void ancestorAdded(AncestorEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ancestorRemoved(AncestorEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ancestorMoved(AncestorEvent event) {
		// TODO Auto-generated method stub
		
	}


}
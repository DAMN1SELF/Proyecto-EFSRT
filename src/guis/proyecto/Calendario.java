package guis.proyecto;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import com.toedter.components.JLocaleChooser;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class Calendario extends JFrame {

	private JPanel contentPane;
	private JCalendar calendar;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calendario frame = new Calendario();
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
	public Calendario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		calendar = new JCalendar();
		contentPane.add(calendar);
		
		dateChooser = new JDateChooser();
		contentPane.add(dateChooser);
	}

}

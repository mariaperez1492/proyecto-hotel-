package es.deusto.spq.client.gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.deusto.spq.pojo.EnumTipoHabitacion;
import es.deusto.spq.pojo.HabitacionData;
import es.deusto.spq.pojo.HotelData;
import es.deusto.spq.pojo.ReservaData;
import es.deusto.spq.pojo.UsuarioData;

import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import java.awt.SystemColor;


public class VentEleccion extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	protected static final Logger logger = LogManager.getLogger();
	private Client client;
//	private JButton btnAtras;
	private WebTarget webTarget;

	private JPanel contentPane;


	/**
	 * Create the frame.
	 */
	public VentEleccion(String hostname, String port, UsuarioData u) {
		
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 650);
		
		JPanel panelSup = new JPanel();
		getContentPane().add(panelSup, BorderLayout.NORTH);
		
		JPanel panelSur = new JPanel();
		getContentPane().add(panelSur, BorderLayout.SOUTH);
		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panelLista = new JPanel();
		getContentPane().add(panelLista, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Elija los servicios que desee: ");
		
		panelLista.add(lblNewLabel_1);

		setContentPane(contentPane);
		
		
        contentPane.setLayout(null);
		
		
		
//		LocalDate fecha1 = fechaInicioChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//		LocalDate fecha2 = fechaFinChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
//		SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
//		
//		Date fecha1 = (Date) fechaInicioChooser.getDate();
//		Date fecha2 = (Date) fechaFinChooser.getDate();
//		String fechaIni = fecha.format(fecha1);
//		String fechaFin = fecha.format(fecha2);
		
		
//
//		// Calcula la diferencia de días entre las dos fechas
//		dias = ChronoUnit.DAYS.between(fecha1, fecha2);
		
		JButton atras = new JButton("HISTORIAL DE MIS RESERVAS");
		atras.setBackground(SystemColor.info);
		atras.setFont(new Font("Tahoma", Font.BOLD, 16));
		atras.setBounds(550, 286, 286, 70);
		contentPane.add(atras);
		atras.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				VentHistorial VentHistorial = new VentHistorial(hostname, port, u);
				VentHistorial.setVisible(true);
				dispose();
		    }
		});
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
	
		
		
		JButton btnNewButton_1 = new JButton("HACER UNA RESERVA");
		btnNewButton_1.setBackground(SystemColor.inactiveCaption);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentListado ventListado = new VentListado(hostname, port, u);
				ventListado.setVisible(true);
				dispose();
	            
		    }
				});
		btnNewButton_1.setBounds(178, 286, 238, 70);
		contentPane.add(btnNewButton_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(382, 33, 199, 102);
		contentPane.add(panel_4);
		
		Image imgLogo = new ImageIcon("src/main/img/logo.png").getImage();
		ImageIcon iconLogo = new ImageIcon(imgLogo.getScaledInstance(180, 110, Image.SCALE_SMOOTH));
		JLabel lblLogo = new JLabel(iconLogo);
		lblLogo.setSize(180, 110);
		panel_4.add(lblLogo);

		
		
		
	}
}

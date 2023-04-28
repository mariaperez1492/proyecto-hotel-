package es.deusto.spq.client.gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import com.toedter.calendar.JDateChooser;


public class VentPago extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	protected static final Logger logger = LogManager.getLogger();
	private Client client;
	private WebTarget webTarget;

	private JPanel contentPane;
	
	private float precioTotal = 0;
	private long dias = 0;

	/**
	 * Create the frame.
	 */
	public VentPago(String hostname, String port, EnumTipoHabitacion tipo, int personas, float precio) {
		
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 650);
		
		JPanel panelSup = new JPanel();
		getContentPane().add(panelSup, BorderLayout.NORTH);
		
		Image imgLogo = new ImageIcon("src/main/img/logo.png").getImage();
		ImageIcon iconLogo = new ImageIcon(imgLogo.getScaledInstance(180, 110, Image.SCALE_SMOOTH));
		JLabel lblLogo = new JLabel(iconLogo);
		lblLogo.setSize(180, 110);
		panelSup.add(lblLogo);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panelLista = new JPanel();
		getContentPane().add(panelLista, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Elija los servicios que desee: ");
		
		panelLista.add(lblNewLabel_1);

		setContentPane(contentPane);
		
		JCheckBox breakfastBox = new JCheckBox("Desayuno (12€/día)");
		breakfastBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		breakfastBox.setBounds(234, 426, 167, 63);
		
		if (breakfastBox.isSelected()) {
			precioTotal = precioTotal + 12;
        }
		
		JCheckBox poolBox = new JCheckBox("Piscina (10€/día)");
		poolBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		poolBox.setBounds(21, 361, 188, 63);
		
		if (poolBox.isSelected()) {
			precioTotal = precioTotal + 10;
        }
		
		JCheckBox spaBox = new JCheckBox("Spa (30€/día)");
		spaBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		spaBox.setBounds(231, 361, 142, 63);
		
		if (spaBox.isSelected()) {
			precioTotal = precioTotal + 16;
        }
		
		JCheckBox gymBox = new JCheckBox("Gimnasio (15€/día)");
		gymBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gymBox.setBounds(21, 426, 163, 63);
		
		if (gymBox.isSelected()) {
			precioTotal = precioTotal + 15;
        }
		
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Pago total: " + precioTotal +". Dias totales: "+ dias);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel.setBounds(21, 519, 226, 63);
        contentPane.add(lblNewLabel);
        

        
        // Agregar los componentes a la ventana
        getContentPane().add(breakfastBox);
        getContentPane().add(poolBox);
        getContentPane().add(spaBox);
        getContentPane().add(gymBox);
		
		JButton btnCerrarSesion = new JButton("Pagar");
		btnCerrarSesion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCerrarSesion.setBounds(496, 574, 88, 29);
		contentPane.add(btnCerrarSesion);
		
		JLabel lblNewLabel_2 = new JLabel("Resumen de su reserva: Una habitación " + tipo +" para "+ personas+" personas por "+precio+ "€ por día.");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(21, 108, 795, 63);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblElijaElTipo = new JLabel("Elija el tipo de pensión:");
		lblElijaElTipo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblElijaElTipo.setBounds(21, 299, 346, 63);
		contentPane.add(lblElijaElTipo);
		
		JLabel lblFechasDeEstancia = new JLabel("Fechas de estancia:");
		lblFechasDeEstancia.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFechasDeEstancia.setBounds(21, 162, 346, 63);
		
		JDateChooser fechaInicioChooser = new JDateChooser();
		fechaInicioChooser.setBounds(21, 269, 150, 25);
		JDateChooser fechaFinChooser = new JDateChooser();
		fechaFinChooser.setBounds(396, 269, 150, 25);
		contentPane.add(fechaInicioChooser);
		contentPane.add(fechaFinChooser);
		
//		LocalDate fecha1 = fechaInicioChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//		LocalDate fecha2 = fechaFinChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//		// Calcula la diferencia de días entre las dos fechas
//		dias = ChronoUnit.DAYS.between(fecha1, fecha2);
		
		contentPane.add(lblFechasDeEstancia);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha inicio:");
		lblFechaDeInicio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFechaDeInicio.setBounds(21, 216, 131, 63);
		contentPane.add(lblFechaDeInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha fin:");
		lblFechaFin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFechaFin.setBounds(396, 216, 131, 63);
		contentPane.add(lblFechaFin);
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentLogin ventLogin = new VentLogin(hostname, port);
				ventLogin.setVisible(true);
				dispose();
			}
		});
		
		
        

	}
}

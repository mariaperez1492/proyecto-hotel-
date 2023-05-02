package es.deusto.spq.client.gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
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


public class VentPago extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	protected static final Logger logger = LogManager.getLogger();
	private Client client;
//	private JButton btnAtras;
	private WebTarget webTarget;

	private JPanel contentPane;
	
	private float precioTotal = 0;
	private long dias = 0;
	private String pension = "";

	/**
	 * Create the frame.
	 */
	public VentPago(String hostname, String port, UsuarioData u, HotelData hot, HabitacionData hab) {
		
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
		
		JCheckBox breakfastBox = new JCheckBox("Desayuno (12€/día)");
		breakfastBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		breakfastBox.setBounds(234, 426, 167, 63);
		
		if (breakfastBox.isSelected()) {
			pension = pension + "desayuno, ";
        }
		
		JCheckBox poolBox = new JCheckBox("Piscina (10€/día)");
		poolBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		poolBox.setBounds(21, 361, 188, 63);
		
		if (poolBox.isSelected()) {
			pension = pension + "piscina, ";
        }
		
		JCheckBox spaBox = new JCheckBox("Spa (30€/día)");
		spaBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		spaBox.setBounds(231, 361, 142, 63);
		
		if (spaBox.isSelected()) {
			pension = pension + "spa, ";
        }
		
		JCheckBox gymBox = new JCheckBox("Gimnasio (15€/día)");
		gymBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		gymBox.setBounds(21, 426, 163, 63);
		
		if (gymBox.isSelected()) {
			pension = pension + "gimnasio, ";
        }
		
        contentPane.setLayout(null);
        

        
//
//		btnAtras = new JButton("Atrás");
//		panelSur.add(btnAtras);
//		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 14));
//		btnAtras.setBounds(786, 531, 122, 27);
//		
//		
//		btnAtras.setBounds(783, 531, 122, 27);
//		
//		
//		btnAtras.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        dispose();  // cierra la ventana VentRegistro
//		        VentListado v = new VentListado(hostname, port);
//		        v.setVisible(true);  // muestra la ventana VentLogin
//		    }
//		});
//        

        
        // Agregar los componentes a la ventana
        getContentPane().add(breakfastBox);
        getContentPane().add(poolBox);
        getContentPane().add(spaBox);
        getContentPane().add(gymBox);
		


		
//		JLabel lblNewLabel_2 = new JLabel("Resumen de su reserva: Una habitación " + hab.getTipoHabitacion() +" para "+ hab.getPersonas()+" personas por "+hab.getPrecio()+ "€ por día.");
//		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		lblNewLabel_2.setBounds(21, 108, 795, 63);
//		contentPane.add(lblNewLabel_2);
		
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
		
//		SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
//		
//		Date fecha1 = (Date) fechaInicioChooser.getDate();
//		Date fecha2 = (Date) fechaFinChooser.getDate();
//		String fechaIni = fecha.format(fecha1);
//		String fechaFin = fecha.format(fecha2);
		
		
//
//		// Calcula la diferencia de días entre las dos fechas
//		dias = ChronoUnit.DAYS.between(fecha1, fecha2);
		
		JButton atras = new JButton("Atrás");
		atras.setBounds(594, 574, 104, 28);
		contentPane.add(atras);
		atras.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        dispose();  // cierra la ventana VentRegistro
		        VentHabitacion v = new VentHabitacion(hostname, port, u, hot);
		        v.setVisible(true);  // muestra la ventana VentLogin
		    }
		});
		
		contentPane.add(lblFechasDeEstancia);
		
		JLabel lblFechaDeInicio = new JLabel("Fecha inicio:");
		lblFechaDeInicio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFechaDeInicio.setBounds(21, 216, 131, 63);
		contentPane.add(lblFechaDeInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha fin:");
		lblFechaFin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFechaFin.setBounds(396, 216, 131, 63);
		contentPane.add(lblFechaFin);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		
	
		
		
		JButton btnNewButton_1 = new JButton("Pagar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				LocalDate fecha1 = fechaInicioChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//				LocalDate fecha2 = fechaFinChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
//				SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
//				
//				Date fecha1 = (Date) fechaInicioChooser.getDate();
//				Date fecha2 = (Date) fechaFinChooser.getDate();
//				String fechaIni = fecha.format(fecha1);
//				String fechaFin = fecha.format(fecha2);
				
	            //logger.info(personas);	
				ReservaData res = new ReservaData(u, hot, hab, "2025-02-02", "2025-02-10", pension, 0);
				VentResumen vent = new VentResumen(hostname, port, res);
	            vent.setVisible(true);
	            dispose();
	            
		    }
				});
		btnNewButton_1.setBounds(285, 561, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(234, 49, 199, 102);
		contentPane.add(panel_4);
		
		Image imgLogo = new ImageIcon("src/main/img/logo.png").getImage();
		ImageIcon iconLogo = new ImageIcon(imgLogo.getScaledInstance(180, 110, Image.SCALE_SMOOTH));
		JLabel lblLogo = new JLabel(iconLogo);
		lblLogo.setSize(180, 110);
		panel_4.add(lblLogo);

		
		
		
	}
}

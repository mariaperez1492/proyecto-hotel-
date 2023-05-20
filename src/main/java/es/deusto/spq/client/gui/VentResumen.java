package es.deusto.spq.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import es.deusto.spq.pojo.HabitacionData;
import es.deusto.spq.pojo.HotelData;
import es.deusto.spq.pojo.ReservaData;
import es.deusto.spq.pojo.UsuarioData;
import es.deusto.spq.server.jdo.Usuario;

public class VentResumen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane2;
	private Client client;
	private JButton btnAtras;
	private WebTarget webTarget;
	private float precioTotal;
	
	protected static final Logger logger = LogManager.getLogger();

	public VentResumen(String hostname, String port, ReservaData res) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 650);
		
		JPanel panelSup = new JPanel();
		getContentPane().add(panelSup, BorderLayout.NORTH);
		
		contentPane2 = new JPanel();
		getContentPane().add(contentPane2, BorderLayout.CENTER);
		
		Image imgLogo = new ImageIcon("src/main/img/logo.png").getImage();
		ImageIcon iconLogo = new ImageIcon(imgLogo.getScaledInstance(180, 110, Image.SCALE_SMOOTH));
		JLabel lblLogo = new JLabel(iconLogo);
		lblLogo.setSize(180, 110);
		panelSup.add(lblLogo);

		//setContentPane(contentPane2);
		
//		String stringFechaIni = res.getFecha_ini();
//		String stringFechaFin = res.getFecha_fin();
//
//		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
//		Date fecha1 = (Date) formatoFecha.parse(stringFechaIni);
//		Date fecha2 = (Date) formatoFecha.parse(stringFechaFin);
//		
//		int dias = ChronoUnit.DAYS.between(fecha1.getTime(), fecha2.getTime());

		precioTotal = res.getHabitacion().getPrecio()*res.calcularDias();
		
		String pen = res.getPension();
		
		if (pen.contains("spa")) {
			precioTotal = precioTotal + (30*res.calcularDias());
		}
		
		if (pen.contains("piscina")) {
			precioTotal = precioTotal + (10*res.calcularDias());
		}
		
		if (pen.contains("desayuno")) {
			precioTotal = precioTotal + (12*res.calcularDias());
		}
		
		if (pen.contains("gimnasio")) {
			precioTotal = precioTotal + (15*res.calcularDias());
		}
		contentPane2.setLayout(null);
		
		
		JLabel lblNewLabel_2 = new JLabel("Resumen de su reserva: Una habitación " + res.getHabitacion().getTipoHabitacion() +" en el hotel "+res.getHotel().getNombre()+" para "+ res.getHabitacion().getPersonas()+" personas por "+res.getHabitacion().getPrecio()+ "€ por día. Incluyendo las siguientes pensiones: " + res.getPension()+ " para "+ res.calcularDias()+" dias.");
		lblNewLabel_2.setBounds(10, 11, 1032, 57);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		contentPane2.add(lblNewLabel_2);
		
        JLabel lblNewLabel3 = new JLabel("Pago total: " + precioTotal);
        lblNewLabel3.setBounds(333, 197, 344, 57);
        lblNewLabel3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        contentPane2.add(lblNewLabel3);
        
        JButton atras = new JButton("Atrás");
        atras.setBounds(436, 312, 91, 38);
		contentPane2.add(atras);
		atras.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        dispose();  // cierra la ventana VentRegistro
		        VentPago v = new VentPago(hostname, port, res.getCliente(), res.getHotel(), res.getHabitacion());
		        v.setVisible(true);  // muestra la ventana VentLogin
		    }
		});
		
		JButton btnPagar = new JButton("Pagar");
		btnPagar.setBounds(278, 312, 120, 42);
		btnPagar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane2.add(btnPagar);
		
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WebTarget registerUserWebTarget = webTarget.path("reserve");
				Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
				
				Response response = invocationBuilder.post(Entity.entity(res, MediaType.APPLICATION_JSON));
			
				if (response.getStatus() == Status.OK.getStatusCode()) {
					JOptionPane.showMessageDialog(null, "Reserva Exitosa");
				} else {
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		}); 
		
		
		
	}
		
//		public boolean makeReservation (ReservaData res) {
//			WebTarget makeReservationWebTarget = webTarget.path("reserve");
//			Invocation.Builder invocationBuilder = makeReservationWebTarget.request(MediaType.APPLICATION_JSON);
//		
//			Response response = invocationBuilder.post(Entity.entity(res, MediaType.APPLICATION_JSON));
//		
//			if (response.getStatus() == Status.OK.getStatusCode()) {
//				return true;
//			} else {
//				logger.error("Error connecting with the server. Code: {}", response.getStatus());
//				return false;
//			}
//		}
//			
		
		
	

}
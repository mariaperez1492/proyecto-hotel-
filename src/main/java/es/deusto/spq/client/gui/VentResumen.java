package es.deusto.spq.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import es.deusto.spq.pojo.HabitacionData;
import es.deusto.spq.pojo.HotelData;
import es.deusto.spq.pojo.ReservaData;
import es.deusto.spq.pojo.UsuarioData;

public class VentResumen extends JFrame {

	private JPanel contentPane;
	private Client client;
	private JButton btnAtras;
	private WebTarget webTarget;
	private float precioTotal;


	public VentResumen(String hostname, String port, ReservaData res) {
		
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

		setContentPane(contentPane);
		
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
		
		
		JLabel lblNewLabel_2 = new JLabel("Resumen de su reserva: Una habitación " + res.getHabitacion().getTipoHabitacion() +" en el hotel "+res.getHotel().getNombre()+" para "+ res.getHabitacion().getPersonas()+" personas por "+res.getHabitacion().getPrecio()+ "€ por día. Incluyendo las siguientes pensiones: " + res.getPension()+ " para "+ res.calcularDias()+" dias.");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(21, 108, 795, 63);
		contentPane.add(lblNewLabel_2);
		
        JLabel lblNewLabel3 = new JLabel("Pago total: " + precioTotal);
        lblNewLabel3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel3.setBounds(21, 519, 226, 63);
        contentPane.add(lblNewLabel3);
		
	}

}

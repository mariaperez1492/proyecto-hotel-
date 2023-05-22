package es.deusto.spq.client.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.deusto.spq.pojo.HotelData;
import es.deusto.spq.pojo.ReservaData;
import es.deusto.spq.pojo.UsuarioData;
import es.deusto.spq.server.jdo.Hotel;
import es.deusto.spq.server.jdo.Reserva;

public class VentInfoAdmin extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Client client;	
	private JButton btnAtras;
	private WebTarget webTarget;
	private static JTextField txtBuscar;
	public static DefaultListModel<ReservaData> dlm;
	public static DefaultListModel<ReservaData> dlmReservasFechaFin;
	protected static final Logger logger = LogManager.getLogger();
	
	public VentInfoAdmin(String hostname, String port, HotelData hotel) {
		
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 650);
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFocusPainted(false);
		
		
		JPanel panelSup = new JPanel();
		getContentPane().add(panelSup, BorderLayout.NORTH);

		
		Image imgLogo = new ImageIcon("src/main/img/logo.png").getImage();
		ImageIcon iconLogo = new ImageIcon(imgLogo.getScaledInstance(180, 110, Image.SCALE_SMOOTH));
		JLabel lblLogo = new JLabel(iconLogo);
		lblLogo.setSize(180, 110);
		panelSup.add(lblLogo);
		
		JPanel panelFiltros = new JPanel(new GridLayout(19, 1)); 
		getContentPane().add(panelFiltros, BorderLayout.WEST);
		
		JLabel lblNewLabel_1 = new JLabel("Elija la fecha: ");
		panelFiltros.add(lblNewLabel_1);
		
		
		
		txtBuscar= new JTextField();
		txtBuscar = Utils.modifyTextField(txtBuscar, "dd/mm/aaaa");
		panelFiltros.add(txtBuscar, BorderLayout.CENTER);
		txtBuscar.setColumns(10);
		panelFiltros.add(btnBuscar);
		
		/**
		 * TABLA
		 */
		
		
		
		JPanel panelSur = new JPanel();
		getContentPane().add(panelSur, BorderLayout.SOUTH);
		
		JPanel panelCentro = new JPanel();
		getContentPane().add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNomHotel = new JLabel("Nombre del hotel: ");
		panelCentro.add(lblNomHotel);
		
		JLabel lblApareceNombre = new JLabel("");
		panelCentro.add(lblApareceNombre);
		
		JLabel lblIngresos = new JLabel("Ingresos generados");
		panelCentro.add(lblIngresos);
		
		JLabel lblNumIngresos = new JLabel("");
		panelCentro.add(lblNumIngresos);
		
		JLabel lblHabOcupadas = new JLabel("Habitaciones ocupadas");
		panelCentro.add(lblHabOcupadas);
		
		JLabel lblNumHabOcupadas = new JLabel("");
		panelCentro.add(lblNumHabOcupadas);
		
		JLabel lblPorcen = new JLabel("Porcentaje de ocupación");
		panelCentro.add(lblPorcen);
		
		JLabel lblAparecePorcen = new JLabel("");
		panelCentro.add(lblAparecePorcen);
		
		
		
		
//		btnAtras.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
////		        dispose(); 
////		        PanelHoteles v = new PanelHoteles(hostname, port);
////		        v.setVisible(true);  // muestra la ventana VentLogin
//		    }
//		});
		
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				
				lblApareceNombre.setText(hotel.getNombre());
				float ingresos = calcularIngresos(txtBuscar.getText(), hotel.getNombre());
				lblNumIngresos.setText(String.valueOf(ingresos));
				
				int ocupacion = calcularOcupacion(txtBuscar.getText(), hotel.getNombre());
				lblNumHabOcupadas.setText(String.valueOf(ocupacion));
				
				float porcentaje = porcentajeOcupacion(txtBuscar.getText(), hotel.getNombre());
				lblAparecePorcen.setText(String.valueOf(porcentaje));
				
			}
		});
		

		
		
				
		
		
	
	}
	public List<ReservaData> getReservas(String nom) throws JsonMappingException, JsonProcessingException {
		
		WebTarget reservaTarget = webTarget.path("getReservas");
		Invocation.Builder invocationBuilder = reservaTarget.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.get();
		ObjectMapper mapper = new ObjectMapper();
		
		List<ReservaData> listData = mapper.readValue(response.readEntity(String.class), new TypeReference<List<ReservaData>>(){});
		List<ReservaData> listDataID = new ArrayList<>();
		for (ReservaData r :listData) {
			if(r.getHotel().getNombre().equals(nom)) {
				listDataID.add(r);
			}
		}
		
		return listDataID;
	}


	public List<ReservaData> obtenerReservas(String fecha, String nom) throws Exception {

		
		List<ReservaData> listData = getReservas(nom);
		List<ReservaData> reservasFecha = new ArrayList<>();
		Date da1 = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
		for (ReservaData reserva: listData) {
			Date dateFechaI = new SimpleDateFormat("yyyy-MM-dd").parse(reserva.getFecha_ini());
			Date dateFechaF = new SimpleDateFormat("yyyy-MM-dd").parse(reserva.getFecha_fin());
			if((da1.equals(dateFechaI) || da1.after(dateFechaI)) && da1.before(dateFechaF)) {
				reservasFecha.add(reserva);
			}
			
		}	
		
		return reservasFecha;
	}
	
	public float calcularIngresos(String fecha, String nom) {
		
		float suma = 0;
		
		try {
			List<ReservaData> lista = obtenerReservas(fecha, nom);
			
			for(ReservaData r:lista) {
				suma = suma + r.getPrecio();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return suma;
	}
	
	public int calcularOcupacion(String fecha, String nom) {
		int ocupacion = 0;
		
		
		try {
			List<ReservaData> lista = obtenerReservas(fecha, nom);
			
			
			ocupacion = lista.size();
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ocupacion;
	}
	
	
	public float porcentajeOcupacion(String fecha, String nom) {
		
		float porcentaje = 0;
		
		int ocupacion = calcularOcupacion(fecha, nom);
		
		try {
			List<ReservaData> lista = obtenerReservas(fecha, nom);
			
			for(ReservaData r:lista) {
				porcentaje = ocupacion * 100 / r.getHotel().getHabitaciones_disp();
			}
			
			System.out.println(porcentaje);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return porcentaje;
	}
	


}

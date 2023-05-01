package es.deusto.spq.client.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowFilter.Entry;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.deusto.spq.pojo.HotelData;
import es.deusto.spq.pojo.ReservaData;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class PanelReservas extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected static final Logger logger = LogManager.getLogger();

	private Client client;
	private WebTarget webTarget;
	
	/**
	 * Create the panel.
	 */
	public PanelReservas(String hostname, String port) {
		
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
		
		JTable table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Cliente");
		model.addColumn("Hotel");
		model.addColumn("Habitacion");
		model.addColumn("Fecha inicio");
		model.addColumn("Fecha fin");
		table.setModel(model);
		
		WebTarget reservaTarget = webTarget.path("getReservas");
		Invocation.Builder invocationBuilder = reservaTarget.request(MediaType.APPLICATION_JSON);
		
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		add(panelNorte, BorderLayout.NORTH);
		
		JPanel panelSur = new JPanel();
		add(panelSur, BorderLayout.SOUTH);
		
		JPanel panelIzquierda = new JPanel();
		add(panelIzquierda, BorderLayout.WEST);
		
		JPanel panelDerecha = new JPanel();
		add(panelDerecha, BorderLayout.EAST);
				
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Elija la ciudad: ");
		panelIzquierda.add(lblNewLabel_1);
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.addItem("Seleccione un hotel");
		comboBox.addItem("Hotel Barcelona1");
		comboBox.addItem("Hotel Barcelona2");
		comboBox.addItem("Hotel San Sebastián");
		comboBox.addItem("Hotel Madrid");
		comboBox.addItem("Hotel Málaga");
		comboBox.addItem("Hotel Valencia");
		panelIzquierda.add(comboBox);
		
		Response response = invocationBuilder.get();
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<ReservaData> listData = mapper.readValue(response.readEntity(String.class), new TypeReference<List<ReservaData>>(){});
			
			Object[] fila;
			for (ReservaData reserva : listData) {
			    fila = new Object[listData.size()];
			    fila[0] = reserva.getCliente();
			    fila[1] = reserva.getHotel();
			    fila[2] = reserva.getHabitacion();
			    fila[3] = reserva.getFecha_ini();
			    fila[4] = reserva.getFecha_fin();
			    
			    model.addRow(fila);
			    }
				
		
			
		} catch (Exception e) {
		
		}
		TableRowSorter<TableModel> trsfiltro = new TableRowSorter(table.getModel());
		table.setRowSorter(trsfiltro);
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String hotelSeleccionado = comboBox.getSelectedItem().toString();
				 TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
			        sorter.setRowFilter(RowFilter.regexFilter(hotelSeleccionado, 1)); // 1 es el índice de la columna donde se encuentra el nombre del hotel
			        
			        // Aplicamos el filtro a la tabla
			        table.setRowSorter(sorter);
					
			}
		});
	}

}


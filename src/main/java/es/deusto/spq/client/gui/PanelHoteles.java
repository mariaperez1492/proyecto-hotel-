package es.deusto.spq.client.gui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.deusto.spq.pojo.HotelData;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class PanelHoteles extends JPanel {
	
	protected static final Logger logger = LogManager.getLogger();

	private Client client;
	private WebTarget webTarget;

	/**
	 * Create the panel.
	 */
	public PanelHoteles(String hostname, String port) {
		setLayout(new BorderLayout(0, 0));
		
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
		
		
		JPanel panelNorte = new JPanel();
		add(panelNorte, BorderLayout.NORTH);
		
		JPanel panelSur = new JPanel();
		add(panelSur, BorderLayout.SOUTH);
		
		JPanel panelIzquierda = new JPanel();
		add(panelIzquierda, BorderLayout.WEST);
		
		JPanel panelDerecha = new JPanel();
		add(panelDerecha, BorderLayout.EAST);
		
		JTable tabla = new JTable();
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Hotel");
		modelo.addColumn("Ciudad");
		modelo.addColumn("Habitaciones Disponibles");
		tabla.setModel(modelo);
		
		JScrollPane scrollPane = new JScrollPane(tabla);
		add(scrollPane, BorderLayout.CENTER);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.addItem("Seleccione una ciudad");
		comboBox.addItem("Barcelona");
		comboBox.addItem("San Sebastián");
		comboBox.addItem("Madrid");
		comboBox.addItem("Málaga");
		comboBox.addItem("Valencia");
		panelIzquierda.add(comboBox);
		
		try {
//			List<HotelData> listData = mapper.readValue(response.readEntity(String.class), new TypeReference<List<HotelData>>(){});
			List<HotelData> listData = getHoteles();
			
			Object[] fila;
			for (HotelData hotel : listData) {
			    fila = new Object[listData.size()];
			    fila[0] = hotel.getNombre();
			    fila[1] = hotel.getCiudad();
			    fila[2] = hotel.getHabitaciones_disp();
			    
			    modelo.addRow(fila);
			    }
			
		} catch (Exception e) {
		
		}


		
		/**
		 * FILTROS
		 */
		
		
	TableRowSorter<TableModel> trsfiltro = new TableRowSorter(tabla.getModel());
	tabla.setRowSorter(trsfiltro);
	
	comboBox.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String valorSeleccionado = comboBox.getSelectedItem().toString();
			if(valorSeleccionado == "Madrid") {
				RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
                    public boolean include(Entry<?, ?> entry) {
                        Object value = entry.getValue(1);
                        return value.equals(valorSeleccionado);
                    }
                };
                ((TableRowSorter) tabla.getRowSorter()).setRowFilter(rf);
				
			}else if (valorSeleccionado == "San Sebastián") {
				RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
                    public boolean include(Entry<?, ?> entry) {
                        Object value = entry.getValue(1);
                        return value.equals(valorSeleccionado);
                    }
                };
                ((TableRowSorter) tabla.getRowSorter()).setRowFilter(rf);
			}else if (valorSeleccionado == "Barcelona") {
				RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
                    public boolean include(Entry<?, ?> entry) {
                        Object value = entry.getValue(1);
                        return value.equals(valorSeleccionado);
                    }
                };
                ((TableRowSorter) tabla.getRowSorter()).setRowFilter(rf);
			}else if (valorSeleccionado == "Valencia") {
				RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
                    public boolean include(Entry<?, ?> entry) {
                        Object value = entry.getValue(1);
                        return value.equals(valorSeleccionado);
                    }
                };
                ((TableRowSorter) tabla.getRowSorter()).setRowFilter(rf);
				
			}else if (valorSeleccionado == "Málaga") {
				RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
                    public boolean include(Entry<?, ?> entry) {
                        Object value = entry.getValue(1);
                        return value.equals(valorSeleccionado);
                    }
                };
                ((TableRowSorter) tabla.getRowSorter()).setRowFilter(rf);
				
			}else {
				TableRowSorter<TableModel> trsfiltro = new TableRowSorter(tabla.getModel());
				tabla.setRowSorter(trsfiltro);
			}
		}
	});
}	
		
		
	
	public List<HotelData> getHoteles() throws JsonMappingException, JsonProcessingException {
		WebTarget hotelTarget = webTarget.path("getHoteles");
		Invocation.Builder invocationBuilder = hotelTarget.request(MediaType.APPLICATION_JSON);
				
		Response response = invocationBuilder.get();
		ObjectMapper mapper = new ObjectMapper();
		
		List<HotelData> listData = mapper.readValue(response.readEntity(String.class), new TypeReference<List<HotelData>>(){});
		return listData;
	}
		

}



package es.deusto.spq.client.gui;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import org.glassfish.grizzly.streams.AbstractStreamWriter.DisposeBufferCompletionHandler;

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
import java.awt.event.MouseListener;
import java.util.List;
import java.awt.GridLayout;
import javax.swing.JButton;


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
		panelIzquierda.setLayout(new GridLayout(8, 1, 0, 0));
		panelIzquierda.add(comboBox);
		
		JLabel lblEliminar = new JLabel("Pulsa Alt + Click para eliminar un hotel:   ");
		panelIzquierda.add(lblEliminar);
		
		JButton btnAniadir = new JButton("Añadir hotel");
		panelIzquierda.add(btnAniadir);
		
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
		
		tabla.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.isAltDown()) {
					DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
					
					int[] selectedRows = tabla.getSelectedRows();
					
					for (int i = selectedRows.length - 1; i >= 0; i--) {
					    modelo.removeRow(selectedRows[i]);
					}
					
					modelo.fireTableDataChanged();
				}
				
				
			}
		});
		
			

		btnAniadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String hotel = JOptionPane.showInputDialog("Ingrese el nombre del hotel");
				String ciudad = JOptionPane.showInputDialog("Ingrese la ciudad donde se encuentra el hotel");
				int habitacionesDisponibles = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor de las habitaciones disponibles"));

				Object[] rowData = {hotel, ciudad, habitacionesDisponibles};
				modelo.addRow(rowData);
				modelo.fireTableDataChanged();

			}
		});




		
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
	
	/* Al seleccionar una fila de la tabla */
	
	tabla.addMouseListener(new MouseAdapter() {
		
	    public void mouseClicked(MouseEvent e) {
	    	
	        if (e.getClickCount() == 1) {
	        	
	            JTable target = (JTable) e.getSource();
	            int row = target.getSelectedRow();
	            
	            String hotel = (String) target.getValueAt(row, 0);
	            String ciudad = (String) target.getValueAt(row, 1);
	            int habitaciones = (int) target.getValueAt(row, 2);

	            HotelData hot = new HotelData(hotel, ciudad, habitaciones);
	            hot.setId(row + 1);
	            
	            VentInfoAdmin vent = new VentInfoAdmin(hostname, port, hot);
	            vent.setVisible(true);
	            
	            
	            
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



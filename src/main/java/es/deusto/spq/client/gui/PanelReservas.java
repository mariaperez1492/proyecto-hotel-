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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.deusto.spq.pojo.EnumTipoHabitacion;
import es.deusto.spq.pojo.HotelData;
import es.deusto.spq.pojo.ReservaData;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;

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
		model.addColumn("Fecha inicio");
		model.addColumn("Fecha fin");
		model.addColumn("Hotel");
		model.addColumn("Habitacion");
		model.addColumn("Pension");
		model.addColumn("Precio");
		table.setModel(model);
		
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
		panelIzquierda.setLayout(new GridLayout(9, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Elija la ciudad: ");
		panelIzquierda.add(lblNewLabel_1);
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.addItem("Seleccione un hotel");
		try {
//			List<HotelData> listData = mapper.readValue(response.readEntity(String.class), new TypeReference<List<HotelData>>(){});
			List<HotelData> listData = getHoteles();
			
			for (HotelData hotel : listData) {

			    comboBox.addItem(hotel.getNombre());
			    }
			
		} catch (Exception e) {
		
		}
		panelIzquierda.add(comboBox);
		
		JLabel lblEliminar = new JLabel("Pulsa Alt + Click para eliminar una reserva:  ");
		panelIzquierda.add(lblEliminar);
		
		JButton btnAniadir = new JButton("Añadir reserva");
		panelIzquierda.add(btnAniadir);
		
		try {
			
			List<ReservaData> listData = getReservas();
			Object[] fila ;
			
			if (!listData.isEmpty()) {
			    fila = new Object[7];

			    for (ReservaData reserva : listData) {
			        fila[0] = reserva.getCliente().getDni();
			        fila[1] = reserva.getFecha_fin();
			        fila[2] = reserva.getFecha_ini();
			        fila[3] = reserva.getHotel().getNombre();
			        fila[4] = reserva.getHabitacion().getTipoHabitacion();
			        fila[5] = reserva.getPension();
			        fila[6] = reserva.getPrecio();
			        model.addRow(fila);
			    }
			}
			
		} catch (Exception e) {
			logger.error("Error retrieving reservas from database", e);
		}
		
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);
		
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String valorSeleccionado = comboBox.getSelectedItem().toString();
		        RowFilter<DefaultTableModel, Object> filtro = RowFilter.regexFilter(valorSeleccionado, 3);
		        sorter.setRowFilter(filtro);
		        if(valorSeleccionado == "Seleccione un hotel") {
		        	TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
		    		table.setRowSorter(sorter);
		        }
					
			}
		});
		
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(e.isAltDown()) {
					DefaultTableModel modelo = (DefaultTableModel) table.getModel();
					
					int[] selectedRows = table.getSelectedRows();
					
					for (int i = selectedRows.length - 1; i >= 0; i--) {
					    modelo.removeRow(selectedRows[i]);
					}
					
					modelo.fireTableDataChanged();
				}
				
				
			}
		});
			
			

		btnAniadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cliente = JOptionPane.showInputDialog("Ingrese el dni del cliente");
				String fecha_ini = JOptionPane.showInputDialog("Ingrese la fecha de inicio (");
				String fecha_fin = JOptionPane.showInputDialog("Ingrese la fecha de fin (");
				String hotel = JOptionPane.showInputDialog("Ingrese el nombre del hotel");
				
			

				EnumTipoHabitacion [] habitacion = EnumTipoHabitacion.values();
				JComboBox<EnumTipoHabitacion> comboBox = new JComboBox<>(habitacion);
				String selectedOption = String.valueOf(JOptionPane.showInputDialog(null, "Seleccione el tipo de habitación:", "Título del diálogo", JOptionPane.QUESTION_MESSAGE, null, habitacion, habitacion[0]));
				String pension = JOptionPane.showInputDialog("Ingrese la pensión");
				float precio = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el precio de la reserva"));
				
				Object[] rowData = {cliente, fecha_ini, fecha_fin, hotel, selectedOption, pension, precio};
				model.addRow(rowData);
				model.fireTableDataChanged();

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
	public List<ReservaData> getReservas() throws JsonMappingException, JsonProcessingException {
		
		WebTarget reservaTarget = webTarget.path("getReservas");
		Invocation.Builder invocationBuilder = reservaTarget.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.get();
		ObjectMapper mapper = new ObjectMapper();
		
		List<ReservaData> listData = mapper.readValue(response.readEntity(String.class), new TypeReference<List<ReservaData>>(){});
		return listData;
	}




}
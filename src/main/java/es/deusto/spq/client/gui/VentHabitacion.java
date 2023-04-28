package es.deusto.spq.client.gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import java.util.logging.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
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

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.deusto.spq.pojo.EnumTipoHabitacion;
import es.deusto.spq.pojo.HabitacionData;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JComboBox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import javax.swing.JButton;
import javax.swing.JRadioButton;

public class VentHabitacion extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	protected static final Logger logger = LogManager.getLogger();
	private Client client;
	private JButton btnAtras;
	private WebTarget webTarget;
	private int personas = 0;
	private float precio = 0;
	private EnumTipoHabitacion tipo;
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public VentHabitacion(String hostname, String port) {
		
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
		
		JPanel panelFiltros = new JPanel(new GridLayout(19, 1)); 
		getContentPane().add(panelFiltros, BorderLayout.WEST);
		
		JLabel lblNewLabel_1 = new JLabel("Eliga el tipo de habitación: ");
		panelFiltros.add(lblNewLabel_1);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.addItem("Seleccione el tipo de habitación");
		comboBox.addItem("SUITE");
		comboBox.addItem("DELUXE");
		comboBox.addItem("ESTANDAR");
		comboBox.addItem("CAMA_EXTRAGRANDE");

		panelFiltros.add(comboBox);
		
		/**
		 * TABLA
		 */
		
		JPanel panelLista = new JPanel();
		getContentPane().add(panelLista, BorderLayout.CENTER);
		
		
		JPanel panelSur = new JPanel();
		getContentPane().add(panelSur, BorderLayout.SOUTH);
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentLogin ventLogin = new VentLogin(hostname, port);
				ventLogin.setVisible(true);
				dispose();
			}
		});
		panelSur.add(btnCerrarSesion);
		
		JButton btnNewButton = new JButton("Reservar");
		
		panelSur.add(btnNewButton);
		
		btnAtras = new JButton("Atrás");
		panelSur.add(btnAtras);
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAtras.setBounds(786, 531, 122, 27);
		
		
		btnAtras.setBounds(783, 531, 122, 27);
		
		
		btnAtras.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        dispose();  // cierra la ventana VentRegistro
		        VentListado v = new VentListado(hostname, port);
		        v.setVisible(true);  // muestra la ventana VentLogin
		    }
		});
		
		JTable table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		
	
		model.addColumn("Tipo de Habitacion");
		model.addColumn("Capacidad");
		model.addColumn("Precio");
		table.setModel(model);
		
		/*
		 * try { ImageIcon imagen = new ImageIcon("src/main/img/Barcelona.jpeg"); JLabel
		 * labelImagen = new JLabel(imagen);
		 * 
		 * JPanel panel = new JPanel(); panel.add(labelImagen);
		 * getContentPane().add(panel);
		 * 
		 * JPanel panelSup2 = new JPanel(); getContentPane().add(panelSup2,
		 * BorderLayout.NORTH);
		 * 
		 * // Cargar la imagen desde el archivo Image imgLogo2 = new
		 * ImageIcon("ruta/a/la/imagen.jpg").getImage(); // Escalar la imagen a un
		 * tamaño adecuado ImageIcon iconLogo2 = new
		 * ImageIcon(imgLogo.getScaledInstance(180, 110, Image.SCALE_SMOOTH)); // Crear
		 * un JLabel con la imagen JLabel lblLogo2 = new JLabel(iconLogo2);
		 * lblLogo2.setSize(180, 110); panelSup2.add(lblLogo2);
		 * 
		 * 
		 * } catch (Exception e) {
		 * 
		 * }
		 */
		
		try {
			List<HabitacionData> listData = getHabitaciones();
			
			Object[] fila;
			for (HabitacionData habitacion : listData) {
			    fila = new Object[listData.size()];
			    fila[0] = habitacion.getTipoHabitacion();
			    fila[1] = habitacion.getPersonas();
			    fila[2] = habitacion.getPrecio();
			    
			    model.addRow(fila);
			    }
			
		} catch (Exception e) {
		
		}
		
		table.addMouseListener(new MouseAdapter() {
			
		    public void mouseClicked(MouseEvent e) {
		    	
		        if (e.getClickCount() == 1) {
		        	
		            JTable target = (JTable) e.getSource();
		            int row = target.getSelectedRow();
		            
		            tipo = (EnumTipoHabitacion) target.getValueAt(row, 0);
		            personas = (int) target.getValueAt(row, 1);
		            precio = (float) target.getValueAt(row, 2);

//		            VentPago vent = new VentPago(hostname, port);
//		            vent.setVisible(true);
//		            dispose(); 
		        }
		    }
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            //logger.info(personas);		
	            VentPago vent = new VentPago(hostname, port, tipo, personas, precio);
	            vent.setVisible(true);
	            dispose(); 

			}
		});
		
		panelLista.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(205, 5, 452, 402);
		panelLista.add(scrollPane);
		
		TableRowSorter<TableModel> trsfiltro = new TableRowSorter(table.getModel());
		table.setRowSorter(trsfiltro);
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				String valorSeleccionado = comboBox.getSelectedItem().toString();
				if(valorSeleccionado == "SUITE") {
					RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
	                    public boolean include(Entry<?, ?> entry) {
	                        Object value = entry.getValue(1);
	                        return value.equals(valorSeleccionado);
	                    }
	                };
	                ((TableRowSorter) table.getRowSorter()).setRowFilter(rf);
					
				}else if (valorSeleccionado == "DELUXE") {
					RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
	                    public boolean include(Entry<?, ?> entry) {
	                        Object value = entry.getValue(0);
	                        return value.equals(valorSeleccionado);
	                    }
	                };
	                ((TableRowSorter) table.getRowSorter()).setRowFilter(rf);
				}else if (valorSeleccionado == "ESTANDAR") {
					RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
	                    public boolean include(Entry<?, ?> entry) {
	                        Object value = entry.getValue(0);
	                        return value.equals(valorSeleccionado);
	                    }
	                };
	                ((TableRowSorter) table.getRowSorter()).setRowFilter(rf);
				}else if (valorSeleccionado == "CAMA_EXTRAGRANDE") {
					RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
	                    public boolean include(Entry<?, ?> entry) {
	                        Object value = entry.getValue(0);
	                        return value.equals(valorSeleccionado);
	                    }
	                };
	                ((TableRowSorter) table.getRowSorter()).setRowFilter(rf);
					
	
					
				}else {
					TableRowSorter<TableModel> trsfiltro = new TableRowSorter(table.getModel());
					table.setRowSorter(trsfiltro);
				}
				
			}
		});
	}	
	
	public List<HabitacionData> getHabitaciones() throws JsonMappingException, JsonProcessingException {
		WebTarget habitacionTarget = webTarget.path("getHabitaciones");
		Invocation.Builder invocationBuilder = habitacionTarget.request(MediaType.APPLICATION_JSON);
				
		Response response = invocationBuilder.get();
		ObjectMapper mapper = new ObjectMapper();
		
		List<HabitacionData> listData = mapper.readValue(response.readEntity(String.class), new TypeReference<List<HabitacionData>>(){});
		return listData;
	}
}
package es.deusto.spq.client.gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import es.deusto.spq.pojo.HotelData;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;

import com.fasterxml.jackson.core.type.TypeReference;
import javax.swing.JButton;

public class VentHabitacion extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	protected static final Logger logger = LogManager.getLogger();
	private Client client;
	private WebTarget webTarget;

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
		comboBox.addItem("Suite");
		comboBox.addItem("Deluxe");
		comboBox.addItem("Estándar");
		comboBox.addItem("Cama extragrande");

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
		
		JTable table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("Capacidad");
		model.addColumn("Precio");
		model.addColumn("Habitaciones Disponibles");
		table.setModel(model);
		
		WebTarget hotelTarget = webTarget.path("getHoteles");
		Invocation.Builder invocationBuilder = hotelTarget.request(MediaType.APPLICATION_JSON);
				
		Response response = invocationBuilder.get();
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			ImageIcon imagen = new ImageIcon("src/main/img/Barcelona.jpeg");
			JLabel labelImagen = new JLabel(imagen);

			JPanel panel = new JPanel();
			panel.add(labelImagen);
			getContentPane().add(panel);

			JPanel panelSup2 = new JPanel();
			getContentPane().add(panelSup2, BorderLayout.NORTH);

			// Cargar la imagen desde el archivo
			Image imgLogo2 = new ImageIcon("ruta/a/la/imagen.jpg").getImage();
			// Escalar la imagen a un tamaño adecuado
			ImageIcon iconLogo2 = new ImageIcon(imgLogo.getScaledInstance(180, 110, Image.SCALE_SMOOTH));
			// Crear un JLabel con la imagen
			JLabel lblLogo2 = new JLabel(iconLogo2);
			lblLogo2.setSize(180, 110);
			panelSup2.add(lblLogo2);

				
		} catch (Exception e) {
		
		}
		
		JScrollPane scrollPane = new JScrollPane(table);
		panelLista.add(scrollPane);
		
		TableRowSorter<TableModel> trsfiltro = new TableRowSorter(table.getModel());
		table.setRowSorter(trsfiltro);
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String valorSeleccionado = comboBox.getSelectedItem().toString();
				if(valorSeleccionado == "Suite") {
					RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
	                    public boolean include(Entry<?, ?> entry) {
	                        Object value = entry.getValue(1);
	                        return value.equals(valorSeleccionado);
	                    }
	                };
	                ((TableRowSorter) table.getRowSorter()).setRowFilter(rf);
					
				}else if (valorSeleccionado == "Deluxe") {
					RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
	                    public boolean include(Entry<?, ?> entry) {
	                        Object value = entry.getValue(1);
	                        return value.equals(valorSeleccionado);
	                    }
	                };
	                ((TableRowSorter) table.getRowSorter()).setRowFilter(rf);
				}else if (valorSeleccionado == "Estándar") {
					RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
	                    public boolean include(Entry<?, ?> entry) {
	                        Object value = entry.getValue(1);
	                        return value.equals(valorSeleccionado);
	                    }
	                };
	                ((TableRowSorter) table.getRowSorter()).setRowFilter(rf);
				}else if (valorSeleccionado == "Cama extragrande") {
					RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
	                    public boolean include(Entry<?, ?> entry) {
	                        Object value = entry.getValue(1);
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
}

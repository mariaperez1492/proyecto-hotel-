package es.deusto.spq.client.gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import es.deusto.spq.pojo.HotelData;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;

public class VentListado extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	public VentListado() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 650);
		
		JPanel panelSup = new JPanel();
		getContentPane().add(panelSup, BorderLayout.NORTH);
		
		Image imgLogo = new ImageIcon(VentLogin.class.getResource("logo.png")).getImage();
		ImageIcon iconLogo = new ImageIcon(imgLogo.getScaledInstance(180, 110, Image.SCALE_SMOOTH));
		JLabel lblLogo = new JLabel(iconLogo);
		lblLogo.setSize(180, 110);
		panelSup.add(lblLogo);
		
		JPanel panelFiltros = new JPanel(new GridLayout(19, 1)); 
		getContentPane().add(panelFiltros, BorderLayout.WEST);
		
		JLabel lblNewLabel_1 = new JLabel("Eliga la ciudad: ");
		panelFiltros.add(lblNewLabel_1);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.addItem("Seleccione una ciudad");
		comboBox.addItem("Barcelona");
		comboBox.addItem("Bilbao");
		comboBox.addItem("Madrid");
		comboBox.addItem("Sevilla");
		comboBox.addItem("Valencia");
		panelFiltros.add(comboBox);
		
		JPanel panelLista = new JPanel();
		getContentPane().add(panelLista, BorderLayout.CENTER);
		
		JTable table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("Hotel");
		model.addColumn("Ciudad");
		model.addColumn("Habitaciones Disponibles");
		table.setModel(model);
		
		
//		model.addRow(new Object[]{"HL Madrid", "Madrid", "87"});
//		model.addRow(new Object[]{"HL Barcelona", "Barcelona", "45"});
//		model.addRow(new Object[]{"HL Bilbao", "Bilbao", "66"});
//		model.addRow(new Object[]{"HL Valencia", "Valencia", "95"});
		
		HotelData hotel1 = new HotelData("HL Madrid", "Madrid", 87 );
		HotelData hotel6 = new HotelData("HL Madrid Serrano", "Madrid", 87 );
		HotelData hotel2 = new HotelData( "HL Barcelona", "Barcelona", 45);
		HotelData hotel3 = new HotelData( "HL Bilbao", "Bilbao", 66);
		HotelData hotel4 = new HotelData( "HL Valencia", "Valencia", 95);
		HotelData hotel5 = new HotelData( "HL Sevilla", "Sevilla", 95);
		
		
		List<HotelData> listaH = new ArrayList();
		listaH.add(hotel1);
		listaH.add(hotel2);
		listaH.add(hotel3);
		listaH.add(hotel4);
		listaH.add(hotel5);
		listaH.add(hotel6);
		
		Object[] fila;
		
		for (HotelData hotel : listaH) {
			fila = new Object[listaH.size()];
			fila[0] = hotel.getNombre();
			fila[1] = hotel.getCiudad();
			fila[2] = hotel.getHabitaciones_disp();
			
			model.addRow(fila);
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
				if(valorSeleccionado == "Madrid") {
					RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
	                    public boolean include(Entry<?, ?> entry) {
	                        Object value = entry.getValue(1);
	                        return value.equals(valorSeleccionado);
	                    }
	                };
	                ((TableRowSorter) table.getRowSorter()).setRowFilter(rf);
					
				}else if (valorSeleccionado == "Bilbao") {
					RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
	                    public boolean include(Entry<?, ?> entry) {
	                        Object value = entry.getValue(1);
	                        return value.equals(valorSeleccionado);
	                    }
	                };
	                ((TableRowSorter) table.getRowSorter()).setRowFilter(rf);
				}else if (valorSeleccionado == "Barcelona") {
					RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
	                    public boolean include(Entry<?, ?> entry) {
	                        Object value = entry.getValue(1);
	                        return value.equals(valorSeleccionado);
	                    }
	                };
	                ((TableRowSorter) table.getRowSorter()).setRowFilter(rf);
				}else if (valorSeleccionado == "Valencia") {
					RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
	                    public boolean include(Entry<?, ?> entry) {
	                        Object value = entry.getValue(1);
	                        return value.equals(valorSeleccionado);
	                    }
	                };
	                ((TableRowSorter) table.getRowSorter()).setRowFilter(rf);
					
				}else if (valorSeleccionado == "Sevilla") {
					RowFilter<Object, Object> rf = new RowFilter<Object, Object>() {
	                    public boolean include(Entry<?, ?> entry) {
	                        Object value = entry.getValue(1);
	                        return value.equals(valorSeleccionado);
	                    }
	                };
	                ((TableRowSorter) table.getRowSorter()).setRowFilter(rf);
					
				}
				
			}
		});
		
		
	}


	
	
	
}

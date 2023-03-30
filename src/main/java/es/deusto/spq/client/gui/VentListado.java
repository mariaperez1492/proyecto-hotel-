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

import es.deusto.spq.pojo.Hotel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;

public class VentListado extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		VentListado v = new VentListado();
		v.setVisible(true);
	}
	
	public VentListado() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 650);
		
		JPanel panelSup = new JPanel();
		getContentPane().add(panelSup, BorderLayout.NORTH);
		
		Image imgStrava = new ImageIcon(VentLogin.class.getResource("logo.png")).getImage();
		ImageIcon iconStrava = new ImageIcon(imgStrava.getScaledInstance(180, 110, Image.SCALE_SMOOTH));
		JLabel lblNewLabel = new JLabel(iconStrava);
		lblNewLabel.setSize(180, 110);
		panelSup.add(lblNewLabel);
		
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
		
		Hotel hotel1 = new Hotel("HL Madrid", "Madrid", 87 );
		Hotel hotel6 = new Hotel("HL Madrid Serrano", "Madrid", 87 );
		Hotel hotel2 = new Hotel( "HL Barcelona", "Barcelona", 45);
		Hotel hotel3 = new Hotel( "HL Bilbao", "Bilbao", 66);
		Hotel hotel4 = new Hotel( "HL Valencia", "Valencia", 95);
		Hotel hotel5 = new Hotel( "HL Sevilla", "Sevilla", 95);
		
		
		List<Hotel> listaH = new ArrayList();
		listaH.add(hotel1);
		listaH.add(hotel2);
		listaH.add(hotel3);
		listaH.add(hotel4);
		listaH.add(hotel5);
		listaH.add(hotel6);
		
		Object[] fila;
		
		for (Hotel hotel : listaH) {
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

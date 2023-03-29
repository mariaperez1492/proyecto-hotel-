package es.deusto.spq.client.gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
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
		comboBox.addItem("Barcelona");
		comboBox.addItem("Bilabo");
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
		
		model.addRow(new Object[]{"HL Madrid", "Madrid", "87"});
		model.addRow(new Object[]{"HL Barcelona", "Barcelona", "45"});
		model.addRow(new Object[]{"HL Bilbao", "Bilbao", "66"});
		model.addRow(new Object[]{"HL Valencia", "Valencia", "95"});
		
		JScrollPane scrollPane = new JScrollPane(table);
		panelLista.add(scrollPane);
		
	}
}

package es.deusto.spq.client.gui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentLogin extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	
	public static void main(String[] args) {
		VentLogin v = new VentLogin();
		v.setVisible(true);
	}
	
	public VentLogin() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 650);
		getContentPane().setLayout(null);
		
		Image imgStrava = new ImageIcon(VentLogin.class.getResource("logo.png")).getImage();
		ImageIcon iconStrava = new ImageIcon(imgStrava.getScaledInstance(350, 210, Image.SCALE_SMOOTH));
		JLabel lblNewLabel = new JLabel(iconStrava);
		lblNewLabel.setBounds(312, 59, 400, 200);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(383, 295, 254, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(383, 365, 254, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("DNI");
		lblNewLabel_1.setBounds(384, 270, 49, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Contrase\u00F1a");
		lblNewLabel_2.setBounds(383, 340, 78, 14);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Inicio Sesi\u00F3n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(462, 434, 118, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Registrar");
		btnNewButton_1.setBounds(462, 487, 118, 23);
		getContentPane().add(btnNewButton_1);
		
		
		
	}
}

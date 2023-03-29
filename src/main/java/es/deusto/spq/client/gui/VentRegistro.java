package es.deusto.spq.client.gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;

public class VentRegistro extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel lblNewLabel_1_2;
	private JLabel lblNewLabel_1_3;
	private JButton btnAtras;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentRegistro frame = new VentRegistro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentRegistro() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 650);
		getContentPane().setLayout(null);
		
		Image imgStrava = new ImageIcon(VentLogin.class.getResource("logo.png")).getImage();
		ImageIcon iconStrava = new ImageIcon(imgStrava.getScaledInstance(350, 210, Image.SCALE_SMOOTH));
		JLabel lblNewLabel = new JLabel(iconStrava);
		lblNewLabel.setBounds(555, 197, 400, 200);
		getContentPane().add(lblNewLabel);
		
		/*
		 * Image imgflecha = new
		 * ImageIcon(VentLogin.class.getResource("flecha.png")).getImage(); ImageIcon
		 * iconflecha = new ImageIcon(imgStrava.getScaledInstance(50, 50,
		 * Image.SCALE_SMOOTH)); JLabel lblNewLabel1 = new JLabel(iconStrava);
		 * lblNewLabel.setBounds(312, 59, 400, 200); getContentPane().add(lblNewLabel);
		 */
		
		JButton btnNewButton = new JButton("Registarse");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(463, 531, 122, 27);
		getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(449, 292, 149, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(449, 351, 149, 19);
		getContentPane().add(textField_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(449, 407, 149, 19);
		getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(449, 465, 149, 19);
		getContentPane().add(passwordField_1);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(449, 269, 96, 13);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("DNI");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(449, 328, 96, 13);
		getContentPane().add(lblNewLabel_1_1);
		
		lblNewLabel_1_2 = new JLabel("Contraseña");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(449, 384, 96, 13);
		getContentPane().add(lblNewLabel_1_2);
		
		lblNewLabel_1_3 = new JLabel("Confirmar contraseña");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(449, 442, 149, 13);
		getContentPane().add(lblNewLabel_1_3);
		
		btnAtras = new JButton("Atrás");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAtras.setBounds(783, 531, 122, 27);
		getContentPane().add(btnAtras);

		setContentPane(contentPane);
	}
}

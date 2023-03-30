package es.deusto.spq.client.gui;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.deusto.spq.server.Resource;
import es.deusto.spq.server.jdo.ClienteDAO;

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
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//this.setSize(1000, 650);
		//getContentPane().setLayout(null);
	    
		Resource resource = new Resource();
		JPanel contentPane = new JPanel();
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    this.setSize(1000, 650);
	    contentPane.setLayout(null);
	    // rest of the code
	    setContentPane(contentPane);
	    
	    ClienteDAO cliente = new ClienteDAO(textField.getText(), passwordField.getText(), textField_1.getText());
		
		//Image imgStrava = new ImageIcon(VentLogin.class.getResource("logo.png")).getImage();
//		ImageIcon iconStrava = new ImageIcon(imgStrava.getScaledInstance(350, 210, Image.SCALE_SMOOTH));
//		JLabel lblNewLabel = new JLabel(iconStrava);
//		lblNewLabel.setBounds(328, 58, 400, 200);
		
		/*
		 * Image imgflecha = new
		 * ImageIcon(VentLogin.class.getResource("flecha.png")).getImage(); ImageIcon
		 * iconflecha = new ImageIcon(imgStrava.getScaledInstance(50, 50,
		 * Image.SCALE_SMOOTH)); JLabel lblNewLabel1 = new JLabel(iconStrava);
		 * lblNewLabel.setBounds(312, 59, 400, 200); getContentPane().add(lblNewLabel);
		 */
		
		JButton btnNewButton = new JButton("Registarse");
		btnNewButton.addActionListener(new ActionListener() {
		
		    public void actionPerformed(ActionEvent e) {
		        VentLogin ventanaLogin = new VentLogin();
		        ventanaLogin.setVisible(true);
		        dispose(); // cierra la ventana actual (VentRegistro)
		    }
		});
		
		
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(463, 531, 122, 27);
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Obtener la contraseña ingresada por el usuario
		        char[] passwordChars = passwordField.getPassword();
		        String password = new String(passwordChars);

		        // Verificar si la contraseña cumple con los requisitos
		        if (password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*\\d.*")) {
		            JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 8 caracteres, una letra mayúscula, una letra minúscula y un número.");
		        } else {
		            // La contraseña es válida
		            // Haga algo aquí, como guardar la contraseña en una base de datos o permitir el acceso al usuario.

		            // Ejemplo: mostrar un mensaje de éxito
		            JOptionPane.showMessageDialog(null, "Contraseña válida. Registro completado exitosamente.");
		        }
		    }
		});
		


		
		textField = new JTextField();
		textField.setBounds(449, 292, 149, 19);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(449, 351, 149, 19);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(449, 407, 149, 19);
		// Obtener la contraseña ingresada por el usuario
		
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(449, 465, 149, 19);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(449, 269, 96, 13);
		
		JLabel lblNewLabel_1_1 = new JLabel("DNI");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(449, 328, 96, 13);
		
		lblNewLabel_1_2 = new JLabel("Contraseña");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(449, 384, 96, 13);
	
		
		lblNewLabel_1_3 = new JLabel("Confirmar contraseña");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(449, 442, 149, 13);
		
		btnAtras = new JButton("Atrás");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAtras.setBounds(783, 531, 122, 27);
		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        dispose();  // cierra la ventana VentRegistro
		        VentLogin v = new VentLogin();
		        v.setVisible(true);  // muestra la ventana VentLogin
		    }
		});
		btnAtras.setBounds(783, 531, 122, 27);
		//btnAtras.setBounds(362, 487, 118, 23);
		getContentPane().add(btnAtras);
		
		
		//contentPane.add(lblNewLabel);
		contentPane.add(btnNewButton);
		contentPane.add(textField);
		contentPane.add(textField_1);
		contentPane.add(passwordField);
		contentPane.add(passwordField_1);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblNewLabel_1_1);
		contentPane.add(lblNewLabel_1_2);
		contentPane.add(lblNewLabel_1_3);
		contentPane.add(btnAtras);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				resource.registerUser(cliente);
			}
		});
		
	}
}

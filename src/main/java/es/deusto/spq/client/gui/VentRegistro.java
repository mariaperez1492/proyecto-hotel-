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

import es.deusto.spq.pojo.ClienteData;
import es.deusto.spq.server.Resource;
import es.deusto.spq.server.jdo.Cliente;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;

public class VentRegistro extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JPasswordField txtContrasenya;
	private JPasswordField txtContrasenya2;
	private JLabel lblContrasenya;
	private JLabel lblConstrasenya2;
	private JButton btnAtras;
	private static ClienteData clienteData;

	public VentRegistro() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    this.setSize(1000, 650);
		
		Resource resource = new Resource();
		
		JPanel contentPane = new JPanel();
	    contentPane.setLayout(null);
	    setContentPane(contentPane);
	    
		Image imgLogo = new ImageIcon("src/main/img/logo.png").getImage();
		ImageIcon iconLog = new ImageIcon(imgLogo.getScaledInstance(350, 210, Image.SCALE_SMOOTH));
		JLabel lblLogo = new JLabel(iconLog);
		lblLogo.setBounds(328, 58, 400, 200);
		
		Image imgFlecha = new ImageIcon("src/main/img/flecha.png").getImage(); 
		ImageIcon iconFlecha = new ImageIcon(imgFlecha.getScaledInstance(50, 50, Image.SCALE_SMOOTH)); 
		JLabel lblFlecha = new JLabel(iconFlecha);
		lblLogo.setBounds(312, 59, 400, 200); 
		getContentPane().add(lblFlecha);
		 
		JButton btnRegistro = new JButton("Registarse");	
		btnRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegistro.setBounds(463, 531, 122, 27);
		
		btnRegistro.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Obtener la contraseña ingresada por el usuario
		        char[] passwordChars = txtContrasenya.getPassword();
		        String password = new String(passwordChars);

		        // Verificar si la contraseña cumple con los requisitos
		        if (password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*\\d.*")) {
		            JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 8 caracteres, una letra mayúscula, una letra minúscula y un número.");
		        } else {
		            // La contraseña es válida
		            // Haga algo aquí, como guardar la contraseña en una base de datos o permitir el acceso al usuario
		            // Ejemplo: mostrar un mensaje de éxito
		            JOptionPane.showMessageDialog(null, "Contraseña válida. Registro completado exitosamente.");
		            clienteData = new ClienteData(txtDni.getText(), txtNombre.getText(), txtContrasenya.getText());
		            VentLogin ventanaLogin = new VentLogin();
			        ventanaLogin.setVisible(true);
			        dispose(); // cierra la ventana actual (VentRegistro)
			        resource.registerUser(clienteData);
		        }
		    }
		});
		
		txtNombre = new JTextField();
		txtNombre.setBounds(449, 292, 149, 19);
		txtNombre.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(449, 351, 149, 19);
		
		txtContrasenya = new JPasswordField();
		txtContrasenya.setBounds(449, 407, 149, 19);
		// Obtener la contraseña ingresada por el usuario
		
		txtContrasenya2 = new JPasswordField();
		txtContrasenya2.setBounds(449, 465, 149, 19);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(449, 269, 96, 13);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDni.setBounds(449, 328, 96, 13);
		
		lblContrasenya = new JLabel("Contraseña");
		lblContrasenya.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasenya.setBounds(449, 384, 96, 13);
	
		
		lblConstrasenya2 = new JLabel("Confirmar contraseña");
		lblConstrasenya2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConstrasenya2.setBounds(449, 442, 149, 13);
		
		btnAtras = new JButton("Atrás");
		btnAtras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAtras.setBounds(786, 531, 122, 27);
		
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
		
		contentPane.add(lblLogo);
		contentPane.add(btnRegistro);
		contentPane.add(txtNombre);
		contentPane.add(txtDni);
		contentPane.add(txtContrasenya);
		contentPane.add(txtContrasenya2);
		contentPane.add(lblNombre);
		contentPane.add(lblDni);
		contentPane.add(lblContrasenya);
		contentPane.add(lblConstrasenya2);
		contentPane.add(btnAtras);
		
	}
}

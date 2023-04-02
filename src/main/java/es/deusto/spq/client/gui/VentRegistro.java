package es.deusto.spq.client.gui;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
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
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;

import es.deusto.spq.pojo.UsuarioData;
import es.deusto.spq.pojo.HotelData;
import es.deusto.spq.server.jdo.Usuario;

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
	private static UsuarioData clienteData;
	
	protected static final Logger logger = LogManager.getLogger();
	private Client client;
	private WebTarget webTarget;

	public VentRegistro(String hostname, String port) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    this.setSize(1000, 650);

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
		
		btnRegistro.addActionListener(new ActionListener() {
			
		    public void actionPerformed(ActionEvent e) {
		    	String password = txtContrasenya.getText();
		    	String contrasenya = txtContrasenya.getText();
		        String contrasenya2 = txtContrasenya2.getText();
		    	
		    	if (password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*\\d.*")) {
		    		JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 8 caracteres, una letra mayúscula, una letra minúscula y un número.");
		    	
		    	} else if(!contrasenya.equals(contrasenya2)){
		    		JOptionPane.showMessageDialog(null, "Las contraseñas introducidas no coinciden");
		    	
		    	} else {
		    		WebTarget registerUserWebTarget = webTarget.path("register");
		    		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		        	
		    		Usuario usuarioDAO = new Usuario();
		    		usuarioDAO.setDni(txtDni.getText());
		    		usuarioDAO.setContrasenya(txtContrasenya.getText());
		    		usuarioDAO.setNombre(txtNombre.getText());
		    		Response response = invocationBuilder.post(Entity.entity(usuarioDAO, MediaType.APPLICATION_JSON));
		    		
		    		if (response.getStatus() != Status.OK.getStatusCode()) {
		    			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		    		} else {
		    			logger.info("User correctly registered");
		    		}
		    		
		    		VentLogin ventanaLogin = new VentLogin(hostname, port);
			        ventanaLogin.setVisible(true);
			        dispose(); 
		    	}
		    }
		});
		
		
//		btnRegistro.addActionListener(new ActionListener() {
//		    public void actionPerformed(ActionEvent e) {
//		        // Obtener la contraseña ingresada por el usuario
//		        char[] passwordChars = txtContrasenya.getPassword();
//		        String password = new String(passwordChars);
//		        
//		        String contrasenya = txtContrasenya.getText();
//		        String contrasenya2 = txtContrasenya2.getText();
//		        String dni = txtDni.getText();
//
//
////		         Verificar si la contraseña cumple con los requisitos
//		        if (password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*\\d.*")) {
//		            JOptionPane.showMessageDialog(null, "La contraseña debe tener al menos 8 caracteres, una letra mayúscula, una letra minúscula y un número.");
//		        }
//		        else if(true){
//		        	
//		    		Usuario usuario2 = new Usuario();
//		    		usuario2.setContrasenya(txtContrasenya.getText());
//		    		usuario2.setNombre(txtNombre.getText());
//		    		usuario2.setDni(txtDni.getText());
//		    		
//		        	WebTarget registerUserWebTarget2 = webTarget.path("getUsuario");
//		    		Invocation.Builder invocationBuilder2 = registerUserWebTarget2
//		    			    .queryParam("dni", usuario2.getDni())
//		    			    .queryParam("contrasenya", usuario2.getContrasenya())
//		    			    .queryParam("nombre", usuario2.getNombre())
//		    				.request(MediaType.APPLICATION_JSON);
//		    		
//		    		Response response2 = invocationBuilder2.get();
//		    		
//		        	JOptionPane.showMessageDialog(null, usuario2.getDni()+"-----" + response2 +"-----" +response2.getHeaderString(dni));
//
//		    				    		
//		    		if (response2.equals(usuario2)){
//			        	JOptionPane.showMessageDialog(null, "El usuario ya está registrado");
//
//		    		}
//		        	JOptionPane.showMessageDialog(null, "El usuario ya está registrado");
//		        } else if(!contrasenya.equals(contrasenya2)){
//		        	JOptionPane.showMessageDialog(null, "Las contraseñas introducidas no coinciden");
//		   
//		        }else {
//		            // La contraseña es válida
//		            // Haga algo aquí, como guardar la contraseña en una base de datos o permitir el acceso al usuario
//		            // Ejemplo: mostrar un mensaje de éxito
//		        	
//		        	WebTarget registerUserWebTarget = webTarget.path("register");
//		    		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
//		        	
//		    		Usuario usuarioDAO = new Usuario();
//		    		usuarioDAO.setDni(txtDni.getText());
//		    		usuarioDAO.setContrasenya(txtContrasenya.getText());
//		    		usuarioDAO.setNombre(txtNombre.getText());
//		    		Response response = invocationBuilder.post(Entity.entity(usuarioDAO, MediaType.APPLICATION_JSON));
//		    		
//		    		if (response.getStatus() != Status.OK.getStatusCode()) {
//		    			logger.error("Error connecting with the server. Code: {}", response.getStatus());
//		    		} else {
//		    			logger.info("User correctly registered");
//		    		}
//		    		
////		        	resource.registerUser(clienteData);
//		            JOptionPane.showMessageDialog(null, "Contraseña válida. Registro completado exitosamente.");
////		            clienteData = new UsuarioData(txtDni.getText(), txtNombre.getText(), txtContrasenya.getText());
//		            
//		            VentLogin ventanaLogin = new VentLogin(hostname, port);
//			        ventanaLogin.setVisible(true);
//			        dispose(); // cierra la ventana actual (VentRegistro)
//			        
//		        }
//		    }
//		});
		
		btnAtras.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        dispose();  // cierra la ventana VentRegistro
		        VentLogin v = new VentLogin(hostname, port);
		        v.setVisible(true);  // muestra la ventana VentLogin
		    }
		});	
	}
}

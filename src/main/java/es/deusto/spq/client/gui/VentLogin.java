package es.deusto.spq.client.gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.deusto.spq.pojo.UsuarioData;

import javax.ws.rs.client.Client;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentLogin extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	protected static final Logger logger = LogManager.getLogger();
	private Client client;
	private WebTarget webTarget;
	
	private JTextField txtDni;
	private JPasswordField txtConstrasenya;
	
	/**
	 * Main
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		if (args.length != 2) {
			logger.info("Use: java Client.Client [host] [port]");
			System.exit(0);
		}
		
		String hostname = args[0];
		String port = args[1];
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentLogin frame = new VentLogin(hostname, port);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}
	
	/**
	 * Constructor
	 */
	
	public VentLogin(String hostname, String port) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 650);
		getContentPane().setLayout(null);
		
		Image imgLogo = new ImageIcon("src/main/img/logo.png").getImage();
		ImageIcon iconLogo = new ImageIcon(imgLogo.getScaledInstance(350, 210, Image.SCALE_SMOOTH));
		JLabel lblLogo = new JLabel(iconLogo);
		lblLogo.setBounds(312, 59, 400, 200);
		getContentPane().add(lblLogo);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(384, 270, 49, 14);
		getContentPane().add(lblDni);
		
		JLabel lblContrasenya = new JLabel("Contrase\u00F1a");
		lblContrasenya.setBounds(383, 340, 78, 14);
		getContentPane().add(lblContrasenya);
		
		txtDni = new JTextField();
		txtDni.setBounds(383, 295, 254, 20);
		getContentPane().add(txtDni);
		txtDni.setColumns(10);
		
		txtConstrasenya = new JPasswordField();
		txtConstrasenya.setBounds(383, 365, 254, 20);
		getContentPane().add(txtConstrasenya);
		txtConstrasenya.setColumns(10);
		
		JButton btnInicio = new JButton("Inicio Sesi\u00F3n");
		btnInicio.setBounds(462, 452, 118, 23);
		getContentPane().add(btnInicio);
		
		/**
		 * Pulsar botón inicio
		 */
		
		btnInicio.addActionListener(new ActionListener(){
			
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				if(loginUser(txtDni.getText(), txtConstrasenya.getText())== 1) {
					UsuarioData u = new UsuarioData(txtDni.getText(), txtConstrasenya.getText());
					VentEleccion ventEleccion = new VentEleccion(hostname, port, u);
					ventEleccion.setVisible(true);
					dispose();
				} else if(loginUser(txtDni.getText(), txtConstrasenya.getText())== 2) {
					VentListadoAdmin ventAdmin = new VentListadoAdmin(hostname, port);
					ventAdmin.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "La contraseña o DNI es incorrecta.");
				}
			}
		});
		
		JButton btnRegistro = new JButton("Registrar");
		btnRegistro.setBounds(462, 487, 118, 23);
		getContentPane().add(btnRegistro);
		
		/**
		 * Pulsar botón registro
		 */
		
		btnRegistro.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				VentRegistro ventRegistro = new VentRegistro(hostname, port);
				ventRegistro.setVisible(true);
				dispose(); 
			}
		});
	}
	

	public int loginUser(String dni, String contrasenya) {
		WebTarget target = webTarget.path("login")
				.queryParam("dni", dni)
				.queryParam("contrasenya", contrasenya);
			
		Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
			
		Response response = invocationBuilder.get();
			
		if (response.getStatus() == Response.Status.OK.getStatusCode()) {
			if (dni.equals("0000")) {
				return 2;
			} else {
				return 1;
			}
		} else {
			return 0;
		}
	}
}

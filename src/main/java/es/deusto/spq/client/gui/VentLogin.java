package es.deusto.spq.client.gui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.cj.xdevapi.Client;

import es.deusto.spq.server.Resource;
import es.deusto.spq.server.jdo.Cliente;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.TreeMap;
import java.awt.event.ActionEvent;

public class VentLogin extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextField txtDni;
	private JTextField txtConstrasenya;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentLogin frame = new VentLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public VentLogin() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 650);
		getContentPane().setLayout(null);
		
		Resource resource = new Resource();
		
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
		
		txtConstrasenya = new JTextField();
		txtConstrasenya.setBounds(383, 365, 254, 20);
		getContentPane().add(txtConstrasenya);
		txtConstrasenya.setColumns(10);
		
		JButton btnInicio = new JButton("Inicio Sesi\u00F3n");
		btnInicio.setBounds(462, 452, 118, 23);
		getContentPane().add(btnInicio);
		
		btnInicio.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e) {
	             
	                // Enviar la solicitud de inicio de sesión al servidor
//	                boolean success = resource.loginUser(cliente);
//	                if (success) {
//
//	                } else {
//	                    // Mostrar un mensaje de error
//	                    JOptionPane.showMessageDialog(LoginFrame.this, "El DNI o la contraseña son incorrectos", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
//	                }
	             	
	            }
	        });
		
		JButton btnRegistro = new JButton("Registrar");
		btnRegistro.setBounds(462, 487, 118, 23);
		getContentPane().add(btnRegistro);
		
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentRegistro ventRegistro = new VentRegistro();
				ventRegistro.setVisible(true);
				dispose(); // Cierra la ventana actual (VentLogin)
			}
		});
	}
}

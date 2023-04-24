package es.deusto.spq.client.gui;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class VentPago extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	protected static final Logger logger = LogManager.getLogger();
	private Client client;
	private WebTarget webTarget;

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public VentPago(String hostname, String port) {
		
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 650);
		
		JPanel panelSup = new JPanel();
		getContentPane().add(panelSup, BorderLayout.NORTH);
		
		Image imgLogo = new ImageIcon("src/main/img/logo.png").getImage();
		ImageIcon iconLogo = new ImageIcon(imgLogo.getScaledInstance(180, 110, Image.SCALE_SMOOTH));
		JLabel lblLogo = new JLabel(iconLogo);
		lblLogo.setSize(180, 110);
		panelSup.add(lblLogo);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel panelLista = new JPanel();
		getContentPane().add(panelLista, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Elija los servicios que desee: ");
		
		panelLista.add(lblNewLabel_1);

		setContentPane(contentPane);
		
		JCheckBox breakfastBox = new JCheckBox("Desayuno (12€/día)");
		JCheckBox poolBox = new JCheckBox("Piscina (10€/día)");
		JCheckBox spaBox = new JCheckBox("Spa (30€/día)");
		JCheckBox gymBox = new JCheckBox("Gimnasio (15€/día)");
        
        // Configuración del layout
        getContentPane().setLayout(new GridLayout(4, 1));
        
        JLabel lblNewLabel = new JLabel("Resumen de su reserva:");
        contentPane.add(lblNewLabel);
        

        
        // Agregar los componentes a la ventana
        getContentPane().add(breakfastBox);
        getContentPane().add(poolBox);
        getContentPane().add(spaBox);
        getContentPane().add(gymBox);
        
		JPanel panelSur = new JPanel();
		getContentPane().add(panelSur, BorderLayout.SOUTH);
		
		JPanel panelNorte = new JPanel();
		getContentPane().add(panelNorte, BorderLayout.NORTH);
		
		JButton btnCerrarSesion = new JButton("Pagar");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentLogin ventLogin = new VentLogin(hostname, port);
				ventLogin.setVisible(true);
				dispose();
			}
		});
		panelSur.add(btnCerrarSesion);    
		
		
        

	}

}

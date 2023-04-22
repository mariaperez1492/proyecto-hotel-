package es.deusto.spq.client.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentListadoAdmin extends JFrame {

	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem m1,m2;
	
	private PanelHoteles ph;
	private PanelReservas pr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentListadoAdmin frame = new VentListadoAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

public VentListadoAdmin() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
		setContentPane(contentPane);
		
		ph = new PanelHoteles();
		pr = new PanelReservas();
		
		JPanel panelNorte = new JPanel();
		getContentPane().add(panelNorte, BorderLayout.NORTH);
		
		JPanel panelSur = new JPanel();
		getContentPane().add(panelSur, BorderLayout.SOUTH);
		
		JPanel panelPrincipal = new JPanel();
		getContentPane().add(panelPrincipal, BorderLayout.CENTER);
		
		Image imgLogo = new ImageIcon("../logo.png").getImage();
		ImageIcon iconLogo = new ImageIcon(imgLogo.getScaledInstance(180, 110, Image.SCALE_SMOOTH));
		JLabel lblLogo = new JLabel(iconLogo);
		lblLogo.setSize(180, 110);
		panelNorte.add(lblLogo);
		
		
		
		menuBar = new JMenuBar();
		menu = new JMenu("Opciones");
		
		
		m1 = new JMenuItem("Listado de Hoteles");
		menu.add(m1);
		m2 = new JMenuItem("Listado de Reservas");
		menu.add(m2);
		menuBar.add(menu);
		panelNorte.add(menuBar);
		
		
		
		m1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panelPrincipal.removeAll();
				panelPrincipal.add(ph);
				panelPrincipal.updateUI();
			}
		});
		
		m2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panelPrincipal.removeAll();
				panelPrincipal.add(pr);
				panelPrincipal.updateUI();
			}
		});
		
		
		
		
		
		
		
		
		
		this.setVisible(true);	
	}

}

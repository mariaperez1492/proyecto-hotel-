package es.deusto.spq.client.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class PanelReservas extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelReservas() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		add(panelNorte, BorderLayout.NORTH);
		
		JPanel panelSur = new JPanel();
		add(panelSur, BorderLayout.SOUTH);
		
		JPanel panelIzquierda = new JPanel();
		add(panelIzquierda, BorderLayout.WEST);
		
		JPanel panelDerecha = new JPanel();
		add(panelDerecha, BorderLayout.EAST);
		
		JPanel panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);

	}

}


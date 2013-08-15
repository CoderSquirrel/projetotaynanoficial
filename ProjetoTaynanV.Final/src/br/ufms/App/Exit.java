package br.ufms.App;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Exit extends JPanel {
	private static final long serialVersionUID = 1L;

	public Exit() {
		setBounds(100, 100, 120, 20);
		setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("");
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});
		label.setIcon(new ImageIcon(getClass().getResource("/img/sair.png")));
		add(label, BorderLayout.EAST);

	}
}

package br.ufms.App;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Resutado extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	public Resutado() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBounds(0, 161, 784, 401);
		panel.setBorder(BorderFactory.createTitledBorder("Resultados"));
		panel.setLayout(new BorderLayout());
		panel.setBounds(0, 77, 778, 485);
		this.add(panel);
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(0, 76, 778, 600);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		panel.add(tabbedPane);

	}
	
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
	
	public void clean(){
		tabbedPane.removeAll();
	}

}

package br.ufms.App;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Resutado extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tabela;
	public Resutado() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBounds(0, 161, 784, 401);
		panel.setBorder(BorderFactory.createTitledBorder("Resultados"));
		panel.setLayout(new BorderLayout());
		panel.setBounds(0, 77, 778, 485);
		this.add(panel);

		tabela = new JTable(new DefaultTableModel(new Object[][] {},
				new String[] { "Classificação", "Palavra",
						"Frequência principal", "Frequência Secundária" }) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return true;
			}
		});
		tabela.setBounds(0, 76, 778, 486);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(3);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(20);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(20);
		panel.add(new JScrollPane(tabela), BorderLayout.CENTER);

	}
	public JTable getTabela() {
		return tabela;
	}
	
	

}

package br.ufms.App;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Opcoes extends JPanel {

	private static final long serialVersionUID = 1L;
	private Entrada entrada;
	private Saida saida;
	private JPanel entradaSaida;
	private TipoRanking ranking;
	private String caminhoEntrada;
	private String caminhoSaida;
	private App app;
	private Acoes acoes;

	public Opcoes(App app) {
		acoes = new Acoes();
		this.app = app;
		setLayout(new GridLayout(0, 1, 0, 0));
		entradaSaida = new JPanel();
		entradaSaida.setLayout(new GridLayout(0, 1, 0, 0));
		entrada = new Entrada();
		entradaSaida.add(entrada);
		saida = new Saida();
		entradaSaida.add(saida);
		add(entradaSaida);
		ranking = new TipoRanking(acoes);
		add(ranking);

	}

	class Acoes {
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == ranking.getJbGerarRanking()) {
					if (caminhoEntrada.equalsIgnoreCase("")) {
						// lblAvisoArquivo.setText("Escolha a pasta");
					} else {
						// lblAvisoArquivo.setText("");
					}
					if (caminhoSaida.equalsIgnoreCase("")) {
						// lblAvisoSalvar.setText("Escolha a pasta");
					} else {
						// lblAvisoSalvar.setText("");
					}
					if (!ranking.getRdbtnRankingGeral().isSelected()
							&& !ranking.getRdbtnRankingIndividual()
									.isSelected()) {
						// lblAvisoTipoTabela.setText("Escolha o tipo");
					} else {
						// lblAvisoTipoTabela.setText("");
					}
					if (ranking.getChckbxPrVisualizar().isSelected()) {
						JOptionPane.showMessageDialog(app, "teste");
					}

				}

			}

		};
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ranking.getChckbxPrVisualizar().isSelected()) {
					app.setLocation(app.getWidth() / 3, 0);
					app.setSize(1024, 768);
				} else {
					app.setLocation(app.getWidth() / 3, app.getHeight() / 3);
					app.setSize(700, 226);
				}
			}
		};
	}
}

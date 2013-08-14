package br.ufms.App;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.ufms.Arquivo.ArquivoTotal;

public class Opcoes extends JPanel {

	private static final long serialVersionUID = 1L;
	private Entrada entrada = new Entrada();
	private Saida saida;
	private JPanel entradaSaida;
	private TipoRanking ranking;
	private App app;
	private Acoes acoes;
	private ArquivoTotal arquivo = new ArquivoTotal();

	public Opcoes(App app) {
		acoes = new Acoes();
		this.app = app;
		setLayout(new GridLayout(0, 1, 0, 0));
		entradaSaida = new JPanel();
		entradaSaida.setLayout(new GridLayout(0, 1, 0, 0));
		entradaSaida.add(entrada);
		saida = new Saida();
		entradaSaida.add(saida);
		add(entradaSaida);
		ranking = new TipoRanking(acoes);
		add(ranking);

	}

	/**
	 * verifica se todos os campos foram preenchidos
	 * 
	 * @return verdadeiro se todos foram falso senão
	 */
	private boolean isOk() {
		if (entrada.getCaminhoEntrada().equalsIgnoreCase("")) {
			return false;
		}
		if (saida.getCaminhoSaida().equalsIgnoreCase("")) {
			return false;
		}
		if (!ranking.getRdbtnRankingGeral().isSelected()
				&& !ranking.getRdbtnRankingIndividual().isSelected()) {
			return false;
		}
		return true;
	}

	class Acoes {
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == ranking.getJbGerarRanking()) {
					if (entrada.getCaminhoEntrada().equalsIgnoreCase("")) {
						entrada.getLbErro().setVisible(true);
					} else {
						entrada.getLbErro().setVisible(false);
					}
					if (saida.getCaminhoSaida().equalsIgnoreCase("")) {
						saida.getLbErro().setVisible(true);
					} else {
						saida.getLbErro().setVisible(false);
					}
					if (ranking.getChckbxPrVisualizar().isSelected()) {
						JOptionPane.showMessageDialog(app, "teste");
					}
					if (isOk()) {
						if (ranking.getRdbtnRankingGeral().isSelected()) {
							arquivo = new ArquivoTotal();
							arquivo.abrirArquivosRankingGeral(entrada
									.getCaminhoEntrada());
							entrada.habilitaDasabilita();
							saida.habilitaDasabilita();
							arquivo.exportarRankingGeral(saida
									.getCaminhoSaida());
							ranking.habilitaDasabilita();
						} else if (ranking.getRdbtnRankingIndividual()
								.isSelected()) {
							arquivo = new ArquivoTotal();
							arquivo.abrirArquivoRankingIndividual(
									saida.getCaminhoSaida(),
									entrada.getCaminhoEntrada());
							entrada.habilitaDasabilita();
							saida.habilitaDasabilita();
							ranking.habilitaDasabilita();
						}

					}
				}

			}

		};
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ranking.getChckbxPrVisualizar().isSelected()) {
					app.setLocation(app.getWidth() / 3, 0);
					app.setSize(1024, 728);
					app.getResultado().setVisible(true);
				} else {
					app.setLocation(app.getWidth() / 3, app.getHeight() / 3);
					app.setSize(700, 300);
					app.getResultado().setVisible(false);
				}
			}
		};
	}
}

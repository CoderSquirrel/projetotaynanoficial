package br.ufms.App;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.ufms.Arquivo.ArquivoTotal;
import br.ufms.Classes.LinhaRankingGeral;

public class Opcao extends JPanel {

	private static final long serialVersionUID = 1L;
	private Entrada entrada = new Entrada();
	private Saida saida;
	private JPanel entradaSaida;
	private TipoRanking ranking;
	private App app;
	private Acoes acoes;
	private ArquivoTotal arquivo = new ArquivoTotal();
	private JTable tabela;

	public Opcao(App app) {
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

	public void mostrarPalavras(List<LinhaRankingGeral> palavras) {
		tabela = app.getResultado().getTabela();
		DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
		int i = 1;
		for (LinhaRankingGeral p : palavras) {
			modelo.addRow(new Object[] { i, p.getPalavra(), p.getQuantidade(),
					p.getFrequenciaSecundaria() });
			i++;
		}
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
					if (isOk()) {
						if (ranking.getRdbtnRankingGeral().isSelected()) {
							arquivo = new ArquivoTotal();
							arquivo.abrirArquivosRankingGeral(entrada
									.getCaminhoEntrada());
							entrada.habilitaDasabilita();
							saida.habilitaDasabilita();

							ranking.habilitaDasabilita();
							mostrarPalavras(arquivo.exportarRankingGeral(saida
									.getCaminhoSaida()));

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

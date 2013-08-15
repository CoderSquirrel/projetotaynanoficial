package br.ufms.App;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	private boolean gerouRanking;

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

	public void individual(List<JScrollPane> scrolls) {
		ranking.getProgressBar().setVisible(false);
		for (JScrollPane scroll : scrolls) {
			app.getResultado().getTabbedPane()
					.addTab(scroll.getName(), null, scroll, null);
		}
	}

	public void geral(List<LinhaRankingGeral> linhas) {
		ranking.getProgressBar().setVisible(false);
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("Indice");
		modelo.addColumn("Palavra");
		modelo.addColumn("Frequência");
		modelo.addColumn("Arquivo");

		JTable tabela = new JTable(modelo);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(3);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
		for (LinhaRankingGeral linha : linhas) {
			modelo.addRow(new Object[] { linha.getI(), linha.getPalavra(),
					linha.getQuantidade(), linha.getFrequenciaSecundaria() });
		}
		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setSize(700, 380);
		app.getResultado().getTabbedPane().addTab("Ranking Geral", scrollPane);

	}

	class Acoes {
		ActionListener actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == ranking.getJbGerarRanking()) {
					if (entrada.getJtfCaminhoEntrada().getText()
							.equalsIgnoreCase("")) {
						entrada.getLbErro().setVisible(true);
					} else {
						entrada.getLbErro().setVisible(false);
					}
					if (saida.getCaminhoSaida().equalsIgnoreCase("")) {
						saida.getLbErro().setVisible(true);
					} else {
						saida.getLbErro().setVisible(false);
					}
					if (entrada.getLbErro().isVisible()
							|| saida.getLbErro().isVisible()) {
						return;
					}
					ranking.getProgressBar().setVisible(true);
					Thread th = new Thread(new Runnable() {
						@Override
						public void run() {
							if (!ranking.getJbGerarRanking().getText()
									.equalsIgnoreCase("Novo")) {
								gerouRanking = true;
								try {
									Thread.sleep(150);
								} catch (InterruptedException e1) {
								}
								if (ranking.getRdbtnRankingGeral().isSelected()) {
									arquivo = new ArquivoTotal();
									entrada.habilitaDasabilita();
									saida.habilitaDasabilita();
									ranking.habilitaDasabilita();
									arquivo.abrirArquivosRankingGeral(entrada
											.getJtfCaminhoEntrada().getText());
									arquivo.exportarRankingGeral(saida
											.getCaminhoSaida());
									geral(arquivo.getLinhasGeral());

								} else if (ranking.getRdbtnRankingIndividual()
										.isSelected()) {
									arquivo = new ArquivoTotal();
									entrada.habilitaDasabilita();
									saida.habilitaDasabilita();
									ranking.habilitaDasabilita();
									arquivo.abrirArquivoRankingIndividual(saida
											.getCaminhoSaida(), entrada
											.getJtfCaminhoEntrada().getText());
									individual(arquivo.getScrolls());
								}
								if (gerouRanking
										&& ranking.getChckbxPrVisualizar()
												.isSelected()) {
									ranking.getLbVisualizar().setVisible(false);
									app.setLocation(app.getWidth() / 3, 0);
									app.setSize(700, 728);
									app.getResultado().setVisible(true);
								}
								ranking.conclui();
							} else {
								ranking.getProgressBar().setVisible(false);
								gerouRanking = false;
								entrada.limpaCampo();
								saida.limpaCampo();
								ranking.getJbGerarRanking().setText(
										"Gerar Ranking");
								entrada.habilitaDasabilita();
								saida.habilitaDasabilita();
								ranking.habilitaDasabilita();
								app.setLocation(app.getWidth() / 3,
										app.getHeight() / 3);
								app.setSize(700, 300);
								app.getResultado().setVisible(false);
								ranking.getChckbxPrVisualizar().setSelected(
										false);
								arquivo.clean();
								app.getResultado().clean();
							}

						}
					});
					try {
						Thread.sleep(500);
						th.start();
					} catch (InterruptedException e1) {
					}
				}

			}

		};
		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (gerouRanking) {
					ranking.getLbVisualizar().setVisible(false);
					if (ranking.getChckbxPrVisualizar().isSelected()) {
						app.setLocation(app.getWidth() / 3, 0);
						app.setSize(700, 728);
						app.getResultado().setVisible(true);
					} else {
						app.setLocation(app.getWidth() / 3, app.getHeight() / 3);
						app.setSize(700, 300);
						app.getResultado().setVisible(false);
					}
				} else {
					if (ranking.getChckbxPrVisualizar().isSelected())
						ranking.getLbVisualizar().setVisible(true);
					else
						ranking.getLbVisualizar().setVisible(false);

				}
			}
		};
	}
}

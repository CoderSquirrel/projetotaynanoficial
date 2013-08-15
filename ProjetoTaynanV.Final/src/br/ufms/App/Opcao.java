package br.ufms.App;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
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
	 * @return verdadeiro se todos foram falso senÃ£o
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
	
	public void individual(List<JScrollPane> scrolls) {
        for (JScrollPane scroll : scrolls) {
        	 app.getResultado().getTabbedPane().addTab(scroll.getName(), null, scroll, null);
        }
    }

    public void geral(List<LinhaRankingGeral> linhas) {

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
							geral(arquivo.getLinhasGeral());
							

						} else if (ranking.getRdbtnRankingIndividual()
								.isSelected()) {
							arquivo = new ArquivoTotal();
							arquivo.abrirArquivoRankingIndividual(
									saida.getCaminhoSaida(),
									entrada.getCaminhoEntrada());
							entrada.habilitaDasabilita();
							saida.habilitaDasabilita();
							ranking.habilitaDasabilita();
							individual(arquivo.getScrolls());
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

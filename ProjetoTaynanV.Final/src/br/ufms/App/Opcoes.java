package br.ufms.App;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class Opcoes extends JPanel {

	private static final long serialVersionUID = 1L;
	private Entrada entrada;
	private Saida saida;
	private JPanel entradaSaida;
	private TipoRanking ranking;
	private String caminhoEntrada;
	private String caminhoSaida;


	public Opcoes() {
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

	public ActionListener acoes = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

		
			 if (e.getSource() == ranking.getJbGerarRanking()) {
				if (caminhoEntrada.equalsIgnoreCase("")) {
//					lblAvisoArquivo.setText("Escolha a pasta");
				} else {
//					lblAvisoArquivo.setText("");
				}
				if (caminhoSaida.equalsIgnoreCase("")) {
//					lblAvisoSalvar.setText("Escolha a pasta");
				} else {
//					lblAvisoSalvar.setText("");
				}
				if (!ranking.getRdbtnRankingGeral().isSelected()
						&& !ranking.getRdbtnRankingIndividual().isSelected()) {
//					lblAvisoTipoTabela.setText("Escolha o tipo");
				} else {
//					lblAvisoTipoTabela.setText("");
				}

			}

		}

	};

}

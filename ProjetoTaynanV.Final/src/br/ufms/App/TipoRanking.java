package br.ufms.App;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import br.ufms.App.Opcao.Acoes;
import br.ufms.utils.RoundedCornerButton;

/**
 * 
 * @author Jasane Schio & Gilberto Leão
 *
 */
public class TipoRanking extends JPanel {

	/**
	 * Declaração de variaveis
	 */
	private static final long serialVersionUID = 1L;
	private JRadioButton rdbtnRankingIndividual, rdbtnRankingGeral;
	private ButtonGroup radioButtons;
	private JButton jbGerarRanking;
	private JCheckBox chckbxPrVisualizar;
	private boolean habilitado;
	private JLabel lbVisualizar, lbStatusGeracao;
	private JProgressBar progressBar;
	public TipoRanking(Acoes acoes) {
		setBounds(100, 100, 740, 105);
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Tipo de Ranking", TitledBorder.LEADING, TitledBorder.TOP,
				null, new Color(0, 0, 0)));
		setLayout(null);

		rdbtnRankingGeral = new JRadioButton("Ranking Geral");
		rdbtnRankingGeral.setSelected(true);
		rdbtnRankingGeral.setBounds(6, 40, 130, 25);
		add(rdbtnRankingGeral);

		rdbtnRankingIndividual = new JRadioButton("Ranking Individual");
		rdbtnRankingIndividual.setBounds(154, 40, 160, 25);
		add(rdbtnRankingIndividual);

		radioButtons = new ButtonGroup();
		radioButtons.add(rdbtnRankingGeral);
		radioButtons.add(rdbtnRankingIndividual);

		radioButtons = new ButtonGroup();
		radioButtons.add(rdbtnRankingGeral);
		radioButtons.add(rdbtnRankingIndividual);

		chckbxPrVisualizar = new JCheckBox("Pr\u00E9 Visualizar");
		chckbxPrVisualizar.addActionListener(acoes.actionListener);
		chckbxPrVisualizar.setBounds(575, 53, 120, 23);
		add(chckbxPrVisualizar);
		
		jbGerarRanking = new RoundedCornerButton("Gerar Ranking");
		jbGerarRanking.addActionListener(acoes.actionListener);
		jbGerarRanking.setBounds(575, 21, 120, 25);
		add(jbGerarRanking);
		
		lbVisualizar = new JLabel("\u00C9 preciso Gerar Ranking Para Visualizar.");
		lbVisualizar.setIcon(new ImageIcon(getClass().getResource("/img/alert.png")));
		lbVisualizar.setBounds(6, 80, 457, 14);
		lbVisualizar.setVisible(false);
		add(lbVisualizar);
		
		lbStatusGeracao = new JLabel();
		lbStatusGeracao.setBounds(0, 90, 740, 20);
		lbStatusGeracao.setHorizontalAlignment(JLabel.CENTER);
		add(lbStatusGeracao);
		
		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setToolTipText("Processando");
		progressBar.setBounds(575, 80, 120, 14);
		progressBar.setVisible(false);
		add(progressBar);
	}

	public void habilitaDasabilita() {
		if (!habilitado) {
			rdbtnRankingGeral.setEnabled(false);
			rdbtnRankingIndividual.setEnabled(false);
			jbGerarRanking.setEnabled(false);
		} else {
			rdbtnRankingGeral.setEnabled(true);
			rdbtnRankingIndividual.setEnabled(true);
			jbGerarRanking.setEnabled(true);
		}
		this.habilitado = !habilitado;
	}
	
	public void conclui(){
		jbGerarRanking.setText("Novo");
		jbGerarRanking.setEnabled(true);
	}
	public void novo(){
		
	}

	public JButton getJbGerarRanking() {
		return jbGerarRanking;
	}

	public JRadioButton getRdbtnRankingIndividual() {
		return rdbtnRankingIndividual;
	}

	public JRadioButton getRdbtnRankingGeral() {
		return rdbtnRankingGeral;
	}

	public JCheckBox getChckbxPrVisualizar() {
		return chckbxPrVisualizar;
	}

	public JLabel getLbVisualizar() {
		return lbVisualizar;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public JLabel getLbStatusGeracao() {
		return lbStatusGeracao;
	}

	
}

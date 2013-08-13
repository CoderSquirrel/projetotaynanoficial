package br.ufms.App;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import br.ufms.utils.RoundedCornerButton;

public class TipoRanking extends JPanel {

	/**
	 * Declaração de variaveis
	 */
	private static final long serialVersionUID = 1L;
	private JRadioButton rdbtnRankingIndividual, rdbtnRankingGeral;
	private ButtonGroup radioButtons;
	private JButton jbGerarRanking;
	
	public TipoRanking(ActionListener acoes) {
		setBounds(100, 100, 740, 85);
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

		JCheckBox chckbxPrVisualizar = new JCheckBox("Pr\u00E9 Visualizar");
		chckbxPrVisualizar.setBounds(575, 53, 120, 23);
		add(chckbxPrVisualizar);
		
		jbGerarRanking = new RoundedCornerButton("Gerar Ranking");
		jbGerarRanking.setBounds(575, 21, 120, 25);
		add(jbGerarRanking);
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

}

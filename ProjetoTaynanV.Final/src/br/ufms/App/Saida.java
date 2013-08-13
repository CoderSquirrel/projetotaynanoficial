package br.ufms.App;

import java.awt.Color;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import br.ufms.utils.RoundJTextField;
import br.ufms.utils.RoundedCornerButton;

import com.jtechlabs.ui.widget.directorychooser.JDirectoryChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Saida extends JPanel {

	/**
	 * Declaração de variaveis
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnProcurarSaida;
	private JTextField jtfCaminhoSaida;
	private String caminhoSaida;

	public Saida() {
		setSize(700, 65);
		setBounds(0, 0, 700, 65);
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Saida", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		setLayout(null);

		btnProcurarSaida = new RoundedCornerButton("Procurar");
		btnProcurarSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				caminhoSaida = escolherPasta(1);
				jtfCaminhoSaida.setText(caminhoSaida);
			}
		});
		btnProcurarSaida.setBounds(575, 21, 120, 25);
		add(btnProcurarSaida);

		jtfCaminhoSaida = new RoundJTextField();
		jtfCaminhoSaida.setText("");
		jtfCaminhoSaida.setBounds(165, 21, 400, 25);
		add(jtfCaminhoSaida);
		jtfCaminhoSaida.setColumns(10);

		JLabel lblCaminhoEntrada = new JLabel("Diret\u00F3rio Saida:");
		lblCaminhoEntrada.setBounds(10, 21, 129, 25);
		add(lblCaminhoEntrada);

	}

	public String escolherPasta(int op) {
		File arquivo = null;
		arquivo = JDirectoryChooser.showDialog(this, arquivo,
				"Selecione o Diretorio",
				"Selecione o Diretorio que contem as Legendas.");
		if (arquivo == null) {
			return "";
		}
		if (arquivo.exists()) {
			caminhoSaida = arquivo.getAbsolutePath();
			

		}
		return caminhoSaida;
	}

	public String getCaminhoSaida() {
		return caminhoSaida;
	}
	
}

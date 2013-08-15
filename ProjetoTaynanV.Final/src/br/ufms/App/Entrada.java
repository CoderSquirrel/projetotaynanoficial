package br.ufms.App;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import br.ufms.utils.RoundJTextField;
import br.ufms.utils.RoundedCornerButton;

import com.jtechlabs.ui.widget.directorychooser.JDirectoryChooser;

import javax.swing.ImageIcon;

import java.awt.Color;

public class Entrada extends JPanel {

	/**
	 * Declaração de variaveis
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnProcurarEntrada;
	private JTextField jtfCaminhoEntrada;
	private JLabel lbErro;
	private boolean habilitado;

	public Entrada() {
		setBounds(100, 100, 700, 88);
		setBorder(new TitledBorder(null, "Entrada", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		setLayout(null);

		btnProcurarEntrada = new RoundedCornerButton("Procurar");
		btnProcurarEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jtfCaminhoEntrada.setText(escolherPasta());
			}
		});
		btnProcurarEntrada.setBounds(575, 21, 120, 25);
		add(btnProcurarEntrada);

		jtfCaminhoEntrada = new RoundJTextField();
		jtfCaminhoEntrada.setText("");
		jtfCaminhoEntrada.setBounds(165, 21, 400, 25);
		add(jtfCaminhoEntrada);
		jtfCaminhoEntrada.setColumns(10);

		JLabel lblCaminhoEntrada = new JLabel("Diret\u00F3rio com Legendas:");
		lblCaminhoEntrada.setBounds(10, 21, 161, 25);
		add(lblCaminhoEntrada);

		lbErro = new JLabel(
				"Diret\u00F3rio com as Legendas Precisa ser Definido.");
		lbErro.setForeground(Color.RED);
		lbErro.setIcon(new ImageIcon(getClass().getResource("/img/error.png")));
		lbErro.setBounds(10, 47, 555, 14);
		lbErro.setVisible(false);
		add(lbErro);
	}

	/**
	 * 
	 * @param op
	 *            Entrada um inteiro 1 ou 2. Se op == 1 então o JFileChooser
	 *            será aberto para abrir um arquivo. Se op == 2 será para
	 *            salvar o arquivo
	 * @return caminhoArquivo que é uma String contendo a pasta selecionada
	 */
	public String escolherPasta() {
		lbErro.setVisible(false);
		File arquivo = null;
		arquivo = JDirectoryChooser.showDialog(this, arquivo,
				"Selecione o Diretorio",
				"Selecione o Diretorio que contem as Legendas.");
		if (arquivo == null) {
			return "";
		}
		if (arquivo.exists()) {
			jtfCaminhoEntrada.setText(arquivo.getAbsolutePath());

		}
		return jtfCaminhoEntrada.getText();
	}

	public JLabel getLbErro() {
		return lbErro;
	}

	public void habilitaDasabilita() {
		if (!habilitado) {
			jtfCaminhoEntrada.setEnabled(false);
			btnProcurarEntrada.setEnabled(false);
		} else {
			jtfCaminhoEntrada.setEnabled(true);
			btnProcurarEntrada.setEnabled(true);
		}
		this.habilitado = !habilitado;
	}
	
	
	public JTextField getJtfCaminhoEntrada() {
		return jtfCaminhoEntrada;
	}

	public void limpaCampo() {
		jtfCaminhoEntrada.setText("");
	}

}

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

import javax.swing.ImageIcon;

public class Saida extends JPanel {

	/**
	 * Declaração de variaveis
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnProcurarSaida;
	private JTextField jtfCaminhoSaida;
	private String caminhoSaida = "";
	private JLabel lbErro;
	private boolean habilitado;

	public Saida() {
		setBounds(100, 100, 700, 88);
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Saida", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		setLayout(null);

		btnProcurarSaida = new RoundedCornerButton("Procurar");
		btnProcurarSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				caminhoSaida = escolherPasta();
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

		JLabel lblCaminhoEntrada = new JLabel("Diret\u00F3rio Sa\u00EDda:");
		lblCaminhoEntrada.setBounds(10, 21, 129, 25);
		add(lblCaminhoEntrada);
		
		lbErro = new JLabel("Diret\u00F3rio de Sa\u00EDda Precisa ser Definido.");
		lbErro.setIcon(new ImageIcon(System.getProperty("user.dir")+("\\img\\error.png")));
		lbErro.setForeground(Color.RED);
		lbErro.setBounds(10, 47, 555, 14);
		lbErro.setVisible(false);
		add(lbErro);

	}

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
			caminhoSaida = arquivo.getAbsolutePath();
			

		}
		return caminhoSaida;
	}
	
	public void habilitaDasabilita() {
		if (!habilitado) {
			jtfCaminhoSaida.setEnabled(false);
			btnProcurarSaida.setEnabled(false);
		} else {
			jtfCaminhoSaida.setEnabled(true);
			btnProcurarSaida.setEnabled(true);
		}
		this.habilitado = !habilitado;
	}

	public String getCaminhoSaida() {
		return caminhoSaida;
	}
	
	public JLabel getLbErro() {
		return lbErro;
	}
}

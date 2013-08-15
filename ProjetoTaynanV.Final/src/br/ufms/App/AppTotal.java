package br.ufms.App;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.ufms.Arquivo.ArquivoTotal;
import br.ufms.utils.RoundJTextField;
import br.ufms.utils.RoundedCornerButton;

public class AppTotal extends JFrame {

        /**
         * Declaração de variaveis
         */
        private static final long serialVersionUID = 1L;
        private JPanel contentPane;
        private JButton btnProcurarEntrada, btnGerarRanking, btnProcurarSaida,  btnFechar;
        private JTextField jtfCaminhoEntrada, jtfCaminhoSaida;
        private String caminhoEntrada = "", caminhoSaida = "";
        private JRadioButton rdbtnRankingIndividual, rdbtnRankingGeral;
        private ButtonGroup radioButtons;
        private JLabel lblAvisoTipoTabela, lblAvisoArquivo, lblAvisoSalvar,
                        lblStatus;
        private ArquivoTotal arquivo = new ArquivoTotal();

        public AppTotal() {
                init();
        }

        /**
         * Inicializa variaveis
         */
        public void init() {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setBounds(100, 100, 800, 200);
                setLocationRelativeTo(null);
                setBackground(Color.LIGHT_GRAY);
                setResizable(false);
                setTitle("Programa Contador de Frequência de Palavras");
                addComponentListener(new ComponentAdapter() {
                        @Override
                        public void componentResized(ComponentEvent e) {
                                setShape(new RoundRectangle2D.Float(0, 0, getWidth() - 4,
                                                getHeight() - 4, 16.0f, 16.0f));

                        }
                });
                setUndecorated(true);

                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                contentPane.setLayout(null);
                setContentPane(contentPane);

                btnProcurarEntrada = new RoundedCornerButton("Procurar");
                btnProcurarEntrada.setBounds(555, 10, 100, 25);
                btnProcurarEntrada.addActionListener(acoes);
                contentPane.add(btnProcurarEntrada);

                btnProcurarSaida = new RoundedCornerButton("Procurar");
                btnProcurarSaida.setBounds(480, 70, 100, 25);
                btnProcurarSaida.addActionListener(acoes);
                contentPane.add(btnProcurarSaida);

                btnGerarRanking = new RoundedCornerButton("Gerar Ranking");
                btnGerarRanking.setBounds(239, 106, 130, 25);
                btnGerarRanking.addActionListener(acoes);

                btnFechar = new RoundedCornerButton("Fechar");
                btnFechar.setFont(new Font("Dialog", Font.BOLD, 10));
                btnFechar.setBounds(710, 10, 78, 25);
                btnFechar.addActionListener(acoes);
                contentPane.add(btnFechar);
                
                JPanel panelGerar = new JPanel();
                panelGerar.setBounds(0, 106, 800, 30);
                panelGerar.add(btnGerarRanking);
                contentPane.add(panelGerar);

                jtfCaminhoEntrada = new RoundJTextField();
                jtfCaminhoEntrada.setText("");
                jtfCaminhoEntrada.setBounds(74, 10, 400, 25);
                contentPane.add(jtfCaminhoEntrada);
                jtfCaminhoEntrada.setColumns(10);

                jtfCaminhoSaida = new RoundJTextField();
                jtfCaminhoSaida.setText("");
                jtfCaminhoSaida.setColumns(10);
                jtfCaminhoSaida.setBounds(74, 70, 400, 25);
                contentPane.add(jtfCaminhoSaida);

                JLabel lblTipoDeTabela = new JLabel("Tipo de Ranking: ");
                lblTipoDeTabela.setBounds(20, 40, 130, 25);
                contentPane.add(lblTipoDeTabela);

                rdbtnRankingGeral = new JRadioButton("Ranking Geral");
                rdbtnRankingGeral.setBounds(160, 40, 130, 25);
                contentPane.add(rdbtnRankingGeral);

                rdbtnRankingIndividual = new JRadioButton("Ranking Individual");
                rdbtnRankingIndividual.setBounds(300, 40, 160, 25);
                contentPane.add(rdbtnRankingIndividual);

                radioButtons = new ButtonGroup();
                radioButtons.add(rdbtnRankingGeral);
                radioButtons.add(rdbtnRankingIndividual);

                lblAvisoTipoTabela = new JLabel("");
                lblAvisoTipoTabela.setForeground(Color.RED);
                lblAvisoTipoTabela.setBounds(628, 40, 160, 25);
                contentPane.add(lblAvisoTipoTabela);

                JLabel lblCaminhoEntrada = new JLabel("Arquivo : ");
                lblCaminhoEntrada.setBounds(20, 10, 120, 25);
                contentPane.add(lblCaminhoEntrada);

                JLabel lblCaminhoSaida = new JLabel("Salvar em: ");
                lblCaminhoSaida.setBounds(20, 70, 78, 25);
                contentPane.add(lblCaminhoSaida);

                lblAvisoArquivo = new JLabel("");
                lblAvisoArquivo.setForeground(Color.RED);
                lblAvisoArquivo.setBounds(628, 10, 160, 25);
                contentPane.add(lblAvisoArquivo);

                lblAvisoSalvar = new JLabel("");
                lblAvisoSalvar.setForeground(Color.RED);
                lblAvisoSalvar.setBounds(628, 70, 160, 25);
                contentPane.add(lblAvisoSalvar);

                lblStatus = new JLabel("");
                lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
                lblStatus.setBounds(0, 140, 800, 25);
                contentPane.add(lblStatus);

        }

        public ActionListener acoes = new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                        if (e.getSource() == btnProcurarEntrada) {
                                caminhoEntrada = arquivo.escolherPasta(1);
                                jtfCaminhoEntrada.setText(caminhoEntrada);
                        } else if (e.getSource() == btnProcurarSaida) {
                                caminhoSaida = arquivo.escolherPasta(2);
                                jtfCaminhoSaida.setText(caminhoSaida);
                        } else if (e.getSource() == btnGerarRanking) {
                                if (caminhoEntrada.equalsIgnoreCase("")) {
                                        lblAvisoArquivo.setText("Escolha a pasta");
                                } else {
                                        lblAvisoArquivo.setText("");
                                }
                                if (caminhoSaida.equalsIgnoreCase("")) {
                                        lblAvisoSalvar.setText("Escolha a pasta");
                                } else {
                                        lblAvisoSalvar.setText("");
                                }
                                if (!rdbtnRankingGeral.isSelected()
                                                && !rdbtnRankingIndividual.isSelected()) {
                                        lblAvisoTipoTabela.setText("Escolha o tipo");
                                } else {
                                        lblAvisoTipoTabela.setText("");
                                }
                                if (isOk()) {
                                        if (rdbtnRankingGeral.isSelected()) {
                                                arquivo = new ArquivoTotal();
                                                arquivo.abrirArquivosRankingGeral(caminhoEntrada);
//                                                lblStatus.setText(arquivo
//                                                                .exportarRankingGeral(caminhoSaida));
                                                btnProcurarEntrada.setEnabled(false);
                                                btnProcurarSaida.setEnabled(false);
                                                btnGerarRanking.setEnabled(false);
                                                rdbtnRankingGeral.setEnabled(false);
                                                rdbtnRankingIndividual.setEnabled(false);
                                                jtfCaminhoEntrada.setEnabled(false);
                                                jtfCaminhoSaida.setEnabled(false);
                                        } else if (rdbtnRankingIndividual.isSelected()) {
                                                arquivo = new ArquivoTotal();
                                                lblStatus.setText(arquivo
                                                                .abrirArquivoRankingIndividual(caminhoSaida,
                                                                                caminhoEntrada));
                                                btnProcurarEntrada.setEnabled(false);
                                                btnProcurarSaida.setEnabled(false);
                                                btnGerarRanking.setEnabled(false);
                                                rdbtnRankingGeral.setEnabled(false);
                                                rdbtnRankingIndividual.setEnabled(false);
                                                jtfCaminhoEntrada.setEnabled(false);
                                                jtfCaminhoSaida.setEnabled(false);
                                        }

                                }

                        }

                }

        };

        /**
         * verifica se todos os campos foram preenchidos
         * 
         * @return verdadeiro se todos foram falso senão
         */
        private boolean isOk() {
                if (caminhoEntrada.equalsIgnoreCase("")) {
                        return false;
                }
                if (caminhoSaida.equalsIgnoreCase("")) {
                        return false;
                }
                if (!rdbtnRankingGeral.isSelected()
                                && !rdbtnRankingIndividual.isSelected()) {
                        return false;
                }
                return true;
        }

        /**
         * Launch the application.
         */
        public static void main(String[] args) {
                EventQueue.invokeLater(new Runnable() {
                        public void run() {
                                try {
                                        AppTotal frame = new AppTotal();
                                        frame.setVisible(true);
                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                });
        }
}
package br.ufms.Arquivo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.ufms.Classes.LinhaRankingGeral;
import br.ufms.Classes.LinhaRankingIndividual;
import br.ufms.Classes.Palavra;
import br.ufms.Exportar.ExportarTotal;

public class ArquivoTotal {

        private BufferedReader br;
        private Scanner sc;
        private List<Palavra> listaPalavras = new ArrayList<Palavra>();
        private List<LinhaRankingGeral> linhasGeral = new ArrayList<LinhaRankingGeral>();
        private int quantidadeDeArquivos = 0;
    	private List<JScrollPane> scrolls = new ArrayList<JScrollPane>();

        /**
         * 
         * @param c
         *            recebe uma String e valida se é considerada ou não uma palavra
         * @return true se for considerada palavra e false se não for
         */
        private boolean validarPalavra(String c) {
                if (c.equals(".") || c.equals(",") || c.equals(";") || c.equals(":")
                                || c.equals("*") || c.equals("!") || c.equals("?")
                                || c.equals("/") || c.equals("\\") || c.equals("\"")
                                || c.equals("'") || c.equals("@") || c.equals("#")
                                || c.equals("$") || c.equals("%") || c.equals("Âš")
                                || c.equals("(") || c.equals(")") || c.equals("+")
                                || c.equals("=") || c.equals("Â£") || c.equals("Â¢")
                                || c.equals("Â¬") || c.equals("ÂŽ") || c.equals("`")
                                || c.equals("[") || c.equals("{") || c.equals("Âª")
                                || c.equals("~") || c.equals("^") || c.equals("]")
                                || c.equals("}") || c.equals("Âº") || c.equals("Â°")
                                || c.equals("|") || c.equals("<") || c.equals(">")
                                || c.equals("\"") || c.equals("\n") || c.equals("-->")
                                || c.equals("...") || c.equals("") || c.contains("00")
                                || c.equals("&") || c.matches("[0-9]+")) {
                        return false;
                } else {
                        return true;
                }
        }

        /**
         * 
         * @param sc
         *            recebe uma String e elemina os caracteres especiais e numeros
         * @return a String refatorada
         */
        private String refatorarPalavra(String sc) {
                String palavra;
                palavra = sc.replace(".", "").replace(",", "").replace("!", "")
                                .replace("?", "").replace("''", "").replace("--", "")
                                .replace("<i>", "").replace("</i>", "").replace("[", "")
                                .replace("]", "").replace("<b>", "").replace("</b>", "")
                                .replace("\"", "").replace("*", "").replace("{", "")
                                .replace("}", "").replace("(", "").replace(")", "")
                                .replace(":", "").replace(";", "").replace("</font>", "")
                                .replace("%", "").replace("$", "");
                if (palavra.startsWith("-")) {
                        palavra = palavra.replaceFirst("-", "");
                }

                return palavra;
        }

        // Métodos de exportação de Ranking Geral
        /**
         * Verifica se o caminho recebido contem arquivos, se o arquivo é uma
         * legenda e a cada arquivo chama o metodo gerarListaGeral()
         * 
         * @param path
         *            recebe o caminho escolhido pelo usuario que contem as legendas
         */
        public void abrirArquivosRankingGeral(String path) {
                File f = new File(path);

                if (f.listFiles() != null) {
                        for (File a : f.listFiles()) {
                                if (a.isFile() && a.getName().endsWith(".srt")) {
                                        gerarListaGeral(a.getAbsolutePath());
                                        quantidadeDeArquivos++;
                                }
                        }
                }

        }

        /**
         * Contem uma lista de String, é lido o arquivo de legenda e apos ser
         * ferificada e refatorada, cada palavra é adicionada a esta lista. Ao fim
         * do arquivo é chamado o método gerarListaDePalavrasGeral()
         * 
         * @param caminho
         *            caminho do arquivo a ser lido
         */
        private void gerarListaGeral(String caminho) {
                List<String> texto = new ArrayList<String>();
                try {
                        br = new BufferedReader(new FileReader(caminho));
                        sc = new Scanner(br);
                        String palavra;
                        while (sc.hasNext()) {
                                palavra = sc.next().toLowerCase();
                                palavra = refatorarPalavra(palavra);
                                if (validarPalavra(palavra))
                                        texto.add(palavra);
                        }
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }

                gerarListaDePalavrasGeral(texto);
        }

        /**
         * Verifica se a palavra já existe na lista de palavras se sim, adiciona 1 a
         * quantidade, se não insere a palavra na lista;
         * 
         * @param texto
         *            lista com palavras validas lidas do arquivo
         */
        private void gerarListaDePalavrasGeral(List<String> texto) {
                for (String c : texto) {
                        boolean has = false;
                        int index = 0;
                        for (Palavra p : listaPalavras) {
                                if (p.getPalavra().equalsIgnoreCase(c)) {
                                        has = true;
                                        break;
                                }
                                index++;
                        }
                        if (has) {
                                listaPalavras.get(index).setCont(
                                                listaPalavras.get(index).getCont() + 1);
                        } else {
                                listaPalavras.add(new Palavra(c, 1));
                        }
                }
                Collections.sort(listaPalavras);
        }

        /**
         * Cria uma lista LinhaRankingGeral que contem as informações que serão
         * adicionadas á tabela gerada
         * 
         * @return uma lista com as informações a serem exportadas
         */
        private List<LinhaRankingGeral> preencherListaGeral() {
                List<LinhaRankingGeral> lista = new ArrayList<LinhaRankingGeral>();
                int i = 1;
                for (Palavra palavra : getListaPalavras()) {
                        double frequenciaSecundaria = palavra.getCont()
                                        / getQuantidadeDeArquivos();
                        LinhaRankingGeral l = new LinhaRankingGeral();
                        l.setI(i);
                        l.setPalavra(palavra.getPalavra());
                        l.setQuantidade(palavra.getCont());
                        l.setFrequenciaSecundaria(frequenciaSecundaria);
                        lista.add(l);
                        i++;
                }

                return lista;
        }

        /**
         * 
         * @param caminho
         *            lugar a ser exportado
         * @return String resultado do metodo exportarRankingGeral do arquivo
         *         Exportar
         */
        public List<LinhaRankingGeral> exportarRankingGeral(String caminho) {
                linhasGeral = preencherListaGeral();
                new ExportarTotal().exportarRankingGeral(caminho,
                                "RankingGeral", linhasGeral);
                return linhasGeral;
        }

        // Métodos para exportar ranking individual

        /**
         * 
         * @param caminho
         *            caminho a set salvo arquivo
         * @param caminhoArquivo
         *            caminho do arquivo de origem
         * @return String com informação das exportações
         */
        public String abrirArquivoRankingIndividual(String caminho,
                        String caminhoArquivo) {
                File f = new File(caminhoArquivo);
                if (f.listFiles() != null) {
                        for (File a : f.listFiles()) {
                                if (a.isFile() && a.getName().endsWith(".srt")) {
                                        gerarListaIndividual(caminho, a.getAbsolutePath(),
                                                        a.getName());
                                }
                        }
                }
                return "Ranking Exportados";
        }

        /**
         * Contem uma lista de String, é lido o arquivo de legenda e apos ser
         * ferificada e refatorada, cada palavra é adicionada a esta lista. Ao fim
         * do arquivo é chamado o método gerarListaDePalavrasIndividual(), gerarListadeLInhaIndividual() e exportarRankingIndividual()
         * 
         * @param caminho
         *            caminho a ser salvo o arquivo a ser exportado
         * @param caminhoArquivo
         *            caminho de origem do arquivo
         * @param nomeArquivo
         *            nome do arquivo de legenda
         */
        private void gerarListaIndividual(String caminho, String caminhoArquivo,
                        String nomeArquivo) {
                List<String> texto = new ArrayList<String>();
                try {
                        br = new BufferedReader(new FileReader(caminhoArquivo));
                        sc = new Scanner(br);
                        String palavra;
                        while (sc.hasNext()) {
                                palavra = sc.next().toLowerCase();
                                palavra = refatorarPalavra(palavra);
                                if (validarPalavra(palavra))
                                        texto.add(palavra);
                        }
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                }
                List<Palavra> palavras = gerarListaPalavrasIndividual(texto);
                List<LinhaRankingIndividual> linhas = gerarListaDeLinhaIndividual(
                                palavras, nomeArquivo);
                preencherScrolls(linhas);
                exportarRankingIndividual(caminho, linhas, nomeArquivo);

        }

        /**
         * Verifica se a palavra já existe na lista de palavras se sim, adiciona 1 a
         * quantidade, se não insere a palavra na lista;
         * 
         * @param texto
         *            lista com palavras validas lidas do arquivo
         * @return lista de Palavras
         */
        private List<Palavra> gerarListaPalavrasIndividual(List<String> texto) {
                List<Palavra> lista = new ArrayList<Palavra>();
                for (String c : texto) {
                        boolean has = false;
                        int index = 0;
                        for (Palavra p : lista) {
                                if (p.getPalavra().equalsIgnoreCase(c)) {
                                        has = true;
                                        break;
                                }
                                index++;
                        }
                        if (has) {

                                lista.get(index).setCont(lista.get(index).getCont() + 1);
                        } else {
                                lista.add(new Palavra(c, 1));
                        }
                }
                Collections.sort(lista);
                return lista;
        }

        /**
         * Cria a lista de linhaRankingIndividual que vai conter, classificação,
         * palavra, contador e nome do arquivo de origem;
         * 
         * @param palavras
         *            lista de palavras encontradas e suas quantidades
         * @param nomeArquivo
         *            nome do arquivo de legenda para ser adicionado no campo Origem
         * @return lista com o conteudo a ser exportado
         */
        private List<LinhaRankingIndividual> gerarListaDeLinhaIndividual(
                        List<Palavra> palavras, String nomeArquivo) {
                List<LinhaRankingIndividual> linhas = new ArrayList<LinhaRankingIndividual>();
                int i = 1;
                for (Palavra palavra : palavras) {
                        LinhaRankingIndividual linha = new LinhaRankingIndividual();
                        linha.setI(i);
                        linha.setPalavra(palavra.getPalavra());
                        linha.setQuantidade(palavra.getCont());
                        linha.setArqivoDeOrigem(nomeArquivo);
                        linhas.add(linha);
                        i++;
                }
                return linhas;
        }

        /**
         * 
         * @param caminho
         *            lugar a ser salvo o arquivo exportado
         * @param linhas
         *            conteudo a ser salvo no arquivo exportado
         * @param nomeArquivoSaida
         *            nome do arquivo
         * @return String resultado do metodo exportarRankingIndividual do arquivo
         *         Exportar
         */
        private String exportarRankingIndividual(String caminho,
                        List<LinhaRankingIndividual> linhas, String nomeArquivoSaida) {
                ExportarTotal exportarTotal = new ExportarTotal();
                return exportarTotal.rankingIndividual(caminho, nomeArquivoSaida,
                                linhas);
        }
        /**
    	 * Preenche a List de scrollPanes que sera usada na previsualizacao
    	 * 
    	 * @param linhas
    	 */
    	public void preencherScrolls(List<LinhaRankingIndividual> linhas){
    		DefaultTableModel modelo = new DefaultTableModel();
    		modelo.addColumn("Indice");
    		modelo.addColumn("Palavra");
    		modelo.addColumn("Frequência");
    		modelo.addColumn("Arquivo");

    		JTable tabela = new JTable(modelo);
    		tabela.getColumnModel().getColumn(0).setPreferredWidth(50);
    		tabela.getColumnModel().getColumn(1).setPreferredWidth(175);
    		tabela.getColumnModel().getColumn(2).setPreferredWidth(150);
    		tabela.getColumnModel().getColumn(3).setPreferredWidth(325);
	    	tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    		for (LinhaRankingIndividual linha : linhas) {
    			modelo.addRow(new Object[] { linha.getI(), linha.getPalavra(),
    					linha.getQuantidade(), linha.getArqivoDeOrigem() });
    		}

    		JScrollPane scrollPane = new JScrollPane(tabela);
    		scrollPane.setSize(700, 380);
    		String original = linhas.get(1).getArqivoDeOrigem();
    		String titulo = "";
    		for (int i = 0; i < 40; i++) {
    			titulo = titulo +original.charAt(i);
    			
    		}
    		scrollPane.setName(titulo);
    		scrolls.add(scrollPane);
    	}
    	
    	public void clean() {
    		listaPalavras.clear();
    		linhasGeral.clear();
    		scrolls.clear();
		}

        // Gets
        public List<Palavra> getListaPalavras() {
                return listaPalavras;
        }

        public int getQuantidadeDeArquivos() {
                return quantidadeDeArquivos;
        }
        public List<JScrollPane> getScrolls() {
    		return scrolls;
    	}
    	public List<LinhaRankingGeral> getLinhasGeral() {
    		return linhasGeral;
    	}
    }
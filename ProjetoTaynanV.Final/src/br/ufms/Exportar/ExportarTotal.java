package br.ufms.Exportar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import br.ufms.Classes.LinhaRankingGeral;
import br.ufms.Classes.LinhaRankingIndividual;

public class ExportarTotal {
	private static HSSFWorkbook workbook;
	private static HSSFSheet sheet;
	private String statusGravacao;
	private List<CellStyle> estilos = new ArrayList<CellStyle>();

	public ExportarTotal() {
		workbook = new HSSFWorkbook();
		estilos = preencherEstilos();
	}

	// Métodos para exportação de Ranking Geral

/**
 * 
 * @param caminho lugar a ser salvo o arquivo
 * @param nomeArquivoSaida  nome do arquivo a ser exportado
 * @param linhas informações a serem salvas
 * @return status de gravação
 */
	public String exportarRankingGeral(String caminho, String nomeArquivoSaida,
			List<LinhaRankingGeral> linhas) {
		sheet = workbook.createSheet(nomeArquivoSaida);
		Row titulo = sheet.createRow(0);
		Cell primeira = titulo.createCell(0);
		primeira.setCellValue("Ranking Geral");
		primeira.setCellStyle(estilos.get(0));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 3));

		Row cabecalho = sheet.createRow(2);

		Cell classificacao = cabecalho.createCell(0);
		classificacao.setCellStyle(estilos.get(2));
		classificacao.setCellValue("Classificação");

		Cell palavra = cabecalho.createCell(1);
		palavra.setCellStyle(estilos.get(2));
		palavra.setCellValue("Palavra");

		Cell freqPrim = cabecalho.createCell(2);
		freqPrim.setCellStyle(estilos.get(2));
		freqPrim.setCellValue("Frequência Primaria");

		Cell freqSec = cabecalho.createCell(3);
		freqSec.setCellStyle(estilos.get(2));
		freqSec.setCellValue("Frequência Secundaria");

		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		sheet.setColumnWidth(1, 10000);

		int numRow = 3;
		for (LinhaRankingGeral linha : linhas) {
			Row nova = sheet.createRow(numRow);

			Cell coluna1 = nova.createCell(0);
			coluna1.setCellStyle(estilos.get(2));
			coluna1.setCellValue(linha.getI());

			Cell coluna2 = nova.createCell(1);
			coluna2.setCellStyle(estilos.get(2));
			coluna2.setCellValue(linha.getPalavra());

			Cell coluna3 = nova.createCell(2);
			coluna3.setCellStyle(estilos.get(2));
			coluna3.setCellValue(linha.getQuantidade());

			Cell coluna4 = nova.createCell(3);
			coluna4.setCellStyle(estilos.get(2));
			coluna4.setCellValue(linha.getFrequenciaSecundaria());
			numRow++;
		}

		Row rodape = sheet.createRow(sheet.getLastRowNum() + 1);
		sheet.addMergedRegion(new CellRangeAddress(numRow, numRow + 1, 0, 3));
		Cell coluna = rodape.createCell(0);
		coluna.setCellValue("Arquivo Gerado por Programa Contador de Frequência de Palavras");
		coluna.setCellStyle(estilos.get(1));

		exportar(caminho, nomeArquivoSaida);
		return statusGravacao;
	}

	// Método para exportação de Ranking Individual
	/**
	 * 
	 * @param caminho lugar a ser exportado o arquivo
	 * @param nomeArquivoSaida nome do aquivo a er exportado
	 * @param linhas informações a serem gravadas
	 * @return status da exportação
	 */
	public String rankingIndividual(String caminho, String nomeArquivoSaida,
			List<LinhaRankingIndividual> linhas) {
		workbook = new HSSFWorkbook();
		estilos = preencherEstilos();

		HSSFSheet sheetIndividual = workbook.createSheet(nomeArquivoSaida);

		Row titulo = sheetIndividual.createRow(0);
		Cell primeira = titulo.createCell(0);
		primeira.setCellValue("Ranking Individual");
		primeira.setCellStyle(estilos.get(0));
		sheetIndividual.addMergedRegion(new CellRangeAddress(0, 1, 0, 3));

		Row cabecalho = sheetIndividual.createRow(2);

		Cell classificacao = cabecalho.createCell(0);
		classificacao.setCellStyle(estilos.get(2));
		classificacao.setCellValue("Classificação ");

		Cell palavra = cabecalho.createCell(1);
		palavra.setCellStyle(estilos.get(2));
		palavra.setCellValue("Palavra ");

		Cell freqPrim = cabecalho.createCell(2);
		freqPrim.setCellStyle(estilos.get(2));
		freqPrim.setCellValue("Frequência ");

		Cell freqSec = cabecalho.createCell(3);
		freqSec.setCellStyle(estilos.get(2));
		freqSec.setCellValue("Arquivo de Origem ");

		int numRow = 3;
		for (LinhaRankingIndividual linha : linhas) {
			Row nova = sheetIndividual.createRow(numRow);

			Cell coluna1 = nova.createCell(0);
			coluna1.setCellStyle(estilos.get(2));
			coluna1.setCellValue(linha.getI());

			Cell coluna2 = nova.createCell(1);
			coluna2.setCellStyle(estilos.get(2));
			coluna2.setCellValue(linha.getPalavra());

			Cell coluna3 = nova.createCell(2);
			coluna3.setCellStyle(estilos.get(2));
			coluna3.setCellValue(linha.getQuantidade());

			Cell coluna4 = nova.createCell(3);
			coluna4.setCellStyle(estilos.get(2));
			coluna4.setCellValue(linha.getArqivoDeOrigem());
			numRow++;
		}
		sheetIndividual.autoSizeColumn(0);
		sheetIndividual.autoSizeColumn(1);
		sheetIndividual.autoSizeColumn(2);
		sheetIndividual.autoSizeColumn(3);

		Row rodape = sheetIndividual
				.createRow(sheetIndividual.getLastRowNum() + 1);
		sheetIndividual.addMergedRegion(new CellRangeAddress(numRow,
				numRow + 1, 0, 3));
		Cell coluna = rodape.createCell(0);
		coluna.setCellValue("Arquivo Gerado por Programa Contador de Frequência de Palavras");
		coluna.setCellStyle(estilos.get(1));

		exportar(caminho, nomeArquivoSaida);
		return statusGravacao;
	}

	// Métodos para ambos
	/**
	 * Metodo que faz a gravação do arquivo
	 * @param caminho lugar a ser exportado o arquivo
	 * @param nomeDoArquivo nome ndo arquivo a ser salvo
	 */
	private void exportar(String caminho, String nomeDoArquivo) {
		try {
			File file = new File(caminho + "/" + nomeDoArquivo + ".xls");
			FileOutputStream out = new FileOutputStream(file, false);
			workbook.write(out);
			out.flush();
			out.close();
			if (file.length() != 0) {
				statusGravacao = "Ranking exportado.";

			} else {
				statusGravacao = "Problema na geração do arquivo.";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getStatusGravacao() {
		return statusGravacao;
	}

	/**
	 * Cria a lista de estilos
	 * @return lista de CellStyle
	 */
	public List<CellStyle> preencherEstilos() {
		List<CellStyle> temp = new ArrayList<CellStyle>();
		CellStyle estilo0 = workbook.createCellStyle();
		CellStyle estilo1 = workbook.createCellStyle();
		CellStyle estilo2 = workbook.createCellStyle();

		Font fonte = workbook.createFont();
		fonte.setFontHeightInPoints((short) 20);
		estilo0.setAlignment(CellStyle.ALIGN_CENTER);
		estilo0.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		estilo0.setWrapText(true);
		estilo0.setFont(fonte);
		temp.add(estilo0);
		Font fonte2 = workbook.createFont();
		fonte.setFontHeightInPoints((short) 14);
		estilo1.setAlignment(CellStyle.ALIGN_CENTER);
		estilo1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		estilo1.setWrapText(true);
		estilo1.setFont(fonte2);
		temp.add(estilo1);

		estilo2.setAlignment(CellStyle.ALIGN_CENTER);
		estilo2.setWrapText(true);
		estilo2.setBorderRight(CellStyle.BORDER_THIN);
		estilo2.setBorderLeft(CellStyle.BORDER_THIN);
		estilo2.setBorderTop(CellStyle.BORDER_THIN);
		estilo2.setBorderBottom(CellStyle.BORDER_THIN);
		temp.add(estilo2);
		
		return temp;
	}
}
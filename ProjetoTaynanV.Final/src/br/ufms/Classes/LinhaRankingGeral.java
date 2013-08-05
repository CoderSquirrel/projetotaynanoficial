package br.ufms.Classes;

public class LinhaRankingGeral implements Comparable<LinhaRankingGeral> {

	private int i;
	private String palavra;
	private int quantidade;
	private double frequenciaSecundaria;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getFrequenciaSecundaria() {
		return frequenciaSecundaria;
	}

	public void setFrequenciaSecundaria(double frequenciaSecundaria) {
		this.frequenciaSecundaria = frequenciaSecundaria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((palavra == null) ? 0 : palavra.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinhaRankingGeral other = (LinhaRankingGeral) obj;
		if (palavra == null) {
			if (other.palavra != null)
				return false;
		} else if (!palavra.equals(other.palavra))
			return false;
		return true;
	}

	@Override
	public int compareTo(LinhaRankingGeral o) {
		if (this.i < o.i) {
			return 1;
		}
		if (this.i > o.i) {
			return -1;
		}

		return 0;
	}

}

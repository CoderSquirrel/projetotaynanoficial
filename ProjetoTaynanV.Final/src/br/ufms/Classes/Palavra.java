package br.ufms.Classes;

/**
 * @author sane
 */

public class Palavra implements Comparable<Palavra> {
	private String palavra;
	private int quantidade;

	public Palavra(String string, int i) {
		this.palavra = string;
		this.quantidade = i;
	}

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public int getCont() {
		return quantidade;
	}

	public void setCont(int cont) {
		this.quantidade = cont;
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
		Palavra other = (Palavra) obj;
		if (palavra == null) {
			if (other.palavra != null)
				return false;
		} else if (!palavra.equals(other.palavra))
			return false;
		return true;
	}

	@Override
	public int compareTo(Palavra arg0) {
		if (this.quantidade < arg0.quantidade) {
			return 1;
		}
		if (this.quantidade > arg0.quantidade) {
			return -1;
		}
		return 0;
	}

}

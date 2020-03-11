package domain;

public class Cidade {

	private String nome;
	private Uf uf;

	public Cidade(String nome, Uf Uf) {
		super();
		this.nome = nome;
		uf = Uf;
	}

	@Override
	public String toString() {
		return getNome() + "(" + uf + ")";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf Uf) {
		uf = Uf;
	}

}

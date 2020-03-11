package domain;

public class Uf {

	private String nome;

	@Override
	public String toString() {
		return getNome();
	}
	
	public Uf(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}

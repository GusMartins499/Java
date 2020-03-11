package model;

public class Marca {
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
	
	public Marca(String nome) {
		this.nome = nome;
	}
}

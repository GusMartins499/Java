package trabalho1;

import java.util.ArrayList;

public class Vertice {
	// nome do vertice para a listagem
	private String nomeDoVertice;
	// lista de vértices para fazer a lista de adjacência
	private ArrayList<Vertice> adjacentes=new ArrayList<Vertice>();

	public String getNomeDoVertice() {
		return nomeDoVertice;
	}

	public void setNomeDoVertice(String nomeDoVertice) {
		this.nomeDoVertice = nomeDoVertice;
	}

	public ArrayList<Vertice> getAdjacentes() {
		return adjacentes;
	}

	public void setAdjacentes(ArrayList<Vertice> adjacentes) {
		this.adjacentes = adjacentes;
	}

}
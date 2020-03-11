package trabalho1;

import java.util.ArrayList;

public class Vertice {
	// nome do vertice para a listagem
	private String nomeDoVertice;
	// lista de v�rtices para fazer a lista de adjac�ncia
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
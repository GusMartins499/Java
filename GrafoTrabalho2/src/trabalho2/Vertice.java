package trabalho2;

import java.util.ArrayList;

public class Vertice {
	// nome do vértice para a listagem
	private String nomeDoVertice;
	// distância do vértice para resolução de Dijkstra
	
	private Double distancia;
	

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	// visitado ou não para resolução de Dijkstra
	private boolean visitado;
	private ArrayList<Vertice> adjacentes = new ArrayList<Vertice>();

	public ArrayList<Vertice> getAdjacentes() {
		return adjacentes;
	}

	public void setAdjacentes(ArrayList<Vertice> adjacentes) {
		this.adjacentes = adjacentes;
	}

	public String getNomeDoVertice() {
		return nomeDoVertice;
	}

	public void setNomeDoVertice(String nomeDoVertice) {
		this.nomeDoVertice = nomeDoVertice;
	}

	
	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

}
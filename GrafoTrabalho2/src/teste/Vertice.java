package teste;

import java.util.ArrayList;
import java.util.List;

public class Vertice implements Comparable<Vertice> {

	private String nome;
	private List<Aresta> listaAdjacente;
	private boolean visitado;
	private Vertice predecessor;
	private int distancia = Integer.MAX_VALUE;

	public Vertice(String nome) {
		this.nome = nome;
		this.listaAdjacente = new ArrayList<>();
	}

	public void addAdjacente(Aresta aresta) {
		this.listaAdjacente.add(aresta);
	}

	public String getnome() {
		return nome;
	}

	public void setnome(String nome) {
		this.nome = nome;
	}

	public List<Aresta> getlistaAdjacente() {
		return listaAdjacente;
	}

	public void setlistaAdjacente(List<Aresta> listaAdjacente) {
		this.listaAdjacente = listaAdjacente;
	}

	public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public Vertice getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Vertice predecessor) {
		this.predecessor = predecessor;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	@Override
	public String toString() {
		return this.nome;
	}

	@Override
	public int compareTo(Vertice outroVertice) {
		return Integer.compare(this.distancia, outroVertice.getDistancia());
	}
}
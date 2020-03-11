package kruskal;

public class Aresta implements Comparable<Aresta> {

	int origem;
	int destino;
	int peso;

	@Override
	public int compareTo(Aresta outraAresta) {
		if (this.peso < outraAresta.peso) {
			return -1;
		} else if (this.peso > outraAresta.peso) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "Origem: " + origem + " Destino: " + destino;
	}

	public Aresta(int origem, int destino, int peso) {
		this.origem = origem;
		this.destino = destino;
		this.peso = peso;
	}

	public int getOrigem() {
		return origem;
	}

	public void setOrigem(int origem) {
		this.origem = origem;
	}

	public int getDestino() {
		return destino;
	}

	public void setDestino(int destino) {
		this.destino = destino;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
}

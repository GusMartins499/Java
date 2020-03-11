package trabalho2;

public class Aresta {
	// vértice de entrada para a conexão com o outro vértice (saída)
	private Vertice vEntrada;
	// vértice de saída para a conexão com o outro vértice (entrada)
	private Vertice vSaida;
	// valor da aresta caso seja um grafo valorado
	private Double valorAresta;

	public Vertice getVEntrada() {
		return vEntrada;
	}

	public void setVEntrada(Vertice vEntrada) {
		this.vEntrada = vEntrada;
	}

	public Vertice getVSaida() {
		return vSaida;
	}

	public void setVSaida(Vertice vSaida) {
		this.vSaida = vSaida;
	}

	public Double getValorAresta() {
		return valorAresta;
	}

	public void setValorAresta(Double valorAresta) {
		this.valorAresta = valorAresta;
	}

}
package trabalho2;

public class Aresta {
	// v�rtice de entrada para a conex�o com o outro v�rtice (sa�da)
	private Vertice vEntrada;
	// v�rtice de sa�da para a conex�o com o outro v�rtice (entrada)
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
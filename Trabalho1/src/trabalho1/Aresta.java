package trabalho1;

public class Aresta {
	//vertice de entrada para a conexão com o outro vertice (saida)
	private Vertice vEntrada;
	//vertice de saida para a conexão com o outro vertice (entrada)
	private Vertice vSaida;
	//valor da aresta caso seja um grafo valorado
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

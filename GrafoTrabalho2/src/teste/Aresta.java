package teste;

public class Aresta {

	private int peso;
	private Vertice vSaida;
	private Vertice vChegada;

	public Aresta(int peso, Vertice vSaida, Vertice vChegada) {
		this.peso = peso;
		this.vSaida = vSaida;
		this.vChegada = vChegada;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public Vertice getvSaida() {
		return vSaida;
	}

	public void setvSaida(Vertice vSaida) {
		this.vSaida = vSaida;
	}

	public Vertice getvChegada() {
		return vChegada;
	}

	public void setvChegada(Vertice vChegada) {
		this.vChegada = vChegada;
	}

}



public class Aresta implements Comparable<Aresta> {

	public Aresta(String ori, String des, int val) {
		origem = ori;
		destino = des;
		valor = val;
	}

	public String origem;
	public String destino;
	public int valor;

	@Override
	public int compareTo(Aresta outraAresta) {
		if (this.valor < outraAresta.valor) {
			return -1;
		} else if (this.valor > outraAresta.valor) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "origem " + origem + " destino " + destino;
	}
}

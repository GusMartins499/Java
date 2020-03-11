package pilha;

public class Pilha {

	Elemento topo = null;

	public void empilha(int vl) {
		Elemento novo = new Elemento(vl);
		if (topo == null) {
			topo = novo;
		} else {
			novo.proximo = topo;
			topo = novo;
		}
	}

	public int desempilha() {
		int vl = topo.valor;
		topo = topo.proximo;
		return vl;
	}

	public boolean vazio() {
		return topo == null;
	}
}

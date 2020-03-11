package carta;

public class PilhaCarta {

	private NoCarta topo = null;

	public PilhaCarta() {
	}

	public boolean estaVazia() {
		return topo == null;
	}

	public void empilha(String nomePropriedade, String descricao) {
		NoCarta novo = new NoCarta(nomePropriedade, descricao);
		if (topo == null)
			topo = novo;
		else {
			novo.proximo = topo;
			topo = novo;
		}
	}

	public String desempilha() {
		String retVal = topo.descProper;
		topo = topo.proximo;
		return retVal;
	}
}

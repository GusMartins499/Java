package jogo;

public class PilhaNoticias {

	public NoPilha topo = null;

	public void empilha(String tipoDaCarta, String noticiaDaCarta) {
		NoPilha novaCarta = new NoPilha(tipoDaCarta, noticiaDaCarta);
		if (topo == null) {
			novaCarta.proximo = null;
			topo = novaCarta;
		} else {
			novaCarta.proximo = topo;
			topo = novaCarta;

		}
	}

	public NoPilha desempilha() {
		NoPilha noticia = topo;
		topo = topo.proximo;
		return noticia;
	}
}

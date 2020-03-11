package jogo;

public class TabuleiroCircular {

	public NoTabuleiro atual, inicio, fim, anterior;
	public int tamanhoTabuleiro;

	public TabuleiroCircular(int tamanhoTab) {
		this.atual = null;
		this.inicio = null;
		this.fim = null;

	}

	public void criaCasasTab() {
		NoTabuleiro novaCasa = new NoTabuleiro();
		if (inicio == null) {
			novaCasa.imovel = null;
			novaCasa.cartaOrImovel = false;
			inicio = novaCasa;
			fim = novaCasa;
		} else {
			novaCasa.anterior = fim;
			fim.proximo = novaCasa;
			fim = novaCasa;
			novaCasa.proximo = inicio;
		}
	}
}

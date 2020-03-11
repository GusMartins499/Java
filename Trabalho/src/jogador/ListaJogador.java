package jogador;

public class ListaJogador {

	NoJogador inicio, atual, proximo, anterior, fim;

	// INSERIR
	public void inserir() {
		NoJogador novoJ = new NoJogador();
		if (inicio == null) {
			inicio = novoJ;
			fim = novoJ;
		} else {
			fim.proximo = novoJ;
			novoJ.anterior = inicio;
			fim = novoJ;
			fim.proximo = inicio;
			inicio.anterior = fim;
		}
	}
	
	public void movimentaJogador(int vlDado) {
		NoJogador novoJ = new NoJogador();
		novoJ.casa = vlDado;
	}
}

package tabuleiro;

import jogador.NoJogador;

public class NoTabuleiro {

	String nomeProper;
	double vlProper;
	NoTabuleiro anterior, proximo;
	public NoJogador proprietario;
	int contador = 0;

	public NoTabuleiro(double vlP, String nomeP) {
		this.vlProper = vlP;
		nomeProper = nomeP;
		anterior = null;
		proximo = null;
		proprietario = null;
		contador++;
	}

	public NoTabuleiro() {

	}
}

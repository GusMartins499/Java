package jogador;

import imovel.NoImovel;

public class NoJogador {

	double saldo;
	boolean prisao;
	NoJogador anterior, proximo;
	public NoImovel imovel;
	int contador,casa;

	public NoJogador() {
		saldo = 2500;
		prisao = false;
		anterior = null;
		proximo = null;
		contador++;
		casa = 0;
		imovel = null;
	}
}

package jogo;

public class NoFila {

	public Imovel nomeDoImovel;
	public NoFila proximo;

	public NoFila(Imovel imovel) {
		this.nomeDoImovel = imovel;
		this.proximo = null;
	}

}

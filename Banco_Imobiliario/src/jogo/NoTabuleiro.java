package jogo;

public class NoTabuleiro {

	public int posicaoAtual;
	public Imovel imovel;
	public boolean cartaOrImovel;
	NoTabuleiro proximo,anterior;

	public boolean isCartaOrImovel() {
		return cartaOrImovel;
	}

	public void setCartaOrImovel(boolean cartaOrImovel) {
		this.cartaOrImovel = cartaOrImovel;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public int getPosicaoAtual() {
		return posicaoAtual;
	}

	public void setPosicaoAtual(int posicaoAtual) {
		this.posicaoAtual = posicaoAtual;
	}
}

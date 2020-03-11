package jogo;

public class FilaImovel {

	public NoFila inicio, fim;

	public void criaFila(Imovel imovel) {

		NoFila novoImovel = new NoFila(imovel);

		if (inicio == null) {
			this.inicio = novoImovel;
			this.fim = novoImovel;
		} else {
			this.inicio.proximo = novoImovel;
			this.fim = novoImovel;
		}

	}

	public Imovel retiraDaFila() {
		Imovel retiraImovel = inicio.nomeDoImovel;
		inicio = inicio.proximo;
		return retiraImovel;
	}

}

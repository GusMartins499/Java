package jogo;

public class NoPilha {

	/*
	 * VERDE=GANHA DINHEIRO VERMELHO=PERDE DINHEIRO PRETO=PRISAO
	 */
	public String tipoCarta;
	public String noticiaCarta;
	public NoPilha proximo;

	public NoPilha(String tipoC, String noticia) {
		tipoCarta = tipoC;
		noticiaCarta = noticia;

		if (tipoC.equalsIgnoreCase("AZUL"))
			this.noticiaCarta = "VOC� GANHOU R$ 200,00";
		this.tipoCarta = "VERDE";
		if (tipoC.equalsIgnoreCase("VERMELHO"))
			this.noticiaCarta = "VOC� PERDEU R$ 150,00";
		this.tipoCarta = "VERMELHO";
		proximo = null;
	}
}

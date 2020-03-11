package tabuleiro;

import javax.swing.JOptionPane;

public class ListaTabuleiro {

	static NoTabuleiro inicio, fim, prximo, anterior, atual;
	int contador;

	public void criaTabuleiro(Double vlProper, String nomeProper) {
		NoTabuleiro casa = new NoTabuleiro(vlProper, nomeProper);
		if (inicio == null) {
			inicio = casa;
			fim = casa;
			contador++;
		} else {
			fim.proximo = casa;
			casa.anterior = inicio;
			fim = casa;
			fim.proximo = inicio;
			inicio.anterior = fim;
			contador++;
		}
	}

	public void movimentaJogadorAteCasa(int posicaoJogador) {
		atual = inicio;
		for (int i = 1; i < posicaoJogador; i++) {
			atual = atual.proximo;

		}
	}

	public double verificaCasaTabuleiro(double valorSaldo) {
		String msg = "";
		if (atual.proprietario == null) {
			msg = JOptionPane.showInputDialog("Deseja comprar a propriedade ?(S/N)");
			if (valorSaldo < atual.vlProper && msg.equalsIgnoreCase("s")) {
				return 0;
			} else if (valorSaldo > atual.vlProper && msg.equalsIgnoreCase("s")) {
				return atual.vlProper;
			}
		} else {
			return atual.proprietario.imovel.vlAluguel;
		}
		return 0;
	}
}

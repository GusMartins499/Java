package trabalho1;

import javax.swing.JOptionPane;

/*
* Autores:
* Gustavo Martins
* Ruhan Gon�alves
*/

public class Trabalho1 {
	public static void main(String[] args) {
		System.out.println("Atividade 1 de grafos");

		// armazena se o grafo � orientado ou n�o, valorado ou n�o, e joga para outras
		// vari�veis
		String orientado = "";
		String valorado = "";
		do {
			orientado = JOptionPane.showInputDialog(
					"O grafo ser� orientado (S/N)" + "\n" + "CASO ESCREVA 'N' SER� TRATADO COMO GRAFO N�O ORIENTADO");
		} while ((!orientado.equalsIgnoreCase("S")) && (!orientado.equalsIgnoreCase("N")));
		if (orientado.equalsIgnoreCase("S")) {
			System.out.print("Orientado / ");
		} else {
			System.out.print("N�o Orientado / ");
		}
		do {
			valorado = JOptionPane.showInputDialog(
					"O grafo ser� valorado (S/N)" + "\n" + "CASO ESCREVA 'N' SER� TRATADO COMO GRAFO N�O VALORADO");
		} while ((!valorado.equalsIgnoreCase("S")) && (!valorado.equalsIgnoreCase("N")));
		if (valorado.equalsIgnoreCase("S")) {
			System.out.println("Valorado");
		} else {
			System.out.println("N�o Valorado");
		}
		System.out.println();

		Grafo grafo = new Grafo();
		grafo.cadastraQtdVertice();
		grafo.cadastraAresta(valorado, orientado);
		grafo.listaDeArestas();
		grafo.matrizAdjacencia();


	}
}

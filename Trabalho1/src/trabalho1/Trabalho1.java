package trabalho1;

import javax.swing.JOptionPane;

/*
* Autores:
* Gustavo Martins
* Ruhan Gonçalves
*/

public class Trabalho1 {
	public static void main(String[] args) {
		System.out.println("Atividade 1 de grafos");

		// armazena se o grafo é orientado ou não, valorado ou não, e joga para outras
		// variáveis
		String orientado = "";
		String valorado = "";
		do {
			orientado = JOptionPane.showInputDialog(
					"O grafo será orientado (S/N)" + "\n" + "CASO ESCREVA 'N' SERÁ TRATADO COMO GRAFO NÃO ORIENTADO");
		} while ((!orientado.equalsIgnoreCase("S")) && (!orientado.equalsIgnoreCase("N")));
		if (orientado.equalsIgnoreCase("S")) {
			System.out.print("Orientado / ");
		} else {
			System.out.print("Não Orientado / ");
		}
		do {
			valorado = JOptionPane.showInputDialog(
					"O grafo será valorado (S/N)" + "\n" + "CASO ESCREVA 'N' SERÁ TRATADO COMO GRAFO NÃO VALORADO");
		} while ((!valorado.equalsIgnoreCase("S")) && (!valorado.equalsIgnoreCase("N")));
		if (valorado.equalsIgnoreCase("S")) {
			System.out.println("Valorado");
		} else {
			System.out.println("Não Valorado");
		}
		System.out.println();

		Grafo grafo = new Grafo();
		grafo.cadastraQtdVertice();
		grafo.cadastraAresta(valorado, orientado);
		grafo.listaDeArestas();
		grafo.matrizAdjacencia();


	}
}

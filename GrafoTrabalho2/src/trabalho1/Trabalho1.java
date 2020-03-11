package trabalho1;

import java.util.Scanner;

/*
* Autores:
* Gustavo Martins
* Ruhan Gonçalves
*/

public class Trabalho1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Atividade 1 de grafos");

		// armazena se o grafo é orientado ou não, valorado ou não, e joga para outras
		// variáveis
		char orientado;
		char valorado;
		// descobre se é orientado ou não
		do {
			System.out.println("O grafo será orientado? (s/n)");
			System.out.print("resposta: ");
			orientado = scan.nextLine().charAt(0);
		} while ((orientado != 's') && (orientado != 'n'));
		// descobre se é valorado ou não
		do {
			System.out.println("O grafo será valorado? (s/n)");
			System.out.print("resposta: ");
			valorado = scan.nextLine().charAt(0);
		} while ((valorado != 's') && (valorado != 'n'));

		if (orientado == 's') {
			System.out.print("Orientado / ");
		} else {
			System.out.print("Não Orientado / ");
		}

		if (valorado == 's') {
			System.out.println("Valorado");
		} else {
			System.out.println("Não Valorado");
		}
		System.out.println();

		Grafo grafo = new Grafo();
		grafo.cadastraVertice();
		grafo.cadastraAresta(valorado, orientado);
		grafo.listaDeArestas();
		grafo.matrizDeAdjacencia();
		grafo.listaDeAdjacencia();

		System.out.println("\n \nFim do programa");

	}
}
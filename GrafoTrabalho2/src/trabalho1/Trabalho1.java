package trabalho1;

import java.util.Scanner;

/*
* Autores:
* Gustavo Martins
* Ruhan Gon�alves
*/

public class Trabalho1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Atividade 1 de grafos");

		// armazena se o grafo � orientado ou n�o, valorado ou n�o, e joga para outras
		// vari�veis
		char orientado;
		char valorado;
		// descobre se � orientado ou n�o
		do {
			System.out.println("O grafo ser� orientado? (s/n)");
			System.out.print("resposta: ");
			orientado = scan.nextLine().charAt(0);
		} while ((orientado != 's') && (orientado != 'n'));
		// descobre se � valorado ou n�o
		do {
			System.out.println("O grafo ser� valorado? (s/n)");
			System.out.print("resposta: ");
			valorado = scan.nextLine().charAt(0);
		} while ((valorado != 's') && (valorado != 'n'));

		if (orientado == 's') {
			System.out.print("Orientado / ");
		} else {
			System.out.print("N�o Orientado / ");
		}

		if (valorado == 's') {
			System.out.println("Valorado");
		} else {
			System.out.println("N�o Valorado");
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
package testee;

import java.util.ArrayList;

/*
 * Autores:
 * Ruhan Gonçalves
 * Gustavo Martins
 */

public class Main {
	public static void main(String[] args) {
		ArrayList<No> nosCadastrados = new ArrayList<No>();
		System.out.println("Atividade 2 de grafos (algoritmo de Dijkstra)");

		Grafo grafo = new Grafo();
		grafo.isGrafoOrientado();
		grafo.isGrafoValorado();

		grafo.cadastraVertices(nosCadastrados);
		grafo.cadastraArestas(nosCadastrados);

		No origem=grafo.defineOrigem(nosCadastrados);

		grafo.adicionaNosAoGrafo(nosCadastrados);
		Dijkstra dijkstra = new Dijkstra();
		dijkstra.verificaMenoresCaminhos(grafo, origem);


		System.out.println("\n\nFim do programa");

	}
}

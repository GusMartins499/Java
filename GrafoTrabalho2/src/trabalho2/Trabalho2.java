package trabalho2;

/*
* Autores:
* Gustavo Martins
* Ruhan Gon�alves
*/

public class Trabalho2 {
	public static void main(String[] args) {
		System.out.println("Atividade 2 de grafos (algoritmo de Dijkstra)");

		Grafo grafo = new Grafo();
		grafo.isGrafoOrientado();
		grafo.isGrafoValorado();

		grafo.cadastraVertice();
		grafo.cadastraAresta();

		grafo.algoritmoDeDijkstra();

		// o m�todo abaixo confere se as arestas foram inseridas corretamente
		// grafo.listaDeArestas();


		System.out.println("\n \nFim do programa");

	}
}
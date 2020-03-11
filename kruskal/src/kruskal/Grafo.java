package kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Grafo {

	int totvertice = 0;
	ArrayList<Aresta> arestasGrafo = new ArrayList<Aresta>();

	public Grafo(int vertice) {
		this.totvertice = vertice;
	}

	public void addAresta(int origem, int destino, int peso) {
		Aresta aresta = new Aresta(origem, destino, peso);
		arestasGrafo.add(aresta);
	}

	public void kruskalArvoreMinima() {
		// ordena o array
		Collections.sort(arestasGrafo);
		// cria a fila de prioridade
		PriorityQueue<Aresta> filaPrioridade = new PriorityQueue<>();
		// adiciona o array ordenado na fila de prioridade, aqui seria a ordenacao das
		// arestas, ordenado por peso da aresta
		for (int i = 0; i < arestasGrafo.size(); i++) {
			filaPrioridade.add(arestasGrafo.get(i));
		}
		// predecessor, vetor para cada vertice
		int[] conjunto = new int[totvertice];
	

		// cria o conjunto dos vertices
		criaConjunto(conjunto);
		System.out.println(filaPrioridade.toString());
		System.out.println();

		ArrayList<Aresta> arestaArvoreMinima = new ArrayList<>();

		// monta a arvore até vertice-1
		int index = 0;
		while (index < totvertice - 1) {
			// remove a aresta com menor peso da fila
			Aresta aresta = filaPrioridade.remove();
			// System.out.println(filaPrioridade.toString());

			// utiliza aqui a aresta removida, a que tem o menor peso
			int x_set = find(conjunto, aresta.origem);
			int y_set = find(conjunto, aresta.destino);
			System.out.println();
			// for(int i=0;i<conjunto.length;i++) {
			// System.out.println(conjunto[i]);
			// }
			// erro, ciclos ...
			if (x_set == y_set) {

			} else {

				arestaArvoreMinima.add(aresta);
				index++;
				union(conjunto, x_set, y_set);
			}
		}
		// print MST
		System.out.println("Arvore mínima: ");
		printGrafo(arestaArvoreMinima);
		System.out.println();

	}

	public void criaConjunto(int[] conjunto) {
		// para cada posicao no vetor, armazena o vertice
		for (int i = 0; i < totvertice; i++) {
			conjunto[i] = i;
			// System.out.println(conjunto[i]);
		}
	}

	public int find(int[] conjunto, int vertex) {
		if (conjunto[vertex] != vertex)
			return find(conjunto, conjunto[vertex]);

		return vertex;

	}

	public void union(int[] conjunto, int x, int y) {
		int x_set_conjunto = find(conjunto, x);
		int y_set_conjunto = find(conjunto, y);

		conjunto[y_set_conjunto] = x_set_conjunto;
		// System.out.println(conjunto[y_set_conjunto]);
	}

	public void printGrafo(ArrayList<Aresta> arestaList) {
		int custoTot = 0;
		for (int i = 0; i < arestaList.size(); i++) {
			Aresta aresta = arestaList.get(i);
			custoTot += aresta.getPeso();
			System.out.println("aresta-" + i + " origem: " + aresta.origem + " destino: " + aresta.destino + " peso: "
					+ aresta.peso);
		}
		System.out.println("Custo da árvore: " + custoTot);

	}

}

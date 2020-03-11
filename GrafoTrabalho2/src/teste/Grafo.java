package teste;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Grafo {

	public void Dijkstra(Vertice verticeInicial) {

		verticeInicial.setDistancia(0);
		PriorityQueue<Vertice> priorityQueue = new PriorityQueue<>();
		priorityQueue.add(verticeInicial);
		verticeInicial.setVisitado(true);

		while (!priorityQueue.isEmpty()) {
			// Getting the minimum distance vertex from priority queue
			Vertice verticeAtual = priorityQueue.poll();

			for (Aresta aresta : verticeAtual.getlistaAdjacente()) {

				Vertice v = aresta.getvChegada();
				if (!v.isVisitado()) {
					int novaDistancia = verticeAtual.getDistancia() + aresta.getPeso();

					if (novaDistancia < v.getDistancia()) {
						priorityQueue.remove(v);
						v.setDistancia(novaDistancia);
						v.setPredecessor(verticeAtual);
						priorityQueue.add(v);
					}
				}
			}
			verticeAtual.setVisitado(true);
		}
	}

	public List<Vertice> caminhoMaisCurto(Vertice vChegada) {
		List<Vertice> caminho = new ArrayList<>();

		for (Vertice vertice = vChegada; vertice != null; vertice = vertice.getPredecessor()) {
			caminho.add(vertice);
		}

		Collections.reverse(caminho);
		return caminho;
	}

}

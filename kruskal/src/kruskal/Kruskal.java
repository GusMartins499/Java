package kruskal;

public class Kruskal {

	public static void main(String[] args) {
		int vertices = 6;
		Grafo grafo = new Grafo(vertices);
        grafo.addAresta(0, 1, 4);
        grafo.addAresta(0, 2, 3);
        grafo.addAresta(1, 2, 1);
        grafo.addAresta(1, 3, 2);
        grafo.addAresta(2, 3, 4);
        grafo.addAresta(3, 4, 2);
        grafo.addAresta(4, 5, 6);
        grafo.kruskalArvoreMinima();
	}
}

package testee;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

public class Dijkstra {

	// método chamado no main para procurar os menores caminhos, partindo de um
	// vértice origem
	public static Grafo verificaMenoresCaminhos(Grafo grafo, No origem) {
		origem.setDistancia(0);
		// Set assim como List serve para armazenar lista, mas diferente de List não
		// aceita elementos duplicados
		Set<No> nosResolvidos = new HashSet<>();
		Set<No> nosNaoResolvidos = new HashSet<>();
		nosNaoResolvidos.add(origem);

		while (nosNaoResolvidos.size() != 0) {
			No noAtual = getNoComMenorDistancia(nosNaoResolvidos);
			nosNaoResolvidos.remove(noAtual);
			for (Entry<No, Integer> parAdjacente : noAtual.getNosVizinhos().entrySet()) {
				No noVizinho = parAdjacente.getKey();
				Integer peso = parAdjacente.getValue();

				if (!nosResolvidos.contains(noVizinho)) {
					calculaMenorDistancia(noVizinho, peso, noAtual);
					nosNaoResolvidos.add(noVizinho);
				}
			}
			nosResolvidos.add(noAtual);
		}
		return grafo;
	}

	public static void calculaMenorDistancia(No noDeAvaliacao, Integer peso, No origem) {
		Integer distanciaOrigem = origem.getDistancia();
		if (distanciaOrigem + peso < noDeAvaliacao.getDistancia()) {
			noDeAvaliacao.setDistancia(distanciaOrigem + peso);
			LinkedList<No> menorCaminho = new LinkedList<>(origem.getMenorCaminho());
			menorCaminho.add(origem);
			noDeAvaliacao.setMenorCaminho(menorCaminho);
			for(int i =0;i<=menorCaminho.size();i++) {
				System.out.println(menorCaminho.get(i).getMenorCaminho());
			}
		}
	}

	public static No getNoComMenorDistancia(Set<No> nosNaoResolvidos) {
		No noComMenorDistancia = null;
		int menorDistancia = Integer.MAX_VALUE;
		for (No no : nosNaoResolvidos) {
			int distanciaDoNo = no.getDistancia();
			if (distanciaDoNo < menorDistancia) {
				menorDistancia = distanciaDoNo;
				noComMenorDistancia = no;
			}
		}
		return noComMenorDistancia;
	}

	

}

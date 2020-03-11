package teste;

import java.util.Scanner;

public class Teste {
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int v = s.nextInt();
		int e = s.nextInt();
		int matrizAdjacente[][] = new int[v][v];
		for (int i = 0; i < e; i++) {
			int v1 = s.nextInt();
			int v2 = s.nextInt();
			int peso = s.nextInt();
			matrizAdjacente[v1][v2] = peso;
			matrizAdjacente[v2][v1] = peso;
		}
		dijistra(matrizAdjacente);
	}

	private static void dijistra(int[][] matrizAdj) {
		int v = matrizAdj.length;
		boolean visitado[] = new boolean[v];
		int distancia[] = new int[v];
		distancia[0] = 0;
		for (int i = 1; i < v; i++) {
			distancia[i] = Integer.MAX_VALUE;
		}
		for (int i = 0; i < v - 1; i++) {
			// encontrar o vertice com menor distancia
			int minVertice = encontraMinVertice(distancia, visitado);
			visitado[minVertice] = true;
			// explorar adjacentes
			for (int j = 0; j < v; j++) {
				if (matrizAdj[minVertice][j] != 0 && !visitado[j] && distancia[minVertice] != Integer.MAX_VALUE) {
					int novaDistancia = distancia[minVertice] + matrizAdj[minVertice][j];
					if (novaDistancia < distancia[j]) {
						distancia[j] = novaDistancia;
					}

				}
			}
		}
		for (int i = 0; i < v; i++) {
			System.out.println(i + " " + distancia[i]);
		}

	}

	private static int encontraMinVertice(int[] distancia, boolean visitado[]) {
		int minVertice = -1;
		for (int i = 0; i < distancia.length; i++) {
			if (!visitado[i] && (minVertice == -1 || distancia[i] < distancia[minVertice])) {
				minVertice = i;
			}
		}
		return minVertice;
	}

}

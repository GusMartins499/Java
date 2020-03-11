package testee;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class Grafo {
	private Set<No> nos = new HashSet<>();
	private boolean isOrientado;
	private boolean isValorado;

	public void adicionaNo(No no) {
		nos.add(no);
	}

	// adiciona os nós no grafo, para calcular os menores caminhos
	public void adicionaNosAoGrafo(ArrayList<No> nosCadastrados) {
		for (int i = 0; i < nosCadastrados.size(); i++) {
			adicionaNo(nosCadastrados.get(i));
		}
	}

	// método para escolher o nó de origem para realizar Dijkstra
	public No defineOrigem(ArrayList<No> nosCadastrados) {
		System.out.println("\nEscolhe o vértice de origem");
		Scanner scan = new Scanner(System.in);
		No verticeOrigem = new No();
		int posMinimo = 1;
		int posMaximo = nosCadastrados.size();
		int pos = -1;
		mostraVertices(nosCadastrados);
		do {
			try {
				System.out.println("Digite a posição do vértice de origem (posição na lista)");
				System.out.print("resposta: ");
				pos = scan.nextInt();
				scan.nextLine();
				if ((pos >= posMinimo) && (pos <= posMaximo)) {
					verticeOrigem = nosCadastrados.get(pos - 1);
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("opção inválida");
				scan.nextLine();
				pos = -1;
			}
		} while ((pos < posMinimo) || (pos > posMaximo));
		return verticeOrigem;
	}

	// cadastra vértices (aqui visto como nó
	public void cadastraVertices(ArrayList<No> nosCadastrados) {
		System.out.println("\nCadastro de vértices");
		Scanner scan = new Scanner(System.in);
		String operacao = "N";
		do {
			System.out.println("Digite o nome do vértice a cadastrar");
			System.out.print("nome: ");
			String nomeV = scan.nextLine();
			No noA = new No(nomeV);
			nosCadastrados.add(noA);
			do {
				System.out.println("Deseja cadastrar mais vértices ao grafo? (S/N)");
				System.out.print("resposta: ");
				operacao = scan.nextLine();
			} while ((!operacao.equalsIgnoreCase("S")) && (!operacao.equalsIgnoreCase("N")));
		} while (!operacao.equalsIgnoreCase("N"));
	}

	// método para cadastrar arestas no grafo
	public void cadastraArestas(ArrayList<No> nosCadastrados) {
		System.out.println("\nCadastro de arestas");
		Scanner scan = new Scanner(System.in);
		String operacao = "N";
		int i = 1;
		mostraVertices(nosCadastrados);
		int posMinimo = 1;
		int posMaximo = nosCadastrados.size();
		int posEnt = -1;
		int posSai = -1;
		do {
			// pede o vértice de entrada da aresta
			do {
				try {
					System.out.println("Digite o vértice de entrada da " + i + "ª aresta (posição na lista)");
					System.out.print("resposta: ");
					posEnt = scan.nextInt();
					scan.nextLine();
					if ((posEnt >= posMinimo) && (posEnt <= posMaximo)) {
						break;
					} else {
						System.out.println("opção inválida");
					}
				} catch (InputMismatchException e) {
					System.out.println("Caractere inválido");
					scan.nextLine();
					posEnt = -1;
				}
			} while ((posEnt < posMinimo) || (posEnt > posMaximo));
			// pede o vértice de saída da aresta
			do {
				try {
					System.out.println("Digite o vértice de saída da " + i + "ª aresta (posição na lista)");
					System.out.print("resposta: ");
					posSai = scan.nextInt();
					scan.nextLine();
					if ((posSai >= posMinimo) && (posSai <= posMaximo)) {
						break;
					} else {
						System.out.println("opção inválida");
					}
				} catch (InputMismatchException e) {
					System.out.println("Caractere inválido");
					scan.nextLine();
					posSai = -1;
				}
			} while ((posSai < posMinimo) || (posSai > posMaximo));
			int valor = -1;
			// se entrada for diferente de saída atribui valor a aresta, se não o valor será
			// 0
			if (posEnt != posSai) {
				// abaixo adiciona valor 1 as arestas se forem não valoradas e pede valor se
				// forem valoradas
				valor = -1;
				if (isValorado == true) {
					do {
						try {
							System.out.println("Digite o valor dessa aresta");
							System.out.print("valor: ");
							valor = scan.nextInt();
							scan.nextLine();
						} catch (InputMismatchException e) {
							System.out.println("opção inválida");
							scan.nextLine();
							valor = -1;
						}
					} while (valor <= 0);
					nosCadastrados.get(posEnt - 1).adicionaDestino(nosCadastrados.get(posSai - 1), valor);
				} else {
					valor = 1;
					nosCadastrados.get(posEnt - 1).adicionaDestino(nosCadastrados.get(posSai - 1), valor);
				}
				// se for não orientado adiciona o caminho de volta, se for orientado não faz
				// nada
				if (isOrientado = false) {
					int aux = posEnt;
					posEnt = posSai;
					posSai = aux;
					nosCadastrados.get(posEnt - 1).adicionaDestino(nosCadastrados.get(posSai - 1), valor);
				}
			} else {
				// aqui se entrada e saída forem iguais, aresta terá valor 0
				valor = 0;
				nosCadastrados.get(posEnt - 1).adicionaDestino(nosCadastrados.get(posSai - 1), valor);
			}
			i++;
			// pede se é para adicionar mais arestas
			do {
				System.out.println("Deseja cadastrar mais arestas ao grafo? (S/N)");
				System.out.print("resposta: ");
				operacao = scan.nextLine();
			} while ((!operacao.equalsIgnoreCase("S")) && (!operacao.equalsIgnoreCase("N")));
		} while (!operacao.equalsIgnoreCase("N"));
	}

	// mostra lista de vértices que foram cadastrados
	public void mostraVertices(ArrayList<No> nosCadastrados) {
		System.out.println("Lista de vértices: ");
		int ultimoDoVertice = nosCadastrados.size() - 1; // usado para facilitar na leitura do for abaixo
		for (int i = 0; i < ultimoDoVertice; i++) {
			System.out.print((i + 1) + " - " + nosCadastrados.get(i).getNome() + ", ");
		}
		System.out.println(nosCadastrados.size() + " - " + nosCadastrados.get(ultimoDoVertice).getNome());
	}

	// metodo que define se o grafo é orientado
	public void isGrafoOrientado() {
		Scanner scan = new Scanner(System.in);
		String orientado;
		do {
			System.out.println("O grafo será orientado? (S/N)");
			System.out.print("resposta: ");
			orientado = scan.nextLine();
		} while ((!orientado.equalsIgnoreCase("S")) && (!orientado.equalsIgnoreCase("N")));
		if (orientado.equalsIgnoreCase("S")) {
			isOrientado = true;
		} else {
			isOrientado = false;
		}
	}

	// metodo que define se o grafo é valorado
	public void isGrafoValorado() {
		Scanner scan = new Scanner(System.in);
		String valorado;
		do {
			System.out.println("O grafo será valorado? (S/N)");
			System.out.print("resposta: ");
			valorado = scan.nextLine();
		} while ((!valorado.equalsIgnoreCase("S")) && (!valorado.equalsIgnoreCase("N")));
		if (valorado.equalsIgnoreCase("S")) {
			isValorado = true;
		} else {
			isValorado = false;
		}
	}

	public Set<No> getNos() {
		return nos;
	}

	public void setNos(Set<No> nos) {
		this.nos = nos;
	}

}

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

	// adiciona os n�s no grafo, para calcular os menores caminhos
	public void adicionaNosAoGrafo(ArrayList<No> nosCadastrados) {
		for (int i = 0; i < nosCadastrados.size(); i++) {
			adicionaNo(nosCadastrados.get(i));
		}
	}

	// m�todo para escolher o n� de origem para realizar Dijkstra
	public No defineOrigem(ArrayList<No> nosCadastrados) {
		System.out.println("\nEscolhe o v�rtice de origem");
		Scanner scan = new Scanner(System.in);
		No verticeOrigem = new No();
		int posMinimo = 1;
		int posMaximo = nosCadastrados.size();
		int pos = -1;
		mostraVertices(nosCadastrados);
		do {
			try {
				System.out.println("Digite a posi��o do v�rtice de origem (posi��o na lista)");
				System.out.print("resposta: ");
				pos = scan.nextInt();
				scan.nextLine();
				if ((pos >= posMinimo) && (pos <= posMaximo)) {
					verticeOrigem = nosCadastrados.get(pos - 1);
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("op��o inv�lida");
				scan.nextLine();
				pos = -1;
			}
		} while ((pos < posMinimo) || (pos > posMaximo));
		return verticeOrigem;
	}

	// cadastra v�rtices (aqui visto como n�
	public void cadastraVertices(ArrayList<No> nosCadastrados) {
		System.out.println("\nCadastro de v�rtices");
		Scanner scan = new Scanner(System.in);
		String operacao = "N";
		do {
			System.out.println("Digite o nome do v�rtice a cadastrar");
			System.out.print("nome: ");
			String nomeV = scan.nextLine();
			No noA = new No(nomeV);
			nosCadastrados.add(noA);
			do {
				System.out.println("Deseja cadastrar mais v�rtices ao grafo? (S/N)");
				System.out.print("resposta: ");
				operacao = scan.nextLine();
			} while ((!operacao.equalsIgnoreCase("S")) && (!operacao.equalsIgnoreCase("N")));
		} while (!operacao.equalsIgnoreCase("N"));
	}

	// m�todo para cadastrar arestas no grafo
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
			// pede o v�rtice de entrada da aresta
			do {
				try {
					System.out.println("Digite o v�rtice de entrada da " + i + "� aresta (posi��o na lista)");
					System.out.print("resposta: ");
					posEnt = scan.nextInt();
					scan.nextLine();
					if ((posEnt >= posMinimo) && (posEnt <= posMaximo)) {
						break;
					} else {
						System.out.println("op��o inv�lida");
					}
				} catch (InputMismatchException e) {
					System.out.println("Caractere inv�lido");
					scan.nextLine();
					posEnt = -1;
				}
			} while ((posEnt < posMinimo) || (posEnt > posMaximo));
			// pede o v�rtice de sa�da da aresta
			do {
				try {
					System.out.println("Digite o v�rtice de sa�da da " + i + "� aresta (posi��o na lista)");
					System.out.print("resposta: ");
					posSai = scan.nextInt();
					scan.nextLine();
					if ((posSai >= posMinimo) && (posSai <= posMaximo)) {
						break;
					} else {
						System.out.println("op��o inv�lida");
					}
				} catch (InputMismatchException e) {
					System.out.println("Caractere inv�lido");
					scan.nextLine();
					posSai = -1;
				}
			} while ((posSai < posMinimo) || (posSai > posMaximo));
			int valor = -1;
			// se entrada for diferente de sa�da atribui valor a aresta, se n�o o valor ser�
			// 0
			if (posEnt != posSai) {
				// abaixo adiciona valor 1 as arestas se forem n�o valoradas e pede valor se
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
							System.out.println("op��o inv�lida");
							scan.nextLine();
							valor = -1;
						}
					} while (valor <= 0);
					nosCadastrados.get(posEnt - 1).adicionaDestino(nosCadastrados.get(posSai - 1), valor);
				} else {
					valor = 1;
					nosCadastrados.get(posEnt - 1).adicionaDestino(nosCadastrados.get(posSai - 1), valor);
				}
				// se for n�o orientado adiciona o caminho de volta, se for orientado n�o faz
				// nada
				if (isOrientado = false) {
					int aux = posEnt;
					posEnt = posSai;
					posSai = aux;
					nosCadastrados.get(posEnt - 1).adicionaDestino(nosCadastrados.get(posSai - 1), valor);
				}
			} else {
				// aqui se entrada e sa�da forem iguais, aresta ter� valor 0
				valor = 0;
				nosCadastrados.get(posEnt - 1).adicionaDestino(nosCadastrados.get(posSai - 1), valor);
			}
			i++;
			// pede se � para adicionar mais arestas
			do {
				System.out.println("Deseja cadastrar mais arestas ao grafo? (S/N)");
				System.out.print("resposta: ");
				operacao = scan.nextLine();
			} while ((!operacao.equalsIgnoreCase("S")) && (!operacao.equalsIgnoreCase("N")));
		} while (!operacao.equalsIgnoreCase("N"));
	}

	// mostra lista de v�rtices que foram cadastrados
	public void mostraVertices(ArrayList<No> nosCadastrados) {
		System.out.println("Lista de v�rtices: ");
		int ultimoDoVertice = nosCadastrados.size() - 1; // usado para facilitar na leitura do for abaixo
		for (int i = 0; i < ultimoDoVertice; i++) {
			System.out.print((i + 1) + " - " + nosCadastrados.get(i).getNome() + ", ");
		}
		System.out.println(nosCadastrados.size() + " - " + nosCadastrados.get(ultimoDoVertice).getNome());
	}

	// metodo que define se o grafo � orientado
	public void isGrafoOrientado() {
		Scanner scan = new Scanner(System.in);
		String orientado;
		do {
			System.out.println("O grafo ser� orientado? (S/N)");
			System.out.print("resposta: ");
			orientado = scan.nextLine();
		} while ((!orientado.equalsIgnoreCase("S")) && (!orientado.equalsIgnoreCase("N")));
		if (orientado.equalsIgnoreCase("S")) {
			isOrientado = true;
		} else {
			isOrientado = false;
		}
	}

	// metodo que define se o grafo � valorado
	public void isGrafoValorado() {
		Scanner scan = new Scanner(System.in);
		String valorado;
		do {
			System.out.println("O grafo ser� valorado? (S/N)");
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

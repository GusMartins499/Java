package trabalho1;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

//criação do objeto grafo para ajudar na implementação
public class Grafo {

	/*
	 * toda a manipulação do grafo é feita aqui, na classe main, só será passado as
	 * informações sobre o grafo
	 */

	private ArrayList<Vertice> listaVertices = new ArrayList<Vertice>();
	private ArrayList<Aresta> listaArestas = new ArrayList<Aresta>();
	private boolean isOrientado;
	private Boolean isValorado;

	// metodo que cadastra as arestas
	public void cadastraAresta(char valoradoSN, char orientadoSN) {
		// os dois métodos abaixo mudam os booleanos de acordo com a resposta
		isGrafoValorado(valoradoSN);
		isGrafoOrientado(orientadoSN);
		Scanner scan = new Scanner(System.in);
		char operacao = 's';
		int i = 1;
		/*
		 * Os dois vértices aabaixo pegarão o mesmo valor dos vértices inseridos no laço
		 * de repetição abaixo Dessa forma é possível fazer uma comparação usando um
		 * método para saber se já existe na lista a aresta em questão
		 */
		Vertice vEnt = new Vertice();
		Vertice vSai = new Vertice();
		do {
			Aresta aresta = new Aresta();
			mostrarVertices();
			int posMinimo = 1;
			int posMaximo = listaVertices.size();
			int pos = -1;
			// pede o vértice de entrada da aresta
			do {
				try {
					System.out.println("Digite o vértice de entrada da " + i + "ª aresta (posição na lista)");
					System.out.print("resposta: ");
					pos = scan.nextInt();
					scan.nextLine();
					if ((pos >= posMinimo) && (pos <= posMaximo)) {
						aresta.setVEntrada(listaVertices.get(pos - 1));
						vEnt = aresta.getVEntrada();
						break;
					} else {
						System.out.println("opção inválida");
					}
				} catch (InputMismatchException e) {
					System.out.println("Caractere inválido");
					scan.nextLine();
					pos = -1;
				}
			} while ((pos < posMinimo) || (pos > posMaximo));
			pos = -1;
			// pede o vértice de saída da aresta
			do {
				try {
					System.out.println("Digite o vértice de saída da " + i + "ª aresta (posição na lista)");
					System.out.print("resposta: ");
					pos = scan.nextInt();
					scan.nextLine();
					if ((pos >= posMinimo) && (pos <= posMaximo)) {
						aresta.setVSaida(listaVertices.get(pos - 1));
						vSai = aresta.getVSaida();
						break;
					} else {
						System.out.println("opção inválida");
					}
				} catch (InputMismatchException e) {
					System.out.println("Caractere inválido");
					scan.nextLine();
					pos = -1;
				}
			} while ((pos < posMinimo) || (pos > posMaximo));
			// o if abaixo verifica se a aresta já existe, se não existir aí sim insere ela
			if (existeAresta(vEnt, vSai) == false) {
				// se for valorado pede valor, se não atribui 0 ao valor
				if (isValorado == false) {
					aresta.setValorAresta(0.0);
				} else {
					if (aresta.getVEntrada() != aresta.getVSaida()) {
						// com esse if, se o vértice de entrada for igual ao de saída será atribuido
						// valor 0
						do {
							try {
								System.out.println("Digite o valor da aresta " + i + "");
								System.out.print("resposta: ");
								aresta.setValorAresta(scan.nextDouble());
								scan.nextLine();
							} catch (InputMismatchException e) {
								System.out.println("Valor inválido");
								aresta.setValorAresta(0.0);
								scan.nextLine();
							}
						} while (aresta.getValorAresta() == 0);
					} else {
						aresta.setValorAresta(0.0);
					}
				}
				listaArestas.add(aresta);
				// caso não seja orientado é preciso adicionar o caminho contrário, isso é feito
				// abaixo
				if (isOrientado == false) {
					if (vEnt != vSai) {
						Vertice vAux = new Vertice();
						Aresta aAux = new Aresta();
						vAux = vEnt;
						vEnt = vSai;
						vSai = vAux;
						aAux.setVEntrada(vEnt);
						aAux.setVSaida(vSai);
						aAux.setValorAresta(aresta.getValorAresta());
						listaArestas.add(aAux);
					}
				}
				i++;
				do {
					System.out.println("Deseja cadastrar mais arestas? (s/n)");
					System.out.print("resposta: ");
					operacao = scan.nextLine().charAt(0);
				} while ((operacao != 's') && (operacao != 'n'));
			} else {
				System.out.println("A aresta já existe no grafo. \nInsira outra aresta.");
			}
		} while (operacao != 'n');
	}

	// metodo que irá cadastrar os vértices do grafo
	public void cadastraVertice() {
		Scanner scan = new Scanner(System.in);
		char operacao = 's';
		do {
			System.out.println("Digite o nome do vértice");
			System.out.print("resposta: ");
			String nomeV = scan.nextLine();
			Vertice vertice = new Vertice();
			vertice.setNomeDoVertice(nomeV);
			listaVertices.add(vertice);
			do {
				System.out.println("Deseja cadastrar mais algum vértice? (s/n)");
				System.out.print("resposta: ");
				operacao = scan.nextLine().charAt(0);
			} while ((operacao != 's') && (operacao != 'n'));
		} while (operacao != 'n');
	}

	// verifica se aresta já existe no grafo e retorna um booleano
	public boolean existeAresta(Vertice vEnt, Vertice vSai) {
		for (int i = 0; i < listaArestas.size(); i++) {
			if (listaArestas.get(i).getVEntrada() == vEnt) {
				if (listaArestas.get(i).getVSaida() == vSai) {
					return true;
				}
			}
		}
		return false;
	}

	// método usado para mostrar os vértices cadastrados
	public void mostrarVertices() {
		System.out.println();
		System.out.println("Lista de vértices: ");
		int ultimoDoVertice = listaVertices.size() - 1; // usado para facilitar na leitura do for abaixo
		for (int i = 0; i < ultimoDoVertice; i++) {
			System.out.print((i + 1) + " - " + listaVertices.get(i).getNomeDoVertice() + ", ");
		}
		System.out.println(listaVertices.size() + " - " + listaVertices.get(ultimoDoVertice).getNomeDoVertice());
	}

	// metodo que faz a lista de arestas
	public void listaDeArestas() {
		System.out.println();
		System.out.println("Lista de arestas: ");
		for (int i = 0; i < listaArestas.size(); i++) {
			if (isValorado == true) {
				System.out.println(listaArestas.get(i).getVEntrada().getNomeDoVertice().toUpperCase() + ""
						+ listaArestas.get(i).getVSaida().getNomeDoVertice().toUpperCase() + ""
						+ listaArestas.get(i).getValorAresta());
			} else {
				System.out.println(listaArestas.get(i).getVEntrada().getNomeDoVertice().toUpperCase() + ""
						+ listaArestas.get(i).getVSaida().getNomeDoVertice().toUpperCase());
			}
		}
	}

	// resolve a matriz de adjacência
	public void matrizDeAdjacencia() {
		int j = 0;
		System.out.println();
		System.out.println("Matriz de adjacência");
		for (int i = 0; i < listaVertices.size(); i++) {
			Vertice vEnt = listaVertices.get(i);
			for (j = 0; j < listaVertices.size(); j++) {
				Vertice vSai = listaVertices.get(j);
				if (existeAresta(vEnt, vSai) == true) {
					System.out.print("1");
				} else {
					System.out.print("0");
				}
			}
			System.out.println();
			j = 0;
		}
	}

	// resolve a lista de adjacência
	public void listaDeAdjacencia() {
		int i = 0;
		int j = 0;
		Vertice vAdj;
		ArrayList<Vertice> vAux;
		System.out.println();
		System.out.println("Lista de adjacência");
		for (i = 0; i < listaVertices.size(); i++) {
			vAux = new ArrayList<Vertice>();
			Vertice vEnt = listaVertices.get(i);
			for (j = 0; j < listaVertices.size(); j++) {
				Vertice vSai = listaVertices.get(j);
				if (existeAresta(vEnt, vSai) == true) {
					vAux.add(vSai);
				}
			}
			j = 0;
			vAdj = new Vertice();
			vAdj.setAdjacentes(vAux);
			// agora lista os vértices adjacentes no trecho abaixo
			for (int k = 0; k < vAux.size(); k++) {
				if (k == 0) {
					System.out.print(listaVertices.get(i).getNomeDoVertice() + " -> ");
				}
				if (k != (vAux.size() - 1)) {
					System.out.print(vAdj.getAdjacentes().get(k).getNomeDoVertice() + ", ");
				} else {
					System.out.println(vAdj.getAdjacentes().get(k).getNomeDoVertice());
				}
			}
			vAux.clear();
		}
	}

	// metodo que define se o grafo é valorado e muda o booleano
	public void isGrafoValorado(char valoradoSN) {
		if (valoradoSN == 's') {
			isValorado = true;
		} else {
			isValorado = false;
		}
	}

	// metodo que define se o grafo é orientado e muda o booleano
	public void isGrafoOrientado(char orientadoSN) {
		if (orientadoSN == 's') {
			isOrientado = true;
		} else {
			isOrientado = false;
		}
	}

}

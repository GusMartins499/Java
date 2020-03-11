package testee;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// classe de n�s, v�rtices
public class No {
	private String nome;
	private LinkedList<No> menorCaminho = new LinkedList<>();
	private Integer distancia = Integer.MAX_VALUE;
	private Map<No, Integer> nosVizinhos = new HashMap<>();

	public No() {
	}

	public No(String nome) {
		this.nome = nome;
	}

	// m�todo para fazer as liga��es, usado no cadastro de arestas na classe Grafo
	public void adicionaDestino(No destino, int distancia) {
		nosVizinhos.put(destino, distancia);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LinkedList<No> getMenorCaminho() {
		return menorCaminho;
	}

	public void setMenorCaminho(LinkedList<No> menorCaminho) {
		this.menorCaminho = menorCaminho;
	}

	public Integer getDistancia() {
		return distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

	public Map<No, Integer> getNosVizinhos() {
		return nosVizinhos;
	}

	public void setNosVizinhos(Map<No, Integer> nosVizinhos) {
		this.nosVizinhos = nosVizinhos;
	}

}

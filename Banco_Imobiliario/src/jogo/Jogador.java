package jogo;

import java.util.ArrayList;
import java.util.Random;

public class Jogador {

	public String nomeJogador;
	public double saldo;

	public ArrayList<Imovel> listaImoveis = new ArrayList<Imovel>();

	public Jogador(String nome) {
		this.nomeJogador = nome;
		this.saldo = 2500;
	}

	public String getNomeJogador() {
		return nomeJogador;
	}

	public void setNomeJogador(String nomeJogador) {
		this.nomeJogador = nomeJogador;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public ArrayList<Imovel> getListaImoveis() {
		return listaImoveis;
	}

	public void setListaImoveis(ArrayList<Imovel> listaImoveis) {
		this.listaImoveis = listaImoveis;
	}

	public int jogarDado() {
		Random random = new Random();
		int valorDoDado = random.nextInt(6);
		if (valorDoDado == 0) {
			jogarDado();
		}
		return valorDoDado;
	}
}

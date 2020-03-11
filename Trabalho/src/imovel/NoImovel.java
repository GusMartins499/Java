package imovel;

public class NoImovel {

	String nomeImo;
	public double vlAluguel;
	NoImovel anterior, proximo;
	int contador=0;

	public NoImovel(String nome, double alguel) {
		nomeImo = nome;
		vlAluguel = alguel;
		anterior = null;
		proximo = null;
		contador++;
	}
}

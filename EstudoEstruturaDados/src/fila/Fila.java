package fila;

public class Fila {

	No inicio, fim;

	public void criaFila(int vl) {
		No novo = new No(vl);
		if (inicio == null) {
			inicio = novo;
			fim = novo;
		} else {
			fim.proximo = novo;
			fim = novo;
		}
	}

	public void retira() {
		// verifica se esta vazio
		int valorRetirado = inicio.valor;
		inicio = inicio.proximo;
		System.out.println(valorRetirado);
	}
	
	public void frente() {
		System.out.println(inicio.valor);
	}
}

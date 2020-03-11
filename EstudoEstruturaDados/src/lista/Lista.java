package lista;

public class Lista {

	No inicio, fim, proximo, atual;
	public static int tamanhoLista = 0;

	public void criaLista(int vl) {
		No novo = new No(vl);
		if (inicio == null) {
			inicio = novo;
			fim = novo;
			tamanhoLista++;
		} else {
			inicio.proximo = novo;
			fim = novo;
			tamanhoLista++;
		}
	}

	public void insereFim(int vl) {
		No novo = new No(vl);
		// No aux = vaiNaPosicao();
		// aux = aux.proximo;
		// aux.proximo = novo;
		fim.proximo = novo;
		fim = novo;
		tamanhoLista++;
		System.out.println("Elemento inserido no fim: " + novo.valor);
	}

	// INSERIR ANTES DO 1ª ELEMENTO DA LISTA
	public void insereInicio(int vl) {
		No novo = new No(vl);
		novo.proximo = inicio;
		inicio = novo;
		tamanhoLista++;
		System.out.println("Elemento inserido no inicio: " + novo.valor);
	}

	public void retornaFim() {
		System.out.println("Elemento fim: " + fim.valor);
	}

	public void retornaInicio() {
		System.out.println("Elemento inicio: " + inicio.valor);
	}

	public void retiraInicio() {
		int vlRetirado = inicio.valor;
		inicio = inicio.proximo;
		tamanhoLista--;
		System.out.println("Elemento inicio retirado :" + vlRetirado);
	}

	public void retiraFim() {
		int vlRetirado = fim.valor;
		atual = vaiNaPosicao();
		//System.out.println("Vai na posicao: "+vaiNaPosicao().valor);
		atual.proximo = null;
		fim = atual;
		tamanhoLista--;
		//System.out.println("Fim.valor: "+fim.valor);
		//System.out.println("Atual: "+atual.valor);
		System.out.println("Elemento fim retirado: " + vlRetirado);

	}

	public No vaiNaPosicao() {
		No aux = inicio;
		for (int i = 1; i < tamanhoLista - 2; i++) {
			aux = aux.proximo;
		}
		return aux;
	}
}

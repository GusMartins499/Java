
public class Fila {
	No inicio, ultimo, atual;
	int tamanho = 0;
	private int valorTotal;

	public void adiciona(String or, String dest, int vl) {
		No novo = new No(or, dest);
		if (inicio == null) {
			tamanho++;
			inicio = novo;
			ultimo = novo;
		} else {
			ultimo.proximo = novo;
			ultimo = novo;
			tamanho++;
		}
	}

	public boolean VerificaVazio() {
		if (inicio == null) {
			return true;
		} else {
			return false;
		}
	}

	public int tamanho() {
		return tamanho;
	}

	public int getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}

}

package imovel;

public class FilaImovel {

	NoImovel inicio, fim;

	public void criaFila(String nome, double aluguel) {
		NoImovel ImovelNovo = new NoImovel(nome, aluguel);
		if (inicio == null) {
			inicio = ImovelNovo;
			fim = ImovelNovo;
		} else {
			fim.proximo = ImovelNovo;
			fim = ImovelNovo;
		}

	}

	public boolean estaVazio() {
		if (inicio == null) {
			return true;
		} else {
			return false;
		}
	}

	// RETIRA
	public String retira() {
		String msg = "";
		if (estaVazio() == false) {
			inicio = inicio.proximo;
			return msg;
		} else {
			msg = "VOCÊ PERDEU!";
		}
		return msg;

	}

}

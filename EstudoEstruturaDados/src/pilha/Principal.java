package pilha;

public class Principal {

	public static void main(String[] args) {
		Pilha p = new Pilha();
		p.empilha(1);
		p.empilha(2);
		p.empilha(3);
		p.empilha(4);
		p.empilha(5);
		while (!p.vazio()) {
			System.out.println("Desempilhando " + p.desempilha());
		}

	}

}

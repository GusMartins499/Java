package application;

import javax.swing.JOptionPane;

public class TesteExcecao {
	
	static int[] vetor;
	
	public static void main(String[] args) {
		
		String menu = "1 - Criar Vetor\n"
				+ "2 - Inserir número no vetor\n"
				+ "3 - Exibe vetor\n"
				+ "4 - Sair";
		
		int op = 0;
		do {
			try {
			op = Integer.parseInt(JOptionPane.showInputDialog(menu));
				if(op == 1)
					criarVetor();
				if(op == 2)
					inserirNumero();
				if(op == 3)
					exibeVetor();
			}catch (NumberFormatException e) {
				msg("Número inválido: "+e.getMessage());
			}catch (NullPointerException e) {
				msg("Vetor não criado");
			}catch (ArrayIndexOutOfBoundsException e) {
				msg("Posição de vetor inválida");
			}catch (Exception e) {
				msg("Erro não identificado");
			}finally {
				msg("finally");
			}
		}while(op!=4);
	}
	
	static void msg(String texto) {
		JOptionPane.showMessageDialog(null, texto);
	}
	
	static void criarVetor() {
		int tam = Integer.parseInt(JOptionPane.showInputDialog("Tamanho:"));
		vetor = new int[tam];
	}
	
	static void inserirNumero() {
		int pos = Integer.parseInt(JOptionPane.showInputDialog("Posição:"));
		int nr = Integer.parseInt(JOptionPane.showInputDialog("Número:"));
		if(nr < 0)
			throw new NumberFormatException("Idade inválida");
		vetor[pos] = nr;
	}
	
	static void exibeVetor() {
		try {
			String x = "";
			for (int i : vetor) {
				x += i + "\n";
			}
			JOptionPane.showMessageDialog(null, x);
		} catch (NullPointerException e) {
			msg("Null pointer tratado no método");
			throw e;
		}
	}

}

package lista;

public class Principal {

	public static void main(String[] args) {
		Lista l = new Lista();
		l.criaLista(1);
		l.criaLista(2);
		l.criaLista(3);
		
		l.retornaInicio();
		l.retornaFim();
		
		l.insereInicio(0);
		l.retornaInicio();
		
		l.insereFim(4);
		l.retornaFim();
		
		//l.retiraInicio();
		//l.retornaInicio();
		
		l.retiraFim();
		l.retornaFim();
	}
	
}

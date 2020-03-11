
import java.util.ArrayList;

public class Kruskal {
	static boolean ori = false ,dest = false;
	public int valorTotal = 0;
	ArrayList<Conjunto> conjuntos =  new ArrayList<Conjunto>();
	Fila fila = new Fila();
	Conjunto c = new Conjunto();
	
	public void kruskal(String o, String d, int tamanhoConjutoE, int vl) {
		if (fila.VerificaVazio()) {
			c.adiciona(o, d);
			conjuntos.add(c);
			valorTotal += vl;
			fila.adiciona(o, d, vl);
		} else {
			verfica(o,d,vl);
		}
	}

	public int MostraTotal() {
		return valorTotal;
	}
	
	private void verfica(String o, String d, int vl) {
	
			for (Conjunto conjunto : conjuntos) {
				System.out.println("origem "+conjunto.getO()+" destino "+conjunto.getD());
				if(conjunto.getO().equalsIgnoreCase(o)) {
					ori = true;
				}
				if(conjunto.getD().equalsIgnoreCase(d)) {
					dest = true;
				}
		}
			
		if (ori == true && dest == true) {
			System.out.println("ja tem no conjunto não se pode criar loop ");
		} else {
			c.adiciona(o, d);
			fila.adiciona(o, d, vl);
			valorTotal += vl;
			conjuntos.add(c);
		}
		ori = false;
		dest = false;
	}
		
	
}


import java.util.ArrayList;
import java.util.Collections;

public class Principal {

	public static void main(String[] args) {
		//Variaveis
		String[] vertices = {"a","b","c","d","e","f","g","h"};
		String [] origem = {"a","b","c","a","c","f","e","e","h"};
		String [] destino = {"b","c","e","e","f","e","g","h","g"};
		int [] valores = {2,1,4,3,7,3,5,3,10};
		Kruskal k  = new Kruskal();
		int tamanhoCaminho = 0;
		ArrayList<Vertice> vert = new ArrayList<Vertice>();
		ArrayList<Aresta> arestas = new ArrayList<Aresta>();
		//
		for (int i = 0; i < vertices.length; i++) {
			Vertice v = new Vertice(vertices[i]);
			vert.add(v);
		}
		for (int i = 0; i < vertices.length; i++) {
			Aresta a = new Aresta(origem[i],destino[i],valores[i]);
			arestas.add(a);
		}
		
		Collections.sort(arestas);
	
		for (Aresta aresta : arestas) {
			k.kruskal(aresta.origem,aresta.destino,destino.length,aresta.valor);
			tamanhoCaminho = k.MostraTotal();
		}
	System.out.println(tamanhoCaminho);
	}

}

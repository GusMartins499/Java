package teste1;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Teste1 {

	public static void main(String[] args) {
		try {
			FileWriter fw = new FileWriter("C:\\Users\\Gustavo\\Desktop\\teste.txt",true);
			BufferedWriter bf = new BufferedWriter(fw);
			for (int i = 0; i < 10; i++) {
				bf.append("linha: "+i+"\n");
			}
			bf.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}

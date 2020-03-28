
public class Main {

	public static void main(String[] args) {
		int v[] = new int[5];
		v[0] = 5;
		v[1] = 9;
		v[2] = 8;
		v[3] = 4;
		v[4] = 3;
		int aux = 0;
		
		for (int i = 0; i < v.length; i++) {
			for (int j = 0; j < v.length; j++) {
				if (v[i] < v[j]) {
					aux = v[i];
					v[i] = v[j];
					v[j] = aux;
				}
			}
			
		}
		for (int i = 0; i < v.length; i++) {
			System.out.println(v[i]);
		}
	}
}

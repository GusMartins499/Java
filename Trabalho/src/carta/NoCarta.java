package carta;

public class NoCarta {

	String nomeProper;
	String descProper;
	NoCarta anterior, proximo;
	int contador = 0;

	public NoCarta(String nomeP, String descP) {
		nomeProper = nomeP;
		descProper = descP;
		anterior = null;
		proximo = null;
		contador++;
	}
}

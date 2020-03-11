
public class Vertice {
	
	public Vertice(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
	
	private String nome;
	private boolean flag;
	private int valor;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public long getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	
}

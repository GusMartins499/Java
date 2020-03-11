package domain;

public class Questao {
	
	private int id;
	private String enunciado;
	private String resp1;
	private String resp2;
	private String resp3;
	private String resp4;
	private Materias materia = new Materias();
	
	public Materias getMateria() {
		return materia;
	}
	public void setMateria(Materias materia) {
		this.materia = materia;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	public String getResp1() {
		return resp1;
	}
	public void setResp1(String resp1) {
		this.resp1 = resp1;
	}
	public String getResp2() {
		return resp2;
	}
	public void setResp2(String resp2) {
		this.resp2 = resp2;
	}
	public String getResp3() {
		return resp3;
	}
	public void setResp3(String resp3) {
		this.resp3 = resp3;
	}
	public String getResp4() {
		return resp4;
	}
	public void setResp4(String resp4) {
		this.resp4 = resp4;
	}
	
}
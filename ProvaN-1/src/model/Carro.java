package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Carro {
	private StringProperty marca = new SimpleStringProperty("");
	private StringProperty modelo = new SimpleStringProperty("");
	private IntegerProperty ano = new SimpleIntegerProperty(0);
	private DoubleProperty valor = new SimpleDoubleProperty(0);
	private StringProperty ar = new SimpleStringProperty("");
	private StringProperty direcao = new SimpleStringProperty("");
	private StringProperty piloto = new SimpleStringProperty("");
	private StringProperty abs = new SimpleStringProperty("");
	private StringProperty outro = new SimpleStringProperty("");
	private IntegerProperty id = new SimpleIntegerProperty(0);

	public final StringProperty marcaProperty() {
		return this.marca;
	}

	public final String getMarca() {
		return this.marcaProperty().get();
	}

	public final void setMarca(final String marca) {
		this.marcaProperty().set(marca);
	}

	public final StringProperty modeloProperty() {
		return this.modelo;
	}

	public final String getModelo() {
		return this.modeloProperty().get();
	}

	public final void setModelo(final String modelo) {
		this.modeloProperty().set(modelo);
	}

	public final IntegerProperty anoProperty() {
		return this.ano;
	}

	public final int getAno() {
		return this.anoProperty().get();
	}

	public final void setAno(final int ano) {
		this.anoProperty().set(ano);
	}

	public final DoubleProperty valorProperty() {
		return this.valor;
	}

	public final double getValor() {
		return this.valorProperty().get();
	}

	public final void setValor(final double valor) {
		this.valorProperty().set(valor);
	}

	public final StringProperty arProperty() {
		return this.ar;
	}

	public final String getAr() {
		return this.arProperty().get();
	}

	public final void setAr(final String ar) {
		this.arProperty().set(ar);
	}

	public final StringProperty direcaoProperty() {
		return this.direcao;
	}

	public final String getDirecao() {
		return this.direcaoProperty().get();
	}

	public final void setDirecao(final String direcao) {
		this.direcaoProperty().set(direcao);
	}

	public final StringProperty pilotoProperty() {
		return this.piloto;
	}

	public final String getPiloto() {
		return this.pilotoProperty().get();
	}

	public final void setPiloto(final String piloto) {
		this.pilotoProperty().set(piloto);
	}

	public final StringProperty absProperty() {
		return this.abs;
	}

	public final String getAbs() {
		return this.absProperty().get();
	}

	public final void setAbs(final String abs) {
		this.absProperty().set(abs);
	}

	public final StringProperty outroProperty() {
		return this.outro;
	}

	public final String getOutro() {
		return this.outroProperty().get();
	}

	public final void setOutro(final String outro) {
		this.outroProperty().set(outro);
	}

	public final IntegerProperty idProperty() {
		return this.id;
	}
	

	public final int getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	

}

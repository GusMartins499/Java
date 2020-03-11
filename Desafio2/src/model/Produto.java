package model;

import javafx.beans.property.DoubleProperty;

import javafx.beans.property.SimpleDoubleProperty;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Produto {

	private StringProperty descricao = new SimpleStringProperty("");
	private DoubleProperty valor = new SimpleDoubleProperty(0);
	private DoubleProperty subTot = new SimpleDoubleProperty(0);
	private DoubleProperty qtd = new SimpleDoubleProperty(0);
	private String urlImg;

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public final StringProperty descricaoProperty() {
		return this.descricao;
	}

	public final String getDescricao() {
		return this.descricaoProperty().get();
	}

	public final void setDescricao(final String descricao) {
		this.descricaoProperty().set(descricao);
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

	public final DoubleProperty subTotProperty() {
		return this.subTot;
	}

	public final double getSubTot() {
		return this.subTotProperty().get();
	}

	public final void setSubTot(final double subTot) {
		this.subTotProperty().set(subTot);
	}

	public final DoubleProperty qtdProperty() {
		return this.qtd;
	}

	public final double getQtd() {
		return this.qtdProperty().get();
	}

	public final void setQtd(final double qtd) {
		this.qtdProperty().set(qtd);
	}

}

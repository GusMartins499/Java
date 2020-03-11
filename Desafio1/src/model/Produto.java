package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Produto {

	private StringProperty descricao = new SimpleStringProperty("");
	private DoubleProperty valor = new SimpleDoubleProperty(0);
	private DoubleProperty subTot = new SimpleDoubleProperty(0);
	private IntegerProperty qtd = new SimpleIntegerProperty(0);

	public DoubleProperty subtotProperty() {
		return this.subTot;
	}

	public Double getsubTot() {
		return this.subtotProperty().get();
	}

	public void setSubTot(final Double subtot) {
		this.subtotProperty().set(subtot);
	}

	public DoubleProperty valorProperty() {
		return this.valor;
	}

	public Double getValor() {
		return this.valorProperty().get();
	}

	public void setValor(final Double valor) {
		this.valorProperty().set(valor);
	}

	public StringProperty descricaoProperty() {
		return this.descricao;
	}

	public String getdescricao() {
		return this.descricaoProperty().get();
	}

	public void setDescricao(final String descricao) {
		this.descricaoProperty().set(descricao);
	}

	public final IntegerProperty qtdProperty() {
		return this.qtd;
	}

	public final int getQtd() {
		return this.qtdProperty().get();
	}

	public final void setQtd(final int qtd) {
		this.qtdProperty().set(qtd);
	}

}

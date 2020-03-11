package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlanoConta {

	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty conta = new SimpleStringProperty("");
	private StringProperty tipoConta = new SimpleStringProperty("");

	@Override
	public String toString() {
		return getConta();
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

	public final StringProperty contaProperty() {
		return this.conta;
	}

	public final String getConta() {
		return this.contaProperty().get();
	}

	public final void setConta(final String conta) {
		this.contaProperty().set(conta);
	}

	public final StringProperty tipoContaProperty() {
		return this.tipoConta;
	}

	public final String getTipoConta() {
		return this.tipoContaProperty().get();
	}

	public final void setTipoConta(final String tipoConta) {
		this.tipoContaProperty().set(tipoConta);
	}

}

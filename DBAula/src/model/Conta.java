package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Conta {

	private IntegerProperty id = new SimpleIntegerProperty(0);
	private DoubleProperty saldo = new SimpleDoubleProperty(0);
	private Agencia agencia = new Agencia();
	private Correntista correntista = new Correntista();

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public Correntista getCorrentista() {
		return correntista;
	}

	public void setCorrentista(Correntista correntista) {
		this.correntista = correntista;
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

	public final DoubleProperty saldoProperty() {
		return this.saldo;
	}

	public final double getSaldo() {
		return this.saldoProperty().get();
	}

	public final void setSaldo(final double saldo) {
		this.saldoProperty().set(saldo);
	}

	@Override
	public String toString() {
		return correntista.getNome() + " R$:" + getSaldo();
	}

}

package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Lancamento {
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty planoConta = new SimpleStringProperty("");
	private StringProperty referencia = new SimpleStringProperty("");
	private StringProperty emissao = new SimpleStringProperty("");
	private StringProperty historico = new SimpleStringProperty("");
	private DoubleProperty valor = new SimpleDoubleProperty(0);
	private StringProperty obs = new SimpleStringProperty("");

	public final StringProperty planoContaProperty() {
		return this.planoConta;
	}

	public final String getPlanoConta() {
		return this.planoContaProperty().get();
	}

	public final void setPlanoConta(final String planoConta) {
		this.planoContaProperty().set(planoConta);
	}

	public final StringProperty referenciaProperty() {
		return this.referencia;
	}

	public final String getReferencia() {
		return this.referenciaProperty().get();
	}

	public final void setReferencia(final String referencia) {
		this.referenciaProperty().set(referencia);
	}

	public final StringProperty emissaoProperty() {
		return this.emissao;
	}

	public final String getEmissao() {
		return this.emissaoProperty().get();
	}

	public final void setEmissao(final String emissao) {
		this.emissaoProperty().set(emissao);
	}

	public final StringProperty historicoProperty() {
		return this.historico;
	}

	public final String getHistorico() {
		return this.historicoProperty().get();
	}

	public final void setHistorico(final String historico) {
		this.historicoProperty().set(historico);
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

	public final StringProperty obsProperty() {
		return this.obs;
	}

	public final String getObs() {
		return this.obsProperty().get();
	}

	public final void setObs(final String obs) {
		this.obsProperty().set(obs);
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

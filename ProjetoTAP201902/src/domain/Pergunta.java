package domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pergunta {

	private StringProperty ColunaEnunciado = new SimpleStringProperty("");
	private StringProperty ColunaResposta = new SimpleStringProperty("");
	private IntegerProperty ColunaId = new SimpleIntegerProperty(0);

	public final StringProperty ColunaEnunciadoProperty() {
		return this.ColunaEnunciado;
	}

	public final String getColunaEnunciado() {
		return this.ColunaEnunciadoProperty().get();
	}

	public final void setColunaEnunciado(final String ColunaEnunciado) {
		this.ColunaEnunciadoProperty().set(ColunaEnunciado);
	}

	public final StringProperty ColunaRespostaProperty() {
		return this.ColunaResposta;
	}

	public final String getColunaResposta() {
		return this.ColunaRespostaProperty().get();
	}

	public final void setColunaResposta(final String ColunaResposta) {
		this.ColunaRespostaProperty().set(ColunaResposta);
	}

	public final IntegerProperty ColunaIdProperty() {
		return this.ColunaId;
	}

	public final int getColunaId() {
		return this.ColunaIdProperty().get();
	}

	public final void setColunaId(final int ColunaId) {
		this.ColunaIdProperty().set(ColunaId);
	}

}

package domain;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {

	private StringProperty login = new SimpleStringProperty("");
	private StringProperty turma = new SimpleStringProperty("");
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private IntegerProperty pontuacao = new SimpleIntegerProperty(0);

	@Override
	public String toString() {
		return getLogin();
	}

	public Usuario() {

	}

	public final StringProperty loginProperty() {
		return this.login;
	}

	public final String getLogin() {
		return this.loginProperty().get();
	}

	public final void setLogin(final String login) {
		this.loginProperty().set(login);
	}

	public final StringProperty turmaProperty() {
		return this.turma;
	}

	public final String getTurma() {
		return this.turmaProperty().get();
	}

	public final void setTurma(final String turma) {
		this.turmaProperty().set(turma);
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

	public final IntegerProperty pontuacaoProperty() {
		return this.pontuacao;
	}

	public final int getPontuacao() {
		return this.pontuacaoProperty().get();
	}

	public final void setPontuacao(final int pontuacao) {
		this.pontuacaoProperty().set(pontuacao);
	}

}

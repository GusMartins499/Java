package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Correntista {

	StringProperty nome = new SimpleStringProperty("");
	IntegerProperty id = new SimpleIntegerProperty(0);
	StringProperty nascimento = new SimpleStringProperty("");
	
	public final StringProperty nomeProperty() {
		return this.nome;
	}
	
	public final String getNome() {
		return this.nomeProperty().get();
	}
	
	public final void setNome(final String nome) {
		this.nomeProperty().set(nome);
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
	
	public final StringProperty nascimentoProperty() {
		return this.nascimento;
	}
	
	public final String getNascimento() {
		return this.nascimentoProperty().get();
	}
	
	public final void setNascimento(final String nascimento) {
		this.nascimentoProperty().set(nascimento);
	}
	
	@Override
	public String toString() {
		return getNome();
	}
	
}

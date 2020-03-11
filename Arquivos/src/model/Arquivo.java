package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Arquivo {
	StringProperty nome = new SimpleStringProperty("");
	StringProperty tamanho = new SimpleStringProperty("");
	StringProperty data = new SimpleStringProperty("");
	
	public final StringProperty nomeProperty() {
		return this.nome;
	}
	
	public final String getNome() {
		return this.nomeProperty().get();
	}
	
	public final void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}
	
	public final StringProperty tamanhoProperty() {
		return this.tamanho;
	}
	
	public final String getTamanho() {
		return this.tamanhoProperty().get();
	}
	
	public final void setTamanho(final String tamanho) {
		this.tamanhoProperty().set(tamanho);
	}
	
	public final StringProperty dataProperty() {
		return this.data;
	}
	
	public final String getData() {
		return this.dataProperty().get();
	}
	
	public final void setData(final String data) {
		this.dataProperty().set(data);
	}
	
	
	
	
}

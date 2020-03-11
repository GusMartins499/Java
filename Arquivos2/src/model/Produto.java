package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Produto {

	StringProperty nome = new SimpleStringProperty("");
	IntegerProperty estoque = new SimpleIntegerProperty(0);
	DoubleProperty valor = new SimpleDoubleProperty(0.0);
	StringProperty unidade = new SimpleStringProperty("");
	public final StringProperty nomeProperty() {
		return this.nome;
	}
	
	public final String getNome() {
		return this.nomeProperty().get();
	}
	
	public final void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}
	
	public final IntegerProperty estoqueProperty() {
		return this.estoque;
	}
	
	public final int getEstoque() {
		return this.estoqueProperty().get();
	}
	
	public final void setEstoque(final int estoque) {
		this.estoqueProperty().set(estoque);
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
	
	public final StringProperty unidadeProperty() {
		return this.unidade;
	}
	
	public final String getUnidade() {
		return this.unidadeProperty().get();
	}
	
	public final void setUnidade(final String unidade) {
		this.unidadeProperty().set(unidade);
	}
	
	
	
}

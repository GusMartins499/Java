package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Competicao {

	private StringProperty nome = new SimpleStringProperty("");
	private IntegerProperty distancia = new SimpleIntegerProperty(0);
	private IntegerProperty colocacao = new SimpleIntegerProperty(0);
	private StringProperty data = new SimpleStringProperty("");
	public final StringProperty nomeProperty() {
		return this.nome;
	}
	
	public final String getNome() {
		return this.nomeProperty().get();
	}
	
	public final void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}
	
	public final IntegerProperty distanciaProperty() {
		return this.distancia;
	}
	
	public final int getDistancia() {
		return this.distanciaProperty().get();
	}
	
	public final void setDistancia(final int distancia) {
		this.distanciaProperty().set(distancia);
	}
	
	public final IntegerProperty colocacaoProperty() {
		return this.colocacao;
	}
	
	public final int getColocacao() {
		return this.colocacaoProperty().get();
	}
	
	public final void setColocacao(final int colocacao) {
		this.colocacaoProperty().set(colocacao);
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

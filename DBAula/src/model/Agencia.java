package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Agencia {

	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty numero = new SimpleStringProperty("");
	private StringProperty cidade =  new SimpleStringProperty("");
	private StringProperty str =  new SimpleStringProperty("");
	
	@Override
	public String toString() {
		return getNumero() + " - " + getCidade();
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
	
	public final StringProperty numeroProperty() {
		return this.numero;
	}
	
	public final String getNumero() {
		return this.numeroProperty().get();
	}
	
	public final void setNumero(final String numero) {
		this.numeroProperty().set(numero);
	}
	
	public final StringProperty cidadeProperty() {
		return this.cidade;
	}
	
	public final String getCidade() {
		return this.cidadeProperty().get();
	}
	
	public final void setCidade(final String cidade) {
		this.cidadeProperty().set(cidade);
	}
	
	
	public final StringProperty strProperty() {
		this.str.set(this.getNumero()+" "+this.getCidade());
		return this.str;
	}
	
	public final String getStr() {
		return this.strProperty().get();
	}
	
	public final void setStr(final String str) {
		this.strProperty().set(str);
	}
	
	

}

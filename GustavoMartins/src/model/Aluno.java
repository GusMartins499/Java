package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Aluno {

	private double nota1;
	private double nota2;
	private double nota3;
	private double mediaAluno;

	public double getMediaAluno() {
		return (this.getNota1() + this.getNota2() + this.getNota3()) / 3;
	}

	public void setMediaAluno(double mediaAluno) {
		this.mediaAluno = mediaAluno;
	}

	public double getNota1() {
		return nota1;
	}

	public void setNota1(double nota1) {
		this.nota1 = nota1;
	}

	public double getNota2() {
		return nota2;
	}

	public void setNota2(double nota2) {
		this.nota2 = nota2;
	}

	public double getNota3() {
		return nota3;
	}

	public void setNota3(double nota3) {
		this.nota3 = nota3;
	}

	StringProperty nome = new SimpleStringProperty("");
	DoubleProperty media = new SimpleDoubleProperty(0.0);
	StringProperty diciplina = new SimpleStringProperty("");
	StringProperty situacao = new SimpleStringProperty("");

	public final StringProperty nomeProperty() {
		return this.nome;
	}

	public final String getNome() {
		return this.nomeProperty().get();
	}

	public final void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}

	public final DoubleProperty mediaProperty() {
		return this.media;
	}

	public final double getMedia() {
		return this.mediaProperty().get();
	}

	public final void setMedia(final double media) {
		this.mediaProperty().set(media);
	}

	public final StringProperty diciplinaProperty() {
		return this.diciplina;
	}

	public final String getDiciplina() {
		return this.diciplinaProperty().get();
	}

	public final void setDiciplina(final String diciplina) {
		this.diciplinaProperty().set(diciplina);
	}

	public final StringProperty situacaoProperty() {
		return this.situacao;
	}

	public final String getSituacao() {
		return this.situacaoProperty().get();
	}

	public final void setSituacao(final String situacao) {
		this.situacaoProperty().set(situacao);
	}

}

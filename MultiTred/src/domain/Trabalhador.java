package domain;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

public class Trabalhador extends Task<Void> {

	private int qtd;
	private long tempo;
	private ProgressBar probarra;

	public Trabalhador(int qtd, long tempo, ProgressBar probarra) {
		this.qtd = qtd;
		this.tempo = tempo;
		this.probarra = probarra;
		this.probarra.setProgress(0);
	}

	public void inicia() {
		double incremento = 1.0 / qtd;
		for (int i = 0; i < qtd; i++) {
			try {
				Thread.sleep(tempo);
				probarra.setProgress(probarra.getProgress() + incremento);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public long getTempo() {
		return tempo;
	}

	public void setTempo(long tempo) {
		this.tempo = tempo;
	}

	public ProgressBar getProbarra() {
		return probarra;
	}

	public void setProbarra(ProgressBar probarra) {
		this.probarra = probarra;
	}

	@Override
	protected Void call() throws Exception {
		double incremento = 1.0 / qtd;
		for (int i = 0; i < qtd; i++) {
			try {
				Thread.sleep(tempo);
				Platform.runLater(() -> {
					probarra.setProgress(probarra.getProgress() + incremento);
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}

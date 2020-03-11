package model;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

public class Atleta extends Task<Void> {

	private int distancia;
	private long tempo;
	private ProgressBar barra;

	public Atleta(int distancia, long tempo, ProgressBar barra) {
		this.distancia = distancia;
		this.tempo = tempo;
		this.barra = barra;
		this.barra.setProgress(0);
	}

	public void inicia() {
		double incremento = 1.0 / distancia;
		for (int i = 0; i < distancia; i++) {
			try {
				Thread.sleep(tempo*1000);
				barra.setProgress(barra.getProgress() + incremento);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public long getTempo() {
		return tempo;
	}

	public void setTempo(long tempo) {
		this.tempo = tempo;
	}

	public ProgressBar getBarra() {
		return barra;
	}

	public void setBarra(ProgressBar barra) {
		this.barra = barra;
	}

	@Override
	protected Void call() throws Exception {
		double incremento = 1.0 / distancia;
		for (int i = 0; i < distancia; i++) {
			try {
				Thread.sleep(tempo*1000);
				Platform.runLater(() -> {
					barra.setProgress(barra.getProgress() + incremento);
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}

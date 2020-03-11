package domain;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

public class Trabalhador extends Task<Void> {

	private int quantidade;
	private long tempo;
	private ProgressBar barra;

	public Trabalhador(int quantidade, long tempo, ProgressBar barra) {
		super();
		this.quantidade = quantidade;
		this.tempo = tempo;
		this.barra.setProgress(0);
	}

	public void inicia() {
		double incremento = 1.0 / quantidade;
		for (int i = 0; i < quantidade; i++) {
			try {
				Thread.sleep(tempo);
				barra.setProgress(barra.getProgress() + incremento);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
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
		double incremento = 1.0 / quantidade;
		for (int i = 0; i < quantidade; i++) {
			try {
				Thread.sleep(tempo);
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

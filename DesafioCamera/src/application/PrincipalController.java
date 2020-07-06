package application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.Util;

public class PrincipalController {
	
	@FXML
	private Button button;
	@FXML
	private CheckBox grayscale;
	@FXML
	private ImageView histograma;
	@FXML
	private ImageView currentFrame;
	
	private ScheduledExecutorService timer;
	private VideoCapture capture = new VideoCapture();
	private boolean cameraActive = false;
		
	// Automaticamento chamado pelo @FXML
	public void initialize() {
		this.capture = new VideoCapture();
		this.cameraActive = false;
	}
	
	@FXML
	protected void startCamera() {
		// Tamanho da camera
		this.currentFrame.setFitWidth(600);
		// Preserva tamanho
		this.currentFrame.setPreserveRatio(true);

		if (!this.cameraActive) {
			// Comeca a transmissao
			this.capture.open(0);

			if (this.capture.isOpened()) {
				this.cameraActive = true;
				// Captura frame a cada 33ms
				Runnable frameGrabber = new Runnable() {
					@Override
					public void run() {
						
						// Processa cada frame
						Mat frame = grabFrame();
						// Converte e mostra o frame
						Image imageToShow = Util.mat2Image(frame);
						updateImageView(currentFrame, imageToShow);
					}
				};
					
				this.timer = Executors.newSingleThreadScheduledExecutor();
				this.timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS);
					
				// Atualiza texto do botao
				this.button.setText("Stop Camera");
			}
			else {
				// Registra o erro
				System.err.println("Impossivel de se conectar com a camera...");
			}
		}
		else {
			// Se a camera ainda não estiver ativa
			this.cameraActive = false;
			// Atualizar novamente o texto do botao
			this.button.setText("Start Camera");
			// Interromper o timer
			this.stopAcquisition();
		}
	}
	
	// Captura um frame do video aberto(se estiver) e retorna um {@link Mat} para mostrar
	private Mat grabFrame() {
		// Inicia tudo
		Mat frame = new Mat();
		
		// Verifica se a captura esta aberta
		if (this.capture.isOpened()) {
			try {
				// Le o frame atual
				this.capture.read(frame);
				
				// Se o frame não estiver vazio, entao o processa
				if (!frame.empty()) {
					// Se o checkbox greyscale estiver selecionado, converter a imagem
					if (grayscale.isSelected()) {
						Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2GRAY);
					}
					
					// Mostra o histogramaa
					this.showhistograma(frame, grayscale.isSelected());
				}
					
			}	
			catch (Exception e) {
				// Registra o erro
				System.err.println("Exception during the frame elaboration: " + e);
			}
		}
		
		return frame;
	}
	
	/* Computa e mostra o histogramaa pela dada imagem; parametro frame para receber a imagem
	e o parametro gray para verificar se a imagem é somenta na escala de cinza*/
	private void showhistograma(Mat frame, boolean gray) {
		// Dividir os frames em multiplas imagens
		List<Mat> images = new ArrayList<Mat>();
		Core.split(frame, images);
		
		// Setar um numero de posições em 256
		MatOfInt histSize = new MatOfInt(256);
		// Somente um canal
		MatOfInt channels = new MatOfInt(0);
		// Setar os limites
		MatOfFloat histRange = new MatOfFloat(0, 256);
		
		// Computar os histogramaas para os componentes R, G, B
		Mat hist_b = new Mat();
		Mat hist_g = new Mat();
		Mat hist_r = new Mat();
			
		// Componente B ou imagem em escala cinza
		Imgproc.calcHist(images.subList(0, 1), channels, new Mat(), hist_b, histSize, histRange, false);
		
		// Componentes G e B se a imagem não estiver em escala cinza
		if (!gray) {
			Imgproc.calcHist(images.subList(1, 2), channels, new Mat(), hist_g, histSize, histRange, false);
			Imgproc.calcHist(images.subList(2, 3), channels, new Mat(), hist_r, histSize, histRange, false);
		}
			
		// Desenha o histogramaa
		int hist_w = 150; // Largura
		int hist_h = 150; // Altura
		int bin_w = (int) Math.round(hist_w / histSize.get(0, 0)[0]);
			
		Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC3, new Scalar(0, 0, 0));
		// Normaliza o resultado para [0, histImage.rows()]
		Core.normalize(hist_b, hist_b, 0, histImage.rows(), Core.NORM_MINMAX, -1, new Mat());
		
		// Para componentes R e G
		if (!gray) {
			Core.normalize(hist_g, hist_g, 0, histImage.rows(), Core.NORM_MINMAX, -1, new Mat());
			Core.normalize(hist_r, hist_r, 0, histImage.rows(), Core.NORM_MINMAX, -1, new Mat());
		}
		
		// Desenha os histogramaas efetivamente
		for (int i = 1; i < histSize.get(0, 0)[0]; i++) {
			// Componente B ou imagem em escala cinza
			Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(hist_b.get(i - 1, 0)[0])),
				new Point(bin_w * (i), hist_h - Math.round(hist_b.get(i, 0)[0])), new Scalar(255, 0, 0), 2, 8, 0);
			// R e G componentes (se a imagem não estiver em escala cinza)
			if (!gray) {
				Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(hist_g.get(i - 1, 0)[0])),
					new Point(bin_w * (i), hist_h - Math.round(hist_g.get(i, 0)[0])), new Scalar(0, 255, 0), 2, 8, 0);
				Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(hist_r.get(i - 1, 0)[0])),
					new Point(bin_w * (i), hist_h - Math.round(hist_r.get(i, 0)[0])), new Scalar(0, 0, 255), 2, 8, 0);
			}
		}
		
		// Atualiza o histogramaa no imageView...
		Image histImg = Util.mat2Image(histImage);
		updateImageView(histograma, histImg);
			
	}
	
	// Interrompe a aquisiçao da camera e libera todos os recursos
	private void stopAcquisition() {
		if (this.timer!=null && !this.timer.isShutdown()) {
			try {
				// Interrompe o timer
				this.timer.shutdown();
				this.timer.awaitTermination(33, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				// Registra o erro
				System.err.println("Exception in stopping the frame capture, trying to release the camera now... " + e);
			}
		}
		
		if (this.capture.isOpened()) {
			// Libera a camera
			this.capture.release();
		}
	}
	
	/* Atualiza o ImageView na thread principal, parametro view para atualizar o ImageView e o
	parametro image para mostrar na tela*/
	private void updateImageView(ImageView view, Image image) {
		Util.onFXThread(view.imageProperty(), image);
	}
	
	// Se estiver fechada a aplicação, interrompe a aquisição da camera
	protected void setClosed() {
		this.stopAcquisition();
	}
	
}
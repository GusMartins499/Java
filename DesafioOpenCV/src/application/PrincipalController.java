package application;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.Utils;

public class PrincipalController {

	@FXML
	private Button button;
	@FXML
	private ImageView currentFrame;

	private ScheduledExecutorService timer;

	private VideoCapture capture = new VideoCapture();

	@FXML
	protected void startCamera(ActionEvent event) {
		Runnable frameGrabber = new Runnable() {

			@Override
			public void run() {
				// m�todo para capturar o frame
				Mat frame = grabFrame();
				// converter a vari�vel frame em bufferdImage
				Image imageToShow = Utils.mat2Image(frame);
				updateImageView(currentFrame, imageToShow);

			}
		};

		this.timer = Executors.newSingleThreadScheduledExecutor();
		this.timer.scheduleAtFixedRate(frameGrabber, 0, 33, TimeUnit.MILLISECONDS);
	}
	
	private void updateImageView(ImageView view, Image image) {
		Utils.onFXThread(view.imageProperty(), image);
	}

	private Mat grabFrame() {
		// Antes de tudo, cria a vari�vel frame do tipo Mat (openCV)
		Mat frame = new Mat();

		// Checagem para saber se est� capturando pela webcan
		if (this.capture.isOpened()) {
			try {
				// L� o frame atual
				this.capture.read(frame);

				// Se o frame n�o estiver vazio, continue
				if (!frame.empty()) {
					Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2GRAY);
				}

			} catch (Exception e) {
				// Log de erro
				System.err.println("Exception during the image elaboration: " + e);
			}
		}

		return frame;
	}
}

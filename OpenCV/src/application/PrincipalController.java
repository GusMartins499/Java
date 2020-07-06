package application;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javafx.fxml.FXML;

public class PrincipalController {

	@FXML
	public void canny() {
		try {
			carregaOpenCV();

			Mat matImgDst = new Mat();
			Mat matImgSrc = Imgcodecs.imread("C:\\Users\\Gustavo\\Pictures\\Processamento digital de imagem\\quadrados.jpg");

			Imgproc.Canny(matImgSrc, matImgDst, 150, 150);
			Imgcodecs.imwrite("C:\\Users\\Gustavo\\Pictures\\Processamento digital de imagem\\Bordas\\Canny.png", matImgDst);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void sobel() {
		try {
			carregaOpenCV();

			Mat matImgDst = new Mat();
			Mat matImgSrc = Imgcodecs.imread("C:\\Users\\Gustavo\\Pictures\\Processamento digital de imagem\\quadrados.jpg");

			Imgproc.Canny(matImgSrc, matImgDst, 10, 100);
			Imgcodecs.imwrite("C:\\Users\\Gustavo\\Pictures\\Processamento digital de imagem\\Bordas\\Sobel.png", matImgDst);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void laPlace() {
		try {
			carregaOpenCV();

			Mat matImgDst = new Mat();
			Mat matImgSrc = Imgcodecs.imread("C:\\Users\\Gustavo\\Pictures\\Processamento digital de imagem\\quadrados.jpg");

			Imgproc.Laplacian(matImgSrc, matImgDst, CvType.CV_64F);
			Imgcodecs.imwrite("C:\\Users\\Gustavo\\Pictures\\Processamento digital de imagem\\Bordas\\LaPlace2.png", matImgDst);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void prewitt() {

		try {
			carregaOpenCV();

			Mat matImgDst = new Mat();

			Mat matImgSrc = Imgcodecs.imread("C:\\Users\\Gustavo\\Pictures\\Processamento digital de imagem\\quadrados.jpg");
			int kernelSize = 9;

			Mat kernel = new Mat(kernelSize, kernelSize, CvType.CV_32F) {
				{
					put(0, 0, 0);
					put(-1, 0, 1);
					put(-1, 0, 1);

					put(1, 1, 1);
					put(0, 0, 0);
					put(-1, -1, -1);

					put(1, 0, 0);
					put(0, 1, 0);
					put(0, -1, -1);
				}
			};

			Imgproc.filter2D(matImgSrc, matImgDst, -1, kernel);
			Imgcodecs.imwrite("C:\\Users\\Gustavo\\Pictures\\Processamento digital de imagem\\Bordas\\Prewitt.png", matImgDst);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void carregaOpenCV() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
}

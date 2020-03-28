package application;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import util.Constantes;
import util.Pixel;

public class PDI {

	public static Image cinzaMediaAritmetica(Image imagem, int pcR, int pcG, int pcB) {

		try {
			int w = (int) imagem.getWidth();
			int h = (int) imagem.getHeight();

			PixelReader pr = imagem.getPixelReader(); // l� as informa��es da imagem e pega as informa��es das imagens
			WritableImage wi = new WritableImage(w, h);
			PixelWriter pw = wi.getPixelWriter();

			for (int i = 0; i < w; i++) {
				for (int j = 0; j < h; j++) {
					Color corA = pr.getColor(i, j);
					double media = (corA.getRed() + corA.getGreen() + corA.getBlue()) / 3;
					if (pcR != 0 || pcG != 0 || pcB != 0)
						media = (corA.getRed() * pcR + corA.getGreen() * pcG + corA.getBlue() * pcB) / 100;
					Color corN = new Color(media, media, media, corA.getOpacity());
					pw.setColor(i, j, corN);
				}
			}
			return wi;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Image limiarizacao(Image imagem, double limiar) {
		try {
			int w = (int) imagem.getWidth();
			int h = (int) imagem.getHeight();

			PixelReader pr = imagem.getPixelReader();
			WritableImage wi = new WritableImage(w, h);
			PixelWriter pw = wi.getPixelWriter();

			for (int i = 0; i < w; i++) {
				for (int j = 0; j < h; j++) {
					Color corA = pr.getColor(i, j);
					Color corN;

					if (corA.getRed() >= limiar)
						corN = new Color(1, 1, 1, corA.getOpacity());
					else if (corA.getBlue() >= limiar)
						corN = new Color(1, 1, 1, corA.getOpacity());
					else if (corA.getGreen() >= limiar)
						corN = new Color(1, 1, 1, corA.getOpacity());
					else
						corN = new Color(0, 0, 0, corA.getOpacity());
					pw.setColor(i, j, corN);
				}
			}
			return wi;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Image negativa(Image imagem) {
		try {
			int w = (int) imagem.getWidth();
			int h = (int) imagem.getHeight();

			PixelReader pr = imagem.getPixelReader();
			WritableImage wi = new WritableImage(w, h);
			PixelWriter pw = wi.getPixelWriter();

			for (int i = 0; i < w; i++) {
				for (int j = 0; j < h; j++) {
					Color corA = pr.getColor(i, j);
					Color corN = new Color(1 - corA.getRed(), 1 - corA.getGreen(), 1 - corA.getBlue(),
							corA.getOpacity());
					pw.setColor(i, j, corN);
				}
			}
			return wi;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Image ruidos(Image imagem, int tipoVizinhos) {
		try {
			int w = (int) imagem.getWidth();
			int h = (int) imagem.getHeight();

			PixelReader pr = imagem.getPixelReader();
			WritableImage wi = new WritableImage(w, h);
			PixelWriter pw = wi.getPixelWriter();

			for (int i = 1; i < w - 1; i++) {
				for (int j = 1; j < h - 1; j++) {
					Color corA = pr.getColor(i, j);
					Pixel p = new Pixel(corA.getRed(), corA.getGreen(), corA.getBlue(), i, j);
					buscavizinhos(imagem, p);
					Pixel vetor[] = null;
					if (tipoVizinhos == Constantes.VIZINHOS3X3)
						vetor = p.viz3;
					if (tipoVizinhos == Constantes.VIZINHOSCRUZ)
						vetor = p.vizC;
					if (tipoVizinhos == Constantes.VIZINHOSX)
						vetor = p.vizX;
					double red = mediana(vetor, Constantes.CANALR);
					double green = mediana(vetor, Constantes.CANALG);
					double blue = mediana(vetor, Constantes.CANALB);
					Color corN = new Color(red, green, blue, corA.getOpacity());
					pw.setColor(i, j, corN);
				}
			}
			return wi;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static double mediana(Pixel[] pixels, int canal) {
		double v[] = new double[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
			if (canal == Constantes.CANALR) {
				v[i] = pixels[i].r;
			}
			if (canal == Constantes.CANALG) {
				v[i] = pixels[i].g;
			}
			if (canal == Constantes.CANALB) {
				v[i] = pixels[i].b;
			}
		}
		v = ordenaVetor(v);
		return v[v.length / 2];
	}

	private static void buscavizinhos(Image imagem, Pixel p) {
		p.vizX = buscaVizinhosX(imagem, p);
		p.vizC = buscaVizinhosC(imagem, p);
		p.viz3 = buscaVizinhos3(imagem, p);
	}

	private static Pixel[] buscaVizinhosX(Image imagem, Pixel p) {
		Pixel[] vizX = new Pixel[5];
		PixelReader pr = imagem.getPixelReader();
		Color cor = pr.getColor(p.x - 1, p.y + 1);
		vizX[0] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x - 1, p.y + 1);
		cor = pr.getColor(p.x + 1, p.y - 1);
		vizX[1] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x + 1, p.y - 1);
		cor = pr.getColor(p.x - 1, p.y - 1);
		vizX[2] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x - 1, p.y - 1);
		cor = pr.getColor(p.x + 1, p.y + 1);
		vizX[3] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x + 1, p.y + 1);
		vizX[4] = p;
		return vizX;
	}

	private static Pixel[] buscaVizinhos3(Image imagem, Pixel p) {
		Pixel[] viz3 = new Pixel[9];
		PixelReader pr = imagem.getPixelReader();
		Color cor = pr.getColor(p.x - 1, p.y - 1);
		viz3[0] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x - 1, p.y - 1);
		cor = pr.getColor(p.x, p.y - 1);
		viz3[1] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x, p.y - 1);
		cor = pr.getColor(p.x + 1, p.y - 1);
		viz3[2] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x + 1, p.y - 1);
		cor = pr.getColor(p.x + 1, p.y);
		viz3[3] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x + 1, p.y);
		cor = pr.getColor(p.x + 1, p.y + 1);
		viz3[4] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x + 1, p.y + 1);
		cor = pr.getColor(p.x, p.y + 1);
		viz3[5] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x, p.y + 1);
		cor = pr.getColor(p.x - 1, p.y + 1);
		viz3[6] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x - 1, p.y + 1);
		cor = pr.getColor(p.x - 1, p.y);
		viz3[7] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x - 1, p.y);
		viz3[8] = p;
		return viz3;
	}

	private static Pixel[] buscaVizinhosC(Image imagem, Pixel p) {
		Pixel[] vizC = new Pixel[5];
		PixelReader pr = imagem.getPixelReader();
		Color cor = pr.getColor(p.x, p.y - 1);
		vizC[0] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x, p.y - 1);
		cor = pr.getColor(p.x + 1, p.y);
		vizC[1] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x + 1, p.y);
		cor = pr.getColor(p.x, p.y + 1);
		vizC[2] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x, p.y + 1);
		cor = pr.getColor(p.x - 1, p.y);
		vizC[3] = new Pixel(cor.getRed(), cor.getGreen(), cor.getBlue(), p.x - 1, p.y);
		vizC[4] = p;
		return vizC;
	}

	private static double[] ordenaVetor(double[] v) {
		double aux = 0;
		for (int i = 0; i < v.length; i++) {
			for (int j = 0; j < v.length; j++) {
				if (v[i] < v[j]) {
					aux = v[i];
					v[i] = v[j];
					v[j] = aux;
				}
			}
		}
		return v;
	}
}

package pdi;

import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import util.Constantes;
import util.Mensagem;
import util.Pixel;

public class PDI {

	public static Image cinzaMediaAritmetica(Image imagem, int pcR, int pcG, int pcB) {

		try {
			int w = (int) imagem.getWidth();
			int h = (int) imagem.getHeight();

			PixelReader pr = imagem.getPixelReader(); // lê as informações da imagem e pega as informações das imagens
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
		} catch (NullPointerException e) {
			Mensagem.exibeMsg("Erro", "Hummm, algo deu errado !", "Selecione primeiro uma imagem !", AlertType.ERROR);
			return null;
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
		} catch (NullPointerException e) {
			Mensagem.exibeMsg("Erro", "Hummm, algo deu errado !", "Selecione primeiro uma imagem !", AlertType.ERROR);
			return null;
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

		} catch (NullPointerException e) {
			Mensagem.exibeMsg("Erro", "Hummm, algo deu errado !", "Selecione primeiro uma imagem !", AlertType.ERROR);
			return null;
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
		} catch (NullPointerException e) {
			Mensagem.exibeMsg("Erro", "Hummm, algo deu errado !", "Selecione primeiro uma imagem !", AlertType.ERROR);
			return null;
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

	public static Image adicao(Image img1, Image img2, double indiceTransparenia1, double indiceTransparenia2) {
		try {
			int w1 = (int) img1.getWidth();
			int h1 = (int) img1.getHeight();
			int w2 = (int) img2.getWidth();
			int h2 = (int) img2.getHeight();
			int w = Math.min(w1, w2);
			int h = Math.min(h1, h2);
			PixelReader pr1 = img1.getPixelReader();
			PixelReader pr2 = img2.getPixelReader();
			WritableImage wi = new WritableImage(w, h);
			PixelWriter pw = wi.getPixelWriter();
			for (int i = 1; i < w; i++) {
				for (int j = 1; j < h; j++) {
					Color corImg1 = pr1.getColor(i, j);
					Color corImg2 = pr2.getColor(i, j);
					double r = (corImg1.getRed() * indiceTransparenia1) + (corImg2.getRed() * indiceTransparenia2);
					double g = (corImg1.getGreen() * indiceTransparenia1) + (corImg2.getGreen() * indiceTransparenia2);
					double b = (corImg1.getBlue() * indiceTransparenia1) + (corImg2.getBlue() * indiceTransparenia2);
					r = r > 1 ? 1 : r;
					g = g > 1 ? 1 : g;
					b = b > 1 ? 1 : b;
					Color newCor = new Color(r, g, b, 1);
					pw.setColor(i, j, newCor);
				}
			}
			return wi;
		} catch (NullPointerException e) {
			Mensagem.exibeMsg("Erro", "Hummm, algo deu errado !", "Selecione primeiro uma imagem !", AlertType.ERROR);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Image subtracao(Image img1, Image img2) {
		try {
			int w1 = (int) img1.getWidth();
			int h1 = (int) img1.getHeight();
			int w2 = (int) img2.getWidth();
			int h2 = (int) img2.getHeight();
			int w = Math.min(w1, w2);
			int h = Math.min(h1, h2);
			PixelReader pr1 = img1.getPixelReader();
			PixelReader pr2 = img2.getPixelReader();
			WritableImage wi = new WritableImage(w, h);
			PixelWriter pw = wi.getPixelWriter();
			for (int i = 1; i < w; i++) {
				for (int j = 1; j < h; j++) {
					Color oldCor1 = pr1.getColor(i, j);
					Color oldCor2 = pr2.getColor(i, j);
					double r = (oldCor1.getRed()) - (oldCor2.getRed());
					double g = (oldCor1.getGreen()) - (oldCor2.getGreen());
					double b = (oldCor1.getBlue()) - (oldCor2.getBlue());
					r = r < 0 ? 0 : r;
					g = g < 0 ? 0 : g;
					b = b < 0 ? 0 : b;
					Color newCor = new Color(r, g, b, oldCor1.getOpacity());
					pw.setColor(i, j, newCor);
				}
			}
			return wi;
		} catch (NullPointerException e) {
			Mensagem.exibeMsg("Erro", "Hummm, algo deu errado !", "Selecione primeiro uma imagem !", AlertType.ERROR);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Image RetanguloImagemComMouse(Image image, int PontoXInicio, int PontoXFim, int PontoYInicio,
			int PontoYFim) {
		int w = (int) image.getWidth();
		int h = (int) image.getHeight();
		PixelReader pr1 = image.getPixelReader();
		WritableImage wi = new WritableImage(w, h);
		PixelWriter pw = wi.getPixelWriter();

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				Color corA = pr1.getColor(i, j);
				pw.setColor(i, j, corA);
			}
		}
		// linha horizontal de cima
		for (int k = PontoXInicio; k < PontoXFim; k++) {
			Color corA = pr1.getColor(k, PontoYInicio);
			if (k <= PontoXFim) {
				double R = (0);
				double G = (1);
				double B = (0);
				Color newColor = new Color(R, G, B, corA.getOpacity());
				pw.setColor(k, PontoYInicio, newColor);
			}
		}
		// linha horizontal debaixo
		for (int k = PontoXInicio; k < PontoXFim; k++) {
			Color corA = pr1.getColor(k, PontoYFim);
			if (k <= PontoXFim) {
				double R = (0);
				double G = (1);
				double B = (0);
				Color newColor = new Color(R, G, B, corA.getOpacity());
				pw.setColor(k, PontoYFim, newColor);
			}
		}
		// linha vertical esquerda
		for (int k = PontoYInicio; k < PontoYFim; k++) {
			Color corA = pr1.getColor(PontoXInicio, k);
			if (k <= PontoYFim) {
				double R = (0);
				double G = (1);
				double B = (0);
				Color newColor = new Color(R, G, B, corA.getOpacity());
				pw.setColor(PontoXInicio, k, newColor);
			}
		}
		// linha vertical direita
		for (int k = PontoYInicio; k < PontoYFim; k++) {
			Color corA = pr1.getColor(PontoXFim, k);
			if (k <= PontoYFim) {
				double R = (0);
				double G = (1);
				double B = (0);
				Color newColor = new Color(R, G, B, corA.getOpacity());
				pw.setColor(PontoXFim, k, newColor);
			}
		}
		return wi;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void getGrafico(Image img, BarChart<String, Number> grafico) {
		// HISTOGRAMA ÚNICO
		// int[] histograma = histogramaUnico(img);
		// XYChart.Series valor = new XYChart.Series();
		// for (int i = 0; i < histograma.length; i++) {
		// valor.getData().add(new XYChart.Data(i + "", histograma[i]));
		// }
		// grafico.getData().addAll(valor);

		// HISTOGRAMA POR CANAL
		int[] histR = histograma(img, 1);
		int[] histG = histograma(img, 2);
		int[] histB = histograma(img, 3);
		XYChart.Series vlrR = new XYChart.Series();
		XYChart.Series vlrG = new XYChart.Series();
		XYChart.Series vlrB = new XYChart.Series();

		for (int i = 0; i < 256; i++) {
			vlrR.getData().add(new XYChart.Data(i + "", histR[i]));
			vlrG.getData().add(new XYChart.Data(i + "", histG[i]));
			vlrB.getData().add(new XYChart.Data(i + "", histB[i]));
		}
		grafico.getData().addAll(vlrR, vlrG, vlrB);

		for (Node n : grafico.lookupAll(".default-color0.chart-bar")) {
			n.setStyle("-fx-bar-fill:red;");
		}
		for (Node n : grafico.lookupAll(".default-color1.chart-bar")) {
			n.setStyle("-fx-bar-fill:green;");
		}
		for (Node n : grafico.lookupAll(".default-color2.chart-bar")) {
			n.setStyle("-fx-bar-fill:blue;");
		}

	}

	public static int[] histograma(Image img, int i) {
		int valorCanal[] = new int[256];
		PixelReader pr = img.getPixelReader();
		double w = (int) img.getWidth();
		double h = (int) img.getHeight();

		if (i == 1) {
			for (int j = 1; j < w; j++) {
				for (int k = 1; k < h; k++) {
					valorCanal[(int) (pr.getColor(j, k).getRed() * 255)]++;
				}
			}
		}
		if (i == 2) {
			for (int j = 1; j < w; j++) {
				for (int k = 1; k < h; k++) {
					valorCanal[(int) (pr.getColor(j, k).getGreen() * 255)]++;
				}
			}
		}
		if (i == 3) {
			for (int j = 1; j < w; j++) {
				for (int k = 1; k < h; k++) {
					valorCanal[(int) (pr.getColor(j, k).getBlue() * 255)]++;
				}
			}
		}
		return valorCanal;
	}

	// public static int[] histogramaUnico(Image img) {
	// int qt[] = new int[256];
	// PixelReader pr = img.getPixelReader();
	// int w = (int) img.getWidth();
	// int h = (int) img.getHeight();
	// for (int i = 0; i < w; i++) {
	// for (int j = 0; j < h; j++) {
	// qt[(int) (pr.getColor(i, j).getRed() * 255)]++;
	// qt[(int) (pr.getColor(i, j).getGreen() * 255)]++;
	// qt[(int) (pr.getColor(i, j).getBlue() * 255)]++;
	// }
	// }
	// return qt;
	// }

	public static Image equalizacaoHistograma(Image img, boolean todos) {
		//equalizacao valida
		int w = (int) img.getWidth();
		int h = (int) img.getHeight();
		PixelReader pr = img.getPixelReader();
		WritableImage wi = new WritableImage(w, h);
		PixelWriter pw = wi.getPixelWriter();

		int[] hR = histograma(img, 1); // 1=red 2=green 3=blue
		int[] hG = histograma(img, 2);
		int[] hB = histograma(img, 3);

		int[] histAcR = histogramaAc(hR); // não tem código, implementar o histograma acumulado
		int[] histAcG = histogramaAc(hG);
		int[] histAcB = histogramaAc(hB);

		int qtTonsRed = qtTons(hR); // valors válidos, contas quantos são 0 dentro do vetor de cada canal e diminuir
									// de 255
		int qtTonsGreen = qtTons(hG);
		int qtTonsBlue = qtTons(hB);

		double minR = pontoMin(hR);
		double minG = pontoMin(hG);
		double minB = pontoMin(hB);
		
		//equalização
		if (todos) {
			qtTonsRed = 255;
			qtTonsGreen = 255;
			qtTonsBlue = 255;
			minR = 0;
			minG = 0;
			minB = 0;
		}

		double n = w * h; // n=quantidade total de pixels na imagem

		for (int i = 1; i < w; i++) {
			for (int j = 1; j < h; j++) {
				Color oldColor = pr.getColor(i, j);

				double acR = histAcR[(int) (oldColor.getRed() * 255)];
				double acG = histAcG[(int) (oldColor.getGreen() * 255)];
				double acB = histAcB[(int) (oldColor.getBlue() * 255)];

				double pxR = ((qtTonsRed - 1) / n) * acR;
				double pxG = ((qtTonsGreen - 1) / n) * acG;
				double pxB = ((qtTonsBlue - 1) / n) * acB;

				double corR = (minR + pxR) / 255;
				double corG = (minG + pxG) / 255;
				double corB = (minB + pxB) / 255;

				Color newColor = new Color(corR, corG, corB, oldColor.getOpacity());
				pw.setColor(i, j, newColor);
			}
		}
		return wi;
	}

	public static int[] histogramaAc(int[] histograma) {
		int[] histogramaAcumulado = new int[256];
		histogramaAcumulado[0] = histograma[0];

		for (int i = 1; i < 256; i++) {
			histogramaAcumulado[i] = histogramaAcumulado[i - 1] + histograma[i];
		}
		return histogramaAcumulado;
	}

	public static int pontoMin(int[] hist) {
		for (int i = 0; i < hist.length; i++) {
			if (hist[i] > 0)
				return i;
		}
		return 0;
	}

	public static int qtTons(int[] histograma) {
		int qt = 0;
		for (int i = 0; i < 256; i++) {
			if (histograma[i] > 0) {
				qt++;
			}
		}
		return qt;
	}

}

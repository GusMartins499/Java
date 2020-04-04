package application;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class PrincipalController {
	@FXML
	private Accordion AccordionMenu;
	@FXML
	private Button btnImg1;
	@FXML
	private Button btnImg2;
	@FXML
	private Button btnSalvar;
	@FXML
	private AnchorPane paneImg1;
	@FXML
	private AnchorPane paneImg2;
	@FXML
	private AnchorPane paneImgResultado;
	@FXML
	private Label lblValorRed;
	@FXML
	private Label lblValorBlue;
	@FXML
	private Label lblValorGreen;
	@FXML
	private Button btnHistograma;
	@FXML
	private ImageView imgView1;
	@FXML
	private ImageView imgView2;
	@FXML
	private ImageView imgView3;
	@FXML
	private TextField txtPorcentagemRed;
	@FXML
	private TextField txtPorcentagemGreen;
	@FXML
	private TextField txtPorcentagemBlue;
	@FXML
	private Slider slider;
	@FXML
	private Button btnNegativa;
	@FXML
	private Button btnRuido;
	@FXML
	private RadioButton rbVizinho3;
	@FXML
	private RadioButton rbVizinhoC;
	@FXML
	private RadioButton rbVizinhoX;
	@FXML
	private Slider sliderTransparencia1;
	@FXML
	private Slider sliderTransparencia2;
	@FXML
	private Button btnAdicao;
	@FXML
	private Button btnSubtracao;

	private Image img1;
	private Image img2;
	private Image img3;

	@FXML
	public void initialize() {

	}

	@FXML
	public void abreImagem1() {
		img1 = abreImagem(imgView1, img1);
	}

	@FXML
	public void abreImagem2() {
		img2 = abreImagem(imgView2, img2);
	}

	public void atualizaImagem3() {
		imgView3.setImage(img3);
		imgView3.setFitWidth(img3.getWidth());
		imgView3.setFitHeight(img3.getHeight());
	}

	private Image abreImagem(ImageView imageView, Image image) {
		File f = selecionaImagem();
		if (f != null) {
			image = new Image(f.toURI().toString()); // pega o caminho da imagem
			imageView.setImage(image); // joga na parte visual
			imageView.setFitWidth(image.getWidth()); // aumenta o tamanho do imageview por causa do anchorpane e coloca
														// o scroll
			imageView.setFitHeight(image.getHeight()); // aumenta o tamanho do imageview por causa do anchorpane e
														// coloca o scroll
			return image;
		}
		return null;
	}

	private File selecionaImagem() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.JPG", "*.png",
				"*.PNG", "*.gif", "*.GIF", "*.bmp", "*.BMP"));
		fileChooser.setInitialDirectory(new File("C:\\Users\\Gustavo\\Pictures\\Processamento digital de imagem\\"));
		File imgSelec = fileChooser.showOpenDialog(null);
		try {
			if (imgSelec != null) {
				return imgSelec;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@FXML
	public void rasterImg(MouseEvent evt) {
		ImageView iw = (ImageView) evt.getTarget();
		if (iw.getImage() != null)
			verificaCor(iw.getImage(), (int) evt.getX(), (int) evt.getY());
	}

	private void verificaCor(Image img, int x, int y) {
		try {
			Color cor = img.getPixelReader().getColor(x - 1, y - 1);
			lblValorRed.setText("R: " + (int) (cor.getRed() * 255));
			lblValorGreen.setText("G: " + (int) (cor.getGreen() * 255));
			lblValorBlue.setText("B: " + (int) (cor.getBlue() * 255));
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	@FXML
	public void cinzaPonderada() {
		img3 = PDI.cinzaMediaAritmetica(img1, Integer.parseInt(txtPorcentagemRed.getText()),
				Integer.parseInt(txtPorcentagemGreen.getText()), Integer.parseInt(txtPorcentagemBlue.getText()));
		atualizaImagem3();
	}

	@FXML
	public void cinzaAritmetica() {
		img3 = PDI.cinzaMediaAritmetica(img1, 0, 0, 0);
		atualizaImagem3();
	}

	@FXML
	public void limiarizacao() {
		img3 = PDI.limiarizacao(img1, slider.getValue() / 255.00);
		atualizaImagem3();
	}

	@FXML
	public void negativa() {
		img3 = PDI.negativa(img1);
		atualizaImagem3();
	}

	@FXML
	public void ruido() {
		if (rbVizinho3.isSelected()) {
			img3 = PDI.ruidos(img1, 1);
			atualizaImagem3();
		}
		if (rbVizinhoC.isSelected()) {
			img3 = PDI.ruidos(img1, 2);
			atualizaImagem3();
		}
		if (rbVizinhoX.isSelected()) {
			img3 = PDI.ruidos(img1, 3);
			atualizaImagem3();
		}
	}

	@FXML
	public void adicao() {
		double transparenciaImagem1 = getValueSlider1();
		double transparenciaImagem2 = getValueSlider2();
		img3 = PDI.adicao(img1, img2, transparenciaImagem1, transparenciaImagem2);
		atualizaImagem3();
	}

	@FXML
	public void subtracao() {
		img3 = PDI.subtracao(img1, img2);
		atualizaImagem3();
	}

	@FXML
	public double getValueSlider1() {
		return sliderTransparencia1.getValue() / 100;
	}

	@FXML
	public double getValueSlider2() {
		return sliderTransparencia2.getValue() / 100;
	}

	@FXML
	public void salvar() {
		if (img3 != null) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("imagem", "*.png"));
			fileChooser
					.setInitialDirectory((new File("C:\\Users\\Gustavo\\Pictures\\Processamento digital de imagem\\")));
			File file = fileChooser.showSaveDialog(null);
			if (file != null) {
				BufferedImage bImg = SwingFXUtils.fromFXImage(img3, null);
				try {
					ImageIO.write(bImg, "PNG", file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

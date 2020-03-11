package application;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	public void cinzaAritmetica() {
		img3 = PDI.cinzaMediaAritmetica(img1, 0, 0, 0);
		atualizaImagem3();
	}
}
